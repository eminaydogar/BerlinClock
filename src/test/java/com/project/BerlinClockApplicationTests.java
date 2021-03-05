package com.project;

import java.time.LocalTime;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.BerlinClockBuilder.BerlinClock;
import com.project.controller.BerlinClockController;

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
	void invalidStringValues() {
		BerlinClock berlinClock = new BerlinClock();
		berlinClock.Process("hh:mm:ss");
		System.out.println("invalidStringValues() : \n"+berlinClock.getClok());
		log.debug("invalidStringValues() : \n"+berlinClock.getClok());
	}
	
	@Test 
	void withDateTime() {
		BerlinClock berlinClock = new BerlinClock();
		berlinClock.Process(new Date());
		System.out.println("withDateTime() : \n"+berlinClock.getClok());
		log.debug("withDateTime() : \n"+berlinClock.getClok());
	}
	
	@Test 
	void withLocalTime() {
		BerlinClock berlinClock = new BerlinClock();
		LocalTime later = LocalTime.now().plusHours(5);
		berlinClock.Process(later);
		System.out.println("withLocalTime() : \n"+berlinClock.getClok());
		log.debug("withLocalTime() : \n"+berlinClock.getClok());
	}
	@Test 
	void callApiWithParam() {
		LocalTime now = LocalTime.now();
		BerlinClockController controller = new BerlinClockController();
		String val = controller.getBerlinClock(now);
		
		System.out.println("callApiWithParam() : \n"+val);
		log.debug("callApiWithParam() : \n"+val);

	}
	@Test 
	void callApiNoParam() {
		
		BerlinClockController controller = new BerlinClockController();
		String val = controller.getBerlinClock(new Date());
		System.out.println("callApiNoParam() : \n"+val);
		log.debug("callApiNoParam() : \n"+val);

	}

}
