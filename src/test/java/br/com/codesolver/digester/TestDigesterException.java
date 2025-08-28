package br.com.codesolver.digester;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.logging.Logger;

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
	private static final Logger logger = Logger.getLogger(TestDigesterException.class.getName());
	
	/**
	 * Teste unitário para {@link DigesterException#DigesterException()}.
	 * @throws DigesterException 
	 */
	@Test
	public void testConstructor() throws DigesterException {
		logger.config("Teste unitário para Teste unitário para DigesterException#DigesterException().");
		assertThrowsExactly(DigesterException.class, () -> {
			throw new DigesterException();
		});
	}
	
	/**
	 * Teste unitário para {@link DigesterException#DigesterException(String)}.
	 * @throws DigesterException 
	 */
	@Test
	public void testConstructorString() throws DigesterException {
		logger.config("Teste unitário para Teste unitário para DigesterException#DigesterException(String).");
		assertThrowsExactly(DigesterException.class, () -> {
			throw new DigesterException("Erro com mensagem");
		});
	}
	
	/**
	 * Teste unitário para {@link DigesterException#DigesterException(Throwable)}.
	 * @throws DigesterException 
	 */
	@Test
	public void testConstructorThrowable() throws DigesterException {
		logger.config("Teste unitário para Teste unitário para DigesterException#DigesterException(Throwable).");
		assertThrowsExactly(DigesterException.class, () -> {
			throw new DigesterException(new Exception());
		});
	}
	
	/**
	 * Teste unitário para {@link DigesterException#DigesterException(String, Throwable)}.
	 * @throws DigesterException 
	 */
	@Test
	public void testConstructorStringThrowable() throws DigesterException {
		logger.config("Teste unitário para Teste unitário para DigesterException#DigesterException(String, Throwable).");
		assertThrowsExactly(DigesterException.class, () -> {
			throw new DigesterException("Erro com mensagem", new Exception());
		});
	}

	/**
	 * Teste unitário para {@link DigesterException#DigesterException(String, Throwable, boolean, boolean)}.
	 * @throws DigesterException 
	 */
	@Test
	public void testConstructorStringThrowableBooleanBoolean() throws DigesterException {
		logger.config("Teste unitário para Teste unitário para DigesterException#DigesterException(String, Throwable, boolean, boolean).");
		assertThrowsExactly(DigesterException.class, () -> {
			throw new DigesterException("Erro com mensagem", new Exception(), false, false);
		});
	}
}
