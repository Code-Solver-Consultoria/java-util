package br.com.codesolver.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Testes unitário de manipulação de texto por {@link br.com.codesolver.util.StringUtil}.
 * 
 * @author <a href="mailto:luciano@codesolver.com.br">Luciano Vieira Rodrigues</a>
 * @since 2025-08-26
 */
public class TestStringUtil {

    /**
     * Test method for {@link br.com.codesolver.util.StringUtil#clean(java.lang.String)}.
     */
    @Test
    public void testClean() {
        String source = "Luçiano está testando çom èrros";
        String target = "Luciano esta testando com erros";
        String result = StringUtil.clean(source);
        assertNotNull(result);
        assertTrue(result.equals(target));
    }

    /**
     * Test method for {@link br.com.codesolver.util.StringUtil#padRight(java.lang.String, int)}.
     */
    @Test
    public void testPadRightStringInt() {
        String source = "teste";
        String target = "teste     ";
        String result = StringUtil.padRight(source, 10);
        assertNotNull(result);
        assertTrue(result.equals(target));
    }

    /**
     * Test method for {@link br.com.codesolver.util.StringUtil#padRight(java.lang.String, java.lang.String, int)}.
     */
    @Test
    public void testPadRightStringStringInt() {
        String source = "teste";
        String target = "teste11111";
        String result = StringUtil.padRight(source, "1", 10);
        assertNotNull(result);
        assertTrue(result.equals(target));
    }

    /**
     * Test method for {@link br.com.codesolver.util.StringUtil#padRight(java.lang.String, java.lang.String, int)},
     * com texto maior que o tamanho final desejado.
     */
    @Test
    public void testPadRightStringStringIntLessThenLenght() {
        String source = "teste1111123456";
        String target = "teste11111";
        String result = StringUtil.padRight(source, "9", 10);
        assertNotNull(result);
        assertTrue(result.equals(target));
    }

    /**
     * Test method for {@link br.com.codesolver.util.StringUtil#padRight(java.lang.String, java.lang.String, int)},
     * com o preenchimento maior que o tamanho final desejado.
     */
    @Test
    public void testPadRightStringStringIntStuffGreater() {
        String source = "teste";
        String target = "testeABCAB";
        String result = StringUtil.padRight(source, "ABC", 10);
        assertNotNull(result);
        assertTrue(result.equals(target));
    }

    /**
     * Test method for {@link br.com.codesolver.util.StringUtil#padLeft(java.lang.String, int)}.
     */
    @Test
    public void testPadLeftStringInt() {
        String source = "teste";
        String target = "     teste";
        String result = StringUtil.padLeft(source, 10);
        assertNotNull(result);
        assertTrue(result.equals(target));
    }

    /**
     * Test method for {@link br.com.codesolver.util.StringUtil#padLeft(java.lang.String, java.lang.String, int)}.
     */
    @Test
    public void testPadLeftStringStringInt() {
        String source = "teste";
        String target = "AAAAAteste";
        String result = StringUtil.padLeft(source, "A", 10);
        assertNotNull(result);
        assertTrue(result.equals(target));
    }

    /**
     * Test method for {@link br.com.codesolver.util.StringUtil#padLeft(java.lang.String, java.lang.String, int)},
     * com texto maior que o tamanho desejado.
     */
    @Test
    public void testPadLeftStringStringIntLessThenLength() {
        String source = "12345AAAAAteste";
        String target = "AAAAAteste";
        String result = StringUtil.padLeft(source, "A", 10);
        assertNotNull(result);
        assertTrue(result.equals(target));
    }

    /**
     * Test method for {@link br.com.codesolver.util.StringUtil#padLeft(java.lang.String, java.lang.String, int)},
     * com texto maior que o tamanho desejado.
     */
    @Test
    public void testPadLeftStringStringIntStuffGreater() {
        String source = "teste";
        String target = "BCABCteste";
        String result = StringUtil.padLeft(source, "ABC", 10);
        assertNotNull(result);
        assertTrue(result.equals(target));
    }

