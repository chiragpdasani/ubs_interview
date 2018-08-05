package com.ubs.opsit.interviews;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ubs.opsit.utils.BerlinClockConstant;

@RunWith(MockitoJUnitRunner.class)
public class TimeConverterTest {

	private static final Logger logger = LoggerFactory.getLogger(TimeConverterTest.class);
	@InjectMocks
	TimeConverterImpl timeConverter;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	private String time;

	@Before
	public void setUp() {
		time = "00:00:00";
	}

	@Test
	public void throws_exception_when_time_is_empty_or_null() {
		time = null;
		logger.debug("throws_exception_when_time_is_ivalid start");

		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(BerlinClockConstant.NO_TIME_ERROR);
		timeConverter.convertTime(time);

		logger.debug("throws_exception_when_time_is_ivalid end");
	}

	@Test
	public void throws_exception_when_time_is_invalid_provided() {
		time = "00:00";
		logger.debug("throws_exception_when_time_is_invalid_provided start");

		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(BerlinClockConstant.INVALID_TIME_ERROR);
		timeConverter.convertTime(time);

		logger.debug("throws_exception_when_time_is_invalid_provided end");
	}

	@Test
	public void throws_exception_when_time_value_not_numberic() {
		time = "01:00:01pm";
		logger.debug("throws_exception_when_time_value_not_numberic start");

		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(BerlinClockConstant.NUMERIC_TIME_ERROR);
		timeConverter.convertTime(time);

		logger.debug("throws_exception_when_time_value_not_numberic end");
	}

	@Test
	public void throws_exception_when_invalid_hours_provided() {
		time = "25:00:00";
		logger.debug("throws_exception_when_invalid_hours_provided start");

		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(BerlinClockConstant.INVALID_HOURS);
		timeConverter.convertTime(time);

		logger.debug("throws_exception_when_invalid_hours_provided end");
	}

	@Test
	public void throws_exception_when_invalid_minutes_provided() {
		time = "24:60:00";
		logger.debug("throws_exception_when_invalid_minutes_provided start");

		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(BerlinClockConstant.INVALID_MINUTES);
		timeConverter.convertTime(time);

		logger.debug("throws_exception_when_invalid_minutes_provided end");
	}

	@Test
	public void throws_exception_when_invalid_seconds_provided() {
		time = "23:59:60";
		logger.debug("throws_exception_when_invalid_seconds_provided start");

		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(BerlinClockConstant.INVALID_SECONDS);
		timeConverter.convertTime(time);

		logger.debug("throws_exception_when_invalid_seconds_provided end");
	}
	
	@Test
	public void throws_exception_when_less_than_zero_hours_provided() {
		time = "-1:00:00";
		logger.debug("throws_exception_when_less_than_zero_hours_provided start");

		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(BerlinClockConstant.INVALID_HOURS);
		timeConverter.convertTime(time);

		logger.debug("throws_exception_when_less_than_zero_hours_provided end");
	}

	@Test
	public void throws_exception_when_less_than_zero_minutes_provided() {
		time = "24:-1:00";
		logger.debug("throws_exception_when_less_than_zero_minutes_provided start");

		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(BerlinClockConstant.INVALID_MINUTES);
		timeConverter.convertTime(time);

		logger.debug("throws_exception_when_less_than_zero_minutes_provided end");
	}

	@Test
	public void throws_exception_when_less_than_zero_seconds_provided() {
		time = "23:59:-1";
		logger.debug("throws_exception_when_less_than_zero_seconds_provided start");

		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(BerlinClockConstant.INVALID_SECONDS);
		timeConverter.convertTime(time);

		logger.debug("throws_exception_when_less_than_zero_seconds_provided end");
	}


	@Test
	public void throws_exception_when_hours_minutes_and_seconds_are_invalid() {
		time = "24:59:59";
		logger.debug("throws_exception_when_hours_minutes_and_seconds_are_invalid start");

		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(BerlinClockConstant.INVALID_TIME_ERROR);
		timeConverter.convertTime(time);

		logger.debug("throws_exception_when_hours_minutes_and_seconds_are_invalid end");
	}

	@Test
	public void test_when_time_is_midnight() {
		logger.debug("test_when_time_is_midnight start");

		String actual = timeConverter.convertTime(time);

		StringBuffer expected = new StringBuffer();
		expected.append("Y").append(BerlinClockConstant.NEW_LINE).append("OOOO").append(BerlinClockConstant.NEW_LINE)
				.append("OOOO").append(BerlinClockConstant.NEW_LINE).append("OOOOOOOOOOO")
				.append(BerlinClockConstant.NEW_LINE).append("OOOO");

		assertEquals(expected.toString(), actual);
		logger.debug("test_when_time_is_midnight end");
	}
	
	@Test
	public void test_when_time_is_middle_of_the_afternoon() {
		logger.debug("test_when_time_is_middle_of_the_afternoon start");
		String time="13:17:01";
		String actual = timeConverter.convertTime(time);

		StringBuffer expected = new StringBuffer();
		expected.append("O").append(BerlinClockConstant.NEW_LINE).append("RROO").append(BerlinClockConstant.NEW_LINE)
				.append("RRRO").append(BerlinClockConstant.NEW_LINE).append("YYROOOOOOOO")
				.append(BerlinClockConstant.NEW_LINE).append("YYOO");

		assertEquals(expected.toString(), actual);
		logger.debug("test_when_time_is_middle_of_the_afternoon end");
	}
	
	@Test
	public void test_when_time_is_just_before_midnight() {
		logger.debug("test_when_time_is_just_before_midnight start");
		String time="23:59:59";
		String actual = timeConverter.convertTime(time);

		StringBuffer expected = new StringBuffer();
		expected.append("O").append(BerlinClockConstant.NEW_LINE).append("RRRR").append(BerlinClockConstant.NEW_LINE)
				.append("RRRO").append(BerlinClockConstant.NEW_LINE).append("YYRYYRYYRYY")
				.append(BerlinClockConstant.NEW_LINE).append("YYYY");

		assertEquals(expected.toString(), actual);
		logger.debug("test_when_time_is_just_before_midnight end");
	}
	
	@Test
	public void test_when_time_is_midnight_with_24_hours(){
		logger.debug("test_when_time_is_midnight_with_24_hours start");
		String time="24:00:00";
		String actual = timeConverter.convertTime(time);

		StringBuffer expected = new StringBuffer();
		expected.append("Y").append(BerlinClockConstant.NEW_LINE).append("RRRR").append(BerlinClockConstant.NEW_LINE)
				.append("RRRR").append(BerlinClockConstant.NEW_LINE).append("OOOOOOOOOOO")
				.append(BerlinClockConstant.NEW_LINE).append("OOOO");

		assertEquals(expected.toString(), actual);
		logger.debug("test_when_time_is_midnight_with_24_hours end");
	}
}
