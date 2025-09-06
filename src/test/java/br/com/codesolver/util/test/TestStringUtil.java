package br.com.codesolver.util.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.logging.Logger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.com.codesolver.util.StringUtil;

/**
 * Testes unitário de manipulação de texto por {@link StringUtil}.
 * 
 * @author <a href="mailto:luciano@codesolver.com.br">Luciano Vieira Rodrigues</a>
 * @since 2025-08-26
 */
public class TestStringUtil {

    /** Log da classe de testes. */
    private static final Logger LOGGER = Logger.getLogger(TestStringUtil.class.getName());

    /** Construtor padrão. */
    public TestStringUtil() {
    }

    /**
	 * Preparando os testes unitários para {@link StringUtil}.
	 */
	@BeforeAll
	public static void before() {
		LOGGER.info("Inicializando os testes unitários para StringUtil.");
	}

	/**
	 * Finalizando os testes unitários para {@link StringUtil}.
	 */
	@AfterAll
	public static void after() {
		LOGGER.info("Finalizando os testes unitários para StringUtil.");
	}

    /**
     * Teste para o método {@link StringUtil#clean(String)}.
     */
    @Test
    public void testClean() {
        LOGGER.config("Testando StringUtil#clean(String).");
        String source = "Luçiano está testando çom èrros";
        String target = "Luciano esta testando com erros";
        String result = StringUtil.clean(source);
        assertNotNull(result);
        assertTrue(result.equals(target));
    }

    /**
     * Teste para o método {@link StringUtil#padRight(String, int)}.
     */
    @Test
    public void testPadRightStringInt() {
        LOGGER.config("Testando StringUtil#padRight(String, int).");
        String source = "teste";
        String target = "teste     ";
        String result = StringUtil.padRight(source, 10);
        assertNotNull(result);
        assertTrue(result.equals(target));
    }

    /**
     * Teste para o método {@link StringUtil#padRight(String, String, int)}.
     */
    @Test
    public void testPadRightStringStringInt() {
        LOGGER.config("Testando StringUtil#padRight(String, String, int).");
        String source = "teste";
        String target = "teste11111";
        String result = StringUtil.padRight(source, "1", 10);
        assertNotNull(result);
        assertTrue(result.equals(target));
    }

    /**
     * Teste para o método {@link StringUtil#padRight(String, String, int)}
     * com texto maior que o tamanho final desejado.
     */
    @Test
    public void testPadRightStringStringIntLessThenLenght() {
        LOGGER.config("Testando StringUtil#padRight(String, String, int) com texto maior que o tamanho final desejado.");
        String source = "teste1111123456";
        String target = "teste11111";
        String result = StringUtil.padRight(source, "9", 10);
        assertNotNull(result);
        assertTrue(result.equals(target));
    }

    /**
     * Teste para o método {@link StringUtil#padRight(String, String, int)}
     * com o preenchimento maior que o tamanho final desejado.
     */
    @Test
    public void testPadRightStringStringIntStuffGreater() {
        LOGGER.config("Testando StringUtil#padRight(String, String, int) com o preenchimento maior que o tamanho final desejado.");
        String source = "teste";
        String target = "testeABCAB";
        String result = StringUtil.padRight(source, "ABC", 10);
        assertNotNull(result);
        assertTrue(result.equals(target));
    }

    /**
     * Teste para o método {@link StringUtil#padLeft(String, int)}.
     */
    @Test
    public void testPadLeftStringInt() {
        LOGGER.config("Testando StringUtil#padLeft(String, int).");
        String source = "teste";
        String target = "     teste";
        String result = StringUtil.padLeft(source, 10);
        assertNotNull(result);
        assertTrue(result.equals(target));
    }

    /**
     * Teste para o método {@link StringUtil#padLeft(String, String, int)}.
     */
    @Test
    public void testPadLeftStringStringInt() {
        LOGGER.config("Testando StringUtil#padLeft(String, String, int).");
        String source = "teste";
        String target = "AAAAAteste";
        String result = StringUtil.padLeft(source, "A", 10);
        assertNotNull(result);
        assertTrue(result.equals(target));
    }

    /**
     * Teste para o método {@link StringUtil#padLeft(String, String, int)}
     * com texto maior que o tamanho desejado.
     */
    @Test
    public void testPadLeftStringStringIntLessThenLength() {
        LOGGER.config("Testando StringUtil#padLeft(String, String, int) com texto maior que o tamanho desejado.");
        String source = "12345AAAAAteste";
        String target = "AAAAAteste";
        String result = StringUtil.padLeft(source, "A", 10);
        assertNotNull(result);
        assertTrue(result.equals(target));
    }

    /**
     * Teste para o método {@link StringUtil#padLeft(String, String, int)}
     * com texto maior que o tamanho desejado.
     */
    @Test
    public void testPadLeftStringStringIntStuffGreater() {
        LOGGER.config("Testando StringUtil#padLeft(String, String, int) com texto maior que o tamanho desejado.");
        String source = "teste";
        String target = "BCABCteste";
        String result = StringUtil.padLeft(source, "ABC", 10);
        assertNotNull(result);
        assertTrue(result.equals(target));
    }

