package com.github.ustc_zzzz.fmltutor.block;

import net.minecraft.block.Block;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * 方块注册类，所有的方块由这个类注册，注册方块以及材质。
 * 
 * @author ZhuFeng 
 * @date 2018年3月2日
 */
public class BlockLoader {
	public static Block grassBlock = new BlockGrassBlock();
	public static Block fluidMercury = new BlockFluidMercury();

	public BlockLoader(FMLPreInitializationEvent event) {
		register(grassBlock, "grass_block");
		register(fluidMercury, "fluid_mercury");
	}

	private static void register(Block block, String name) {
		GameRegistry.registerBlock(block.setRegistryName(name));
	}

	@SideOnly(Side.CLIENT)
	public static void registerRenders() {
		registerRender(grassBlock);
	}

	/**
	 * setCustomModelResourceLocation方法有三个参数：
	 * 第一个参数是要被注册的物品。
	 * 第二个参数是这个物品的Metadata。Metadata是一个用于区分同一个物品或方块的不同状态的数据，比如钟表的十六种状态、羊毛的十六种颜色，在3.2.1节会讲到Metadata，默认为零就好了。
	 * 第三个参数就是这个物品模型的资源位置了，资源位置是类ModelResourceLocation的一个实例，它用于描述一个模型，在后面我们还会比较常用到这个类的。
	 * 
	 * ModelResourceLocation被用于标注模型的位置，通常为由冒号（:）和井号（#）分隔的三个字符串组成，对于我们这里构造的ModelResourceLocation，它的一部分通过调用物品的getRegistryName方法得到，第二部分由我们指定，为inventory，是一个固定的字符串，代表作为一个物品的渲染模型。
	 * 在这里，第一部分为fmltutor:golden_egg，第二部分为inventory，组合后的ModelResourceLocation就是fmltutor:golden_egg#inventory。Minecraft便会去相应的目录下寻找相应的资源：
	 * fmltutor指示游戏应该在assets.fmltutor包下找到这个资源
	 * inventory指示游戏应该在assets.fmltutor.models.item包下找到这个资源
	 * golden_egg指示这个资源就是assets.fmltutor.models.item.golden_egg.json，对应到源代码，就是src/main/resources/assets/fmltutor/models/item/golden_egg.json这一文件
	 * 
	 * '@SideOnly' 注解的作用是注解这一方法、类等只作用于客户端或服务端。
	 */
	@SideOnly(Side.CLIENT)
	private static void registerRender(Block block) {
		ModelResourceLocation model = new ModelResourceLocation(block.getRegistryName(), "inventory");
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, model);
	}
}