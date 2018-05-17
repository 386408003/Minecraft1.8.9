package com.github.ustc_zzzz.fmltutor.command;

import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

/**
 * 加载系统命令的辅助类
 * 
 * @author ZhuFeng 
 * @date 2018年3月26日
 */
public class CommandLoader {
	public CommandLoader(FMLServerStartingEvent event) {
		event.registerServerCommand(new CommandPosition());
	}
}