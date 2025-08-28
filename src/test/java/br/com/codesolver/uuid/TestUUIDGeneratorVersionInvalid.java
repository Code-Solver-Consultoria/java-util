package br.com.codesolver.uuid;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

/**
 * Testes unitários para {@link UUIDGeneratorVersionInvalid}.
 *
 * @author <a href="mailto:luciano@codesolver.com.br">Luciano Vieira Rodrigues</a>
 * @since 2025-08-26
 */
public class TestUUIDGeneratorVersionInvalid {
	
	/** Log da classe. */
	private static final Logger logger = Logger.getLogger(TestUUIDGeneratorVersionInvalid.class.getName());
	
	/**
	 * Teste unitário para {@link UUIDGeneratorVersionInvalid#UUIDGeneratorVersionInvalid()}.
	 */
	@Test
	public void testConstructor() {
		logger.config("Teste unitário para Teste unitário para UUIDGeneratorVersionInvalid#UUIDGeneratorVersionInvalid().");
		assertThrowsExactly(UUIDGeneratorVersionInvalid.class, () -> {
			throw new UUIDGeneratorVersionInvalid();
		});
	}
	
	/**
	 * Teste unitário para {@link UUIDGeneratorVersionInvalid#UUIDGeneratorVersionInvalid(String)}.
	 */
	@Test
	public void testConstructorString() {
		logger.config("Teste unitário para Teste unitário para UUIDGeneratorVersionInvalid#UUIDGeneratorVersionInvalid(String).");
		assertThrowsExactly(UUIDGeneratorVersionInvalid.class, () -> {
			throw new UUIDGeneratorVersionInvalid("Erro com mensagem");
		});
	}
	
	/**
	 * Teste unitário para {@link UUIDGeneratorVersionInvalid#UUIDGeneratorVersionInvalid(Throwable)}.
	 */
	@Test
	public void testConstructorThrowable() {
		logger.config("Teste unitário para Teste unitário para UUIDGeneratorVersionInvalid#UUIDGeneratorVersionInvalid(Throwable).");
		assertThrowsExactly(UUIDGeneratorVersionInvalid.class, () -> {
			throw new UUIDGeneratorVersionInvalid(new Exception());
		});
	}
	
	/**
	 * Teste unitário para {@link UUIDGeneratorVersionInvalid#UUIDGeneratorVersionInvalid(String, Throwable)}.
	 */
	@Test
	public void testConstructorStringThrowable() {
		logger.config("Teste unitário para Teste unitário para UUIDGeneratorVersionInvalid#UUIDGeneratorVersionInvalid(String, Throwable).");
		assertThrowsExactly(UUIDGeneratorVersionInvalid.class, () -> {
			throw new UUIDGeneratorVersionInvalid("Erro com mensagem", new Exception());
		});
	}

	/**
	 * Teste unitário para {@link UUIDGeneratorVersionInvalid#UUIDGeneratorVersionInvalid(String, Throwable, boolean, boolean)}.
	 */
	@Test
	public void testConstructorStringThrowableBooleanBoolean() {
		logger.config("Teste unitário para Teste unitário para UUIDGeneratorVersionInvalid#UUIDGeneratorVersionInvalid(String, Throwable, boolean, boolean).");
		assertThrowsExactly(UUIDGeneratorVersionInvalid.class, () -> {
			throw new UUIDGeneratorVersionInvalid("Erro com mensagem", new Exception(), false, false);
		});
	}
}
