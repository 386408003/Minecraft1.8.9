package com.github.ustc_zzzz.fmltutor.common;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

/**
 * 加载配置文件的辅助类
 * 
 * 这里的event.getSuggestedConfigurationFile()，就是Forge推荐的配置文件位置。
 * 这个位置在游戏根目录的config文件夹下，名为“<Mod id>.cfg”，这里就是“fmltutor.cfg”。
 * 
 * @author ZhuFeng 
 * @date 2018年3月2日
 */
public class ConfigLoader {
	private static Configuration config;
	private static Logger logger;
	public static int diamondBurnTime;
	public static int enchantmentFireBurn;

	public ConfigLoader(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		load();
	}

	public static void load() {
		logger.info("Started loading config. ");
		
		//钻石燃烧的时间
		String comment = "How many seconds can a diamond burn in a furnace. ";
		diamondBurnTime = config.get(Configuration.CATEGORY_GENERAL,
				"diamondBurnTime", 640, comment).getInt();

		//附魔的ID
		comment = "Fire burn enchantment id. ";
		enchantmentFireBurn = config.get(Configuration.CATEGORY_GENERAL,
				"enchantmentFireBurn", 36, comment).getInt();
		
		config.save();
		logger.info("Finished loading config. ");
	}

	public static Logger logger() {
		return logger;
	}
}