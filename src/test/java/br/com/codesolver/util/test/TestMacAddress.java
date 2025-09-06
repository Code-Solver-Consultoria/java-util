package br.com.codesolver.util.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.InvalidParameterException;
import java.util.logging.Logger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.codesolver.util.MacAddress;

/**
 * Testes unitários para {@link MacAddress}.
 *
 * @author <a href="mailto:luciano@codesolver.com.br">Luciano Vieira Rodrigues</a>
 * @since 2025-09-06
 */
public class TestMacAddress {

    /** Log da classe. */
    private static final Logger LOGGER = Logger.getLogger(TestMacAddress.class.getName());

    /** Valores de teste. */
    private static final byte[] MAC_ADDRESS = {0x01, 0X02, 0X03, 0X04, 0X05, 0X06};

    /** Texto representando o valor de teste. */
    private static final String MAX_ADDRESS_TEXT = "01:02:03:04:05:06";

    /** Construtor padrão. */
    public TestMacAddress() {
    }

    /**
	 * Preparando os testes unitários para {@link MacAddress}.
	 */
	@BeforeAll
	public static void before() {
		LOGGER.info("Inicializando os testes unitários para MacAddress.");
	}

	/**
	 * Finalizando os testes unitários para {@link MacAddress}.
	 */
	@AfterAll
	public static void after() {
		LOGGER.info("Finalizando os testes unitários para MacAddress.");
	}

    /**
     * Cria um novo objeto com parâmetros iniciais connhecidos.
     *
     * @return {@link MacAddress}.
     */
    private MacAddress createWithAddress() {
        int index = 0;
        return new MacAddress(
            MAC_ADDRESS[index++],
            MAC_ADDRESS[index++],
            MAC_ADDRESS[index++],
            MAC_ADDRESS[index++],
            MAC_ADDRESS[index++],
            MAC_ADDRESS[index++]
        );
    }

    /**
     * Teste para o construtor {@link MacAddress#MacAddress()}.
     */
    @Test
    public void testConstructor() {
        LOGGER.config("Testando MacAddress#MacAddress().");
        MacAddress address = new MacAddress();
        assertNotNull(address);
        for (int i = 0; i < MacAddress.SIZE; i++) {
            byte b = address.getOctect(i);
            assertEquals(0, b);
        }
    }

    /**
     * Teste para o construtor {@link MacAddress#MacAddress(byte, byte, byte, byte, byte, byte)}.
     */
    @Test
    public void testConstructorWithParams() {
        LOGGER.config("Testando MacAddress#MacAddress(byte, byte, byte, byte, byte, byte).");
        MacAddress address = createWithAddress();
        assertNotNull(address);
    }

    /**
     * Teste para o método {@link MacAddress#equals(Object)}.
     */
    @Test
    void testEquals() {
        LOGGER.config("Testando MacAddress#equals(Object).");

        MacAddress obj1 = new MacAddress();
        MacAddress obj2 = new MacAddress();
        assertTrue(obj1.equals(obj2));
        assertTrue(obj1.equals(obj1));

        MacAddress obj3 = createWithAddress();
        assertFalse(obj1.equals(obj3));
        assertFalse(obj1.equals(new Object()));
    }

    /**
     * Teste para o método {@link MacAddress#getOctect(int)}.
     */
    @Test
    void testGetOctect() {
        LOGGER.config("Testando MacAddress#getOctect(int).");
        MacAddress address = createWithAddress();
        for (int i = 0; i < MacAddress.SIZE; i++) {
            assertEquals(MAC_ADDRESS[i], address.getOctect(i));
        }

        assertThrows(InvalidParameterException.class, () -> {
            address.getOctect(MacAddress.SIZE);
        });

        assertThrows(InvalidParameterException.class, () -> {
            address.getOctect(-1);
        });
    }

    @Test
    void testHashCode() {
        MacAddress address = new MacAddress();
        assertTrue(address.hashCode() > 0);
    }

    @Test
    void testSetOctect() {
        MacAddress address = new MacAddress();
        for (int i = 0; i < MacAddress.SIZE; i++) {
            address.setOctect(i, MAC_ADDRESS[i]);
        }
        for (int i = 0; i < MacAddress.SIZE; i++) {
            assertEquals(MAC_ADDRESS[i], address.getOctect(i));
        }

        assertThrows(InvalidParameterException.class, () -> {
            address.setOctect(MacAddress.SIZE, (byte) 0);
        });
        assertThrows(InvalidParameterException.class, () -> {
            address.setOctect(-1, (byte) 0);
        });
    }

    @Test
    void testToString() {
        MacAddress address = createWithAddress();
        assertEquals(MAX_ADDRESS_TEXT, address.toString());
    }
}
