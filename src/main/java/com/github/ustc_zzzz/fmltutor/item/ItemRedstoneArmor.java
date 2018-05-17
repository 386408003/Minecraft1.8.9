package com.github.ustc_zzzz.fmltutor.item;

import net.minecraft.item.ItemArmor;

import com.github.ustc_zzzz.fmltutor.creativetab.CreativeTabsLoader;
import com.github.ustc_zzzz.fmltutor.util.NewMaterial;

/**
 * ItemArmor的构造方法共有三个参数：
 * 第一个参数表示该盔甲的ArmorMaterial，自然就是我们刚刚创建的那个。
 * 第二个参数的名称为renderIndex，目前在源代码中没有找到对其的引用，作者个人认为其在某个版本中被弃用了，随便填一个就可以了。但是为了保证不同的ArmorMaterial对应不同的值，作者这里使用了该ArmorMaterial的序数值。
 * 第三个参数表示该盔甲的类型，0为头盔，1为胸甲，2为护腿，3为靴子。
 * 这里新建了四个子类，分别表示头盔、胸甲、护腿、和靴子。
 * 
 * @author ZhuFeng 
 * @date 2018年3月3日
 */
public class ItemRedstoneArmor extends ItemArmor {

	public ItemRedstoneArmor(int armorType) {
		super(NewMaterial.REDSTONE_ARMOR, NewMaterial.REDSTONE_ARMOR.ordinal(), armorType);
	}
	
	/**
	 * 红石头盔
	 * 
	 * @author ZhuFeng 
	 * @date 2018年3月3日
	 */
	public static class Helmet extends ItemRedstoneArmor {
		public Helmet() {
			super(0);
			this.setUnlocalizedName("redstoneHelmet");
			this.setCreativeTab(CreativeTabsLoader.tabFMLTutor);
		}
	}

	/**
	 * 红石胸甲
	 * 
	 * @author ZhuFeng 
	 * @date 2018年3月3日
	 */
	public static class Chestplate extends ItemRedstoneArmor {
		public Chestplate() {
			super(1);
			this.setUnlocalizedName("redstoneChestplate");
			this.setCreativeTab(CreativeTabsLoader.tabFMLTutor);
		}
	}

	/**
	 * 红石护腿
	 * 
	 * @author ZhuFeng 
	 * @date 2018年3月3日
	 */
	public static class Leggings extends ItemRedstoneArmor {
		public Leggings() {
			super(2);
			this.setUnlocalizedName("redstoneLeggings");
			this.setCreativeTab(CreativeTabsLoader.tabFMLTutor);
		}
	}

	/**
	 * 红石靴子
	 * 
	 * @author ZhuFeng 
	 * @date 2018年3月3日
	 */
	public static class Boots extends ItemRedstoneArmor {
		public Boots() {
			super(3);
			this.setUnlocalizedName("redstoneBoots");
			this.setCreativeTab(CreativeTabsLoader.tabFMLTutor);
		}
	}
}