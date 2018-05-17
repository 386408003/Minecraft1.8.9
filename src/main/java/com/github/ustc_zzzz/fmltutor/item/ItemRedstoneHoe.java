package com.github.ustc_zzzz.fmltutor.item;

import net.minecraft.item.ItemHoe;

import com.github.ustc_zzzz.fmltutor.creativetab.CreativeTabsLoader;
import com.github.ustc_zzzz.fmltutor.util.NewMaterial;

/**
 * 红石锄头
 * 
 * @author ZhuFeng
 * @date 2018年3月2日
 */
public class ItemRedstoneHoe extends ItemHoe {

	public ItemRedstoneHoe() {
		super(NewMaterial.REDSTONE);
		this.setUnlocalizedName("redstoneHoe");
		this.setCreativeTab(CreativeTabsLoader.tabFMLTutor);
	}
}
