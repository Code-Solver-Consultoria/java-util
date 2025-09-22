package br.com.codesolver.uuid.test;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.logging.Logger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.codesolver.uuid.UUIDGeneratorNodeInvalid;

/**
 * Testes unitários para {@link UUIDGeneratorNodeInvalid}.
 *
 * @author <a href="mailto:luciano@codesolver.com.br">Luciano Vieira Rodrigues</a>
 * @since 2025-08-26
 */
public class TestUUIDGeneratorNodeInvalid {
	
	/** Log da classe. */
	private static final Logger LOGGER = Logger.getLogger(TestUUIDGeneratorNodeInvalid.class.getName());

	/** Construtor padrão. */
	public TestUUIDGeneratorNodeInvalid() {
	}

	/**
	 * Preparando os testes unitários para {@link UUIDGeneratorNodeInvalid}..
	 */
	@BeforeAll
	public static void before() {
		LOGGER.info("Preparando os testes unitários para UUIDGeneratorNodeInvalid.");
	}

	/**
	 * Finalizando os testes unitários de {@link UUIDGeneratorNodeInvalid}..
	 */
	@AfterAll
	public static void after() {
		LOGGER.info("Finalizando os testes unitários para UUIDGeneratorNodeInvalid.");
	}
	
	/**
	 * Teste unitário para {@link UUIDGeneratorNodeInvalid#UUIDGeneratorNodeInvalid()}.
	 */
	@Test
	public void testConstructor() {
		LOGGER.config("Testando UUIDGeneratorNodeInvalid#UUIDGeneratorNodeInvalid().");
		assertThrowsExactly(UUIDGeneratorNodeInvalid.class, () -> {
			throw new UUIDGeneratorNodeInvalid();
		});
	}
	
	/**
	 * Teste unitário para {@link UUIDGeneratorNodeInvalid#UUIDGeneratorNodeInvalid(String)}.
	 */
	@Test
	public void testConstructorString() {
		LOGGER.config("Testando UUIDGeneratorNodeInvalid#UUIDGeneratorNodeInvalid(String).");
		assertThrowsExactly(UUIDGeneratorNodeInvalid.class, () -> {
			throw new UUIDGeneratorNodeInvalid("Erro com mensagem");
		});
	}
	
	/**
	 * Teste unitário para {@link UUIDGeneratorNodeInvalid#UUIDGeneratorNodeInvalid(Throwable)}.
	 */
	@Test
	public void testConstructorThrowable() {
		LOGGER.config("Testando UUIDGeneratorNodeInvalid#UUIDGeneratorNodeInvalid(Throwable).");
		assertThrowsExactly(UUIDGeneratorNodeInvalid.class, () -> {
			throw new UUIDGeneratorNodeInvalid(new Exception());
		});
	}
	
	/**
	 * Teste unitário para {@link UUIDGeneratorNodeInvalid#UUIDGeneratorNodeInvalid(String, Throwable)}.
	 */
	@Test
	public void testConstructorStringThrowable() {
		LOGGER.config("Testando UUIDGeneratorNodeInvalid#UUIDGeneratorNodeInvalid(String, Throwable).");
		assertThrowsExactly(UUIDGeneratorNodeInvalid.class, () -> {
			throw new UUIDGeneratorNodeInvalid("Erro com mensagem", new Exception());
		});
	}
}
