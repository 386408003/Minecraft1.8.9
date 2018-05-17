package com.github.ustc_zzzz.fmltutor.worldgen;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

/**
 * 生成萤石的世界生成器
 * 
 * 如果不是探险类Mod，一个好的Mod，除了一些必要的矿物生成，真正世界生成的部分是很少的。
 * 换句话说，一个好的Mod，如果不具备很强烈的探险性质，应该尽量减少世界生成部分，可以换一些方式，比如使用方块破坏掉落、生物破坏掉落、等等，以实现新的物品、方块等的产生。
 * 
 * @author ZhuFeng 
 * @date 2018年3月30日
 */
public class WorldGeneratorGlowstone extends WorldGenerator {
	/**
	 * WorldGenMinable类：
	 * 第一个参数表示生成的矿物的方块状态，方块状态的相关内容在中级部分会有所讲解，这里只需要知道通过方块的getDefaultState方法获取这个方块状态就可以了，比如这里我们需要生成萤石。
	 * 第二个参数表示生成的矿物的大小，当然实际大小是会有出入的，这里我们定为16
	 */
	private final WorldGenerator glowstoneGenerator = new WorldGenMinable(Blocks.glowstone.getDefaultState(), 16);
	
	/**
	 * 第一个参数代表当前待生成的世界
	 * 第二个参数是一个和当前世界种子、当前区块的x坐标（第二个参数）、和当前区块的z坐标（第三个参数）相关的随机数发生器，换言之，如果当前即将生成的是同一个世界种子和同一个区块，这个随机数发生器总会是一致的。
	 * 第三个参数代表当前待生成的区块，一般而言，传入该方法的该参数，其Y坐标永远为0，X坐标和Z坐标代表该区块的西北方向，也就是X坐标和Z坐标最小的地方，同时X坐标和Z坐标都是16的倍数
	 * 返回值代表该生成是否成功，如果不成功游戏可能会试图重新调用这个方法生成一次，这里我们让它永远为true
	 */
	@Override
	public boolean generate(World world, Random rand, BlockPos pos) {
		/**
		 * 这个条件判断语句其实可以不存在，不过为什么我们要加上去呢？
		 * 因为为了方便Mod间的相互协作，我们向Forge触发了OreGenEvent.GenerateMinable事件，这样如果有Mod想要阻止萤石的生成，直接监听和原版类似的事件就可以了。
		 * 与人方便，自己方便。
		 */
		if (TerrainGen.generateOre(world, rand, this, pos, OreGenEvent.GenerateMinable.EventType.CUSTOM)) {
			//一般情况下一个区块不会只生成一次矿物，比如这里我们通过循环四次的方式在当前区块进行四次矿物生成。
			for (int i = 0; i < 4; ++i) {
				//然后我们随机在当前区块内生成XYZ三个坐标值，当然这里我们需要使用Forge提供的随机数生成器，不难看出，这里我们设定萤石的生成范围是Y坐标（也就是纵坐标）从16到32，X坐标和Z坐标也没有超出一个区块的范围。
				int posX = pos.getX() + rand.nextInt(16);
				int posY = 16 + rand.nextInt(16);
				int posZ = pos.getZ() + rand.nextInt(16);
				BlockPos blockpos = new BlockPos(posX, posY, posZ);
				//有的时候，在世界上生成的矿物，还需要依赖于生物群系，比如绿宝石的生成就和生物群系密切相关，这里我们使用World类的getBiomeGenForCoords方法获取这个方块所在位置所在的生物群系。
				BiomeGenBase biomeGenBase = world.getBiomeGenForCoords(blockpos);
				//这里教程作为演示，对于生物群系的特殊设定是获取生物群系的降雨量（在0到65536之间），如果生物群系的降雨量如果较大（比如雨林），则该次生成成功的概率偏小，如果偏小（比如沙漠），则该次生成成功的概率偏大。
				if (biomeGenBase.getIntRainfall() < rand.nextInt(65536)) {
					glowstoneGenerator.generate(world, rand, blockpos);
				}
			}
		}
		return true;
	}
}