package com.github.ustc_zzzz.fmltutor.item;

import net.minecraft.item.ItemSword;

import com.github.ustc_zzzz.fmltutor.creativetab.CreativeTabsLoader;
import com.github.ustc_zzzz.fmltutor.util.NewMaterial;

/**
 * 红石剑
 * 
 * @author ZhuFeng
 * @date 2018年3月2日
 */
public class ItemRedstoneSword extends ItemSword{

	public ItemRedstoneSword() {
		super(NewMaterial.REDSTONE);
		this.setUnlocalizedName("redstoneSword");
		this.setCreativeTab(CreativeTabsLoader.tabFMLTutor);
	}
}