    /**
     * Test method for {@link br.com.codesolver.util.StringUtil#leftZero(int, int)}.
     */
    @Test
    public void testLeftZero() {
        String target = "0000000010";
        String result = StringUtil.leftZero(10, 10);
        assertNotNull(result);
        assertTrue(result.equals(target));
    }

    /**
     * Test method for {@link br.com.codesolver.util.StringUtil#toHex(byte[])}.
     */
    @Test
    public void testToHex() {
        byte[] buffer = {'A', 'B', 'C', 'D', 'E', 'a', 'b', 'c', 'd', 'e'};
        String target = "41424344456162636465";
        String result = StringUtil.toHex(buffer);
        assertNotNull(result);
        assertTrue(result.length() == (buffer.length * 2));
        assertTrue(result.equals(target));
    }

    /**
     * Test method for {@link br.com.codesolver.util.StringUtil#toHex(byte[])},
     * discartando os zeros iniciais.
     */
    @Test
    public void testToHexDiscardZeros() {
        byte[] buffer = {0, 'A', 'B', 'C', 'D', 'E', 0, 'a', 'b', 'c', 'd', 'e'};
        String target = "4142434445006162636465";
        String result = StringUtil.toHex(buffer);
        assertNotNull(result);
        assertTrue(result.length() == ((buffer.length - 1)* 2)); // 1 = zeros discartados
        assertTrue(result.equals(target));
    }

    /**
     * Test method for {@link br.com.codesolver.util.StringUtil#toHex(byte[])},
     * discartando os zeros iniciais.
     */
    @Test
    public void testToHexIgnoringAppendZeros() {
        byte[] buffer = {1, 'A', 'B', 'C', 'D', 'E', 0, 'a', 'b', 'c', 'd', 'e'};
        String target = "14142434445006162636465";
        String result = StringUtil.toHex(buffer);
        assertNotNull(result);
        assertTrue(result.length() == ((buffer.length* 2) - 1)); // 1 = zeros discartados no início do hash
        assertTrue(result.equals(target));
    }

    /**
     * Test method for {@link br.com.codesolver.util.StringUtil#toHex(byte[])},
     * adicionando zeros no início para manter o tamanho coerente.
     */
    @Test
    public void testToHexSufftZeros() {
        byte[] buffer = {'A', 'B', 'C', 'D', 'E', 'a', 'b', 'c', 'd', 'e', 1};
        String target = "4142434445616263646501";
        String result = StringUtil.toHex(buffer);
        assertNotNull(result);
        assertTrue(result.length() == (buffer.length* 2));
        assertTrue(result.equals(target));
    }

    /**
     * Test method for {@link br.com.codesolver.util.StringUtil#toHexSpaced(byte[], int)}.
     */
    @Test
    public void testToHexSpacedByteArrayInt() {
        byte[] buffer = {'A', 'B', 'C', 'D', 'E', 'a', 'b', 'c', 'd', 'e'};
        String target = "41 42 43 44 45";
        int length    = 5;
        String result = StringUtil.toHexSpaced(buffer, length);
        assertNotNull(result);
        assertTrue(result.equals(target));
    }

    /**
     * Test method for {@link br.com.codesolver.util.StringUtil#toHexSpaced(byte[])}.
     */
    @Test
    public void testToHexSpacedByteArray() {
        byte[] buffer = {'A', 'B', 'C', 'D', 'E', 'a', 'b', 'c', 'd', 'e'};
        String target = "41 42 43 44 45 61 62 63 64 65";
        String result = StringUtil.toHexSpaced(buffer);
        assertNotNull(result);
        assertTrue(result.equals(target));
    }

