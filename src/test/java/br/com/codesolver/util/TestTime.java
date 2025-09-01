package br.com.codesolver.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.logging.Logger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
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

	/** Log da classe de testes. */
    private static final Logger LOGGER = Logger.getLogger(TestTime.class.getName());
	
	/** Objeto de teste. */
	private static Time test;

	/** Construtor padrão. */
	public TestTime() {
	}

    /**
	 * Preparando os testes unitários para {@link Time}.
	 */
	@BeforeAll
	public static void before() {
		LOGGER.info("Inicializando os testes unitários para Time.");
	}

	/**
	 * Finalizando os testes unitários para {@link Time}.
	 */
	@AfterAll
	public static void after() {
		LOGGER.info("Finalizando os testes unitários para Time.");
	}

	/**
	 * Teste para o construtor de {@link Time#Time()}.
	 */
	@Test
	@Order(0)
	public void testTime() {
		LOGGER.config("Testando Time#Time().");
		Time test = new Time();
		assertNotNull(test);
	}

	/**
	 * Teste para o construtor de {@link Time#Time(int, int, int)}.
	 */
	@Test
	@Order(1)
	public void testTimeIntIntInt() {
		LOGGER.config("Testando Time#Time(int, int, int).");
		test = new Time(12, 30, 0);
	}

	/**
	 * Teste para o método {@link Time#getHour()}.
	 */
	@Test
	@Order(2)
	public void testGetHour() {
		LOGGER.config("Testando Time#getHour().");
		assertEquals(test.getHour(), 12);
	}

	/**
	 * Teste para o método {@link Time#getMinute()}.
	 */
	@Test
	@Order(2)
	public void testGetMinute() {
		LOGGER.config("Testando Time#getMinute().");
		assertEquals(test.getMinute(), 30);
	}

	/**
	 * Teste para o método {@link Time#getSecond()}.
	 */
	@Test
	@Order(2)
	public void testGetSecond() {
		LOGGER.config("Testando Time#getSecond().");
		assertEquals(test.getSecond(), 0);
	}

	/**
	 * Teste para o método {@link Time#parse(String)}.
	 */
	@Test
	@Order(2)
	public void testParse() {
		LOGGER.config("Testando Time#parse(String).");
		Time other = Time.parse("12:30:00");
		assertNotNull(other);
		assertEquals(other, test);
	}

	/**
	 * Teste para o método {@link Time#toString()}.
	 */
	@Test
	@Order(2)
	public void testToString() {
		LOGGER.config("Testando Time#toString().");
		assertEquals(test.toString(), "12:30:00");
	}

	/**
	 * Teste para o método {@link Time#equals(Object)}.
	 */
	@Test
	@Order(2)
	public void testEqualsObject() {
		LOGGER.config("Testando Time#equals(Object).");
		Time other = new Time(12, 30, 0);
		assertTrue(test.equals(other));
	}
}
