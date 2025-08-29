package br.com.codesolver.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Manipulação de Datas.
 *
 * @author <a href="mailto:luciano@codesolver.com.br">Luciano Vieira
 *         Rodrigues</a>
 * @since 2025-08-26
 */
public final class DateUtil {

    /** Total de milisegundos em 1 segundo. */
    private static final int SECOND_IN_MILIS = 1000;

    /** Total de segundos em 1 minuto. */
    private static final int MINUTE_IN_SECONDS = 60;

    /** Total de minutos em 1 hora. */
    private static final int HOUR_IN_MINUTES = 60;

    /** Total de horas em 1 dia. */
    private static final int DAY_IN_HOURS = 24;

    /** Formatos reconhecidos de datas. */
    private static Map<Locale, String> dateFormats = null;

    /** Formatos reconhecidos de datas e horas. */
    private static Map<Locale, String> dateTimeFormats = null;

    /** Formato padrão para regiões desconhecias. */
    private static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";

    /** Formato padrão para regiões desconhecidas. */
    private static final String DEFAULT_DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";

    // Inicializando as configuraçãoes de data para Brasil e Estados Unidos.
    static {
        Locale.setDefault(Locale.of("pt", "BR"));

        dateFormats = new HashMap<Locale, String>();
        dateFormats.put(Locale.getDefault(), DEFAULT_DATE_FORMAT);
        dateFormats.put(Locale.US, "MM-dd-yyyy");

        dateTimeFormats = new HashMap<Locale, String>();
        dateTimeFormats.put(Locale.getDefault(), DEFAULT_DATE_TIME_FORMAT);
        dateTimeFormats.put(Locale.US, "MM-dd-yyyy hh:mm:ss a");
    }

    /** Construtor oculto. */
    private DateUtil() {
    }

    /**
     * Mapa de chave e valor contento as localizadas e seus formatos de data.
     *
     * @return Mapa de formatos.
     */
    private static Map<Locale, String> getDateFormats() {
        return dateFormats;
    }

    /**
     * Mapa de chave valor contendo as localidades e seus formatos de data e hora.
     *
     * @return Mapa de formatos.
     */
    private static Map<Locale, String> getDateTimeFormats() {
        return dateTimeFormats;
    }

    /**
     * Remove o horário de uma data.
     *
     * @param date {@link Date}.
     * @return {@link Date}.
     */
    public static Date truncate(Date date) {
        Date result = null;
        if (date != null) {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            calendar.set(Calendar.AM_PM, Calendar.AM);
            result = calendar.getTime();
        }
        return result;
    }

