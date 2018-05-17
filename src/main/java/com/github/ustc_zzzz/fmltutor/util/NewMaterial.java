package com.github.ustc_zzzz.fmltutor.util;

import com.github.ustc_zzzz.fmltutor.FMLTutor;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

/**
 * 新的制作材料类
 * 
 * @author ZhuFeng
 * @date 2018年3月2日
 */
public class NewMaterial {
	/**
	 * 五种原生材料
	 * WOOD(0, 59, 2.0F, 0.0F, 15),
	 * STONE(1, 131, 4.0F, 1.0F, 5),
	 * IRON(2, 250, 6.0F, 2.0F, 14),
	 * EMERALD(3, 1561, 8.0F, 3.0F, 10),
	 * GOLD(0, 32, 12.0F, 0.0F, 22);
	 * 
	 * name 参数应该是自定义的名字吧？
	 * harvestLevel 参数表示制作出的工具等级。这一点在镐中尤其明显，如木头为0，只能挖掘对应等级为0的方块才能掉落物品，如石头等，而钻石为3，就可以挖掘出对应等级为3的，其他镐挖不出物品的方块，如黑曜石。
	 * maxUses 参数表示制作出的工具对应耐久。如钻石工具就是1561，耐久最高，而木工具为59，耐久最低。
	 * efficiency 参数表示制作出的工具使用效率。使用效率和该参数的值成正比。
	 * damageVsEntity 参数表示攻击伤害力度。同样该力度和该参数的值成正相关。
	 * enchantability 参数与附魔等级相关。Minecraft中关于附魔等级的系统十分复杂。但是有一点，就是该值越高，对应的附魔就越容易得到高等级。这也是为何金更容易得到高等级附魔，而石头得到的附魔就相当低。
	 */
	public static final Item.ToolMaterial REDSTONE = EnumHelper
			.addToolMaterial("REDSTONE", 3, 1226, 16.0F, 2.0F, 20);

