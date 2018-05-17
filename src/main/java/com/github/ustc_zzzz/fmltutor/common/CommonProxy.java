package com.github.ustc_zzzz.fmltutor.common;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import com.github.ustc_zzzz.fmltutor.achievement.AchievementLoader;
import com.github.ustc_zzzz.fmltutor.block.BlockLoader;
import com.github.ustc_zzzz.fmltutor.command.CommandLoader;
import com.github.ustc_zzzz.fmltutor.crafting.CraftingLoader;
import com.github.ustc_zzzz.fmltutor.creativetab.CreativeTabsLoader;
import com.github.ustc_zzzz.fmltutor.enchantment.EnchantmentLoader;
import com.github.ustc_zzzz.fmltutor.fluid.FluidLoader;
import com.github.ustc_zzzz.fmltutor.item.ItemLoader;
import com.github.ustc_zzzz.fmltutor.potion.PotionLoader;
import com.github.ustc_zzzz.fmltutor.worldgen.WorldGeneratorLoader;

/**
 * 公共的代理类
 * 
 * @author ZhuFeng 
 * @date 2018年3月2日
 */
public class CommonProxy {
	public void preInit(FMLPreInitializationEvent event) {
		new ConfigLoader(event);
		new CreativeTabsLoader(event);
		new FluidLoader(event);
		new ItemLoader(event);
		new BlockLoader(event);
		new OreDictionaryLoader(event);
		new PotionLoader(event);
	}

	public void init(FMLInitializationEvent event) {
		new CraftingLoader();
		new EnchantmentLoader();
		new AchievementLoader();
		new EventLoader();
		new WorldGeneratorLoader();
	}

	public void postInit(FMLPostInitializationEvent event) {

	}
	
	public void serverStarting(FMLServerStartingEvent event) {
		new CommandLoader(event);
	}
}