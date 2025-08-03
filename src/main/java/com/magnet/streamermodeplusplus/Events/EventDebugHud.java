package com.magnet.streamermodeplusplus.Events;

import java.util.List;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public interface EventDebugHud
{
	 
	void onRender(List<String> lines);
	
    Event<EventDebugHud> EVENT = EventFactory.createArrayBacked(
            EventDebugHud.class,
            (listeners) -> (lines) -> {
                for (EventDebugHud listener : listeners) {
                    listener.onRender(lines);
                }
            }
        );
}