	/**
	 * 五种原生材料
	 * LEATHER("leather", 5, new int[]{1, 3, 2, 1}, 15),
	 * CHAIN("chainmail", 15, new int[]{2, 5, 4, 1}, 12),
	 * IRON("iron", 15, new int[]{2, 6, 5, 2}, 9),
	 * GOLD("gold", 7, new int[]{2, 5, 3, 1}, 25),
	 * DIAMOND("diamond", 33, new int[]{3, 8, 6, 3}, 10);
	 * 
	 * name 参数应该是自定义的名字吧？
	 * rextureName 参数与该ArmorMaterial的材质所在位置有关，这一部分的稍后面会讲到。这里是“fmltutor:redstone”。
	 * maxDamage 参数和该ArmorMaterial对应的盔甲的耐久成正比。这里刻意降低了大小，为10。
	 * reductionAmounts 参数的四个元素表示对应盔甲的头盔、胸甲、护腿、和靴子抵御伤害的能力，如皮甲分别为1，3，2，1，和为7，钻石甲分别为3，8，6，3，和为20，请不要让四个元素值的和超过这个值。这里为2，6，4，2，和为14。
	 * enchantability 参数和ToolMaterial一样，和对应盔甲的附魔能力正相关，同样，金盔甲的附魔能力最高。这里为10。
	 * 
	 * 盔甲的材质图是两个大小为64x32的图片。rextureName决定了这两个图片的位置。
	 * 例如，钻石的name参数为diamond，其两张图片的位置就是textures/models/armor/diamond_layer_1.png和textures/models/armor/diamond_layer_2.png。
	 * 这里我们的ArmorMaterial的name参数为fmltutor:redstone，其两张图片的位置就是fmltutor:textures/models/armor/redstone_layer_1.png和fmltutor:textures/models/armor/redstone_layer_2.png了。
	 * 
	 * （assets/fmltutor/demo/armor_texture_analysis.png）
	 * （材质分区图，其中F表示前面，B表示后面，L表示左面。R表示右面，U表示顶面，D表示底面，紫色背景表示尺寸，每格大小为7x7，边框尺寸为1）
	 * 我们注意到，这一张材质图被分成了五个大部分，每一个部分都有不同的尺寸。
	 * 它们分别为头（Head，8x8x8），头饰（Headwear，8x8x8）；下肢（RightLeg/LeftLeg，4x12x4）；身体（Body，8x12x4）；和上肢（RightArm/LeftArm，4x12x4）。每一个部分分成了六个小部分，表示六个面。
	 * 那。。。为什么是两张图呢？
	 * 这是因为当游戏渲染不同的盔甲的时候，使用的材质图不一样。当游戏渲染护腿时使用第二张图，这里就是redstone_layer_2.png，渲染其他类型的盔甲时使用第一张图，这里为redstone_layer_1.png。
	 * 游戏会根据玩家已经穿戴的盔甲，决定哪一部分被渲染：
	 * 当玩家穿戴上头盔，游戏渲染第一张图的Head和Headwear部分。
	 * 当玩家穿戴上胸甲，游戏渲染第一张图的Body和RightArm/LeftArm部分。
	 * 当玩家穿戴上护腿，游戏渲染第二张图的Body和RightLeg/LeftLeg部分。
	 * 当玩家穿戴上靴子，游戏渲染第一张图的RightLeg/LeftLeg部分。
	 * 
	 * 这里准备了一张已经划分好不同部分的，大小为64x32的图，以方便读者设计盔甲。（assets/fmltutor/demo/armor_texture.png）
	 */
	public static final ItemArmor.ArmorMaterial REDSTONE_ARMOR = EnumHelper
			.addArmorMaterial("REDSTONE", FMLTutor.MODID + ":" + "redstone",
					10, new int[] { 2, 6, 4, 2 }, 10);

	
	/**
	 * DamageSource 类常用的伤害类型
	 * 
	 * public static DamageSource inFire; 
	 * 当站在火中时产生
	 * public static DamageSource lightningBolt; 
	 * 当遭雷劈时产生
	 * public static DamageSource onFire; 
	 * 当着火时产生
	 * public static DamageSource lava; 
	 * 当在岩浆中产生
	 * public static DamageSource inWall; 
	 * 当被方块窒息时产生
	 * public static DamageSource drown; 
	 * 当被水窒息时产生
	 * public static DamageSource starve; 
	 * 当饥饿值为零时产生
	 * public static DamageSource cactus; 
	 * 当被仙人掌刺伤时产生
	 * public static DamageSource fall; 
	 * 当受到跌落伤害时产生
	 * public static DamageSource outOfWorld; 
	 * 当跌落出这个世界时产生
	 * public static DamageSource generic; 
	 * 当死亡原因未知时产生
	 * public static DamageSource magic; 
	 * 当受到有伤害效果药水伤害时产生
	 * public static DamageSource wither; 
	 * 当被凋灵效果伤害时产生
	 * public static DamageSource anvil; 
	 * 当头顶铁砧时产生
	 * public static DamageSource fallingBlock; 
	 * 当头顶掉落的方块时产生
	 * 
	 * DamageSource的属性
	 * 
	 * setDefficultyScaled方法设置的属性表示受到的伤害随着难度的变化而变化。
	 * setExplosion方法设置的属性表示该伤害由爆炸造成，爆炸保护附魔会起到作用。
	 * setDamageBypassesArmor设置伤害不会因为盔甲的保护而折减。
	 * setDamageAllowedInCreativeMode设置创造模式同样会受到伤害。
	 * setDamageIsAbsolute设置伤害是绝对的，不会受到附魔、药水效果等影响。
	 * setFireDamage设置伤害由火焰造成，火焰保护附魔会起到作用。
	 * setMagicDamage设置伤害是由药水造成的。
	 * setProjectile设置伤害由弹射物造成，弹射物保护附魔会起到作用。
	 * 
	 */
	public static void DamageSource(){}
}
