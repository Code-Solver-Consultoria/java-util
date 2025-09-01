package br.com.codesolver.uuid;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.logging.Logger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Testes unitários para {@link UUIDGeneratorVersionInvalid}.
 *
 * @author <a href="mailto:luciano@codesolver.com.br">Luciano Vieira Rodrigues</a>
 * @since 2025-08-26
 */
public class TestUUIDGeneratorVersionInvalid {
	
	/** Log da classe. */
	private static final Logger LOGGER = Logger.getLogger(TestUUIDGeneratorVersionInvalid.class.getName());

	/** Construtor padrão. */
	public TestUUIDGeneratorVersionInvalid() {
	}

	/**
	 * Preparando os testes unitários para {@link UUIDGeneratorVersionInvalid}..
	 */
	@BeforeAll
	public static void before() {
		LOGGER.info("Preparando os testes unitários para UUIDGeneratorVersionInvalid.");
	}

	/**
	 * Finalizando os testes unitários para {@link UUIDGeneratorVersionInvalid}..
	 */
	@AfterAll
	public static void after() {
		LOGGER.info("Finalizando os testes unitários para UUIDGeneratorVersionInvalid.");
	}
	
	/**
	 * Teste unitário para {@link UUIDGeneratorVersionInvalid#UUIDGeneratorVersionInvalid()}.
	 */
	@Test
	public void testConstructor() {
		LOGGER.config("Testando UUIDGeneratorVersionInvalid#UUIDGeneratorVersionInvalid().");
		assertThrowsExactly(UUIDGeneratorVersionInvalid.class, () -> {
			throw new UUIDGeneratorVersionInvalid();
		});
	}
	
	/**
	 * Teste unitário para {@link UUIDGeneratorVersionInvalid#UUIDGeneratorVersionInvalid(String)}.
	 */
	@Test
	public void testConstructorString() {
		LOGGER.config("Testando UUIDGeneratorVersionInvalid#UUIDGeneratorVersionInvalid(String).");
		assertThrowsExactly(UUIDGeneratorVersionInvalid.class, () -> {
			throw new UUIDGeneratorVersionInvalid("Erro com mensagem");
		});
	}
	
	/**
	 * Teste unitário para {@link UUIDGeneratorVersionInvalid#UUIDGeneratorVersionInvalid(Throwable)}.
	 */
	@Test
	public void testConstructorThrowable() {
		LOGGER.config("Testando UUIDGeneratorVersionInvalid#UUIDGeneratorVersionInvalid(Throwable).");
		assertThrowsExactly(UUIDGeneratorVersionInvalid.class, () -> {
			throw new UUIDGeneratorVersionInvalid(new Exception());
		});
	}
	
	/**
	 * Teste unitário para {@link UUIDGeneratorVersionInvalid#UUIDGeneratorVersionInvalid(String, Throwable)}.
	 */
	@Test
	public void testConstructorStringThrowable() {
		LOGGER.config("Testando UUIDGeneratorVersionInvalid#UUIDGeneratorVersionInvalid(String, Throwable).");
		assertThrowsExactly(UUIDGeneratorVersionInvalid.class, () -> {
			throw new UUIDGeneratorVersionInvalid("Erro com mensagem", new Exception());
		});
	}

	/**
	 * Teste unitário para {@link UUIDGeneratorVersionInvalid#UUIDGeneratorVersionInvalid(String, Throwable, boolean, boolean)}.
	 */
	@Test
	public void testConstructorStringThrowableBooleanBoolean() {
		LOGGER.config("Testando UUIDGeneratorVersionInvalid#UUIDGeneratorVersionInvalid(String, Throwable, boolean, boolean).");
		assertThrowsExactly(UUIDGeneratorVersionInvalid.class, () -> {
			throw new UUIDGeneratorVersionInvalid("Erro com mensagem", new Exception(), false, false);
		});
	}
}
