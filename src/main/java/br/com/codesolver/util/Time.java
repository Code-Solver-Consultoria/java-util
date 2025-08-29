package br.com.codesolver.util;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * No Java, utiliza-se a classe Date para gerenciar os horários. Mas, se o dia, mês e ano 
 * não forem realmente iguais, a comparação de horários não será considerada igual.
 *
 * <p>
 * A classe Time serve para gerenciar apenas os horários, sem que se tenha a preocupação com os
 * outros fatores da classe Date.
 *
 * <p>
 * A data base é 1 de Janeiro de 1970 (UTC).
 *
 * @author <a href="mailto:luciano@codesolver.com.br">Luciano Vieira Rodrigues</a>
 * @since 2025-08-26
 */
public class Time implements Serializable {

	/** Valor máximo para segundos. */
	private static final int MAX_SECONDS = 60;

	/** Valor máximo para minutos. */
	private static final int MAX_MINUTES = 60;

	/** Valor máximo para horas. */
	private static final int MAX_HOURS = 24;
	
	/**
	 * Ano base para gerenciamento do horário.
	 */
	private static final int YEAR = 1970;
	
	/**
	 * Mês base para gerenciamento do horário.
	 */
	private static final int MONTH = Calendar.JANUARY;
	
	/**
	 * Dia base para gerenciamento do horário.
	 */
	private static final int DATE = 1;
	
	/**
	 * Expressão regular para pré-validação do horário.
	 */
	private static final Pattern PATTERN = Pattern.compile("[0-2]\\d:[0-5]\\d:[0-5]\\d");

	/**
	 * Calendário padrão para gerenciar os horários.
	 */
	private Calendar calendar;
	
	/**
	 * Construtor padrão, assumindo o horário de criação.
	 */
	public Time() {
		calendar = new GregorianCalendar();
		calendar.set(Calendar.YEAR, YEAR);
		calendar.set(Calendar.MONTH, MONTH);
		calendar.set(Calendar.DATE, DATE);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.setFirstDayOfWeek(Calendar.SUNDAY);
	}

	/**
	 * Construtor atribuindo o horário inicial. Os milisegundos são zerados.
	 *
	 * @param hour Hora, no formato 24 horas.
	 * @param minute Minuto.
	 * @param second Segundo.
	 * @throws TimeRuntimeException Horário informado inválido.
	 */
	public Time(int hour, int minute, int second) {
		this();
		if (second >= MAX_SECONDS) {
			throw new TimeRuntimeException("Segundos devem ser menor que 60.");
		}
		if (minute >= MAX_MINUTES) {
			throw new TimeRuntimeException("Minutos devem ser menor que 60.");
		}
		if (hour >= MAX_HOURS) {
			throw new TimeRuntimeException("Horas devem ser menor que 24.");
		}
		calendar.set(Calendar.SECOND, second);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
	}
	
	/**
	 * Recupera a hora do dia, no formato 24 horas.
	 *
	 * @return int
	 */
	public int getHour() {
		return calendar.get(Calendar.HOUR_OF_DAY);
	}
	
	/**
	 * Recupera os minutos.
	 *
	 * @return int
	 */
	public int getMinute() {
		return calendar.get(Calendar.MINUTE);
	}
	
	/**
	 * Recupera os segundos do horário.
	 *
	 * @return int
	 */
	public int getSecond() {
		return calendar.get(Calendar.SECOND);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	// CHECKSTYLE:OFF
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((calendar == null) ? 0 : calendar.hashCode());
		return result;
	}
	// CHECKSTYLE:ON

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Time other = (Time) obj;
		if (calendar == null) {
			if (other.calendar != null) {
				return false;
			}
		} else if (toString().compareTo(other.toString()) != 0) {
			return false;
		}
		return true;
	}
	
	/**
	 * Recupera um horário baseado em uma String, no formato <b>hh:mm:ss</b>.
	 *
	 * @param value Horário.
	 * @return {@link Time}.
	 */
	public static Time parse(String value) {
		Time result		= null;
		Matcher matcher = PATTERN.matcher(value); 	
		if (matcher.matches()) {
			String[] parts	= value.split(":");
			int hour		= Integer.valueOf(parts[0]);
			int minute		= Integer.valueOf(parts[1]);
			int second		= Integer.valueOf(parts[2]);
			result			= new Time(hour, minute, second);
		} else {
			throw new TimeRuntimeException(
					String.format("%s não tem um formato válido para horário (hh:mm:ss)", 
					value));
		}
		return result;
	}

	@Override
	public String toString() {
		return String.format("%02d:%02d:%02d", getHour(), getMinute(), getSecond());
	}
}
