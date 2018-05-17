package com.github.ustc_zzzz.fmltutor;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import com.github.ustc_zzzz.fmltutor.common.CommonProxy;

/**
 * MOD主类
 * 
 * 1.8.9（本教程）表示该Mod只支持Minecraft 1.8.9
 * [1.8,1.9)表示该Mod支持从1.8（包含）到1.9（不包含）的所有Minecraft版本
 * [1.8,1.10]表示该Mod支持从1.8（包含）到1.10（包含）的所有Minecraft版本
 * [1.8,)表示该Mod支持从1.8（包含）之后出现的所有Minecraft版本
 * (,1.8],[1.9,)表示该Mod支持1.8（包含）之前出现的所有Minecraft版本和从1.9（包含）之后出现的所有Minecraft版本
 * 
 * @author ZhuFeng
 * @date 2018年3月2日
 */
@Mod(modid = FMLTutor.MODID, name = FMLTutor.NAME, version = FMLTutor.VERSION, acceptedMinecraftVersions = "[1.7,1.9]")
public class FMLTutor {
	public static final String MODID = "fmltutor";
	public static final String NAME = "FML Tutor";
	public static final String VERSION = "1.0.0";

	@Instance(FMLTutor.MODID)
	public static FMLTutor instance;

	@SidedProxy(clientSide = "com.github.ustc_zzzz.fmltutor.client.ClientProxy", serverSide = "com.github.ustc_zzzz.fmltutor.common.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		// TODO
		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		// TODO
		proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		// TODO
		proxy.postInit(event);
	}
	
	@EventHandler
	public void serverStarting(FMLServerStartingEvent event) {
		proxy.serverStarting(event);
	}
}