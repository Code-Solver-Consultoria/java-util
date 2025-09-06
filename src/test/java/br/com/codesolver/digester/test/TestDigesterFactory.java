package br.com.codesolver.digester.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.logging.Logger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.codesolver.digester.AlgorithmType;
import br.com.codesolver.digester.Digester;
import br.com.codesolver.digester.DigesterCRC16;
import br.com.codesolver.digester.DigesterFactory;
import br.com.codesolver.digester.DigesterSUN;

/**
 * Teste unitários para {@link DigesterFactory}.
 * 
 * @author <a href="mailto:luciano@codesolver.com.br">Luciano Vieira Rodrigues</a>
 * @since 2025-08-26
 */
public class TestDigesterFactory {
	
	/** Log da classe. */
	private static final Logger LOGGER = Logger.getLogger(TestDigesterFactory.class.getName());

	/** Construtor padrão. */
	public TestDigesterFactory() {
	}
	
	/**
	 * Preparando a classe para teste de {@link DigesterFactory}.
	 */
	@BeforeAll
	public static void beforeClass() {
		LOGGER.info("Inicializando os testes para DigesterFactory.");
	}

	/**
	 * Finalizando os testes da classe {@link DigesterFactory}.
	 */
	@AfterAll
	public static void afterClass() {
		LOGGER.info("Finalizando os testes para DigesterFactory.");
	}
	/**
	 * Teste para o método {@link DigesterFactory#getInstance(AlgorithmType)}
	 * para um CRC16.
	 */
	@Test
	public void testGetInstanceAlgorithmTypeCRC16() {
		LOGGER.config("Testando DigesterFactory#getInstance(AlgorithmType) para CRC16.");
		Digester digester = DigesterFactory.getInstance(AlgorithmType.CRC16);
		assertNotNull(digester);
		assertEquals(digester.getClass(), DigesterCRC16.class);
	}

	/**
	 * Teste para o método {@link DigesterFactory#getInstance(AlgorithmType)}
	 * para um MD5.
	 */
	@Test
	public void testGetInstanceAlgorithmTypeMD5() {
		LOGGER.config("Testando DigesterFactory#getInstance(AlgorithmType) para MD5.");
		Digester digester = DigesterFactory.getInstance(AlgorithmType.MD5);
		assertNotNull(digester);
		assertEquals(digester.getClass(), DigesterSUN.class);
	}
}
