package br.com.codesolver.digester;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

/**
 * Testes unitários para {@link DigesterCRC16}.
 * 
 * @author <a href="mailto:luciano@codesolver.com.br">Luciano Vieira Rodrigues</a>
 * @since 2025-08-26
 */
public class TestDigesterCRC16 {
	
	private static final byte[] BUFFER = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
	
	private static final short CRC = 27649;
	
	private static final String HASH = "6c01";
	
	/**
	 * Test method for {@link br.com.codesolver.digester.DigesterCRC16#DigesterCRC16()}.
	 */
	@Test
	public void testDigesterCRC16() {
		DigesterCRC digester = new DigesterCRC16();
		assertNotNull(digester);
	}

	/**
	 * Test method for {@link br.com.codesolver.digester.DigesterCRC16#reset()}.
	 */
	@Test
	public void testReset() {
		DigesterCRC digester	= new DigesterCRC16();
		digester.update(BUFFER);
		digester.reset();
		short crc = digester.getValue();
		assertEquals(0, crc);
	}

	/**
	 * Test method for {@link br.com.codesolver.digester.DigesterCRC16#getValue()}.
	 */
	@Test
	public void testGetValue() {
		DigesterCRC digester	= new DigesterCRC16();
		short crc = digester.getValue();
		assertEquals(0, crc);
		
		digester.update(BUFFER);
		crc = digester.getValue();
		assertEquals(CRC, crc);
	}

	/**
	 * Test method for {@link br.com.codesolver.digester.DigesterCRC16#digest()}.
	 */
	@Test
	public void testDigest() {
		byte[] buffer 			= {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
		DigesterCRC digester	= new DigesterCRC16();
		short crc = digester.getValue();
		assertEquals(0, crc);
		
		digester.update(buffer);
		String hash	= digester.digest();
		assertEquals(HASH, hash);
	}
}