    /**
     * Teste para o método {@link StringUtil#leftZero(int, int)}.
     */
    @Test
    public void testLeftZero() {
        LOGGER.config("Testando StringUtil#leftZero(int, int).");
        String target = "0000000010";
        String result = StringUtil.leftZero(10, 10);
        assertNotNull(result);
        assertTrue(result.equals(target));
    }

    /**
     * Teste para o método {@link StringUtil#toHex(byte[])}.
     */
    @Test
    public void testToHex() {
        LOGGER.config("Testando StringUtil#toHex(byte[]).");
        byte[] buffer = {'A', 'B', 'C', 'D', 'E', 'a', 'b', 'c', 'd', 'e'};
        String target = "41424344456162636465";
        String result = StringUtil.toHex(buffer);
        assertNotNull(result);
        assertTrue(result.length() == (buffer.length * 2));
        assertTrue(result.equals(target));
    }

    /**
     * Teste para o método {@link StringUtil#toHex(byte[])}
     * discartando os zeros iniciais.
     */
    @Test
    public void testToHexDiscardZeros() {
        LOGGER.config("Testando StringUtil#toHex(byte[]) discartando os zeros iniciais.");
        byte[] buffer = {0, 'A', 'B', 'C', 'D', 'E', 0, 'a', 'b', 'c', 'd', 'e'};
        String target = "4142434445006162636465";
        String result = StringUtil.toHex(buffer);
        assertNotNull(result);
        assertTrue(result.length() == ((buffer.length - 1)* 2)); // 1 = zeros discartados
        assertTrue(result.equals(target));
    }

    /**
     * Teste para o método {@link StringUtil#toHex(byte[])}
     * discartando os zeros iniciais.
     */
    @Test
    public void testToHexIgnoringAppendZeros() {
        LOGGER.config("Testando StringUtil#toHex(byte[]) discartando os zeros iniciais.");
        byte[] buffer = {1, 'A', 'B', 'C', 'D', 'E', 0, 'a', 'b', 'c', 'd', 'e'};
        String target = "14142434445006162636465";
        String result = StringUtil.toHex(buffer);
        assertNotNull(result);
        assertTrue(result.length() == ((buffer.length* 2) - 1)); // 1 = zeros discartados no início do hash
        assertTrue(result.equals(target));
    }

    /**
     * Teste para o método {@link StringUtil#toHex(byte[])}
     * adicionando zeros no início para manter o tamanho coerente.
     */
    @Test
    public void testToHexSufftZeros() {
        LOGGER.config("Testando {@link StringUtil#toHex(byte[])} adicionando zeros no início para manter o tamanho coerente.");
        byte[] buffer = {'A', 'B', 'C', 'D', 'E', 'a', 'b', 'c', 'd', 'e', 1};
        String target = "4142434445616263646501";
        String result = StringUtil.toHex(buffer);
        assertNotNull(result);
        assertTrue(result.length() == (buffer.length* 2));
        assertTrue(result.equals(target));
    }

    /**
     * Teste para o método {@link StringUtil#toHexSpaced(byte[], int)}.
     */
    @Test
    public void testToHexSpacedByteArrayInt() {
        LOGGER.config("Testando StringUtil#toHexSpaced(byte[], int).");
        byte[] buffer = {'A', 'B', 'C', 'D', 'E', 'a', 'b', 'c', 'd', 'e'};
        String target = "41 42 43 44 45";
        int length    = 5;
        String result = StringUtil.toHexSpaced(buffer, length);
        assertNotNull(result);
        assertTrue(result.equals(target));
    }

    /**
     * Teste para o método {@link StringUtil#toHexSpaced(byte[])}.
     */
    @Test
    public void testToHexSpacedByteArray() {
        LOGGER.config("Testando StringUtil#toHexSpaced(byte[]).");
        byte[] buffer = {'A', 'B', 'C', 'D', 'E', 'a', 'b', 'c', 'd', 'e'};
        String target = "41 42 43 44 45 61 62 63 64 65";
        String result = StringUtil.toHexSpaced(buffer);
        assertNotNull(result);
        assertTrue(result.equals(target));
    }

    /**
     * Teste para o método {@link StringUtil#toHexSpaced(byte[])}
     * adicionando zeros para normalizar o HASH.
     */
    @Test
    public void testToHexSpacedByteArrayAppendingZeros() {
        LOGGER.config("Testando StringUtil#toHexSpaced(byte[]) adicionando zeros para normalizar o HASH.");
        byte[] buffer = {2, 'A', 'B', 'C', 'D', 'E', 'a', 'b', 'c', 'd', 'e', 1};
        String target = "02 41 42 43 44 45 61 62 63 64 65 01";
        String result = StringUtil.toHexSpaced(buffer);
        assertNotNull(result);
        assertTrue(result.equals(target));
    }

