package com.magnet.streamermodeplusplus.Events;

import java.util.List;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public interface EventDebugHud {
	void onLeftRender(List<String> lines);
	void onRightRender(List<String> lines);

	Event<EventDebugHud> EVENT = EventFactory.createArrayBacked(
		EventDebugHud.class,
		(listeners) -> new EventDebugHud() {
			@Override
			public void onLeftRender(List<String> lines) {
				for (EventDebugHud listener : listeners) {
					listener.onLeftRender(lines);
				}
			}

			@Override
			public void onRightRender(List<String> lines) {
				for (EventDebugHud listener : listeners) {
					listener.onRightRender(lines);
				}
			}
		}
	);
}


