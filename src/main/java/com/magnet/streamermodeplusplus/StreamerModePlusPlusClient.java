package com.magnet.streamermodeplusplus;

import org.lwjgl.glfw.GLFW;

import com.magnet.streamermodeplusplus.Events.EventDebugHud;
import com.magnet.streamermodeplusplus.Screens.ModMenuScreen;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

public class StreamerModePlusPlusClient implements ClientModInitializer
{
	
	public static final StreamerModePlusPlusClient INSTANCE = new StreamerModePlusPlusClient();
	
	public static boolean mixinHideCoordsDebugHudEnabled = false;
	public static boolean mixinHideBlockInfoDebugHudEnabled = false;
	public static boolean mixinHideChunkInfoDebugHudEnabled = false;
	public static boolean mixinHideFaceInfoDebugHudEnabled = false;
	
	public String hiddenCoordsMessage = "XYZ: HIDDEN FROM STREAMERMODEPLUSPLUS :)";
	public String hiddenBlockMessage = "Block: HIDDEN FROM STREAMERMODEPLUSPLUS :)";
	public String hiddenChunkMessage = "Chunk: HIDDEN FROM STREAMERMODEPLUSPLUS :)";
	public String hiddenFaceMessage = "Facing: HIDDEN FROM STREAMERMODEPLUSPLUS :)";
	
	private static KeyBinding MenuBind;

    public static StreamerModePlusPlusClient getInstance() {
        return INSTANCE;
    }
	
	public String getHiddenFaceMessage() {
		return hiddenFaceMessage;
	}

	public void setHiddenFaceMessage(String hiddenFaceMessage) {
		this.hiddenFaceMessage = hiddenFaceMessage;
	}

	public String getHiddenCoordsMessage() {
		return hiddenCoordsMessage;
	}

	public void setHiddenCoordsMessage(String hiddenCoordsMessage) {
		this.hiddenCoordsMessage = hiddenCoordsMessage;
	}

	public String getHiddenBlockMessage() {
		return hiddenBlockMessage;
	}

	public void setHiddenBlockMessage(String hiddenBlockMessage) {
		this.hiddenBlockMessage = hiddenBlockMessage;
	}

	public String getHiddenChunkMessage() {
		return hiddenChunkMessage;
	}

	public void setHiddenChunkMessage(String hiddenChunkMessage) {
		this.hiddenChunkMessage = hiddenChunkMessage;
	}

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
					MinecraftClient.getInstance().setScreen(new ModMenuScreen());
				}
			});
			
			modifyF3Coords();
	}
	
	public void modifyF3Coords() 
	{
			EventDebugHud.EVENT.register(lines -> {
		        for (int i = 0; i < lines.size(); i++) {
		            String line = lines.get(i);

		            if (mixinHideCoordsDebugHudEnabled && line.startsWith("XYZ:")) {
		                lines.set(i, StreamerModePlusPlusClient.INSTANCE.hiddenCoordsMessage);
		            }

		            if (mixinHideBlockInfoDebugHudEnabled && line.startsWith("Block:")) {
		                lines.set(i, StreamerModePlusPlusClient.INSTANCE.hiddenBlockMessage);
		            }

		            if (mixinHideChunkInfoDebugHudEnabled && line.startsWith("Chunk:")) {
		                lines.set(i, StreamerModePlusPlusClient.INSTANCE.hiddenChunkMessage);
		            }

		            if (mixinHideFaceInfoDebugHudEnabled && line.startsWith("Facing:")) {
		                lines.set(i, StreamerModePlusPlusClient.INSTANCE.hiddenFaceMessage);
		            }
		        }
			});
	}

}
