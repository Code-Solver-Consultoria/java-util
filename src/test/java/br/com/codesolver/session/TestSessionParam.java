package br.com.codesolver.session;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.logging.Logger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * Testes unitários para {@link SessionParam}.
 *
 * @author <a href="mailto:luciano@codesolver.com.br">Luciano Vieira Rodrigues</a>
 * @since 2025-08-26
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestSessionParam {

	/** Log da classe. */
	private static final Logger LOGGER = Logger.getLogger(TestSessionParam.class.getName());
	
	/**
	 * Nome do parâmetro para teste.
	 */
	private static final String NAME = "name";
	
	/**
	 * Objeto de teste.
	 */
	private static SessionParam<String> param;

	/** Construtor padrão. */
	public TestSessionParam() {
	}
	
	/**
	 * Preparando a classe para teste de {@link SessionParam}.
	 */
	@BeforeAll
	public static void beforeClass() {
		LOGGER.info("Inicializando os testes para SessionParam.");
		param	= null;
	}

	/**
	 * Finalizando os testes da classe {@link SessionParam}.
	 */
	@AfterAll
	public static void afterClass() {
		LOGGER.info("Finalizando os testes para SessionParam.");
		param	= null;
	}
	
	/**
	 * Teste para {@link SessionParam#SessionParam(String)}
	 */
	@Test
	@Order(1)
	public void testConstructor() {
		LOGGER.config("Testando SessionParam#SessionParam(String)");
		param = new SessionParam<String>(NAME);
		assertNotNull(param);
	}
	
	/**
	 * Teste para {@link SessionParam#SessionParam(String)}, com 
	 * parâmetro nulo.
	 */
	@Test
	public void testConstructorNull() {
		LOGGER.config("Testando SessionParam#SessionParam(String), com parâmetro nulo");
		assertThrowsExactly(SessionRuntimeException.class, () -> {
			new SessionParam<Integer>(null);
		});
	}
	
	/**
	 * Teste para {@link SessionParam#SessionParam(String)}, com 
	 * parâmetro vazio.
	 */
	@Test
	public void testConstructorEmpty() {
		LOGGER.config("Testando SessionParam#SessionParam(String), com parâmetro vazio.");
		assertThrowsExactly(SessionRuntimeException.class, () -> {
			new SessionParam<String>("");
		});
	}	
	
	/**
	 * Teste para {@link SessionParam#SessionParam(String)}, com 
	 * parâmetro repetido.
	 */
	@Test
	@Order(2)
	public void testConstructorRepeated() {
		LOGGER.config("Testando SessionParam#SessionParam(String), com parâmetro repetido.");
		assertThrowsExactly(SessionRuntimeException.class, () -> {
			new SessionParam<String>(NAME);
		});
	}
	
	/**
	 * Teste para {@link SessionParam#validate(Object)}
	 */
	@Test
	@Order(2)
	public void testValidate() {
		LOGGER.config("Testando SessionParam#validate(Object).");
		param.validate("Luciano");
	}
	
	/**
	 * Teste para {@link SessionParam#validate(Object)}, com parâmetro nulo
	 */
	@Test
	@Order(2)
	public void testValidateNull() {
		LOGGER.config("Testando SessionParam#validate(Object).");
		assertThrowsExactly(SessionRuntimeException.class, () -> {
			param.validate(null);
		});
	}	
}
