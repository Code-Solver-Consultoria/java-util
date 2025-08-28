package br.com.codesolver.session;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.logging.Logger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * Testes unitários para {@link SimpleSessionManager}.
 *
 * @author <a href="mailto:luciano@codesolver.com.br">Luciano Vieira Rodrigues</a>
 * @since 2025-08-26
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestSimpleSessionManager {
	
	/** Log da classe. */
	private static final Logger logger = Logger.getLogger(TestSimpleSessionManager.class.getName());
	
	/** Parâmetro de identificação de ID de sessão. */
	private static final Integer ID = 1;
	
	/** Parâmetro de controle do objeto criado para teste. */
	private static final SessionParam<String> CONTROL = new SessionParam<String>("CONTROL");
	
	/** Valor para o parâmetro de controle de objetos criados para teste. */
	private static final String CONTROL_VALUE = "Criado para teste";
	
	/**
	 * Objeto de teste.
	 */
	private static SimpleSessionManager<SimpleSession> manager;

	@BeforeAll
	public static void beforeClass() {
		logger.info("Inicializando os testes para SimpleSessionManager.");
		manager = new SimpleSessionManager<SimpleSession>(SimpleSession.class);
	}

	@AfterAll
	public static void afterClass() {
		logger.info("Finalizando os testes para SimpleSessionManager.");
		manager = null;
	}

	/**
	 * Teste para {@link SimpleSessionManager#get(SessionParam, Object)}, 
	 * para criar o primeiro objeto de sessão.
	 */
	@Test
	@Order(1)
	public void testGet() {
		logger.config("Testando SimpleSessionManager#get(SessionParam, Object).");
		Session result = manager.get(Session.ID, ID, true);
		assertNotNull(result);
		
		result.setParam(CONTROL, CONTROL_VALUE);
		
		Session first = manager.get(Session.ID, ID);
		assertEquals(first, result);
		
		String control0 = result.getParam(CONTROL);
		String control1 = first.getParam(CONTROL);
		assertEquals(control0, control1);
		
		Session second = manager.get(Session.ID, ID + 1, true);
		assertNotEquals(second, result);
	}

	/**
	 * Teste para {@link SimpleSessionManager#remove(SessionParam, Object)}
	 */
	@Test
	@Order(2)
	public void testRemove() {
		logger.config("Testando SimpleSessionManager#remove(SessionParam, Object).");
		Session result = manager.remove(Session.ID, ID);
		assertNotNull(result);
		
		Session other = manager.remove(Session.ID, ID);
		assertNull(other);
	}

	/**
	 * Teste para {@link SimpleSessionManager#array()}
	 */
	@Test
	@Order(3)
	public void testArray() {
		logger.config("Testando SimpleSessionManager#array().");
		Session[] sessions = manager.array();
		assertNotNull(sessions);
		assertEquals(sessions.length, 1);
	}

	/**
	 * Teste para {@link SimpleSessionManager#clear()}
	 */
	@Test
	@Order(4)
	public void testClear() {
		logger.config("Testando SimpleSessionManager#clear().");
		manager.clear();
	}
	
	/**
	 * Teste para {@link SimpleSessionManager#size()}
	 */
	@Test
	@Order(5)
	public void testSize() {
		logger.config("Testando SimpleSessionManager#size().");
		assertEquals(manager.size(), 0);
	}	
}