    /**
     * Test method for {@link br.com.codesolver.util.StringUtil#toHexSpaced(byte[])},
     * adicionando zeros para normalizar o HASH.
     */
    @Test
    public void testToHexSpacedByteArrayAppendingZeros() {
        byte[] buffer = {2, 'A', 'B', 'C', 'D', 'E', 'a', 'b', 'c', 'd', 'e', 1};
        String target = "02 41 42 43 44 45 61 62 63 64 65 01";
        String result = StringUtil.toHexSpaced(buffer);
        assertNotNull(result);
        assertTrue(result.equals(target));
    }

    /**
     * Test method for {@link br.com.codesolver.util.StringUtil#hexToBytes(java.lang.String)}.
     */
    @Test
    public void testHexToBytes() {
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
     * Test method for {@link br.com.codesolver.util.StringUtil#hexToBytes(java.lang.String)}.
     */
    @Test
    public void testHexToBytesAppendZeros() {
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
     * Test method for {@link br.com.codesolver.util.StringUtil#hexToBytes(java.lang.String)}.
     */
    @Test
    public void testHexToBytesRemoveSpaces() {
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
     * Test method for {@link br.com.codesolver.util.StringUtil#decodeIPv4(byte[])}.
     */
    @Test
    public void test1DecodeIPv4() {
        assertEquals("255.255.255.255", StringUtil.decodeIPv4(new byte[] {(byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF}));
        assertEquals("0.0.0.0", StringUtil.decodeIPv4(new byte[] {(byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00}));
        assertEquals("1.1.1.1", StringUtil.decodeIPv4(new byte[] {(byte)0x01, (byte)0x01, (byte)0x01, (byte)0x01}));
    }


    @Test
    public void test2DecodeIPv4() {
        boolean throwException = false;
        try {
            StringUtil.decodeIPv4(null);
        } catch(IllegalArgumentException e) {
            throwException = true;
        }
        if(!throwException) {
            throw new AssertionError();
        }

        throwException = false;
        try {
            StringUtil.decodeIPv4(new byte[] {0x00});
        } catch(IllegalArgumentException e) {
            throwException = true;
        }
        if(!throwException) {
            throw new AssertionError();
        }
    }

    @Test
    public void test1EncodeIPv4() {

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

    @Test
    public void test2EncodeIPv4() {

        boolean throwException = false;

        try {
            StringUtil.encodeIPv4(null);
        } catch (IllegalArgumentException e) {
            throwException = true;
        }
        if(!throwException) {
            throw new AssertionError();
        }

        throwException = false;

        try {
            StringUtil.encodeIPv4("");
        } catch (IllegalArgumentException e) {
            throwException = true;
        }
        if(!throwException) {
            throw new AssertionError();
        }

        throwException = false;

        try {
            StringUtil.encodeIPv4("#$%T$%G#$");
        } catch (IllegalArgumentException e) {
            throwException = true;
        }
        if(!throwException) {
            throw new AssertionError();
        }

        throwException = false;

        try {
            StringUtil.encodeIPv4("256.256.256.256");
        } catch (IllegalArgumentException e) {
            throwException = true;
        }
        if(!throwException) {
            throw new AssertionError();
        }
    }

    @Test
    public void testEncodeDecodeIPv4() {
        assertEquals("255.255.255.255", StringUtil.decodeIPv4(StringUtil.encodeIPv4("255.255.255.255")));
        assertEquals("0.0.0.0", StringUtil.decodeIPv4(StringUtil.encodeIPv4("0.0.0.0")));
        assertEquals("1.1.1.1", StringUtil.decodeIPv4(StringUtil.encodeIPv4("1.1.1.1")));
        assertEquals("1.2.3.4", StringUtil.decodeIPv4(StringUtil.encodeIPv4("1.2.3.4")));
        assertEquals("10.104.10.8", StringUtil.decodeIPv4(StringUtil.encodeIPv4("10.104.10.8")));
        assertEquals("192.198.196.88", StringUtil.decodeIPv4(StringUtil.encodeIPv4("192.198.196.88")));
    }

}
