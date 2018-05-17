package com.github.ustc_zzzz.fmltutor.client;

import com.github.ustc_zzzz.fmltutor.block.BlockLoader;
import com.github.ustc_zzzz.fmltutor.fluid.FluidLoader;
import com.github.ustc_zzzz.fmltutor.item.ItemLoader;

/**
 * 加载材质的辅助类
 * 
 * @author ZhuFeng 
 * @date 2018年3月2日
 */
public class ItemRenderLoader {
	public ItemRenderLoader() {
		ItemLoader.registerRenders();
		BlockLoader.registerRenders();
		FluidLoader.registerRenders();
	}
}