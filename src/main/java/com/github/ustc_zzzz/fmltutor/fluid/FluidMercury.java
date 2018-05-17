package com.github.ustc_zzzz.fmltutor.fluid;

import com.github.ustc_zzzz.fmltutor.FMLTutor;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

/**
 * 新的流体水银
 * 
 * Fluid类构造方法参数：
 * 第一个参数表示的是这个流体的名称，使用这个流体的现实生活中的名称就可以了
 * 第二个参数表示的是这个流体静止的时候使用的贴图位置，我们通过实例化一个ResourceLocation类来完成
 * 第三个参数表示的是这个流体流动的时候使用的贴图位置，和第二个参数同理
 * 
 * Forge规定的流体，有着五个常见的属性，供对应的方块、桶、和一些Mod使用：
 * setDensity方法用于设置这个流体的密度，单位为千克每立方米，默认为水的密度，也就是1000
 * setViscosity方法用于设置这个流体的粘度，单位为千分之一平方米每秒，使用运动粘度，默认为水的粘度，也就是1000
 * setLuminosity方法用于设置这个流体的亮度，也就是在Minecraft中的亮度，默认为水的亮度，也就是0
 * setTemperature方法用于设置这个流体的温度，使用热力学温标，也就是开尔文，默认为室温，也就是300
 * setGaseous方法用于标注这个流体是否为气体，默认不是
 * 
 * 流体、火焰等动画的描述是在同一个文件夹下的一个比材质文件名多了一个名为.mcmeta的后缀的文件中定义。
 * （assets/fmltutor/textures/fluid/mercury_still.png.mcmeta/assets/fmltutor/textures/fluid/mercury_flow.png.mcmeta）
 * 前面的文件表示的是两gametick一帧，而后面的表示的是一gametick一帧
 * 这里建议表示流动的材质宽度为32px，表示静止的材质宽度为16px，因为流体的流动包括斜向的流动，总而言之，流动的材质宽度应该是静止的两倍。
 * 
 * @author ZhuFeng 
 * @date 2018年3月30日
 */
public class FluidMercury extends Fluid {
	public static final ResourceLocation still = new ResourceLocation(
			FMLTutor.MODID + ":" + "fluid/mercury_still");
	public static final ResourceLocation flowing = new ResourceLocation(
			FMLTutor.MODID + ":" + "fluid/mercury_flow");

	public FluidMercury() {
		super("mercury", FluidMercury.still, FluidMercury.flowing);
		this.setUnlocalizedName("fluidMercury");
		this.setDensity(13600);
		this.setViscosity(750);
		this.setLuminosity(0);
		this.setTemperature(300);
	}
}