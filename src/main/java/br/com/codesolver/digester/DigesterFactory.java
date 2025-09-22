package br.com.codesolver.digester;

import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.logging.Logger;

/**
 * Cria o processador de HASH adequado para o algorítimo informado.
 *
 * @author <a href="mailto:luciano@codesolver.com.br">Luciano Vieira Rodrigues</a>
 * @since 2025-08-26
 * @see AlgorithmType
 */
public final class DigesterFactory {

	/** Log da classe. */
	private static final Logger LOGGER = Logger.getLogger(DigesterFactory.class.getName());

	/** 
	 * Construtor oculto.
	 */
	private DigesterFactory() {
	}

	/**
	 * Recupera o processador de HASH adequando para o algorítimo.
	 *
	 * @param <T> Um descendente de {@link Digester}.
	 * @param algorithm {@link AlgorithmType}.
	 * @return {@link Digester}.
	 * @throws DigesterException Algorítimo inválido.
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Digester> T getInstance(AlgorithmType algorithm) {
		if (algorithm == null) {
            String message = "Algoritimo não pode ser nulo.";
            LOGGER.severe(message);
            throw new DigesterException(message);
        }
		T result = null;
		try {
			switch (algorithm) {
				case CRC16: 
					result = (T) new DigesterCRC16();
					break;
				default:
					result = (T) new DigesterSUN(algorithm);
			}
		} catch (NoSuchAlgorithmException e) {
			String message = MessageFormat.format("Algoritimo {0} inválido.", algorithm.name());
            LOGGER.severe(message);
            throw new DigesterException(message, e);
		} 
		return result;
	}
}
