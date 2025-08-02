package com.magnet.streamermodeplusplus.EventBus.handler;

import com.magnet.streamermodeplusplus.Events.Event;

public abstract class EventHandler {

	private final String id;

	public EventHandler(String id) {
		this.id = id;
	}

	public abstract boolean subscribe(Object object);

	public abstract boolean unsubscribe(Object object);

	public abstract void post(Event event);

	public String getId() {
		return id;
	}
}
