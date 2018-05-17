package com.github.ustc_zzzz.fmltutor.worldgen;

import net.minecraft.util.BlockPos;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * 世界生成器的辅助类
 * 
 * @author ZhuFeng 
 * @date 2018年3月30日
 */
public class WorldGeneratorLoader {
	private static WorldGenerator generatorGlowstone = new WorldGeneratorGlowstone();
	private BlockPos pos;

	public WorldGeneratorLoader() {
		MinecraftForge.ORE_GEN_BUS.register(this);
	}

	/**
	 * Forge的所有矿物生成相关事件，都需要在MinecraftForge.ORE_GEN_BUS上注册，这些事件都有一个共同的名为OreGenEvent的基类。
	 * Forge为这个类提供了三个子类，用于在不同矿物的生成阶段注入相关的代码
	 * 
	 * OreGenEvent.Pre在主世界的矿物即将开始生成时触发
	 * OreGenEvent.Post在主世界的矿物生成结束（绿宝石除外）时触发
	 * OreGenEvent.GenerateMinable在所有世界的相关矿物的一种即将开始生成时触发
	 * 
	 * 上面的三个事件都是基于区块的，也就是说，每次生成是以区块为单位进行的，生成的时候会在该区块生成相应数量的矿物堆。
	 * 关于OreGenEvent.GenerateMinable，其中有一个type字段指示生成的矿物是什么类型的。
	 * 这里需要注意的是，只要是在地下替换石头的行为都会被认作是矿物，所以这里生成的矿物并不是我们一般意义上的矿物，还包括泥土、沙砾等。
	 * 
	 * OreGenEvent.Pre和OreGenEvent.Post会在山地地形对应的区块分别调用两次，
	 * 原因是原版游戏中生成绿宝石等山地特有矿物的阶段和生成其他普通矿物的阶段是分离的，所以会分别调用两次。
	 * 我们把调用的方块坐标位置记录下来以避免第二次调用的发生。
	 * OreGenEvent.Post类只会作用于主世界，如果读者想要添加下界的世界生成，可以监听生成石英矿的事件。
	 * @param event
	 */
	@SubscribeEvent
	public void onOreGenPost(OreGenEvent.Post event) {
		if (!event.pos.equals(this.pos)) {
			this.pos = event.pos;
			generatorGlowstone.generate(event.world, event.rand, event.pos);
		}
	}

	/**
	 * 我们监听了OreGenEvent.GenerateMinable事件，并在其的type字段为ANDESITE时调用setResult方法并传入Event.Result.DENY参数。
	 * 因为OreGenEvent.GenerateMinable事件拥有@HasResult注解，所以将其设置为Event.Result.DENY便可以阻止相应矿物的生成，设置为其他代表继续允许矿物生成。
	 * 作为示例，我们监听这个事件，并检查生成的是不是安山岩，如果是，则取消生成。
	 * @param event
	 */
	@SubscribeEvent
	public void onOreGenGenerateMinable(OreGenEvent.GenerateMinable event) {
		if (event.type == OreGenEvent.GenerateMinable.EventType.ANDESITE) {
			event.setResult(Event.Result.DENY);
		}
	}
}