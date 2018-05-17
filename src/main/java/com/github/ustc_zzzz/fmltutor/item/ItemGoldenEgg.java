package com.github.ustc_zzzz.fmltutor.item;

import net.minecraft.item.Item;

import com.github.ustc_zzzz.fmltutor.creativetab.CreativeTabsLoader;

/**
 * 金蛋
 * 
 * @author ZhuFeng 
 * @date 2018年3月2日
 */
public class ItemGoldenEgg extends Item {
	public ItemGoldenEgg() {
		super();
		this.setUnlocalizedName("goldenEgg");
		this.setCreativeTab(CreativeTabsLoader.tabFMLTutor);
	}
}