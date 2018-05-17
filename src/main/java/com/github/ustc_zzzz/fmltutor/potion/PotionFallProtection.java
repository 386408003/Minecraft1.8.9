package com.github.ustc_zzzz.fmltutor.potion;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

import com.github.ustc_zzzz.fmltutor.FMLTutor;

/**
 * 构造方法参数详解：
 * 第一个参数表示这个药水效果的名称，其使用方式和附魔相同
 * 第二个参数表示这个附魔是否有害，这里很明显是无害的
 * 第三个参数表示这个附魔的粒子效果（螺旋）颜色，这里定为深红色
 * 
 * setPotionName方法和附魔的setName方法，以及方块、物品等的setUnlocalizedName方法类似。
 * （assets/fmltutor/demo/inventory_potion_analysis.png）
 * setIconIndex方法表示这个药水效果在显示的时候使用的图标在上图中的位置，两个参数表示x和y坐标，这里设置为和速度药水效果的图标一致。
 * 
 * @author ZhuFeng 
 * @date 2018年3月17日
 */
public class PotionFallProtection extends Potion {
	private static final ResourceLocation res = new ResourceLocation(FMLTutor.MODID + ":" + "textures/gui/potion.png");

	public PotionFallProtection() {
		super(new ResourceLocation(FMLTutor.MODID + ":" + "fall_protection"), false, 0x7F0000);
		this.setPotionName("potion.fallProtection");
		//this.setIconIndex(0, 0);
	}
	
	/**
	 * x参数表示药水效果框左上角的横坐标
	 * y参数表示药水效果框左上角的纵坐标
	 * effect参数表示该药水效果对应的PotionEffect
	 * mc参数表示当前的这个游戏
	 * 
	 * bindTexture方法用于绑定我们想要用于绘制的图片，这里就是上面我们提供的图片。
	 * 
	 * drawTexturedModalRect方法就是用于绘制这个图标了。
	 * 第一个参数和第二个参数表示绘制的图标在游戏中的左上角的横纵坐标（xy值）。这里照着原版的数据做就行了
	 * 第三个参数和第四个参数表示绘制的图标在图片中的左上角的横纵坐标（uv值）。这里是整张图的左上角，自然都是零
	 * 第五个参数和第六个参数表示绘制的图标大小。这里和原版一样，是18x18
	 * 
	 * 需要提供一个大小为256x256（其它尺寸是不可行的，只能256x256）的图片，并在左上角放上对应的18x18图标。
	 */
	@Override
	public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
		mc.getTextureManager().bindTexture(PotionFallProtection.res);
		mc.currentScreen.drawTexturedModalRect(x + 6, y + 7, 0, 0, 18, 18);
	}
}