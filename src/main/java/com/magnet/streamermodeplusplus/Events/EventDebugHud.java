package com.magnet.streamermodeplusplus.Events;

import java.util.List;

public class EventDebugHud extends Event 
{
	
	public List<String> lines;
	
    public EventDebugHud(List<String> lines) 
    {
    	this.lines = lines;
    }
}

