package com.github.ustc_zzzz.fmltutor.common;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.oredict.OreDictionary;

/**
 * 矿物字典辅助类
 * 
 * registerOre这个方法有两个参数，第一个参数是一个字符串，表示该物品想要被注册到的矿物字典索引名称，第二个参数，就是想要注册到矿物字典的物品了。
 * getOres方法用于获取同一矿物字典索引下的所有物品，这个方法接受一个表示矿物字典索引的字符串作为参数，返回的是一个包含同一矿物字典索引下的所有物品的列表。
 * getOreNames方法返回一个字符串数组，其中包含了所有的矿物字典索引。
 * 
 * @author ZhuFeng 
 * @date 2018年3月30日
 */
public class OreDictionaryLoader {
	/**
	 * 我们取出了所有注册进矿物字典的红石粉，并把其注册进了萤石粉的矿物字典。
	 * 取出了所有注册进矿物字典的萤石粉，并把其注册进了红石粉的矿物字典。
	 * @param event
	 */
	public OreDictionaryLoader(FMLPreInitializationEvent event) {
		List<ItemStack> dustRedstones = OreDictionary.getOres("dustRedstone");
		List<ItemStack> dustGlowstones = OreDictionary.getOres("dustGlowstone");
		for (ItemStack itemStack : dustGlowstones) {
			OreDictionary.registerOre("dustRedstone", itemStack);
		}
		for (ItemStack itemStack : dustRedstones) {
			OreDictionary.registerOre("dustGlowstone", itemStack);
		}
	}
}