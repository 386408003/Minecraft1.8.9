package com.github.ustc_zzzz.fmltutor.client;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import com.github.ustc_zzzz.fmltutor.common.CommonProxy;

/**
 * 只有客户端才执行的代理类
 * 
 * @author ZhuFeng 
 * @date 2018年3月2日
 */
public class ClientProxy extends CommonProxy {
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		new ItemRenderLoader();
	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
		new KeyLoader();
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}
}