    /**
     * Adiciona dias em uma data informada.
     *
     * @param date  Data base.
     * @param value Dias para adicionar.
     * @return {@link Date}.
     */
    public static Date addDay(Date date, int value) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, value);
        return calendar.getTime();
    }

    /**
     * Transforma uma data em sua representação de caracteres para o idioma padrão.
     *
     * @param date {@link Date}.
     * @return Data no formato de texto, formatado para a localidade padrão.
     */
    public static String dateToString(Date date) {
        return dateToString(date, Locale.getDefault());
    }

    /**
     * Transforma uma data em sua representação de caracteres pra o idioma
     * informado.
     *
     * @param date   {@link Date}.
     * @param locale {@link Locale}.
     * @return {@link String}.
     */
    public static String dateToString(Date date, Locale locale) {
        String dateFormat = getDateFormats().get(locale);
        if (dateFormat == null) {
            dateFormat = DEFAULT_DATE_FORMAT;
        }
        return new SimpleDateFormat(dateFormat).format(date);
    }

    /**
     * Transforma um sequência de caracteres para uma data no idioma padrão.
     *
     * @param value Texto representando uma data.
     * @return {@link Date}.
     * @throws ParseException Não foi possível interpretar o valor.
     */
    public static Date stringToDate(String value) throws ParseException {
        return stringToDate(value, Locale.getDefault());
    }

    /**
     * Transforma um sequência de caracteres para uma data no idioma informado.
     *
     * @param value  Texto representando uma data.
     * @param locale {@link Locale}.
     * @return {@link Date}.
     * @throws ParseException Não foi possível interpretar o valor.
     */
    public static Date stringToDate(String value, Locale locale) throws ParseException {
        String dateFormat = getDateFormats().get(locale);
        if (dateFormat == null) {
            dateFormat = DEFAULT_DATE_FORMAT;
        }
        return new SimpleDateFormat(dateFormat).parse(value);
    }

    /**
     * Transforma uma data e hora em sua representação de caracteres para o idioma
     * padrão.
     *
     * @param date {@link Date}.
     * @return Data no formato de texto da localidade padrão.
     */
    public static String dateTimeToString(Date date) {
        return dateTimeToString(date, Locale.getDefault());
    }

    /**
     * Transforma uma data e hora em sua representação de caracteres para o idioma
     * informado.
     *
     * @param date   {@link Date}.
     * @param locale {@link Locale}.
     * @return {@link String}.
     */
    public static String dateTimeToString(Date date, Locale locale) {
        String dateFormat = getDateTimeFormats().get(locale);
        if (dateFormat == null) {
            dateFormat = DEFAULT_DATE_TIME_FORMAT;
        }
        return new SimpleDateFormat(dateFormat).format(date);
    }

    /**
     * Transforma uma representação de caracteres para uma data e hora no idioma
     * informado.
     *
     * @param value Representação em texto de uma data e hora.
     * @return {@link Date}.
     * @throws ParseException Não foi possível interpretar o valor.
     */
    public static Date stringToDateTime(String value) throws ParseException {
        return stringToDateTime(value, Locale.getDefault());
    }

    /**
     * Transforma uma representação de caracteres para uma data e hora no idioma
     * informado.
     *
     * @param value  Representação em texto de uma data e hora.
     * @param locale {@link Locale}.
     * @return {@link Date}.
     * @throws ParseException Valor informado não pode ser interpretado.
     */
    public static Date stringToDateTime(String value, Locale locale) throws ParseException {
        String dateFormat = getDateTimeFormats().get(locale);
        if (dateFormat == null) {
            dateFormat = DEFAULT_DATE_TIME_FORMAT;
        }
        return new SimpleDateFormat(dateFormat).parse(value);
    }

    /**
     * Verifica se uma data refere-se a um final de semana.
     *
     * @param date {@link Date}.
     * @return Verdadeiro ou false.
     */
    public static boolean isWeekend(Date date) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY
                || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
    }

    /**
     * Calcula a diferença de milisegundos entre duas datas, onde a data inicial
     * deve ser menor que a data final.
     *
     * @param begin  {@link Date} inicial.
     * @param finish {@link Date} final.
     * @return Diferença em milisegundos entre as datas.
     */
    public static long milisBetween(Date begin, Date finish) {
        long result = 0;
        if (begin.before(finish)) {
            result = finish.getTime() - begin.getTime();
        }
        return result;
    }

    /**
     * Calcula a diferença de segundos entre duas datas, onde a data inicial
     * deve ser menor que a data final.
     *
     * @param begin  {@link Date} inicial.
     * @param finish {@link Date} final.
     * @return Diferença em segundos entre as datas.
     */
    public static long secondsBetween(Date begin, Date finish) {
        Calendar cbegin     = new GregorianCalendar();
        Calendar cfinish    = new GregorianCalendar();

        cbegin.setTime(begin);
        cbegin.set(Calendar.MILLISECOND, 0);
        cfinish.setTime(finish);
        cfinish.set(Calendar.MILLISECOND, 0);

        long difference = milisBetween(cbegin.getTime(), cfinish.getTime());
        long result = 0;
        if (difference >= SECOND_IN_MILIS) {
            result = difference / SECOND_IN_MILIS;
        }
        return result;
    }

    /**
     * Calcula a diferença de minutos entre duas datas, onde a data inicial
     * deve ser menor que a data final.
     *
     * @param begin  {@link Date} inicial.
     * @param finish {@link Date} final.
     * @return Diferença em minutos entre as datas.
     */
    public static long minutesBetween(Date begin, Date finish) {
        long difference = secondsBetween(begin, finish);
        long result = 0;
        if (difference >= MINUTE_IN_SECONDS) {
            result = difference / MINUTE_IN_SECONDS;
        }
        return result;
    }

    /**
     * Calcula a diferença de horas entre duas datas, onde a data inicial
     * deve ser menor que a data final.
     *
     * @param begin  {@link Date} inicial.
     * @param finish {@link Date} final.
     * @return Diferença em horas entre as datas.
     */
    public static long hoursBetween(Date begin, Date finish) {
        long difference = minutesBetween(begin, finish);
        long result = 0;
        if (difference >= HOUR_IN_MINUTES) {
            result = difference / HOUR_IN_MINUTES;
        }
        return result;
    }

    /**
     * Calcula a diferença de dias entre duas datas, onde a data inicial
     * deve ser menor que a data final.
     *
     * @param begin  {@link Date} inicial.
     * @param finish {@link Date} final.
     * @return Diferença em dias entre as datas.
     */
    public static long daysBetween(Date begin, Date finish) {
        long difference = hoursBetween(begin, finish);
        long result = 0;
        if (difference >= DAY_IN_HOURS) {
            result = difference / DAY_IN_HOURS;
        }
        return result;
    }
}
