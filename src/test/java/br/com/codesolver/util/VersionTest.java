package br.com.codesolver.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

/** 
 * Testes unitários para {@link Version}.
 * 
 * @author <a href="mailto:luciano@codesolver.com.br">Luciano Vieira Rodrigues</a>
 * @since 2025-08-26
 */
public class VersionTest {
	
	@Test
	public void testVersion() {
		Version test = new Version();
		assertNotNull(test);
		assertEquals(test.getMajor(), "0");
		assertEquals(test.getMinor(), "0");
		assertEquals(test.getBuild(), "0");
	}
	
	@Test
	public void testDecode() {
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
