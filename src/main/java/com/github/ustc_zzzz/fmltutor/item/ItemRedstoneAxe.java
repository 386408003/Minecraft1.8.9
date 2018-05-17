package com.github.ustc_zzzz.fmltutor.item;

import net.minecraft.item.ItemAxe;

import com.github.ustc_zzzz.fmltutor.creativetab.CreativeTabsLoader;
import com.github.ustc_zzzz.fmltutor.util.NewMaterial;

/**
 * 红石斧头
 * 
 * @author ZhuFeng
 * @date 2018年3月2日
 */
public class ItemRedstoneAxe extends ItemAxe {

	public ItemRedstoneAxe() {
		super(NewMaterial.REDSTONE);
		this.setUnlocalizedName("redstoneAxe");
		this.setCreativeTab(CreativeTabsLoader.tabFMLTutor);
	}
}
