package com.project.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.BerlinClockBuilder.BerlinClock;

@RestController
@RequestMapping(value = "/api/berlinClock")
public class BerlinClockController {
	private static Logger log = LoggerFactory.getLogger(BerlinClockController.class);
	// Berlin Time
	// private LocalTime time = Instant.now().plus(1,
	// ChronoUnit.HOURS).atZone(ZoneOffset.UTC).toLocalTime();

	private BerlinClock berlinClock = new BerlinClock();


	@GetMapping(value = "")
	public String getBerlinClock() {
		log.info("getBerlinClock() called");
		berlinClock.Process(new Date());
		return berlinClock.getClok();

	}

	@GetMapping(value = "/{time}")
	public String getBerlinClock(@PathVariable(value = "time") Object time) {
		log.info("getBerlinClock(Object time) called");
		berlinClock.Process(time);
		return berlinClock.getClok();
	}

	@GetMapping(params = "time")
	public String getBerlinClockwithParams(@RequestParam Object time) {
		log.info("getBerlinClockwithParams(Object time) called");
		berlinClock.Process(time);
		return berlinClock.getClok();
	}

}
