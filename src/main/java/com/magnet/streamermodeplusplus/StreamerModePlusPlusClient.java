package com.magnet.streamermodeplusplus;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

public class StreamerModePlusPlusClient implements ClientModInitializer
{
	
	private static KeyBinding MenuBind;

	@Override
	public void onInitializeClient() 
	{
			MenuBind = KeyBindingHelper.registerKeyBinding(
					new KeyBinding("key.streamermodeplusplus.smppmenubind", 
							InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_MINUS, 
							"category.streamermodeplusplus.streamermodeplusplus")
					);
			
			ClientTickEvents.END_CLIENT_TICK.register(client -> {
				while (MenuBind.wasPressed()) 
				{
					
				}
			});
	}

}
