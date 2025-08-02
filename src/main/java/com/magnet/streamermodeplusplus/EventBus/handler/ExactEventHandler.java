package com.magnet.streamermodeplusplus.EventBus.handler;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.logging.log4j.Logger;

import com.magnet.streamermodeplusplus.EventBus.EventSubscribe;
import com.magnet.streamermodeplusplus.EventBus.EventSubscriber;
import com.magnet.streamermodeplusplus.Events.Event;

/**
 * Very fast event handler that only posts events to an exact event class.
 */
public class ExactEventHandler extends EventHandler {

	// <Event Class, Subscribers>
	private final Map<Class<?>, List<EventSubscriber>> subscribers = new ConcurrentHashMap<>();

	public ExactEventHandler(String id) {
		super(id);
	}

	public boolean subscribe(Object object) {
		boolean added = false;
		for (Method m: object.getClass().getDeclaredMethods()) {
			if (m.isAnnotationPresent(EventSubscribe.class) && m.getParameters().length != 0) {
				subscribers.computeIfAbsent(m.getParameters()[0].getType(), k -> new CopyOnWriteArrayList<>()).add(new EventSubscriber(object, m));
				added = true;
			}
		}

		return added;
	}

	public boolean unsubscribe(Object object) {
		boolean[] removed = new boolean[1];
		subscribers.values().removeIf(v -> {
			removed[0] |= v.removeIf(s -> object.getClass().equals(s.getTargetClass()));
			return v.isEmpty();
		});
		
		return removed[0];
	}

	public void post(Event event, Logger logger) {
		List<EventSubscriber> sList = subscribers.get(event.getClass());
		if (sList != null) {
			for (EventSubscriber s: sList) {
				try {
					s.callSubscriber(event);
				} catch (Throwable t) {
					logger.error("Exception thrown by subscriber method " + s.getSignature() + " when dispatching event: " + s.getEventClass().getName(), t);
				}
			}
		}
	}

	@Override
	public void post(Event event) {
		// TODO Auto-generated method stub
		
	}
}
