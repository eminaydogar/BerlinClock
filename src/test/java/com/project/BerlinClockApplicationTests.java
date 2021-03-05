package com.project;

import java.time.LocalTime;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.BerlinClockBuilder.BerlinClock;

@SpringBootTest
class BerlinClockApplicationTests {
	private static Logger log = LoggerFactory.getLogger(BerlinClockApplicationTests.class);
	@Test
	void contextLoads() {
		BerlinClock berlinClock = new BerlinClock();
		berlinClock.Process("21:22:55");
		System.out.println("contextLoads() : "+berlinClock.getClok());
		log.debug("contextLoads() : "+berlinClock.getClok());
	}
	
	@Test
	void inValidCharacters() {
		BerlinClock berlinClock = new BerlinClock();
		berlinClock.Process("21-22-55");
		System.out.println("inValidCharacters() : "+berlinClock.getClok());
		log.debug("inValidCharacters() : "+berlinClock.getClok());
	}
	
	@Test
	void allZero() {
		BerlinClock berlinClock = new BerlinClock();
		berlinClock.Process("00:00:00");
		System.out.println("allZero() : "+berlinClock.getClok());
		log.debug("allZero() : "+berlinClock.getClok());
	}
	
	@Test 
	void Stringvalues() {
		BerlinClock berlinClock = new BerlinClock();
		berlinClock.Process("hh:mm:ss");
		System.out.println("Stringvalues() : "+berlinClock.getClok());
		log.debug("Stringvalues() : "+berlinClock.getClok());
	}
	
	@Test 
	void withDateTime() {
		BerlinClock berlinClock = new BerlinClock();
		berlinClock.Process(new Date());
		System.out.println("withDateTime() : "+berlinClock.getClok());
		log.debug("withDateTime() : "+berlinClock.getClok());
	}
	
	@Test 
	void withLocalTime() {
		BerlinClock berlinClock = new BerlinClock();
		LocalTime now = LocalTime.now();
		berlinClock.Process(now);
		System.out.println("withLocalTime() : "+berlinClock.getClok());
		log.debug("withLocalTime() : "+berlinClock.getClok());
	}

}
