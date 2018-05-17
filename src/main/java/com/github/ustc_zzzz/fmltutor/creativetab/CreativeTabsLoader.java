package com.github.ustc_zzzz.fmltutor.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * 加载创造模式工具栏的辅助类
 * 
 * @author ZhuFeng 
 * @date 2018年3月2日
 */
public class CreativeTabsLoader {
	public static CreativeTabs tabFMLTutor;

	public CreativeTabsLoader(FMLPreInitializationEvent event) {
		tabFMLTutor = new CreativeTabsFMLTutor();
	}
}