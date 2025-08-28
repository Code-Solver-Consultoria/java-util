package br.com.codesolver.digester;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Testse unitários para verificação de HASH.
 * 
 * @author <a href="mailto:luciano@codesolver.com.br">Luciano Vieira Rodrigues</a>
 * @since 2025-08-26
 */
public class TestDigesterSUN {

	/**
	 * Test method for {@link br.com.codesolver.digester.DigesterSUN#DigesterSUN(br.com.codesolver.digester.AlgorithmType)}.
	 */
	@Test
	public void testDigesterSUN() {
		Digester digester = new DigesterSUN(AlgorithmType.SHA_256);
		assertNotNull(digester);
	}
	
	/**
	 * Test method for {@link br.com.codesolver.digester.DigesterSUN#DigesterSUN(br.com.codesolver.digester.AlgorithmType)},
	 * com algorítimo nulo
	 */
	@Test
	public void testDigesterSUNAlgorithmNull() {
		assertThrowsExactly(DigesterException.class, () -> {
			new DigesterSUN((AlgorithmType) null);
		});
	}
	
	/**
	 * Test method for {@link br.com.codesolver.digester.DigesterSUN#parse(byte[])}.
	 */
	@Test
	public void testParseByteArray() {
		byte[] buffer 			= {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
		DigesterSUN digester	= new DigesterSUN(AlgorithmType.SHA_1);
		String hash				= digester.parse(buffer);
		assertEquals("e89ad5a9631c3efdded7e3ecce79b4d0fedce1bf", hash);
	}

	/**
	 * Test method for {@link br.com.codesolver.digester.DigesterSUN#parse(java.lang.String)}.
	 */
	@Test
	public void testParseString() {
		DigesterSUN digester	= new DigesterSUN(AlgorithmType.SHA_1);
		String hash				= digester.parse("luciano");
		assertEquals("714c325800a92dd51457e3f27dd898534b01058e", hash);
	}

	/**
	 * Test method for {@link br.com.codesolver.digester.DigesterSUN#verify(byte[], java.lang.String)}.
	 */
	@Test
	public void testVerifyByteArrayString() {
		byte[] buffer 			= {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
		DigesterSUN digester	= new DigesterSUN(AlgorithmType.SHA_1);
		boolean valid			= digester.verify(buffer, "e89ad5a9631c3efdded7e3ecce79b4d0fedce1bf");
		assertTrue(valid);
		
		boolean invalid			= digester.verify(buffer, "a89ad5a9631c3efdded7e3ecce79b4d0fedce1bf");
		assertFalse(invalid);
	}

	/**
	 * Test method for {@link br.com.codesolver.digester.DigesterSUN#verify(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testVerifyStringString() {
		DigesterSUN digester	= new DigesterSUN(AlgorithmType.SHA_1);
		boolean valid			= digester.verify("luciano", "714c325800a92dd51457e3f27dd898534b01058e");
		assertTrue(valid);
		
		boolean invalid			= digester.verify("luciano", "014c325800a92dd51457e3f27dd898534b01058e");
		assertFalse(invalid);
	}
	
	/**
	 * Test method for {@link br.com.codesolver.digester.DigesterSUN#reset()}.
	 */
	@Test
	public void testReset() {
		byte[] buffer 		= {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
		Digester digester	= new DigesterSUN(AlgorithmType.MD2);
		String control		= digester.digest();
		digester.update(buffer);
		digester.reset();
		String hash = digester.digest();
		assertEquals(control, hash);
	}

	/**
	 * Test method for {@link br.com.codesolver.digester.DigesterSUN#update(byte[])}.
	 */
	@Test
	public void testUpdate() {
		byte[] buffer 		= {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
		Digester digester	= new DigesterSUN(AlgorithmType.MD2);
		String control		= digester.digest();
		
		digester.update(buffer);
		String hash 		= digester.digest();
		assertFalse(control.equals(hash));
		assertEquals("c581c06c2a58920784b533334b14d2c2", hash);
	}

	/**
	 * Test method for {@link br.com.codesolver.digester.DigesterSUN#digest()}.
	 */
	@Test
	public void testDigest() {
		Digester digester	= new DigesterSUN(AlgorithmType.MD2);
		String hash			= digester.digest();
		assertEquals("8350e5a3e24c153df2275c9f80692773", hash);
	}	
}
