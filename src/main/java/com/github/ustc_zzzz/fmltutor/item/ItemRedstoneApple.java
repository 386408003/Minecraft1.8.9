package com.github.ustc_zzzz.fmltutor.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import com.github.ustc_zzzz.fmltutor.creativetab.CreativeTabsLoader;

/**
 * 红石苹果
 * 
 * amount表示该食物所能回复的饥饿值，苹果是4。
 * saturation表示该食物所能添加的相对饱和度，其正比于饱和度和饥饿值的比值。
 * isWolfFood表示该食物能否被狼食用。
 * 饱和度的计算：2 * amount * saturation。如面包的amount为5，其saturation为0.6F，对应的饱和度为2*5*0.6 = 6。
 * 
 * 食物	amount	saturation
 * 苹果		4	0.3F
 * 面包		5	0.6F
 * 生猪排	3	0.3F
 * 熟猪排	8	0.8F
 * 曲奇		2	0.1F
 * 西瓜片	2	0.3F
 * 生牛肉	3	0.3F
 * 牛排		8	0.8F
 * 生鸡肉	2	0.3F
 * 熟鸡肉	6	0.6F
 * 腐肉		4	0.1F
 * 蜘蛛眼	2	0.8F
 * 烤马铃薯	5	0.6F
 * 毒马铃薯	2	0.3F
 * 金萝卜	6	1.2F
 * 南瓜派	8	0.3F
 * 
 * @author ZhuFeng 
 * @date 2018年3月2日
 */
public class ItemRedstoneApple extends ItemFood {
	public ItemRedstoneApple() {
		super(4, 0.6F, false);
		//该食物何时何地都可以被食用，即便玩家不需要回复饥饿度和饱和值。
		this.setAlwaysEdible();
		this.setUnlocalizedName("redstoneApple");
		this.setCreativeTab(CreativeTabsLoader.tabFMLTutor);
		/**
		 * 第一个参数表示对应药水效果的potionId，读者可以去net.minecraft.potion.Potion类中查看MC提供的二十四种药水效果，这里为伤害吸收。
		 * 第二个参数表示对应药水效果的持续时间，以秒计数，这里为十秒。
		 * 第三个参数表示对应药水效果的等级，很明显，0为一级，1为二级，2为三级，以此类推，这里为二级。
		 * 最后一个参数表示产生该药水效果的概率，这里为100%。
		 */
		this.setPotionEffect(Potion.absorption.id, 10, 1, 1.0F);
	}
	
	/**
	 * 食用该食物还会给玩家带来十秒的饱和二效果，和十点经验。
	 * 这里有一点不同的地方，就是PotionEffect的构造函数使用的时间是以gametick计数的。
	 */
	@Override
	public void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
		if (!worldIn.isRemote) {
			player.addPotionEffect(new PotionEffect(Potion.saturation.id, 200, 1));
			player.addExperience(10);
		}
		super.onFoodEaten(stack, worldIn, player);
	}
}