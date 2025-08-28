package br.com.codesolver.uuid;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.logging.Logger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Testes unitários para {@link UUIDGeneratorNodeInvalid}.
 *
 * @author <a href="mailto:luciano@codesolver.com.br">Luciano Vieira Rodrigues</a>
 * @since 2025-08-26
 */
public class TestUUIDGeneratorNodeInvalid {
	
	/** Log da classe. */
	private static final Logger logger = Logger.getLogger(TestUUIDGeneratorNodeInvalid.class.getName());

	/**
	 * Preparando os testes unitários para TestUUIDGeneratorNodeInvalid.
	 */
	@BeforeAll
	public static void before() {
		logger.info("Preparando os testes unitários para TestUUIDGeneratorNodeInvalid.");
	}

	/**
	 * Finalizando os testes unitários de TestUUIDGeneratorNodeInvalid.
	 */
	@AfterAll
	public static void after() {
		logger.info("Finalizando os testes unitários para TestUUIDGeneratorNodeInvalid.");
	}
	
	/**
	 * Teste unitário para {@link UUIDGeneratorNodeInvalid#UUIDGeneratorNodeInvalid()}.
	 */
	@Test
	public void testConstructor() {
		logger.config("Teste unitário para UUIDGeneratorNodeInvalid#UUIDGeneratorNodeInvalid().");
		assertThrowsExactly(UUIDGeneratorNodeInvalid.class, () -> {
			throw new UUIDGeneratorNodeInvalid();
		});
	}
	
	/**
	 * Teste unitário para {@link UUIDGeneratorNodeInvalid#UUIDGeneratorNodeInvalid(String)}.
	 */
	@Test
	public void testConstructorString() {
		logger.config("Teste unitário para UUIDGeneratorNodeInvalid#UUIDGeneratorNodeInvalid(String).");
		assertThrowsExactly(UUIDGeneratorNodeInvalid.class, () -> {
			throw new UUIDGeneratorNodeInvalid("Erro com mensagem");
		});
	}
	
	/**
	 * Teste unitário para {@link UUIDGeneratorNodeInvalid#UUIDGeneratorNodeInvalid(Throwable)}.
	 */
	@Test
	public void testConstructorThrowable() {
		logger.config("Teste unitário para UUIDGeneratorNodeInvalid#UUIDGeneratorNodeInvalid(Throwable).");
		assertThrowsExactly(UUIDGeneratorNodeInvalid.class, () -> {
			throw new UUIDGeneratorNodeInvalid(new Exception());
		});
	}
	
	/**
	 * Teste unitário para {@link UUIDGeneratorNodeInvalid#UUIDGeneratorNodeInvalid(String, Throwable)}.
	 */
	@Test
	public void testConstructorStringThrowable() {
		logger.config("Teste unitário para UUIDGeneratorNodeInvalid#UUIDGeneratorNodeInvalid(String, Throwable).");
		assertThrowsExactly(UUIDGeneratorNodeInvalid.class, () -> {
			throw new UUIDGeneratorNodeInvalid("Erro com mensagem", new Exception());
		});
	}

	/**
	 * Teste unitário para {@link UUIDGeneratorNodeInvalid#UUIDGeneratorNodeInvalid(String, Throwable, boolean, boolean)}.
	 */
	@Test
	public void testConstructorStringThrowableBooleanBoolean() {
		logger.config("Teste unitário para UUIDGeneratorNodeInvalid#UUIDGeneratorNodeInvalid(String, Throwable, boolean, boolean).");
		assertThrowsExactly(UUIDGeneratorNodeInvalid.class, () -> {
			throw new UUIDGeneratorNodeInvalid("Erro com mensagem", new Exception(), false, false);
		});
	}
}
