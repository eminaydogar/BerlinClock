package com.project.BerlinClockBuilder;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.BerlinClockBuilder.models.Time;

/*
 * 
 * @author Emin.Aydogar
 * 
 */

public class BerlinClock {
	private static Logger log = LoggerFactory.getLogger(BerlinClock.class);
	private final String Lamp0 = "LAMP0";
	private final String Lamp1 = "LAMP1";
	private final String Lamp2 = "LAMP2";
	private final String Lamp3 = "LAMP3";
	private final String Lamp4 = "LAMP4";
	/*
	 * Üstteki sarı ışık birkaç saniyede bir yanıp söner (çift saniyeler için yanar,
	 * tek saniyeler için söner). En üstteki iki satır saatleri temsil eder. Üst
	 * sıradaki ışıklar, her biri beş saati temsil ederken, alt sıradaki lambalar
	 * her biri bir saati temsil eder. En alttaki iki sıra dakikayı temsil eder.
	 * Yine, her üçüncü sıra lambası 5 dakikayı temsil ediyor, yani 11 tane var. Her
	 * bir alt sıra lambası bir dakikayı temsil eder.
	 * 
	 * Örneğin, öğleden sonra 4 (16 saat), ilk satırda 3 lamba ve ikinci satırda 1
	 * ışık (3x5 + 1) ile temsil edilir. Aynı şekilde, 27 dakika üçüncü sırada 5
	 * ışıkla ve en alt sırada 2 ışıkla temsil edilir (5x5 + 2).
	 * 
	 * Bir saatin çeyreğini gösteren 3., 6. ve 9. lambalar dışında üçüncü sıradaki
	 * lambaların hepsinin sarı olduğunu fark edebilirsiniz . Bu, sarı lambalar gibi
	 * 5 dakikayı temsil ettikleri için sadece görsel bir kolaylıktır.
	 * 
	 * 
	 */
	private String clock;

	public BerlinClock() {}

	public void Process(Object time) {
		log.info("starting process");
		if (time == null)
			throw new NullPointerException("Time was null");
		String formatedTime = validateTime(time);

		try {
			String[] timeValues = formatedTime.toString().split(":");
			if (timeValues.length != 3)
				throw new StringIndexOutOfBoundsException();
			Time instanceTime = new Time(timeValues);

			this.clock = berlinClockBuilder(instanceTime);
			log.info("end process");

		} catch (Exception ex) {
			new Exception("SYSTEM_ERROR" + ex);
			log.error("Error while processing time! e: " + ex);
		}
	}

	private String berlinClockBuilder(Time instanceTime) {

		String lamp0, lamp1, lamp2, lamp3, lamp4;
		lamp0 = lampStatus(Lamp0, instanceTime.getSecond());
		lamp1 = lampStatus(Lamp1, instanceTime.getHour());
		lamp2 = lampStatus(Lamp2, instanceTime.getHour());
		lamp3 = lampStatus(Lamp3, instanceTime.getMinute());
		lamp4 = lampStatus(Lamp4, instanceTime.getMinute());

		return toString(lamp0, lamp1, lamp2, lamp3, lamp4);
	}

	private String lampStatus(String lampType, int clockType) {
		String returnValue = "";
		int Red, Yellow, Non;
		switch (lampType) {
		case Lamp0:
			returnValue = clockType % 2 == 0 ? "YELLOW" : "NON";
			break;
		case Lamp1:
			Red = clockType / 5;
			Non = 4 - Red;
			for (int i = 0; i < Red; i++)
				returnValue += "RED ";
			for (int i = 0; i < Non; i++)
				returnValue += "NON ";
			break;
		case Lamp2:
			Red = clockType % 5;
			Non = 4 - Red;
			for (int i = 0; i < Red; i++)
				returnValue += "RED ";
			for (int i = 0; i < Non; i++)
				returnValue += "NON ";
			break;
		case Lamp3:
			Yellow = clockType / 5;
			Non = 11 - Yellow;
			for (int i = 0; i < Yellow; i++)
				returnValue += "YELLOW ";
			for (int i = 0; i < Non; i++)
				returnValue += "NON ";
			returnValue = returnValue.replaceAll("YELLOW YELLOW YELLOW ", "YELLOW YELLOW RED ");
			break;
		case Lamp4:
			Yellow = clockType % 5;
			Non = 4 - Yellow;
			for (int i = 0; i < Yellow; i++)
				returnValue += "YELLOW ";
			for (int i = 0; i < Non; i++)
				returnValue += "NON ";
			break;
		}
		return returnValue;

	}

	private String toString(String... params) {
		String val = "";
		for (int i = 0; i < params.length; i++)
			val += params[i] + "\n";
		return val;

	}

	private String validateTime(Object o) {
		String val = "";
		if (o instanceof String) {
			val = ((String) o).trim();
			if (val.length() > 8)
				throw new IllegalArgumentException("Time is not valid !");

		} else if (o instanceof Date) {
			SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss");
			val = formatTime.format(((Date) o));
		}

		else if (o instanceof LocalTime) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
			val = ((LocalTime) o).format(dtf);
		} else {
			throw new IllegalArgumentException("No valid object found");
		}

		return val;

	}

	public String getClok() {
		return clock;
	}

}
