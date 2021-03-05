package com.project.BerlinClockBuilder.models;

import java.text.*;

public class Time {

	private int hour;
	private int minute;
	private int second;


	public Time() {}

	public Time(String[] values) throws ParseException {
		try {
			this.hour = Integer.valueOf(values[0]);
			this.minute = Integer.valueOf(values[1]);
			this.second = Integer.valueOf(values[2]);
			if(!validateStringRange(hour, minute, second))
				throw new StringIndexOutOfBoundsException("value was not in the requested range");
		} catch (Exception ex) {
			throw new ParseException("Couldn't parse value to Integer. ex :"+ex,1);
		}

	}
	
	private boolean validateStringRange(int hour,int minute,int second) {
		if(hour<0||hour>24) return false;
		if(minute<0||minute>59) return false;
		if(second<0||second>59) return false;
		return true;
	}

	public Time(int hour, int minute, int second) {
		super();
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	

}
