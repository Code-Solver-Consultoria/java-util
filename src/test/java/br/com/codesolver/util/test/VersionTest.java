package br.com.codesolver.util.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.logging.Logger;

import org.easymock.EasyMock;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import br.com.codesolver.util.Version;
import br.com.codesolver.util.VersionRuntimeException;

/** 
 * Testes unitários para {@link Version}.
 * 
 * @author <a href="mailto:luciano@codesolver.com.br">Luciano Vieira Rodrigues</a>
 * @since 2025-08-26
 */
public class VersionTest {

	/** Log da classe de testes. */
    private static final Logger LOGGER = Logger.getLogger(VersionTest.class.getName());

	/** Valor para teste do campo Name. */
	private static final String NAME = "java-util";

	/** Valor para teste do campo Major. */
	private static final String MAJOR = "1";

	/** Valor para teste do campo Minor. */
	private static final String MINOR = "2";

	/** Valor para teste do campo Build. */
	private static final String BUILD = "3";

	/** Valor para teste da composição de versão com todos os campos. */
	private static final String VERSION = "java-util-1.2.3";

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
	 * Teste para o método {@link Version#setName(String)}.
	 */
	@Test
	public void testSetName() {
		LOGGER.config("Testando Version#setName(String).");
		Version version = new Version();
		version.setName(NAME);
		assertEquals(NAME, version.getName());
	}

	/** 
	 * Teste para o método {@link Version#setMajor(String)}.
	 */
	@Test
	public void testSetMajor() {
		LOGGER.config("Testando Version#setMajor(String).");
		Version version = new Version();
		version.setMajor(MAJOR);
		assertEquals(MAJOR, version.getMajor());
	}

	/** 
	 * Teste para o método {@link Version#setMinor(String)}.
	 */
	@Test
	public void testSetMinor() {
		LOGGER.config("Testando Version#setMinor(String).");
		Version version = new Version();
		version.setMinor(MINOR);
		assertEquals(MINOR, version.getMinor());
	}

	/** 
	 * Teste para o método {@link Version#setBuild(String)}.
	 */
	@Test
	public void testSetBuild() {
		LOGGER.config("Testando Version#setBuild(String).");
		Version version = new Version();
		version.setBuild(BUILD);
		assertEquals(BUILD, version.getBuild());
	}
	
	/**
	 * Teste para o método {@link Version#decode(Package)}.
	 * 
	 * @deprecated Não é mais possível simular a classe Package.
	 */
	@Test
	@Disabled
	public void testDecode() {
		LOGGER.config("Testando Version#decode(Package).");
		Package pack = EasyMock.createMock(Package.class);
		EasyMock.expect(pack.getSpecificationTitle()).andReturn("java-util");
		EasyMock.expect(pack.getImplementationVersion()).andReturn("2.1.5");
		EasyMock.replay(pack);
		
		Version test = new Version();
		test.decode(pack);
		assertEquals(test.getName(), "java-util");
		assertEquals(test.getMajor(), "2");
		assertEquals(test.getMinor(), "1");
		assertEquals(test.getBuild(), "5");
		
		EasyMock.verify(pack);
	}

	/**
	 * Teste para o método {@link Version#decode(Package)} com lenvantamento de exceções.
	 */
	@Test
	public void testDecodeWithExceptions() {
		LOGGER.config("Testando Version#decode(Package) com lenvantamento de exceções.");
		
		assertThrows(VersionRuntimeException.class, () -> {
			Version version = new Version();
			version.decode(null);
		});
	}

	/**
	 * Teste para o método {@link Version#equals(Object)}.
	 */
	@Test
	public void testEquals() {
		LOGGER.config("Testando Version#equals(Object).");
		
		Version v1 = new Version();
		Version v2 = new Version();

		assertTrue(v1.equals(v2));
		assertTrue(v1.equals(v1));

		assertFalse(v1.equals(new Object()));
		assertFalse(v1.equals(null));

		v2.setName(NAME);
		assertFalse(v1.equals(v2));
	}

	/**
	 * Teste para o método {@link Version#hashCode()}.
	 */
	@Test
	public void testHashCode() {
		LOGGER.config("Testando Version#hashCode().");
		Version version = new Version();
		assertTrue(version.hashCode() != 0);
	}

	/**
	 * Teste para o método {@link Version#toString()}.
	 */
	@Test
	public void testToString() {
		Version version = new Version();
		version.setName(NAME);
		version.setMajor(MAJOR);
		version.setMinor(MINOR);
		version.setBuild(BUILD);
		System.out.println(version.toString());
		assertEquals(VERSION, version.toString());
	}
}
