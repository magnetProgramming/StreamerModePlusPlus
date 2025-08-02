package com.magnet.streamermodeplusplus.EventBus;

import java.util.concurrent.atomic.AtomicLong;


import com.magnet.streamermodeplusplus.EventBus.handler.EventHandler;
import com.magnet.streamermodeplusplus.Events.Event;

public class ModEventBus {

	private final EventHandler handler;
	private final AtomicLong eventsPosted = new AtomicLong();
	

	public ModEventBus(EventHandler handler) {
		this.handler = handler;
	}

	public boolean subscribe(Object object) {
		return handler.subscribe(object);
	}

	public boolean unsubscribe(Object object) {
		return handler.unsubscribe(object);
	}

	public void post(Event event) {
		handler.post(event);
		eventsPosted.getAndIncrement();
	}

	public long getEventsPosted() {
		return eventsPosted.get();
	}
}
