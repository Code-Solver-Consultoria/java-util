package br.com.codesolver.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.junit.jupiter.api.Test;

/**
 * Teste unitários para tratamento do {@link br.com.codesolver.util.DateUtil}.
 * 
 * @author <a href="mailto:luciano@codesolver.com.br">Luciano Vieira Rodrigues</a>
 * @since 2025-08-26
 */
public class TestDateUtil {
	
    /**
     * Test method for {@link br.com.codesolver.util.DateUtil#truncate(java.util.Date)}.
     */
    @Test
    public void testTruncate() {
        Calendar source		= new GregorianCalendar();
        Date result			= DateUtil.truncate(source.getTime());
        Calendar controller	=  new GregorianCalendar();
        controller.setTime(result);

        assertEquals(source.get(Calendar.YEAR), controller.get(Calendar.YEAR));
        assertEquals(source.get(Calendar.MONTH), controller.get(Calendar.MONTH));
        assertEquals(source.get(Calendar.DATE), controller.get(Calendar.DATE));
        assertEquals(0, controller.get(Calendar.HOUR));
        assertEquals(0, controller.get(Calendar.MINUTE));
        assertEquals(0, controller.get(Calendar.SECOND));
        assertEquals(0, controller.get(Calendar.MILLISECOND));
        assertEquals(Calendar.AM, controller.get(Calendar.AM_PM));
    }
    
    /**
     * Test method for {@link br.com.codesolver.util.DateUtil#truncate(java.util.Date)},
     * com data nula.
     */
    @Test
    public void testTruncateNull() {
        Date result	= DateUtil.truncate(null);
        assertNull(result);;
    }    

    /**
     * Test method for {@link br.com.codesolver.util.DateUtil#addDay(java.util.Date, int)}.
     */
    @Test
    public void testAddDay() {
        Calendar source		= new GregorianCalendar(2014, Calendar.AUGUST, 6);
        Calendar target		= new GregorianCalendar(2014, Calendar.AUGUST, 7);
        Date result			= DateUtil.addDay(source.getTime(), 1);
        Calendar controller	= new GregorianCalendar();
        controller.setTime(result);
        assertEquals(target.get(Calendar.DATE), controller.get(Calendar.DATE));
    }

    /**
     * Test method for {@link br.com.codesolver.util.DateUtil#dateToString(java.util.Date)}.
     */
    @Test
    public void testDateToStringDate() {
        Calendar source		= new GregorianCalendar(2013, Calendar.AUGUST, 6);
        String target		= "06/08/2013";
        String result		= DateUtil.dateToString(source.getTime());
        assertTrue(result.equals(target));
    }

    /**
     * Test method for {@link br.com.codesolver.util.DateUtil#dateToString(java.util.Date, java.util.Locale)}.
     */
    @Test
    public void testDateToStringDateLocale() {
        Calendar source		= new GregorianCalendar(2013, Calendar.AUGUST, 6);
        String target		= "08-06-2013";
        String result		= DateUtil.dateToString(source.getTime(), Locale.US);
        assertTrue(result.equals(target));
    }
    
    /**
     * Test method for {@link br.com.codesolver.util.DateUtil#dateToString(java.util.Date, java.util.Locale)},
     * com um Locale inválido.
     */
    @Test
    public void testDateToStringDateLocaleInvalid() {
        Calendar source		= new GregorianCalendar(2013, Calendar.AUGUST, 6);
        String target		= "06/08/2013";
        String result		= DateUtil.dateToString(source.getTime(), Locale.CHINA);
        assertTrue(result.equals(target));
    }
    
    /**
     * Teste method for {@link DateUtil#stringToDate(String)}
     */
    @Test
    public void testStringToDateString() throws Exception {
    	Calendar target	= new GregorianCalendar(2014, Calendar.JANUARY, 30, 0, 0, 0);
    	String source	= "30/01/2014";
    	Date result		= DateUtil.stringToDate(source);
    	assertEquals(result, target.getTime());
    }
    
    /**
     * Teste method for {@link DateUtil#stringToDate(String, Locale)}
     */
    @Test
    public void testStringToDateStringLocale() throws Exception {
    	Calendar target	= new GregorianCalendar(2014, Calendar.JANUARY, 30, 0, 0, 0);
    	String source	= "01-30-2014";
    	Date result		= DateUtil.stringToDate(source, Locale.US);
    	assertEquals(result, target.getTime());
    }
    
    /**
     * Teste method for {@link DateUtil#stringToDate(String, Locale)}
     */
    @Test
    public void testStringToDateStringLocaleInvalid() throws Exception {
    	Calendar target	= new GregorianCalendar(2014, Calendar.JANUARY, 30, 0, 0, 0);
    	String source	= "30/01/2014";
    	Date result		= DateUtil.stringToDate(source, Locale.CHINA);
    	assertEquals(result, target.getTime());
    }      

