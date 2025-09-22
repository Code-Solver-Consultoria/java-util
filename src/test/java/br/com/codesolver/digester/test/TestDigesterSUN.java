package br.com.codesolver.digester.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.Charset;
import java.util.logging.Logger;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import br.com.codesolver.digester.AlgorithmType;
import br.com.codesolver.digester.DigesterFactory;
import br.com.codesolver.digester.DigesterSUN;

/**
 * Testes unitários para {@link DigesterSUN}. 
 *
 * @author <a href="mailto:luciano@codesolver.com.br">Luciano Vieira Rodrigues</a>
 * @since 2025-09-05
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestDigesterSUN {
    
    /** Log da classe.s */
    private static final Logger LOGGER = Logger.getLogger(TestDigesterSUN.class.getName());

    /**
     * Palavra usada para testes de HASH.
     */
    private static final String TEXT = "luciano";

    /**
     * Matriz de bytes representando a palavra usada nos testes.
     */
    private static final byte[] TEXT_BYTES = TEXT.getBytes(Charset.defaultCharset());

    /**
     * Hash da palavra <b>luciano</b> com algorítimo SHA-256.
     */
    private static final String TEXT_SHA_256 = "94dbbde77990245afe521519e98cf5a48fba5cb5cec9c11422ef3d21bea088f";

    /**
     * Hash quando nenhuma informação foi passada para o {@link DigesterSUN}.
     */
    private static final String NULL_HASH_SHA_256 = "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855";

    /** Objeto de Teste. */
    private static DigesterSUN digester;

    /**
     * HASH recuperado pelo método {@link DigesterSUN#digest()} e utilizado para testar
     * outros métodos.
     */
    private static String hash;

    /** Construtor padrão. */
    public TestDigesterSUN() {
    }

    /**
	 * Preparando a classe para teste de {@link DigesterSUN}.
	 */
	@BeforeAll
	public static void beforeClass() {
		LOGGER.info("Inicializando os testes para DigesterSUN.");
        digester = DigesterFactory.getInstance(AlgorithmType.SHA_256);
        hash = null;
	}

	/**
	 * Finalizando os testes da classe {@link DigesterSUN}.
	 */
	@AfterAll
	public static void afterClass() {
		LOGGER.info("Finalizando os testes para DigesterSUN.");
        digester = null;
        hash = null;
	}

    /**
     * Teste para o método {@link DigesterSUN#update(byte[])}.
     */
    @Test
    @Order(1)
    void testUpdate() {
        LOGGER.config("Testando DigesterSUN#update(byte[]).");
        digester.update(TEXT_BYTES);
    }

    /**
     * Teste para o método {@link DigesterSUN#digest()}.
     */
    @Test
    @Order(2)
    void testDigest() {
        LOGGER.config("Testando DigesterSUN#digest().");
        hash = digester.digest();
        assertEquals(TEXT_SHA_256, hash);
    }

    /**
     * Teste para o método {@link DigesterSUN#verify(String, String)}.
     */
    @Test
    @Order(3)
    void testVerifyStringString() {
        LOGGER.config("Testando DigesterSUN#verify(String, String).");
        assertTrue(digester.verify(TEXT, hash));
    }

    /**
     * Teste para o método {@link DigesterSUN#verify(byte[], String)}.
     */
    @Test
    @Order(3)
    void testVerifyBytesString() {
        LOGGER.config("Testando DigesterSUN#verify(byte[], String).");
        assertTrue(digester.verify(TEXT_BYTES, hash));
    }

    /**
     * Teste para o método {@link DigesterSUN#parse(String)}.
     */
    @Test
    @Order(4)
    void testParseString() {
        LOGGER.config("Testando DigesterSUN#parse(String).");
        String text = digester.parse(TEXT);
        assertEquals(TEXT_SHA_256, text);
    }

    /**
     * Teste para o método {@link DigesterSUN#parse(byte[])}.
     */
    @Test
    @Order(4)
    void testParseBytes() {
        LOGGER.config("Testando DigesterSUN#parse(byte[]).");
        String text = digester.parse(TEXT_BYTES);
        assertEquals(TEXT_SHA_256, text);
    }

    /**
     * Teste para o método {@link DigesterSUN#reset()}.
     */
    @Test
    @Order(5)
    void testReset() {
        LOGGER.config("Testando DigesterSUN#reset().");
        digester.reset();
        String hash = digester.digest();
        assertEquals(NULL_HASH_SHA_256, hash);
    }
}
