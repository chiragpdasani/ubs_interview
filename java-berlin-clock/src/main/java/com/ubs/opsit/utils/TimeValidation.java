package com.ubs.opsit.utils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeValidation {
	private static final Logger logger = LoggerFactory.getLogger(TimeValidation.class);

	public static void validateTime(String time) {
		logger.debug("validateTime start for time ={}", time);

		if (StringUtils.isEmpty(time)) {
			throw new IllegalArgumentException(BerlinClockConstant.NO_TIME_ERROR);
		}
		
		String[] times = time.split(":");

	    if(times.length != 3) {
	    	throw new IllegalArgumentException(BerlinClockConstant.INVALID_TIME_ERROR);
	    }
		
	    int hours, minutes, seconds = 0;
	    
	    try {
            hours = Integer.parseInt(times[0]);
            minutes = Integer.parseInt(times[1]);
            seconds = Integer.parseInt(times[2]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(BerlinClockConstant.NUMERIC_TIME_ERROR);
        }
	    
	    if (hours < 0 || hours > 24) {
	    	throw new IllegalArgumentException(BerlinClockConstant.INVALID_HOURS);
	    }
	    
        if (minutes < 0 || minutes > 59) {
        	throw new IllegalArgumentException(BerlinClockConstant.INVALID_MINUTES);
        }
        if (seconds < 0 || seconds > 59) {
        	throw new IllegalArgumentException(BerlinClockConstant.INVALID_SECONDS);
        }
        
        if(hours==24 && (minutes !=0 || seconds != 0)){
        	throw new IllegalArgumentException(BerlinClockConstant.INVALID_TIME_ERROR);
        }
		logger.debug("validateTime end with no error");

	}
}
