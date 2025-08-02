package com.magnet.streamermodeplusplus;

import net.fabricmc.api.ModInitializer;

import com.magnet.streamermodeplusplus.EventBus.ModEventBus;

public class StreamerModePlusPlus implements ModInitializer {
	public static final String MOD_ID = "streamermodeplusplus";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	
	public static ModEventBus eventBus = new ModEventBus(new com.magnet.streamermodeplusplus.EventBus.handler.InexactEventHandler("streamermode"));

	@Override
	public void onInitialize() 
	{
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		
	}
}