    /**
     * Teste para o método {@link StringUtil#hexToBytes(String)}.
     */
    @Test
    public void testHexToBytes() {
        LOGGER.config("Testando StringUtil#hexToBytes(String).");
        String source = "41424344456162636465";
        byte[] target = {'A', 'B', 'C', 'D', 'E', 'a', 'b', 'c', 'd', 'e'};
        byte[] result = StringUtil.hexToBytes(source);
        assertNotNull(result);
        assertTrue(result.length == target.length);
        for (int i = 0; i < target.length; i++) {
            assertEquals(target[i], result[i]);
        }
    }

    /**
     * Teste para o método {@link StringUtil#hexToBytes(String)}.
     */
    @Test
    public void testHexToBytesAppendZeros() {
        LOGGER.config("Testando StringUtil#hexToBytes(String).");
        String source = "141424344456162636465";
        byte[] target = {1, 'A', 'B', 'C', 'D', 'E', 'a', 'b', 'c', 'd', 'e'};
        byte[] result = StringUtil.hexToBytes(source);
        assertNotNull(result);
        assertTrue(result.length == target.length);
        for (int i = 0; i < target.length; i++) {
            assertEquals(target[i], result[i]);
        }
    }

    /**
     * Teste para o método {@link StringUtil#hexToBytes(String)}.
     */
    @Test
    public void testHexToBytesRemoveSpaces() {
        LOGGER.config("Testando StringUtil#hexToBytes(String).");
        String source = "1 41 42 43 44 45 61 62 63 64 65";
        byte[] target = {1, 'A', 'B', 'C', 'D', 'E', 'a', 'b', 'c', 'd', 'e'};
        byte[] result = StringUtil.hexToBytes(source);
        assertNotNull(result);
        assertTrue(result.length == target.length);
        for (int i = 0; i < target.length; i++) {
            assertEquals(target[i], result[i]);
        }
    }

    /**
     * Teste para o método {@link StringUtil#decodeIPv4(byte[])}.
     */
    @Test
    public void testDecodeIPv4() {
        LOGGER.config("Testando StringUtil#decodeIPv4(byte[]).");
        assertEquals("255.255.255.255", StringUtil.decodeIPv4(new byte[] {(byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF}));
        assertEquals("0.0.0.0", StringUtil.decodeIPv4(new byte[] {(byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00}));
        assertEquals("1.1.1.1", StringUtil.decodeIPv4(new byte[] {(byte)0x01, (byte)0x01, (byte)0x01, (byte)0x01}));
    }


    /**
     * Teste para o método {@link StringUtil#decodeIPv4(byte[])}
     * com lançamento de exceções.
     */
    @Test
    public void testDecodeIPv4Exceptions() {
        LOGGER.config("Testando StringUtil#decodeIPv4(byte[]) com lançamento de exceções.");
        assertThrows(IllegalArgumentException.class, () -> {
            StringUtil.decodeIPv4(null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            StringUtil.decodeIPv4(new byte[] {0x00});
        });
    }

    /**
     * Teste para o método {@link StringUtil#encodeIPv4(String)}.
     */
    @Test
    public void testEncodeIPv4() {
        LOGGER.config("Testando StringUtil#encodeIPv4(String).");

        byte[] octectos = StringUtil.encodeIPv4("255.255.255.255");
        assertTrue(4 == octectos.length);
        assertTrue(octectos[0] == (byte)0xFF);
        assertTrue(octectos[1] == (byte)0xFF);
        assertTrue(octectos[2] == (byte)0xFF);
        assertTrue(octectos[3] == (byte)0xFF);

        octectos = StringUtil.encodeIPv4("0.0.0.0");
        assertTrue(4 == octectos.length);
        assertTrue(octectos[0] == (byte)0x00);
        assertTrue(octectos[1] == (byte)0x00);
        assertTrue(octectos[2] == (byte)0x00);
        assertTrue(octectos[3] == (byte)0x00);

        octectos = StringUtil.encodeIPv4("170.170.170.170");
        assertTrue(4 == octectos.length);
        assertTrue(octectos[0] == (byte)0xAA);
        assertTrue(octectos[1] == (byte)0xAA);
        assertTrue(octectos[2] == (byte)0xAA);
        assertTrue(octectos[3] == (byte)0xAA);
    }

    /**
     * Teste para o método {@link StringUtil#encodeIPv4(String)}
     * com lançamento de exceções.
     */
    @Test
    public void testEncodeIPv4Exceptions() {
        LOGGER.config("Testando StringUtil#encodeIPv4(String) com lançamento de exeções.");

        assertThrows(IllegalArgumentException.class, () -> {
            StringUtil.encodeIPv4(null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            StringUtil.encodeIPv4("");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            StringUtil.encodeIPv4("#$%T$%G#$");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            StringUtil.encodeIPv4("256.256.256.256");
        });
    }
}
