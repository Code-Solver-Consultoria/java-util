package br.com.codesolver.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.logging.Logger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Teste unitários para tratamento do {@link DateUtil}.
 * 
 * @author <a href="mailto:luciano@codesolver.com.br">Luciano Vieira Rodrigues</a>
 * @since 2025-08-26
 */
public class TestDateUtil {

    /** Log da classe de testes. */
    private static final Logger LOGGER = Logger.getLogger(TestDateUtil.class.getName());

    /** Construtor padrão. */
    public TestDateUtil() {
    }

    /**
	 * Preparando os testes unitários para {@link DateUtil}.
	 */
	@BeforeAll
	public static void before() {
		LOGGER.info("Inicializando os testes unitários para DateUtil.");
	}

	/**
	 * Finalizando os testes unitários para {@link DateUtil}.
	 */
	@AfterAll
	public static void after() {
		LOGGER.info("Finalizando os testes unitários para DateUtil.");
	}

	
    /**
     * Teste para o método {@link DateUtil#truncate(java.util.Date)}.
     */
    @Test
    public void testTruncate() {
        LOGGER.config("Testando DateUtil#truncate(java.util.Date).");
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
     * Teste para o método {@link DateUtil#truncate(java.util.Date)},
     * com data nula.
     */
    @Test
    public void testTruncateNull() {
        LOGGER.config("Testando DateUtil#truncate(java.util.Date) com a data nula.");
        Date result	= DateUtil.truncate(null);
        assertNull(result);;
    }    

    /**
     * Teste para o método {@link DateUtil#addDay(java.util.Date, int)}.
     */
    @Test
    public void testAddDay() {
        LOGGER.config("Testando DateUtil#addDay(java.util.Date, int).");
        Calendar source		= new GregorianCalendar(2014, Calendar.AUGUST, 6);
        Calendar target		= new GregorianCalendar(2014, Calendar.AUGUST, 7);
        Date result			= DateUtil.addDay(source.getTime(), 1);
        Calendar controller	= new GregorianCalendar();
        controller.setTime(result);
        assertEquals(target.get(Calendar.DATE), controller.get(Calendar.DATE));
    }

    /**
     * Teste para o método {@link DateUtil#dateToString(java.util.Date)}.
     */
    @Test
    public void testDateToStringDate() {
        LOGGER.config("Testando DateUtil#dateToString(java.util.Date).");
        Calendar source		= new GregorianCalendar(2013, Calendar.AUGUST, 6);
        String target		= "06/08/2013";
        String result		= DateUtil.dateToString(source.getTime());
        assertTrue(result.equals(target));
    }

    /**
     * Teste para o método {@link DateUtil#dateToString(java.util.Date, java.util.Locale)}.
     */
    @Test
    public void testDateToStringDateLocale() {
        LOGGER.config("Testando DateUtil#dateToString(java.util.Date, java.util.Locale).");
        Calendar source		= new GregorianCalendar(2013, Calendar.AUGUST, 6);
        String target		= "08-06-2013";
        String result		= DateUtil.dateToString(source.getTime(), Locale.US);
        assertTrue(result.equals(target));
    }
    
    /**
     * Teste para o método {@link DateUtil#dateToString(java.util.Date, java.util.Locale)},
     * com um Locale inválido.
     */
    @Test
    public void testDateToStringDateLocaleInvalid() {
        LOGGER.config("Testando DateUtil#dateToString(java.util.Date, java.util.Locale) com Locale inválido.");
        Calendar source		= new GregorianCalendar(2013, Calendar.AUGUST, 6);
        String target		= "06/08/2013";
        String result		= DateUtil.dateToString(source.getTime(), Locale.CHINA);
        assertTrue(result.equals(target));
    }
    
    /**
     * Teste para o método {@link DateUtil#stringToDate(String)}.
     *
     * @throws Exception Falha no teste.
     */
    @Test
    public void testStringToDateString() throws Exception {
        LOGGER.config("Testando DateUtil#stringToDate(String).");
    	Calendar target	= new GregorianCalendar(2014, Calendar.JANUARY, 30, 0, 0, 0);
    	String source	= "30/01/2014";
    	Date result		= DateUtil.stringToDate(source);
    	assertEquals(result, target.getTime());
    }
    
    /**
     * Teste para o método {@link DateUtil#stringToDate(String, Locale)}.
     *
     * @throws Exception Falha no teste.
     */
    @Test
    public void testStringToDateStringLocale() throws Exception {
        LOGGER.config("Testando DateUtil#stringToDate(String, Locale).");
    	Calendar target	= new GregorianCalendar(2014, Calendar.JANUARY, 30, 0, 0, 0);
    	String source	= "01-30-2014";
    	Date result		= DateUtil.stringToDate(source, Locale.US);
    	assertEquals(result, target.getTime());
    }
    
    /**
     * Teste para o método {@link DateUtil#stringToDate(String, Locale)}.
     *
     * @throws Exception Falha no teste.
     */
    @Test
    public void testStringToDateStringLocaleInvalid() throws Exception {
        LOGGER.config("Testando DateUtil#stringToDate(String, Locale).");
    	Calendar target	= new GregorianCalendar(2014, Calendar.JANUARY, 30, 0, 0, 0);
    	String source	= "30/01/2014";
    	Date result		= DateUtil.stringToDate(source, Locale.CHINA);
    	assertEquals(result, target.getTime());
    }      

    /**
     * Teste para o método {@link DateUtil#dateTimeToString(java.util.Date)}.
     */
    @Test
    public void testDateTimeToStringDate() {
        LOGGER.config("Testando DateUtil#dateTimeToString(java.util.Date).");
        Calendar source		= new GregorianCalendar(2013, Calendar.AUGUST, 6, 15, 04, 13);
        String target		= "06/08/2013 15:04:13";
        String result		= DateUtil.dateTimeToString(source.getTime());
        assertTrue(result.equals(target));
    }

    /**
     * Teste para o método {@link DateUtil#dateTimeToString(java.util.Date, java.util.Locale)}.
     */
    @Test
    public void testDateTimeToStringDateLocale() {
        LOGGER.config("Testando DateUtil#dateTimeToString(java.util.Date, java.util.Locale).");
        Calendar source		= new GregorianCalendar(2013, Calendar.AUGUST, 6, 15, 04, 13);
        String target		= "08-06-2013 03:04:13 PM";
        String result		= DateUtil.dateTimeToString(source.getTime(), Locale.US);
        assertTrue(result.equals(target));
    }
    
    /**
     * Teste para o método {@link DateUtil#dateTimeToString(java.util.Date, java.util.Locale)}
     * com um Locale inválido.
     */
    @Test
    public void testDateTimeToStringDateLocaleInvalid() {
        LOGGER.config("Testando DateUtil#dateTimeToString(java.util.Date, java.util.Locale) com Locale inválido.");
        Calendar source		= new GregorianCalendar(2013, Calendar.AUGUST, 6, 15, 04, 13);
        String target		= "06/08/2013 15:04:13";
        String result		= DateUtil.dateTimeToString(source.getTime(), Locale.GERMANY);
        assertTrue(result.equals(target));
    }    

    /**
     * Teste para o método {@link DateUtil#stringToDateTime(String)}.
     *
     * @throws Exception Falha no teste.
     */
    @Test
    public void testStringToDateTimeString() throws Exception {
        LOGGER.config("Testando DateUtil#stringToDateTime(String).");
    	Calendar target	= new GregorianCalendar(2014, Calendar.JANUARY, 30, 15, 27, 34);
    	String source	= "30/01/2014 15:27:34";
    	Date result		= DateUtil.stringToDateTime(source);
    	assertEquals(result, target.getTime());
    }
    
    /**
     * Teste para o método {@link DateUtil#stringToDateTime(String, Locale)}.
     *
     * @throws Exception Falha no teste.
     */
    @Test
    public void testStringToDateTimeStringLocale() throws Exception {
        LOGGER.config("Testando DateUtil#stringToDateTime(String, Locale).");
    	Calendar target	= new GregorianCalendar(2014, Calendar.JANUARY, 30, 15, 27, 34);
    	String source	= "01-30-2014 03:27:34 PM";
    	Date result		= DateUtil.stringToDateTime(source, Locale.US);
    	assertEquals(result, target.getTime());
    }
    
    /**
     * Teste para o método {@link DateUtil#stringToDateTime(String, Locale)}.
     *
     * @throws Exception Falha no teste.
     */
    @Test
    public void testStringToDateTimeStringLocaleInvalid() throws Exception {
        LOGGER.config("Testando DateUtil#stringToDateTime(String, Locale).");
    	Calendar target	= new GregorianCalendar(2014, Calendar.JANUARY, 30, 15, 27, 34);
    	String source	= "30/01/2014 15:27:34";
    	Date result		= DateUtil.stringToDateTime(source, Locale.CHINA);
    	assertEquals(result, target.getTime());
    }
    
    /**
     * Teste para o método {@link DateUtil#isWeekend(java.util.Date)}
     * para sábado.
     */
    @Test
    public void testIsWeekendSaturday() {
        LOGGER.config("Testando {@link DateUtil#isWeekend(java.util.Date)} para sábado.");
        Calendar source	= new GregorianCalendar(2013, Calendar.AUGUST, 10);
        boolean result	= DateUtil.isWeekend(source.getTime());
        assertTrue(result);
    }
    
    /**
     * Teste para o método {@link DateUtil#isWeekend(java.util.Date)}
     * para domingo
     */
    @Test
    public void testIsWeekendSunday() {
        LOGGER.config("Testando {@link DateUtil#isWeekend(java.util.Date)} para domingo.");
        Calendar source	= new GregorianCalendar(2013, Calendar.AUGUST, 11);
        boolean result	= DateUtil.isWeekend(source.getTime());
        assertTrue(result);
    }
    
    /**
     * Teste para o método {@link DateUtil#isWeekend(java.util.Date)}
     * para dias normais.
     */
    @Test
    public void testIsWeekendFalse() {
        LOGGER.config("Testando {@link DateUtil#isWeekend(java.util.Date)} para dias normais.");
        Calendar source	= new GregorianCalendar(2013, Calendar.AUGUST, 6);
        boolean result	= DateUtil.isWeekend(source.getTime());
        assertFalse(result);
    }

    /**
     * Teste para o método {@link DateUtil#milisBetween(java.util.Date, java.util.Date)}.
     *
     * @throws Exception Falha no teste.
     */
    @Test
    public void testMilisBetween() throws Exception {
        LOGGER.config("Testando DateUtil#milisBetween(java.util.Date, java.util.Date).");
    	Calendar start 	= new GregorianCalendar();
    	Calendar finish	= new GregorianCalendar();
    	finish.setTime(start.getTime());
    	finish.add(Calendar.MILLISECOND, 123);
        long result	= DateUtil.milisBetween(start.getTime(), finish.getTime());
        assertEquals(123, result);
    }
    
    /**
     * Teste para o método {@link DateUtil#milisBetween(java.util.Date, java.util.Date)}
     * com data final menor que a data inicial.
     *
     * @throws Exception Falha no teste.
     */
    @Test
    public void testMilisBetweenInvalid() throws Exception {
        LOGGER.config("Testando DateUtil#milisBetween(java.util.Date, java.util.Date) com data final menor que a data inicial.");
    	Calendar start 	= new GregorianCalendar();
    	Calendar finish	= new GregorianCalendar();
    	finish.setTime(start.getTime());
    	finish.add(Calendar.MILLISECOND, 123);
        long result	= DateUtil.milisBetween(finish.getTime(), start.getTime());
        assertEquals(0, result);
    }

    /**
     * Teste para o método {@link DateUtil#secondsBetween(java.util.Date, java.util.Date)}.
     *
     * @throws Exception Falha no teste.
     */
    @Test
    public void testSecondsBetween() throws Exception {
        LOGGER.config("Testando DateUtil#secondsBetween(java.util.Date, java.util.Date).");
        Date start	= new GregorianCalendar(2013, Calendar.AUGUST, 6, 15, 17, 06).getTime();
        Date finish	= new GregorianCalendar(2013, Calendar.AUGUST, 6, 15, 17, 16).getTime();
        long result	= DateUtil.secondsBetween(start, finish);
        assertEquals(10, result);
    }
    
    /**
     * Teste para o método {@link DateUtil#secondsBetween(java.util.Date, java.util.Date)}
     * sem segundos significativos para cálculo.
     *
     * @throws Exception Falha no teste.
     */
    @Test
    public void testSecondsBetweenInvalid() throws Exception {
        LOGGER.config("Testando DateUtil#secondsBetween(java.util.Date, java.util.Date) sem segundos significativos para cálculo.");
        Date start	= new GregorianCalendar(2013, Calendar.AUGUST, 6, 15, 17, 06).getTime();
        Date finish	= new GregorianCalendar(2013, Calendar.AUGUST, 6, 15, 17, 06).getTime();
        long result	= DateUtil.secondsBetween(start, finish);
        assertEquals(0, result);
    }    

    /**
     * Teste para o método {@link DateUtil#minutesBetween(java.util.Date, java.util.Date)}.
     */
    @Test
    public void testMinutesBetween() {
        LOGGER.config("Testando DateUtil#minutesBetween(java.util.Date, java.util.Date).");
        Date start	= new GregorianCalendar(2013, Calendar.AUGUST, 6, 15, 17, 06).getTime();
        Date finish	= new GregorianCalendar(2013, Calendar.AUGUST, 6, 15, 18, 16).getTime();
        long result	= DateUtil.minutesBetween(start, finish);
        assertEquals(1, result);
    }
    
    /**
     * Teste para o método {@link DateUtil#minutesBetween(java.util.Date, java.util.Date)}
     * sem minutos significativos para cálculo.
     */
    @Test
    public void testMinutesBetweenInvalid() {
        LOGGER.config("Testando DateUtil#minutesBetween(java.util.Date, java.util.Date) sem minutos significativos para cálculo.");
        Date start	= new GregorianCalendar(2013, Calendar.AUGUST, 6, 15, 17, 06).getTime();
        Date finish	= new GregorianCalendar(2013, Calendar.AUGUST, 6, 15, 17, 16).getTime();
        long result	= DateUtil.minutesBetween(start, finish);
        assertEquals(0, result);
    }    

    /**
     * Teste para o método {@link DateUtil#hoursBetween(java.util.Date, java.util.Date)}.
     */
    @Test
    public void testHoursBetween() {
        LOGGER.config("Testando DateUtil#hoursBetween(java.util.Date, java.util.Date).");
        Date start	= new GregorianCalendar(2013, Calendar.AUGUST, 6, 15, 17, 06).getTime();
        Date finish	= new GregorianCalendar(2013, Calendar.AUGUST, 6, 18, 18, 16).getTime();
        long result	= DateUtil.hoursBetween(start, finish);
        assertEquals(3, result);
    }
    
    /**
     * Teste para o método {@link DateUtil#hoursBetween(java.util.Date, java.util.Date)}
     * sem horas significativas para cálculo.
     */
    @Test
    public void testHoursBetweenInvalid() {
        LOGGER.config("Testando DateUtil#hoursBetween(java.util.Date, java.util.Date) sem horas significativas para cálculo.");
        Date start	= new GregorianCalendar(2013, Calendar.AUGUST, 6, 15, 17, 06).getTime();
        Date finish	= new GregorianCalendar(2013, Calendar.AUGUST, 6, 15, 18, 16).getTime();
        long result	= DateUtil.hoursBetween(start, finish);
        assertEquals(0, result);
    }    

    /**
     * Teste para o método {@link DateUtil#daysBetween(java.util.Date, java.util.Date)}.
     */
    @Test
    public void testDaysBetween() {
        LOGGER.config("Testando DateUtil#daysBetween(java.util.Date, java.util.Date).");
        Date start	= new GregorianCalendar(2013, Calendar.AUGUST, 6, 15, 17, 06).getTime();
        Date finish	= new GregorianCalendar(2013, Calendar.AUGUST, 10, 18, 18, 16).getTime();
        long result	= DateUtil.daysBetween(start, finish);
        assertEquals(4, result);
    }
    
    /**
     * Teste para o método {@link DateUtil#daysBetween(java.util.Date, java.util.Date)}
     * sem dias significativos para cálculo.
     */
    @Test
    public void testDaysBetweenInvalid() {
        LOGGER.config("Testando DateUtil#daysBetween(java.util.Date, java.util.Date) sem dias significativos para cálculo..");
        Date start	= new GregorianCalendar(2013, Calendar.AUGUST, 6, 15, 17, 06).getTime();
        Date finish	= new GregorianCalendar(2013, Calendar.AUGUST, 6, 18, 18, 16).getTime();
        long result	= DateUtil.daysBetween(start, finish);
        assertEquals(0, result);
    }    
}
