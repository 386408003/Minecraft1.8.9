package com.github.ustc_zzzz.fmltutor.client;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

/**
 * 按键绑定的辅助类
 * 
 * KeyBinding的三个参数含义：
 * description参数表示这个键的介绍。
 * keyCode参数表示这个键的默认键名。这里是H。
 * category参数表示这个键所在的键类别。
 * 
 * @author ZhuFeng 
 * @date 2018年3月23日
 */
public class KeyLoader {
	public static KeyBinding showTime;

	public KeyLoader() {
		KeyLoader.showTime = new KeyBinding("key.fmltutor.showTime",
				Keyboard.KEY_H, "key.categories.fmltutor");

		ClientRegistry.registerKeyBinding(KeyLoader.showTime);
	}
}