    /**
     * Test method for {@link br.com.codesolver.util.DateUtil#dateTimeToString(java.util.Date)}.
     */
    @Test
    public void testDateTimeToStringDate() {
        Calendar source		= new GregorianCalendar(2013, Calendar.AUGUST, 6, 15, 04, 13);
        String target		= "06/08/2013 15:04:13";
        String result		= DateUtil.dateTimeToString(source.getTime());
        assertTrue(result.equals(target));
    }

    /**
     * Test method for {@link br.com.codesolver.util.DateUtil#dateTimeToString(java.util.Date, java.util.Locale)}.
     */
    @Test
    public void testDateTimeToStringDateLocale() {
        Calendar source		= new GregorianCalendar(2013, Calendar.AUGUST, 6, 15, 04, 13);
        String target		= "08-06-2013 03:04:13 PM";
        String result		= DateUtil.dateTimeToString(source.getTime(), Locale.US);
        assertTrue(result.equals(target));
    }
    
    /**
     * Test method for {@link br.com.codesolver.util.DateUtil#dateTimeToString(java.util.Date, java.util.Locale)},
     * com um Locale inválido.
     */
    @Test
    public void testDateTimeToStringDateLocaleInvalid() {
        Calendar source		= new GregorianCalendar(2013, Calendar.AUGUST, 6, 15, 04, 13);
        String target		= "06/08/2013 15:04:13";
        String result		= DateUtil.dateTimeToString(source.getTime(), Locale.GERMANY);
        assertTrue(result.equals(target));
    }    

    /**
     * Teste method for {@link DateUtil#stringToDateTime(String)}
     */
    @Test
    public void testStringToDateTimeString() throws Exception {
    	Calendar target	= new GregorianCalendar(2014, Calendar.JANUARY, 30, 15, 27, 34);
    	String source	= "30/01/2014 15:27:34";
    	Date result		= DateUtil.stringToDateTime(source);
    	assertEquals(result, target.getTime());
    }
    
    /**
     * Teste method for {@link DateUtil#stringToDateTime(String, Locale)}
     */
    @Test
    public void testStringToDateTimeStringLocale() throws Exception {
    	Calendar target	= new GregorianCalendar(2014, Calendar.JANUARY, 30, 15, 27, 34);
    	String source	= "01-30-2014 03:27:34 PM";
    	Date result		= DateUtil.stringToDateTime(source, Locale.US);
    	assertEquals(result, target.getTime());
    }
    
    /**
     * Teste method for {@link DateUtil#stringToDateTime(String, Locale)}
     */
    @Test
    public void testStringToDateTimeStringLocaleInvalid() throws Exception {
    	Calendar target	= new GregorianCalendar(2014, Calendar.JANUARY, 30, 15, 27, 34);
    	String source	= "30/01/2014 15:27:34";
    	Date result		= DateUtil.stringToDateTime(source, Locale.CHINA);
    	assertEquals(result, target.getTime());
    }
    
    /**
     * Test method for {@link br.com.codesolver.util.DateUtil#isWeekend(java.util.Date)},
     * para sábado.
     */
    @Test
    public void testIsWeekendSaturday() {
        Calendar source	= new GregorianCalendar(2013, Calendar.AUGUST, 10);
        boolean result	= DateUtil.isWeekend(source.getTime());
        assertTrue(result);
    }
    
    /**
     * Test method for {@link br.com.codesolver.util.DateUtil#isWeekend(java.util.Date)},
     * para domingo
     */
    @Test
    public void testIsWeekendSunday() {
        Calendar source	= new GregorianCalendar(2013, Calendar.AUGUST, 11);
        boolean result	= DateUtil.isWeekend(source.getTime());
        assertTrue(result);
    }
    
    /**
     * Test method for {@link br.com.codesolver.util.DateUtil#isWeekend(java.util.Date)},
     * para dias normais.
     */
    @Test
    public void testIsWeekendFalse() {
        Calendar source	= new GregorianCalendar(2013, Calendar.AUGUST, 6);
        boolean result	= DateUtil.isWeekend(source.getTime());
        assertFalse(result);
    }

    /**
     * Test method for {@link br.com.codesolver.util.DateUtil#milisBetween(java.util.Date, java.util.Date)}.
     * @throws InterruptedException
     */
    @Test
    public void testMilisBetween() throws InterruptedException {
    	Calendar start 	= new GregorianCalendar();
    	Calendar finish	= new GregorianCalendar();
    	finish.setTime(start.getTime());
    	finish.add(Calendar.MILLISECOND, 123);
        long result	= DateUtil.milisBetween(start.getTime(), finish.getTime());
        assertEquals(123, result);
    }
    
