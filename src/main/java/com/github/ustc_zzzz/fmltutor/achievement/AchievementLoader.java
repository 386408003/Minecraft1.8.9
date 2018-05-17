package com.github.ustc_zzzz.fmltutor.achievement;

import net.minecraft.init.Blocks;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.common.AchievementPage;

import com.github.ustc_zzzz.fmltutor.FMLTutor;
import com.github.ustc_zzzz.fmltutor.block.BlockLoader;
import com.github.ustc_zzzz.fmltutor.item.ItemLoader;

/**
 * 第一个参数是这个成就的名称。
 * 第二个参数是这个成就的非本地化名称。
 * 第三个参数和第四个参数是这个成就所在成就图中的位置，分别为x和y坐标。
 * 第五个参数是这个成就在成就图上显示的图标。
 * 最后一个参数是这个成就依赖的成就，如果这个成就独立，那么设为null。
 * 
 * （assets/fmltutor/demo/achievement_analysis.png）
 * 这张图是一张Minecraft原版所有成就的坐标位置图，其中x和y均为零的位置。
 * setSpecial方法用于设置这个成就是一种特殊成就，在成就图上会有花边，获得成就时显示的文字也是紫色的。
 * registerStat方法注册这个成就。
 * 
 * 调用玩家的triggerAchievement方法，获得我们想要获得的成就。
 * 
 * @author ZhuFeng 
 * @date 2018年3月23日
 */
public class AchievementLoader {
	//人不如猪 成就
	public static Achievement worseThanPig = new Achievement(
			"achievement.fmltutor.worseThanPig", "fmltutor.worseThanPig", 5,
			-4, ItemLoader.goldenEgg, AchievementList.buildSword);

	//制造草方块 成就
	public static Achievement buildGrassBlock = new Achievement(
			"achievement.fmltutor.buildGrassBlock", "fmltutor.buildGrassBlock",
			0, 0, Blocks.vine, null);
	//使草方块爆炸 成就
	public static Achievement explosionFromGrassBlock = new Achievement(
			"achievement.fmltutor.explosionFromGrassBlock",
			"fmltutor.explosionFromGrassBlock", 2, -1, BlockLoader.grassBlock,
			buildGrassBlock);

	/**
	 * AchievementPage类构造方法的第一个参数，是这个成就页的名称，这里用这个Mod的名称代替。
	 * 第二个参数开始，就是这个成就页的所有成就，没有加入任何成就页的成就默认在原版的成就页上显示。
	 * initIndependentStat方法用于设置这个成就是一个独立的、不依赖于其他成就的成就。很明显，一个成就页必须至少存在这样一个成就。
	 * registerAchievementPage这个静态方法，就是注册这个成就页了。
	 */
	public static AchievementPage pageFMLTutor = new AchievementPage(
			FMLTutor.NAME, buildGrassBlock, explosionFromGrassBlock);

	public AchievementLoader() {
		//注册一种特殊成就，在成就图上会有花边，获得成就时显示的文字也是紫色的。
		worseThanPig.setSpecial().registerStat();

		//注册一个独立的、不依赖于其他成就的成就
		buildGrassBlock.initIndependentStat().registerStat();
		//注册一种特殊成就，在成就图上会有花边，获得成就时显示的文字也是紫色的。
		explosionFromGrassBlock.setSpecial().registerStat();

		//注册成就页
		AchievementPage.registerAchievementPage(pageFMLTutor);
	}
}