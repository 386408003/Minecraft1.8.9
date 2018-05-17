package com.github.ustc_zzzz.fmltutor.block;

import com.github.ustc_zzzz.fmltutor.creativetab.CreativeTabsLoader;
import com.github.ustc_zzzz.fmltutor.fluid.FluidLoader;

import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidClassic;

/**
 * 水银流体对应的方块类
 * 
 * BlockFluidClassic类的构造方法有两个参数，分别是对应的流体和这种流体方块使用的材质，这里的材质使用水就好了。
 * 
 * @author ZhuFeng 
 * @date 2018年3月30日
 */
public class BlockFluidMercury extends BlockFluidClassic {
	public BlockFluidMercury() {
		super(FluidLoader.fluidMercury, Material.water);
		this.setUnlocalizedName("fluidMercury");
		this.setCreativeTab(CreativeTabsLoader.tabFMLTutor);
	}
}