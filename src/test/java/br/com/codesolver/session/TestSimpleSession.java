package br.com.codesolver.session;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.logging.Logger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * Testes unitários para {@link SimpleSession}.
 *
 * @author <a href="mailto:luciano@codesolver.com.br">Luciano Vieira Rodrigues</a>
 * @since 2025-08-26
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestSimpleSession {
	
	/** Log da classe. */
	private static final Logger LOGGER = Logger.getLogger(TestSimpleSession.class.getName());

	/** Valor para testar a sessão o parâmetro ID da sessão. */
	private static final Integer PARAM_ID = Integer.MAX_VALUE;
	
	/**
	 * Objeto de Teste.
	 */
	private static SimpleSession session;

	/** Construtor padrão. */
	public TestSimpleSession() {
	}

	/**
	 * Preparando os testes para {@link SimpleSession}.
	 */
	@BeforeAll
	public static void beforeClass() {
		LOGGER.info("Inicializando os testes para SimpleSession");
		session = new SimpleSession();
	}

	/**
	 * Finalizando os testes para {@link SimpleSession}.
	 */
	@AfterAll
	public static void afterClass() {
		LOGGER.info("Finalizando os testes para SimpleSession");
		session = null;
	}

	/**
	 * Teste para {@link SimpleSession#setParam(SessionParam, Object)}
	 */
	@Test
	@Order(1)
	public void testSetParam() {
		LOGGER.config("Testando SimpleSession#setParam(SessionParam, Object).");
		session.setParam(Session.ID, PARAM_ID);
		assertEquals(1, session.getParams().size());
	}
	
	/**
	 * Teste para {@link SimpleSession#getParam(SessionParam)}
	 */
	@Test
	@Order(2)
	public void testGetParam() {
		LOGGER.config("Testando SimpleSession#getParam(SessionParam).");
		Integer result = session.getParam(Session.ID);
		assertEquals(result, PARAM_ID);
	}	

	/**
	 * Teste para {@link SimpleSession#getParam(SessionParam)}
	 */
	@Test
	public void testGetParamInvalid() {
		LOGGER.config("Testando SimpleSession#getParam(SessionParam), com parâmetro inválido.");
		SessionParam<Integer> param = new SessionParam<Integer>("invalid");
		Integer result = session.getParam(param);
		assertNull(result);
	}
	
	/**
	 * Teste para {@link SimpleSession#compareTo(Session)}
	 */
	@Test
	@Order(2)
	public void testSessionCompareTo() {
		LOGGER.config("Testando SimpleSession#compareTo(Session)");
		Session comparable = new SimpleSession();
		comparable.setParam(Session.ID, PARAM_ID);
		int result = session.compareTo(comparable);
		assertEquals(result, 0);
	}
	
	/**
	 * Teste para {@link SimpleSession#compareTo(Session)}, com outro valor.
	 */
	@Test
	@Order(2)
	public void testSessionCompareToOtherValue() {
		LOGGER.config("Testando SimpleSession#compareTo(Session), com outro valor");
		Session comparable = new SimpleSession();
		comparable.setParam(Session.ID, PARAM_ID - 1);
		int result = session.compareTo(comparable);
		assertEquals(result, 1);
	}	
	
	/**
	 * Teste para {@link SimpleSession#compareTo(Session)}, com parâmetro de sessão nulo.
	 */
	@Test
	@Order(2)
	public void testSessionCompareToParamNull() {
		LOGGER.config("Testando SimpleSession#compareTo(Session), com parâmetro de sessão nulo");
		Session comparable = new SimpleSession();
		int result = session.compareTo(comparable);
		assertEquals(result, 1);
	}
	
	/**
	 * Teste para {@link SimpleSession#compareTo(Session)}, com sessão nulo.
	 */
	@Test
	@Order(2)
	public void testSessionCompareToNull() {
		LOGGER.config("Testando SimpleSession#compareTo(Session), com parâmetro de sessão nulo");
		assertThrowsExactly(SessionRuntimeException.class, () -> {
			session.compareTo(null);
		});
	}
	
	/**
	 * Teste para {@link SimpleSession#compareTo(Session)}, com o próprio parâmetro sessão nulo.
	 */
	@Test
	@Order(3)
	public void testSessionCompareSelfParamNull() {
		LOGGER.config("Testando SimpleSession#compareTo(Session), com parâmetro de sessão nulo");
		Session comparable = new SimpleSession();
		int result = comparable.compareTo(session);
		assertEquals(result, -1);
	}	
}
