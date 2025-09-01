package br.com.codesolver.digester;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.logging.Logger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Testse unitários para verificação de HASH com {@link DigesterSUN}.
 * 
 * @author <a href="mailto:luciano@codesolver.com.br">Luciano Vieira Rodrigues</a>
 * @since 2025-08-26
 */
public class TestDigesterSUN {

	/** Log da classe. */
	private static final Logger LOGGER = Logger.getLogger(TestDigesterSUN.class.getName());

	/** Construtor padrão. */
	public TestDigesterSUN() {
	}
	
	/**
	 * Preparando a classe para teste de {@link DigesterSUN}.
	 */
	@BeforeAll
	public static void beforeClass() {
		LOGGER.info("Inicializando os testes para DigesterSUN.");
	}

	/**
	 * Finalizando os testes da classe {@link DigesterSUN}.
	 */
	@AfterAll
	public static void afterClass() {
		LOGGER.info("Finalizando os testes para DigesterSUN.");
	}
	
	/**
	 * Teste para o método {@link DigesterSUN#DigesterSUN(AlgorithmType)}.
	 */
	@Test
	public void testDigesterSUN() {
		LOGGER.config("Testando DigesterSUN#DigesterSUN(AlgorithmType).");
		Digester digester = new DigesterSUN(AlgorithmType.SHA_256);
		assertNotNull(digester);
	}
	
	/**
	 * Teste para o método {@link DigesterSUN#DigesterSUN(AlgorithmType)}
	 * com algorítimo nulo.
	 */
	@Test
	public void testDigesterSUNAlgorithmNull() {
		LOGGER.config("Testando DigesterSUN#DigesterSUN(AlgorithmType) com algorítimo nulo.");
		assertThrowsExactly(DigesterException.class, () -> {
			new DigesterSUN((AlgorithmType) null);
		});
	}
	
	/**
	 * Teste para o método {@link DigesterSUN#parse(byte[])}.
	 */
	@Test
	public void testParseByteArray() {
		LOGGER.config("Testando DigesterSUN#parse(byte[]).");
		byte[] buffer 			= {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
		DigesterSUN digester	= new DigesterSUN(AlgorithmType.SHA_1);
		String hash				= digester.parse(buffer);
		assertEquals("e89ad5a9631c3efdded7e3ecce79b4d0fedce1bf", hash);
	}

	/**
	 * Teste para o método {@link DigesterSUN#parse(String)}.
	 */
	@Test
	public void testParseString() {
		LOGGER.config("Testando DigesterSUN#parse(String).");
		DigesterSUN digester	= new DigesterSUN(AlgorithmType.SHA_1);
		String hash				= digester.parse("luciano");
		assertEquals("714c325800a92dd51457e3f27dd898534b01058e", hash);
	}

	/**
	 * Teste para o método {@link DigesterSUN#verify(byte[], String)}.
	 */
	@Test
	public void testVerifyByteArrayString() {
		LOGGER.config("Testando DigesterSUN#verify(byte[], String).");
		byte[] buffer 			= {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
		DigesterSUN digester	= new DigesterSUN(AlgorithmType.SHA_1);
		boolean valid			= digester.verify(buffer, "e89ad5a9631c3efdded7e3ecce79b4d0fedce1bf");
		assertTrue(valid);
		
		boolean invalid			= digester.verify(buffer, "a89ad5a9631c3efdded7e3ecce79b4d0fedce1bf");
		assertFalse(invalid);
	}

	/**
	 * Teste para o método {@link DigesterSUN#verify(String, String)}.
	 */
	@Test
	public void testVerifyStringString() {
		LOGGER.config("Testando DigesterSUN#verify(String, String).");
		DigesterSUN digester	= new DigesterSUN(AlgorithmType.SHA_1);
		boolean valid			= digester.verify("luciano", "714c325800a92dd51457e3f27dd898534b01058e");
		assertTrue(valid);
		
		boolean invalid			= digester.verify("luciano", "014c325800a92dd51457e3f27dd898534b01058e");
		assertFalse(invalid);
	}
	
	/**
	 * Teste para o método {@link DigesterSUN#reset()}.
	 */
	@Test
	public void testReset() {
		LOGGER.config("Testando DigesterSUN#reset().");
		byte[] buffer 		= {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
		Digester digester	= new DigesterSUN(AlgorithmType.MD2);
		String control		= digester.digest();
		digester.update(buffer);
		digester.reset();
		String hash = digester.digest();
		assertEquals(control, hash);
	}

	/**
	 * Teste para o método {@link DigesterSUN#update(byte[])}.
	 */
	@Test
	public void testUpdate() {
		LOGGER.config("Testando DigesterSUN#update(byte[]).");
		byte[] buffer 		= {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
		Digester digester	= new DigesterSUN(AlgorithmType.MD2);
		String control		= digester.digest();
		
		digester.update(buffer);
		String hash 		= digester.digest();
		assertFalse(control.equals(hash));
		assertEquals("c581c06c2a58920784b533334b14d2c2", hash);
	}

	/**
	 * Teste para o método {@link DigesterSUN#digest()}.
	 */
	@Test
	public void testDigest() {
		LOGGER.config("Testando DigesterSUN#digest().");
		Digester digester	= new DigesterSUN(AlgorithmType.MD2);
		String hash			= digester.digest();
		assertEquals("8350e5a3e24c153df2275c9f80692773", hash);
	}	
}
