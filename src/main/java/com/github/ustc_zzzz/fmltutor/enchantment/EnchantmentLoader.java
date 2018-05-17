package com.github.ustc_zzzz.fmltutor.enchantment;

import net.minecraft.enchantment.Enchantment;

import com.github.ustc_zzzz.fmltutor.common.ConfigLoader;

/**
 * 附魔加载辅助类
 * 
 * addToBookList方法使得该附魔被注册，使其在附魔台上可以被注册到，在创造模式物品栏上也可以找到对应的附魔书。
 * 
 * @author ZhuFeng 
 * @date 2018年3月17日
 */
public class EnchantmentLoader {
	public static Enchantment fireBurn;
	
	public EnchantmentLoader() {
		try {
			fireBurn = new EnchantmentFireBurn();
			Enchantment.addToBookList(fireBurn);
		} catch (Exception e) {
			//由于附魔ID冲突导致报错
			String comment = "Duplicate or illegal enchantment id: {}, the registry of class '{}' will be skipped. ";
			ConfigLoader.logger().error(comment,
					ConfigLoader.enchantmentFireBurn,
					EnchantmentFireBurn.class.getName());
		}
	}
}
