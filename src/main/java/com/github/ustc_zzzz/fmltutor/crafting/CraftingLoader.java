package com.github.ustc_zzzz.fmltutor.crafting;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

import com.github.ustc_zzzz.fmltutor.block.BlockLoader;
import com.github.ustc_zzzz.fmltutor.common.ConfigLoader;
import com.github.ustc_zzzz.fmltutor.item.ItemLoader;


/**
 * 注册合成表
 * 注册燃烧规则
 * 注册燃料
 * 
 * registerBlock方法用于注册方块
 * registerFuelHandler方法用于注册燃料
 * registerItem方法用于注册物品
 * registerTileEntity方法用于注册TileEntity（后面会讲到什么是TileEntity）
 * registerWorldGenerator方法用于注册世界生成器以生成不同的世界
 * addRecipe方法和addShapedRecipe方法用于注册合成表
 * addSmelting方法用于注册物品烧炼规则
 * 
 * @author ZhuFeng 
 * @date 2018年3月2日
 */
public class CraftingLoader {
	public CraftingLoader() {
		registerRecipe();
		registerSmelting();
		registerFuel();
	}

	/**
	 * 注册合成表
	 */
	private static void registerRecipe() {
		//草方块
		GameRegistry.addShapedRecipe(new ItemStack(BlockLoader.grassBlock),
				new Object[] { "##", "##", '#', Blocks.vine });
		GameRegistry.addShapelessRecipe(new ItemStack(Blocks.vine, 4),
				BlockLoader.grassBlock);
		/**
		 * 使用方块注册合成表
		 */
		/* //金蛋
		GameRegistry.addShapedRecipe(new ItemStack(ItemLoader.goldenEgg),
				new Object[] { "###", "#*#", "###", '#', Items.gold_ingot, '*',
						Items.egg });
		//红石斧头
		GameRegistry.addShapedRecipe(new ItemStack(ItemLoader.redstoneAxe),
				new Object[] { "## ", "#* ", " * ", '#', Items.redstone, '*',
						Items.stick });
		GameRegistry.addShapedRecipe(new ItemStack(ItemLoader.redstoneAxe),
				new Object[] { " ##", " *#", " * ", '#', Items.redstone, '*',
						Items.stick });
		//红石锄头
		GameRegistry.addShapedRecipe(new ItemStack(ItemLoader.redstoneHoe),
				new Object[] { "## ", " * ", " * ", '#', Items.redstone, '*',
						Items.stick });
		GameRegistry.addShapedRecipe(new ItemStack(ItemLoader.redstoneHoe),
				new Object[] { " ##", " * ", " * ", '#', Items.redstone, '*',
						Items.stick });
		//红石镐子
		GameRegistry.addShapedRecipe(new ItemStack(ItemLoader.redstonePickaxe),
				new Object[] { "###", " * ", " * ", '#', Items.redstone, '*',
						Items.stick });
		//红石铲子
		GameRegistry.addShapedRecipe(new ItemStack(ItemLoader.redstoneShovel),
				new Object[] { " # ", " * ", " * ", '#', Items.redstone, '*',
						Items.stick });
		//红石剑
		GameRegistry.addShapedRecipe(new ItemStack(ItemLoader.redstoneSword),
				new Object[] { " # ", " # ", " * ", '#', Items.redstone, '*',
						Items.stick });
		//红石苹果
		GameRegistry.addShapedRecipe(new ItemStack(ItemLoader.redstoneApple),
				new Object[] { "###", "#*#", "###", '#', Items.redstone, '*',
						Items.apple });
		//红石头盔
		GameRegistry.addShapedRecipe(new ItemStack(ItemLoader.redstoneHelmet),
				new Object[] { "###", "# #", '#', Items.redstone });
		//红石胸甲
		GameRegistry.addShapedRecipe(new ItemStack(
				ItemLoader.redstoneChestplate), new Object[] { "# #", "###",
				"###", '#', Items.redstone });
		//红石护腿
		GameRegistry.addShapedRecipe(
				new ItemStack(ItemLoader.redstoneLeggings), new Object[] {
						"###", "# #", "# #", '#', Items.redstone });
		//红石靴子
		GameRegistry.addShapedRecipe(new ItemStack(ItemLoader.redstoneBoots),
				new Object[] { "# #", "# #", '#', Items.redstone }); */
		/**
		 * 使用矿物字典索引注册合成表
		 * 
		 * Forge提供了两个类：ShapedOreRecipe和ShapelessOreRecipe。
		 * 这两个类的构造方法的参数和向GameRegistry.addShapedRecipe和GameRegistry.addShapelessRecipe方法提供的参数类似，只不过把矿物字典部分换成了作为字符串的矿物字典索引。
		 * 把ShapedOreRecipe和ShapelessOreRecipe两个类的实例作为GameRegistry.addRecipe的参数传入即可。
		 */
		 //金蛋
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.goldenEgg),
				new Object[] { "###", "#*#", "###", '#', "ingotGold", '*', Items.egg }));
		//红石斧头
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.redstoneAxe),
				new Object[] { "## ", "#* ", " * ", '#', "dustRedstone", '*', "stickWood" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.redstoneAxe),
				new Object[] { " ##", " *#", " * ", '#', "dustRedstone", '*', "stickWood" }));
		//红石锄头
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.redstoneHoe),
				new Object[] { "## ", " * ", " * ", '#', "dustRedstone", '*', "stickWood" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.redstoneHoe),
				new Object[] { " ##", " * ", " * ", '#', "dustRedstone", '*', "stickWood" }));
		//红石镐子
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.redstonePickaxe),
				new Object[] { "###", " * ", " * ", '#', "dustRedstone", '*', "stickWood" }));
		//红石铲子
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.redstoneShovel),
				new Object[] { " # ", " * ", " * ", '#', "dustRedstone", '*', "stickWood" }));
		//红石剑
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.redstoneSword),
				new Object[] { " # ", " # ", " * ", '#', "dustRedstone", '*', "stickWood" }));
		//红石苹果
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.redstoneApple),
				new Object[] { "###", "#*#", "###", '#', "dustRedstone", '*', Items.apple }));
		//红石头盔
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.redstoneHelmet),
				new Object[] { "###", "# #", '#', "dustRedstone" }));
		//红石胸甲
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.redstoneChestplate),
				new Object[] { "# #", "###", "###", '#', "dustRedstone" }));
		//红石护腿
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.redstoneLeggings),
				new Object[] { "###", "# #", "# #", '#', "dustRedstone" }));
		//红石靴子
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemLoader.redstoneBoots),
				new Object[] { "# #", "# #", '#', "dustRedstone" }));
	}

	/**
	 * 注册燃烧规则
	 * 
	 * addSmelting 第一个参数是待烧炼的物品。
	 * addSmelting 第二个参数是烧炼后的物品。
	 * addSmelting 第三个参数是烧炼后玩家可以得到的经验。
	 */
	private static void registerSmelting() {
		GameRegistry.addSmelting(BlockLoader.grassBlock, new ItemStack(Items.coal), 0.5F);
	}

	/**
	 * 注册燃料
	 * 
	 * 烧炼时间为gametick，一秒为20个gametick
	 * 树苗　　100
	 * 木板　　200
	 * 煤炭　　1600
	 * 烈焰棒　2400
	 * 煤炭块　16000
	 * 岩浆桶　20000
	 */
	private static void registerFuel() {
		GameRegistry.registerFuelHandler(new IFuelHandler() {
			@Override
			public int getBurnTime(ItemStack fuel) {
				return Items.diamond != fuel.getItem() ? 0 : Math.max(0,
						ConfigLoader.diamondBurnTime) * 20;
			}
		});
	}
}