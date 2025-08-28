package br.com.codesolver.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * Testes unitários para a classe {@link Time}
 * 
 * @author <a href="mailto:luciano@codesolver.com.br">Luciano Vieira Rodrigues</a>
 * @since 2025-08-26
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestTime {
	
	private static Time test;

	@Test
	@Order(0)
	public void testTime() {
		Time test = new Time();
		assertNotNull(test);
	}

	@Test
	@Order(1)
	public void testTimeIntIntInt() {
		test = new Time(12, 30, 0);
	}

	@Test
	@Order(2)
	public void testGetHour() {
		assertEquals(test.getHour(), 12);
	}

	@Test
	@Order(2)
	public void testGetMinute() {
		assertEquals(test.getMinute(), 30);
	}

	@Test
	@Order(2)
	public void testGetSecond() {
		assertEquals(test.getSecond(), 0);
	}

	@Test
	@Order(2)
	public void testParse() {
		Time other = Time.parse("12:30:00");
		assertNotNull(other);
		assertEquals(other, test);
	}

	@Test
	@Order(2)
	public void testToString() {
		assertEquals(test.toString(), "12:30:00");
	}

	@Test
	@Order(2)
	public void testEqualsObject() {
		Time other = new Time(12, 30, 0);
		assertTrue(test.equals(other));
	}
}
