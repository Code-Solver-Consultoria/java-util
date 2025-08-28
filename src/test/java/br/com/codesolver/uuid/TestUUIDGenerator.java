package br.com.codesolver.uuid;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.Test;

/**
 * Teste unitários para {@link UUIDGenerator}.
 * 
 * @author <a href="mailto:luciano@codesolver.com.br">Luciano Vieira Rodrigues</a>
 * @since 2025-08-26
 */
public class TestUUIDGenerator {

	/**
	 * Test method for {@link br.com.codesolver.uuid.UUIDGenerator#UUIDGenerator(java.lang.String)}.
	 */
	@Test
	public void testUUIDGenerator() {
		UUIDGenerator generator = new UUIDGenerator("node");
		assertNotNull(generator);
	}
	
	/**
	 * Test method for {@link br.com.codesolver.uuid.UUIDGenerator#UUIDGenerator(java.lang.String)},
	 * com node nulo
	 */
	@Test
	public void testUUIDGeneratorNodeNull() {
		assertThrowsExactly(UUIDGeneratorNodeInvalid.class, () -> {
			new UUIDGenerator(null);
		});
	}
	
	/**
	 * Test method for {@link br.com.codesolver.uuid.UUIDGenerator#UUIDGenerator(java.lang.String)},
	 * com node vazio
	 */
	@Test
	public void testUUIDGeneratorNodeEmpty() {
		assertThrowsExactly(UUIDGeneratorNodeInvalid.class, () -> {
			new UUIDGenerator("");
		});
	}
	
	/**
	 * Test method for {@link br.com.codesolver.uuid.UUIDGenerator#UUIDGenerator(java.lang.String)},
	 * com node inválido por exceder o tamanho.
	 */
	@Test
	public void testUUIDGeneratorNodeInvalid() {
		assertThrowsExactly(UUIDGeneratorNodeInvalid.class, () -> {
			new UUIDGenerator("1234567890");
		});
	}

	/**
	 * Test method for {@link br.com.codesolver.uuid.UUIDGenerator#generate()}.
	 */
	@Test
	public void testGenerate() {
		UUIDGenerator generator = new UUIDGenerator("node");
		assertNotNull(generator);
		
		UUID uuid = generator.generate();
		assertNotNull(uuid);
	}
	
	/**
	 * Test method for {@link br.com.codesolver.uuid.UUIDGenerator#generate()}.
	 * @throws InterruptedException 
	 */
	@Test
	public void testGenerateMulti() throws InterruptedException {
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
	 * Test method for {@link br.com.codesolver.uuid.UUIDGenerator#getDate(java.util.UUID)}.
	 */
	@Test
	public void testGetDate() {
		UUIDGenerator generator = new UUIDGenerator("node");
		assertNotNull(generator);
		
		UUID uuid = generator.generate();
		assertNotNull(uuid);
		
		Date date = UUIDGenerator.getDate(uuid);
		assertNotNull(date);
	}
	
	/**
	 * Test method for {@link br.com.codesolver.uuid.UUIDGenerator#getDate(java.util.UUID)}.
	 */
	@Test
	public void testGetDateErrorType() {
		assertThrowsExactly(UUIDGeneratorVersionInvalid.class, () -> {
			UUID uuid = UUID.randomUUID();
			UUIDGenerator.getDate(uuid);
		});
	}
}
