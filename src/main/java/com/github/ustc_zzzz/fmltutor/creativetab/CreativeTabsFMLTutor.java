package com.github.ustc_zzzz.fmltutor.creativetab;

import com.github.ustc_zzzz.fmltutor.item.ItemLoader;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * 创造模式工具栏
 * 
 * @author ZhuFeng 
 * @date 2018年3月2日
 */
public class CreativeTabsFMLTutor extends CreativeTabs {
	public CreativeTabsFMLTutor() {
		super("fmltutor");
		this.setBackgroundImageName("fmltutor.png");
	}

	@Override
	public Item getTabIconItem() {
		return ItemLoader.goldenEgg;
	}
	
	/**
	 * 是否有搜索框
	 */
	@Override
	public boolean hasSearchBar() {
		return true;
	}
}