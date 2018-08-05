package com.ubs.opsit.interviews;

import java.util.Arrays;
import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ubs.opsit.utils.BerlinClockConstant;
import com.ubs.opsit.utils.LampType;
import com.ubs.opsit.utils.TimeValidation;

public class TimeConverterImpl implements TimeConverter{
	private static final Logger logger = LoggerFactory.getLogger(TimeConverterImpl.class);
	
	private static final String ZERO="O";
	private static final String YYY="YYY";
	private static final String YYR="YYR";
	
	@Override
	public String convertTime(String aTime) {
		logger.debug("convertTime start with time = {}",aTime);
		
		TimeValidation.validateTime(aTime);
		
		String[] times = aTime.split(":");
		int hours = Integer.parseInt(times[0]);
		int minutes = Integer.parseInt(times[1]);
		int seconds = Integer.parseInt(times[2]);
		
		String result=convertTime(hours,minutes,seconds);
		logger.debug("convertTime end with result = {}",result);
		return result;
	}

	private String convertTime(int hours,int minutes,int seconds) {
		logger.debug("convertTime start with hours = {}, minutes= {}, seconds= {}",hours,minutes,seconds);
		
        String lamp = (seconds % 2 == 0) ? LampType.YELLOW.getValue() : ZERO;
        String row1 = createRow(hours / 5, 4, LampType.RED);
        String row2 = createRow(hours % 5, 4, LampType.RED);
        String row3 = createRow(minutes / 5, 11, LampType.YELLOW).replaceAll(YYY, YYR);
        String row4 = createRow(minutes % 5, 4, LampType.YELLOW);

        String result= String.join(BerlinClockConstant.NEW_LINE, Arrays.asList(lamp, row1, row2, row3, row4));
        logger.debug("convertTime end with result = {}",result);
        
        return result;
    }
	
	 private String createRow(int totalOnLamps, int totalLamps, LampType lampType) {

	        int totalOffLamps = totalLamps - totalOnLamps;
	        String on = String.join(BerlinClockConstant.EMPTY_STRING, Collections.nCopies(totalOnLamps, lampType.getValue()));
	        String off = String.join(BerlinClockConstant.EMPTY_STRING, Collections.nCopies(totalOffLamps, ZERO));

	        return on + off;
	    }

}
