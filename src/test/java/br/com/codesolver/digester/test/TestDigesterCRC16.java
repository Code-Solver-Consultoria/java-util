package br.com.codesolver.digester.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.logging.Logger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import br.com.codesolver.digester.AlgorithmType;
import br.com.codesolver.digester.DigesterCRC;
import br.com.codesolver.digester.DigesterCRC16;
import br.com.codesolver.digester.DigesterFactory;

/**
 * Testes unitários para {@link DigesterCRC16}.
 * 
 * @author <a href="mailto:luciano@codesolver.com.br">Luciano Vieira Rodrigues</a>
 * @since 2025-08-26
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestDigesterCRC16 {

	/** Log da classe. */
	private static final Logger LOGGER = Logger.getLogger(TestDigesterCRC16.class.getName());
	
	/** Matriz de bytes para controle dos testes unitários. */
	private static final byte[] BUFFER = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
	
	/** Valor de CRC conhecido. */
	private static final short CRC = 27649;
	
	/** HASH do CRC conhecido. */
	private static final String HASH = "6c01";

	/** Objeto de teste. */
	private static DigesterCRC digester;

	/** Construtor padrão. */
	public TestDigesterCRC16() {
	}
	
	/**
	 * Preparando a classe para teste de {@link DigesterCRC16}.
	 */
	@BeforeAll
	public static void beforeClass() {
		LOGGER.info("Inicializando os testes para DigesterCRC16.");
		digester = DigesterFactory.getInstance(AlgorithmType.CRC16);
	}

	/**
	 * Finalizando os testes da classe {@link DigesterCRC16}.
	 */
	@AfterAll
	public static void afterClass() {
		LOGGER.info("Finalizando os testes para DigesterCRC16.");
		digester = null;
	}

	/**
	 * Teste para inicialização de {@link DigesterCRC16}.
	 */
	@Order(1)
	public void testInit() {
		LOGGER.config("Testando a inicialização de DigesterCRC16.");
		short crc = digester.getValue();
		assertEquals(0, crc);
	}

	/**
	 * Teste para o método {@link DigesterCRC16#getValue()}.
	 */
	@Test
	@Order(2)
	public void testGetValue() {	
		digester.update(BUFFER);
		short crc = digester.getValue();
		assertEquals(CRC, crc);
	}

	/**
	 * Teste para o método {@link DigesterCRC16#digest()}.
	 */
	@Test
	@Order(3)
	public void testDigest() {
		String hash	= digester.digest();
		assertEquals(HASH, hash);
	}

		/**
	 * Teste para o método {@link DigesterCRC16#reset()}.
	 */
	@Test
	@Order(4)
	public void testReset() {
		digester.reset();
		short crc = digester.getValue();
		assertEquals(0, crc);
	}
}
