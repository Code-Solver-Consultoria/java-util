package br.com.codesolver.digester;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.logging.Logger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Testes unitários para {@link DigesterException}.
 *
 * @author Luciano Vieira Rodrigues
 * @since 20/08/2013
 * @version 1.0
 */
public class TestDigesterException {
	
	/** Log da classe. */
	private static final Logger LOGGER = Logger.getLogger(TestDigesterException.class.getName());
	
	/** Construtor padrão. */
	public TestDigesterException() {
	}
	
	/**
	 * Preparando a classe para teste de {@link DigesterException}.
	 */
	@BeforeAll
	public static void beforeClass() {
		LOGGER.info("Inicializando os testes para DigesterException.");
	}

	/**
	 * Finalizando os testes da classe {@link DigesterException}.
	 */
	@AfterAll
	public static void afterClass() {
		LOGGER.info("Finalizando os testes para DigesterException.");
	}

	/**
	 * Teste para o método {@link DigesterException#DigesterException()}.
	 */
	@Test
	public void testConstructor() {
		LOGGER.config("Testando DigesterException#DigesterException().");
		assertThrowsExactly(DigesterException.class, () -> {
			throw new DigesterException();
		});
	}
	
	/**
	 * Teste para o método {@link DigesterException#DigesterException(String)}.
	 */
	@Test
	public void testConstructorString() {
		LOGGER.config("Testando DigesterException#DigesterException(String).");
		assertThrowsExactly(DigesterException.class, () -> {
			throw new DigesterException("Erro com mensagem");
		});
	}
	
	/**
	 * Teste para o método {@link DigesterException#DigesterException(Throwable)}.
	 */
	@Test
	public void testConstructorThrowable() {
		LOGGER.config("Testando DigesterException#DigesterException(Throwable).");
		assertThrowsExactly(DigesterException.class, () -> {
			throw new DigesterException(new Exception());
		});
	}
	
	/**
	 * Teste para o método {@link DigesterException#DigesterException(String, Throwable)}.
	 */
	@Test
	public void testConstructorStringThrowable() {
		LOGGER.config("Testando DigesterException#DigesterException(String, Throwable).");
		assertThrowsExactly(DigesterException.class, () -> {
			throw new DigesterException("Erro com mensagem", new Exception());
		});
	}

	/**
	 * Teste para o método {@link DigesterException#DigesterException(String, Throwable, boolean, boolean)}.
	 */
	@Test
	public void testConstructorStringThrowableBooleanBoolean() {
		LOGGER.config("Testando DigesterException#DigesterException(String, Throwable, boolean, boolean).");
		assertThrowsExactly(DigesterException.class, () -> {
			throw new DigesterException("Erro com mensagem", new Exception(), false, false);
		});
	}
}
