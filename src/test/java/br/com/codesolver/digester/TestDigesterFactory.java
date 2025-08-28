package br.com.codesolver.digester;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

/**
 * Teste unitários para {@link DigesterFactory}.
 * 
 * @author <a href="mailto:luciano@codesolver.com.br">Luciano Vieira Rodrigues</a>
 * @since 2025-08-26
 */
public class TestDigesterFactory {
	
	/**
	 * Test method for {@link br.com.codesolver.digester.DigesterFactory#getInstance(br.com.codesolver.digester.AlgorithmType)},
	 * para um CRC16
	 */
	@Test
	public void testGetInstanceAlgorithmTypeCRC16() {
		Digester digester = DigesterFactory.getInstance(AlgorithmType.CRC16);
		assertNotNull(digester);
		assertEquals(digester.getClass(), DigesterCRC16.class);
	}

	/**
	 * Test method for {@link br.com.codesolver.digester.DigesterFactory#getInstance(br.com.codesolver.digester.AlgorithmType)},
	 * para um MD5
	 */
	@Test
	public void testGetInstanceAlgorithmTypeMD5() {
		Digester digester = DigesterFactory.getInstance(AlgorithmType.MD5);
		assertNotNull(digester);
		assertEquals(digester.getClass(), DigesterSUN.class);
	}
}
