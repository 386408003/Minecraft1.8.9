package com.github.ustc_zzzz.fmltutor.enchantment;

import com.github.ustc_zzzz.fmltutor.FMLTutor;
import com.github.ustc_zzzz.fmltutor.common.ConfigLoader;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

/**
 * 新的附魔种类 火焰灼烧
 * 
 * enchID指的就是这个附魔的ID，我们看到原版已经定义了很多ID，当新建的ID重复时，游戏会报错。
 * enchName指的就是这个附魔的名称，使用ResourceLocation的方式标记，比如时运就是"minecraft:fortune"，精准采集就是"minecraft:silk_touch"，这个名称和方块、物品的ID是类似的。
 * enchWeight指的就是这个附魔的权重，和修复附魔需要的经验等级成负相关，和通过附魔台得到该种附魔的概率成正相关。
 * enchType表示这种附魔是什么类型的，有武器、工具、弓等多种。
 * 
 * @author ZhuFeng 
 * @date 2018年3月17日
 */
public class EnchantmentFireBurn extends Enchantment {
	public EnchantmentFireBurn() {
		super(ConfigLoader.enchantmentFireBurn, new ResourceLocation(
				FMLTutor.MODID + ":" + "fire_burn"), 1,
				EnumEnchantmentType.DIGGER);
		this.setName("fireBurn");
	}

	/**
	 * 可以获取到此附魔的最低等级
	 */
	@Override
	public int getMinEnchantability(int enchantmentLevel) {
		return 15;
	}

	/**
	 * 可以获取到此附魔的最高等级
	 */
	@Override
	public int getMaxEnchantability(int enchantmentLevel) {
		return super.getMinEnchantability(enchantmentLevel) + 50;
	}

	/**
	 * 附魔的最大等级，这个附魔只有一个等级
	 */
	@Override
	public int getMaxLevel() {
		return 1;
	}

	/**
	 * 这个附魔可否与其他附魔共存。
	 * 这里设定为不能和精准采集silkTouch 和时运fortune 共存。
	 */
	@Override
	public boolean canApplyTogether(Enchantment ench) {
		return super.canApplyTogether(ench)
				&& ench.effectId != silkTouch.effectId
				&& ench.effectId != fortune.effectId;
	}

	/**
	 * 这个附魔可以作用的物品。
	 * 作用对象是所有工具和剪刀。
	 */
	@Override
	public boolean canApply(ItemStack stack) {
		return stack.getItem() == Items.shears ? true : super.canApply(stack);
	}
}