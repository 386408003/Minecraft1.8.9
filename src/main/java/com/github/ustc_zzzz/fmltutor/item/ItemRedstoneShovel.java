package com.github.ustc_zzzz.fmltutor.item;

import net.minecraft.item.ItemSpade;

import com.github.ustc_zzzz.fmltutor.creativetab.CreativeTabsLoader;
import com.github.ustc_zzzz.fmltutor.util.NewMaterial;

/**
 * 红石铲子
 * 
 * @author ZhuFeng
 * @date 2018年3月2日
 */
public class ItemRedstoneShovel extends ItemSpade{

	public ItemRedstoneShovel() {
		super(NewMaterial.REDSTONE);
		this.setUnlocalizedName("redstoneShovel");
		this.setCreativeTab(CreativeTabsLoader.tabFMLTutor);
	}
}
