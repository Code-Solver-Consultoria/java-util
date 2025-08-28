package br.com.codesolver.uuid;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.Date;
import java.util.UUID;
import java.util.logging.Logger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Teste unitários para {@link UUIDGenerator}.
 * 
 * @author <a href="mailto:luciano@codesolver.com.br">Luciano Vieira Rodrigues</a>
 * @since 2025-08-26
 */
public class TestUUIDGenerator {

	/** Log da classe. */
	private static final Logger logger = Logger.getLogger(TestUUIDGenerator.class.getName());

	/**
	 * Preparando os testes unitários para TestUUIDGenerator.
	 */
	@BeforeAll
	public static void before() {
		logger.info("Preparando os testes unitários para TestUUIDGenerator.");
	}

	/**
	 * Finalizando os testes unitários para TestUUIDGenerator.
	 */
	@AfterAll
	public static void after() {
		logger.info("Finalizando os testes unitários para TestUUIDGenerator.");
	}

	/**
	 * Teste unitário para {@link br.com.codesolver.uuid.UUIDGenerator#UUIDGenerator(java.lang.String)}.
	 */
	@Test
	public void testUUIDGenerator() {
		logger.config("Teste unitário para br.com.codesolver.uuid.UUIDGenerator#UUIDGenerator(java.lang.String).");
		UUIDGenerator generator = new UUIDGenerator("node");
		assertNotNull(generator);
	}
	
	/**
	 * Teste unitário para {@link br.com.codesolver.uuid.UUIDGenerator#UUIDGenerator(java.lang.String)}
	 * com node nulo.
	 */
	@Test
	public void testUUIDGeneratorNodeNull() {
		logger.config("Teste unitário para br.com.codesolver.uuid.UUIDGenerator#UUIDGenerator(java.lang.String) com node nulo.");
		assertThrowsExactly(UUIDGeneratorNodeInvalid.class, () -> {
			new UUIDGenerator(null);
		});
	}
	
	/**
	 * Teste unitário para {@link br.com.codesolver.uuid.UUIDGenerator#UUIDGenerator(java.lang.String)}
	 * com node vazio.
	 */
	@Test
	public void testUUIDGeneratorNodeEmpty() {
		logger.config("Teste unitário para br.com.codesolver.uuid.UUIDGenerator#UUIDGenerator(java.lang.String) com node vazio.");
		assertThrowsExactly(UUIDGeneratorNodeInvalid.class, () -> {
			new UUIDGenerator("");
		});
	}
	
	/**
	 * Teste unitário para {@link br.com.codesolver.uuid.UUIDGenerator#UUIDGenerator(java.lang.String)}
	 * com node inválido por exceder o tamanho.
	 */
	@Test
	public void testUUIDGeneratorNodeInvalid() {
		logger.config("Teste unitário para br.com.codesolver.uuid.UUIDGenerator#UUIDGenerator(java.lang.String) com node inválido por exceder o tamanho.");
		assertThrowsExactly(UUIDGeneratorNodeInvalid.class, () -> {
			new UUIDGenerator("1234567890");
		});
	}

	/**
	 * Teste unitário para {@link br.com.codesolver.uuid.UUIDGenerator#generate()}.
	 */
	@Test
	public void testGenerate() {
		logger.config("Teste unitário para br.com.codesolver.uuid.UUIDGenerator#generate().");
		UUIDGenerator generator = new UUIDGenerator("node");
		assertNotNull(generator);
		
		UUID uuid = generator.generate();
		assertNotNull(uuid);
	}
	
	/**
	 * Teste unitário para {@link br.com.codesolver.uuid.UUIDGenerator#generate()}.
	 */
	@Test
	public void testGenerateMulti() {
		logger.config("Teste unitário para br.com.codesolver.uuid.UUIDGenerator#generate().");
		UUIDGenerator generator = new UUIDGenerator("node");
		assertNotNull(generator);
		
		UUID uuid1 = generator.generate();
		UUID uuid2 = generator.generate();
		UUID uuid3 = generator.generate();
		UUID uuid4 = generator.generate();
		assertNotNull(uuid1);
		assertNotNull(uuid2);
		assertNotNull(uuid3);
		assertNotNull(uuid4);
		assertNotSame(uuid1, uuid2);
		assertNotSame(uuid1, uuid3);
		assertNotSame(uuid1, uuid4);
		assertNotSame(uuid2, uuid3);
		assertNotSame(uuid2, uuid4);
		assertNotSame(uuid3, uuid4);
	}


	/**
	 * Teste unitário para {@link br.com.codesolver.uuid.UUIDGenerator#getDate(java.util.UUID)}.
	 */
	@Test
	public void testGetDate() {
		logger.config("Teste unitário para br.com.codesolver.uuid.UUIDGenerator#getDate(java.util.UUID).");
		UUIDGenerator generator = new UUIDGenerator("node");
		assertNotNull(generator);
		
		UUID uuid = generator.generate();
		assertNotNull(uuid);
		
		Date date = UUIDGenerator.getDate(uuid);
		assertNotNull(date);
	}
	
	/**
	 * Teste unitário para {@link br.com.codesolver.uuid.UUIDGenerator#getDate(java.util.UUID)} 
	 * com UUID diferente do tipo 1.
	 */
	@Test
	public void testGetDateErrorType() {
		logger.config("Teste unitário para br.com.codesolver.uuid.UUIDGenerator#getDate(java.util.UUID) com UUID diferente do tipo 1.");
		assertThrowsExactly(UUIDGeneratorVersionInvalid.class, () -> {
			UUID uuid = UUID.randomUUID();
			UUIDGenerator.getDate(uuid);
		});
	}
}
