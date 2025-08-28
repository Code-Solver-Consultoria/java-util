package br.com.codesolver.digester;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.logging.Logger;

import br.com.codesolver.util.StringUtil;

/**
 * Cálculo de HASH.
 *
 * @author <a href="mailto:luciano@codesolver.com.br">Luciano Vieira Rodrigues</a>
 * @since 2025-08-26
 */
public class DigesterSUN implements Digester {

    /** Log da classe. */
    private static final Logger LOGGER = Logger.getLogger(DigesterSUN.class.getName());

    /** Digester utilizado para calculo do hash. */
    private MessageDigest digester;

    /**
     * Prepara o componente de calculo de HASH.
     *
     * @param algorithm {@link AlgorithmType}.
     * @throws DigesterException Algorítimo inválido.
     */
    public DigesterSUN(AlgorithmType algorithm) {
        String message;
        if (algorithm == null) {
            message = "Algoritimo não pode ser nulo.";
            LOGGER.severe(message);
            throw new DigesterException(message);
        }
        message = MessageFormat.format("Preparando o algoritimo {0}.", algorithm.name());
        LOGGER.fine(message);
        try {
            digester = MessageDigest.getInstance(algorithm.getValue());
        } catch (NoSuchAlgorithmException e) {
            message = MessageFormat.format("Algoritimo {0} inválido.", algorithm.name());
            LOGGER.severe(message);
            throw new DigesterException(message, e);
        }
    }

    /**
     * Calcula o HASH para a matriz de bytes enviada.
     *
     * @param value Matriz de bytes.
     * @return HASH.
     */
    public String parse(byte[] value) {
        digester.reset();
        digester.update(value);
        byte[] buffer = digester.digest();
        return StringUtil.toHex(buffer);
    }

    /**
     * Calcula o HASH para um texto informado.
     *
     * @param value Texto para calcular o HASH.
     * @return HASH.
     */
    public String parse(String value) {
        return parse(value.getBytes());
    }

    /**
     * Verifica a integridade de uma informação.
     *
     * @param value Sequência para ser verificada.
     * @param hash Assinatura HASH.
     * @return Verdadeiro se o HASH coincidir.
     */
    public boolean verify(byte[] value, String hash) {
        String aux = parse(value);
        return aux.equals(hash);
    }

    /**
     * Verifica a integridade de uma informação.
     *
     * @param value Texto para ser verificado.
     * @param hash Assinatura HASH.
     * @return Verdadeiro se o HASH coincidir.
     */
    public boolean verify(String value, String hash) {
        return verify(value.getBytes(), hash);
    }

    /**
     * Reinicia o processo de calculo do HASH.
     */
    @Override
    public void reset() {
        digester.reset();
    }

    /**
     * Acumula informações para o calculo do HASH.
     *
     * @param buffer Sequência para ser acumulada.
     */
    @Override
    public void update(byte[] buffer) {
        digester.update(buffer);
    }

    /**
     * Calcula o hash e retorna sua representação em texto.
     *
     * @return HASH hexadecimal, representado em forma de texto.
     */
    @Override
    public String digest() {
        return StringUtil.toHex(digester.digest());
    }
}
