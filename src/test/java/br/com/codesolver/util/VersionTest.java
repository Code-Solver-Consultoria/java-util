package br.com.codesolver.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.logging.Logger;

import org.easymock.EasyMock;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/** 
 * Testes unitários para {@link Version}.
 * 
 * @author <a href="mailto:luciano@codesolver.com.br">Luciano Vieira Rodrigues</a>
 * @since 2025-08-26
 */
public class VersionTest {

	/** Log da classe de testes. */
    private static final Logger LOGGER = Logger.getLogger(VersionTest.class.getName());

	/** Construtor padrão. */
	public VersionTest() {
	}

    /**
	 * Preparando os testes unitários para {@link Version}.
	 */
	@BeforeAll
	public static void before() {
		LOGGER.info("Inicializando os testes unitários para Version.");
	}

	/**
	 * Finalizando os testes unitários para {@link Version}.
	 */
	@AfterAll
	public static void after() {
		LOGGER.info("Finalizando os testes unitários para Version.");
	}
	
	/**
	 * Teste para o construtor {@link Version#Version()}.
	 */
	@Test
	public void testVersion() {
		LOGGER.config("Testando Version#Version().");
		Version test = new Version();
		assertNotNull(test);
		assertEquals(test.getMajor(), "0");
		assertEquals(test.getMinor(), "0");
		assertEquals(test.getBuild(), "0");
	}
	
	/**
	 * Teste para o método {@link Version#decode(Package)}.
	 */
	@Test
	public void testDecode() {
		LOGGER.config("Testando Version#decode(Package).");
		Package packageMock = EasyMock.createMock(Package.class);
		EasyMock.expect(packageMock.getSpecificationTitle()).andReturn("java-util");
		EasyMock.expect(packageMock.getImplementationVersion()).andReturn("2.1.3");
		EasyMock.replay(packageMock);
		
		Version test = new Version();
		test.decode(packageMock);
		assertEquals(test.getName(), "java-util");
		assertEquals(test.getMajor(), "2");
		assertEquals(test.getMinor(), "1");
		assertEquals(test.getBuild(), "3");
		
		EasyMock.verify(packageMock);
	}
}