    /**
     * Test method for {@link br.com.codesolver.util.DateUtil#milisBetween(java.util.Date, java.util.Date)},
     * com data final menor que a data inicial.
     * @throws InterruptedException
     */
    @Test
    public void testMilisBetweenInvalid() throws InterruptedException {
    	Calendar start 	= new GregorianCalendar();
    	Calendar finish	= new GregorianCalendar();
    	finish.setTime(start.getTime());
    	finish.add(Calendar.MILLISECOND, 123);
        long result	= DateUtil.milisBetween(finish.getTime(), start.getTime());
        assertEquals(0, result);
    }

    /**
     * Test method for {@link br.com.codesolver.util.DateUtil#secondsBetween(java.util.Date, java.util.Date)}.
     * @throws InterruptedException
     */
    @Test
    public void testSecondsBetween() throws InterruptedException {
        Date start	= new GregorianCalendar(2013, Calendar.AUGUST, 6, 15, 17, 06).getTime();
        Date finish	= new GregorianCalendar(2013, Calendar.AUGUST, 6, 15, 17, 16).getTime();
        long result	= DateUtil.secondsBetween(start, finish);
        assertEquals(10, result);
    }
    
    /**
     * Test method for {@link br.com.codesolver.util.DateUtil#secondsBetween(java.util.Date, java.util.Date)},
     * sem segundos significativos para cálculo.
     * @throws InterruptedException
     */
    @Test
    public void testSecondsBetweenInvalid() throws InterruptedException {
        Date start	= new GregorianCalendar(2013, Calendar.AUGUST, 6, 15, 17, 06).getTime();
        Date finish	= new GregorianCalendar(2013, Calendar.AUGUST, 6, 15, 17, 06).getTime();
        long result	= DateUtil.secondsBetween(start, finish);
        assertEquals(0, result);
    }    

    /**
     * Test method for {@link br.com.codesolver.util.DateUtil#minutesBetween(java.util.Date, java.util.Date)}.
     */
    @Test
    public void testMinutesBetween() {
        Date start	= new GregorianCalendar(2013, Calendar.AUGUST, 6, 15, 17, 06).getTime();
        Date finish	= new GregorianCalendar(2013, Calendar.AUGUST, 6, 15, 18, 16).getTime();
        long result	= DateUtil.minutesBetween(start, finish);
        assertEquals(1, result);
    }
    
    /**
     * Test method for {@link br.com.codesolver.util.DateUtil#minutesBetween(java.util.Date, java.util.Date)},
     * sem minutos significativos para cálculo
     */
    @Test
    public void testMinutesBetweenInvalid() {
        Date start	= new GregorianCalendar(2013, Calendar.AUGUST, 6, 15, 17, 06).getTime();
        Date finish	= new GregorianCalendar(2013, Calendar.AUGUST, 6, 15, 17, 16).getTime();
        long result	= DateUtil.minutesBetween(start, finish);
        assertEquals(0, result);
    }    

    /**
     * Test method for {@link br.com.codesolver.util.DateUtil#hoursBetween(java.util.Date, java.util.Date)}.
     */
    @Test
    public void testHoursBetween() {
        Date start	= new GregorianCalendar(2013, Calendar.AUGUST, 6, 15, 17, 06).getTime();
        Date finish	= new GregorianCalendar(2013, Calendar.AUGUST, 6, 18, 18, 16).getTime();
        long result	= DateUtil.hoursBetween(start, finish);
        assertEquals(3, result);
    }
    
    /**
     * Test method for {@link br.com.codesolver.util.DateUtil#hoursBetween(java.util.Date, java.util.Date)},
     * sem horas significativas para cálculo
     */
    @Test
    public void testHoursBetweenInvalid() {
        Date start	= new GregorianCalendar(2013, Calendar.AUGUST, 6, 15, 17, 06).getTime();
        Date finish	= new GregorianCalendar(2013, Calendar.AUGUST, 6, 15, 18, 16).getTime();
        long result	= DateUtil.hoursBetween(start, finish);
        assertEquals(0, result);
    }    

    /**
     * Test method for {@link br.com.codesolver.util.DateUtil#daysBetween(java.util.Date, java.util.Date)}.
     */
    @Test
    public void testDaysBetween() {
        Date start	= new GregorianCalendar(2013, Calendar.AUGUST, 6, 15, 17, 06).getTime();
        Date finish	= new GregorianCalendar(2013, Calendar.AUGUST, 10, 18, 18, 16).getTime();
        long result	= DateUtil.daysBetween(start, finish);
        assertEquals(4, result);
    }
    
    /**
     * Test method for {@link br.com.codesolver.util.DateUtil#daysBetween(java.util.Date, java.util.Date)},
     * sem dias significativos para cálculo.
     */
    @Test
    public void testDaysBetweenInvalid() {
        Date start	= new GregorianCalendar(2013, Calendar.AUGUST, 6, 15, 17, 06).getTime();
        Date finish	= new GregorianCalendar(2013, Calendar.AUGUST, 6, 18, 18, 16).getTime();
        long result	= DateUtil.daysBetween(start, finish);
        assertEquals(0, result);
    }    
}
