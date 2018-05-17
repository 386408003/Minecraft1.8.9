package com.github.ustc_zzzz.fmltutor.fluid;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.github.ustc_zzzz.fmltutor.FMLTutor;
import com.github.ustc_zzzz.fmltutor.block.BlockLoader;

/**
 * 加载流体的辅助类
 * 
 * @author ZhuFeng 
 * @date 2018年3月30日
 */
public class FluidLoader {
	public static Fluid fluidMercury = new FluidMercury();

	public FluidLoader(FMLPreInitializationEvent event) {
		//如果这个流体已经被注册（比如其他Mod），那么我们就使用被注册过的流体，并顺道扔出一条日志信息。
		if (FluidRegistry.isFluidRegistered(fluidMercury)) {
			event.getModLog().info("Found fluid {}, the registration is canceled. ", fluidMercury.getName());
			fluidMercury = FluidRegistry.getFluid(fluidMercury.getName());
		} else {
			FluidRegistry.registerFluid(fluidMercury);
		}
	}
	
	@SideOnly(Side.CLIENT)
	public static void registerRenders() {
		registerFluidRender((BlockFluidBase) BlockLoader.fluidMercury, "fluid_mercury");
	}

	/**
	 * 流体模型渲染
	 * 这里不需要像一般的方块一样注册这个方块的渲染模型，因为流体方块的渲染方法和普通方块不一样
	 * 
	 * @param blockFluid
	 * @param blockStateName
	 */
	@SideOnly(Side.CLIENT)
	public static void registerFluidRender(BlockFluidBase blockFluid, String blockStateName) {
		//assets.fmltutor.blockstates包下的fluid_mercury.json文件
		final String location = FMLTutor.MODID + ":" + blockStateName;
		final Item itemFluid = Item.getItemFromBlock(blockFluid);
		ModelLoader.setCustomMeshDefinition(itemFluid,
				new ItemMeshDefinition() {
					@Override
					public ModelResourceLocation getModelLocation(ItemStack stack) {
						return new ModelResourceLocation(location, "fluid");
					}
				});
		ModelLoader.setCustomStateMapper(blockFluid, new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return new ModelResourceLocation(location, "fluid");
			}
		});
	}
}