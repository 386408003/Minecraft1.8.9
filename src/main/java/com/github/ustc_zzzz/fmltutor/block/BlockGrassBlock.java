package com.github.ustc_zzzz.fmltutor.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import com.github.ustc_zzzz.fmltutor.common.EventLoader;
import com.github.ustc_zzzz.fmltutor.creativetab.CreativeTabsLoader;

/**
 * 草方块
 * 
 * setBlockUnbreakable方法用于设定方块的硬度为-1，即不能损坏。
 * setHardness方法用于设定方块的硬度，如黑曜石是50，铁块5，金块3，圆石2，石头1.5，南瓜1，泥土0.5，甘蔗0，基岩-1。
 * setHarvestLevel方法用于设定方块的可挖掘等级，如钻石镐是3，铁2，石1，木金0。
 * setLightLevel方法用于设定方块的光照，其周围的光照为设定值x15，如岩浆1.0，对应15，红石火把0.5，对应7.5。
 * setLightOpacity方法用于设定方块的透光率，数值越大透光率越低，如树叶和蜘蛛网是1，水和冰3。
 * setResistance方法用于设定方块的爆炸抗性，如木头的抗性为4，石头为10，黑曜石为2000，基岩为6000000。
 * setStepSound方法用于设定走在方块上的响声。 
 * setTickRandomly方法用于设定方块是否会接受随机Tick（如农作物）。
 * 
 * @author ZhuFeng
 * @date 2018年3月2日
 */
public class BlockGrassBlock extends Block {
	public BlockGrassBlock() {
		super(Material.ground);
		this.setUnlocalizedName("grassBlock");
		this.setHardness(0.5F);
		this.setStepSound(soundTypeGrass);
		this.setCreativeTab(CreativeTabsLoader.tabFMLTutor);
	}
	
	/**
	 * 当方块活跃时（即使用地上的方块时）
	 * 事件总线注册新增的草方块右击事件并且如果事件是可以取消的情况下将草方块置为空气方块
	 */
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos,
			IBlockState state, EntityPlayer playerIn, EnumFacing side,
			float hitX, float hitY, float hitZ) {
		EventLoader.PlayerRightClickGrassBlockEvent event;
		event = new EventLoader.PlayerRightClickGrassBlockEvent(playerIn, pos,
				worldIn);
		EventLoader.EVENT_BUS.post(event);
		if (!event.isCanceled() && !worldIn.isRemote) {
			worldIn.setBlockToAir(pos);
			return true;
		}
		return false;
	}
}