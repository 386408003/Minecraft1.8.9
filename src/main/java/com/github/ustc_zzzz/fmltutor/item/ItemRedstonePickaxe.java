package com.github.ustc_zzzz.fmltutor.item;

import net.minecraft.item.ItemPickaxe;

import com.github.ustc_zzzz.fmltutor.creativetab.CreativeTabsLoader;
import com.github.ustc_zzzz.fmltutor.util.NewMaterial;

/**
 * 红石镐子
 * 
 * @author ZhuFeng
 * @date 2018年3月2日
 */
public class ItemRedstonePickaxe extends ItemPickaxe {

	public ItemRedstonePickaxe() {
		super(NewMaterial.REDSTONE);
		this.setUnlocalizedName("redstonePickaxe");
		this.setCreativeTab(CreativeTabsLoader.tabFMLTutor);
	}
}