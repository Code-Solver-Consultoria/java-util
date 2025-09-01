package br.com.codesolver.digester;

/**
 * Cria o processador de HASH adequado para o algorítimo informado.
 *
 * @author <a href="mailto:luciano@codesolver.com.br">Luciano Vieira Rodrigues</a>
 * @since 2025-08-26
 * @see AlgorithmType
 */
public final class DigesterFactory {

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
		T result = null;
		switch (algorithm) {
			case CRC16: 
				result = (T) new DigesterCRC16();
				break;
			default:
				result = (T) new DigesterSUN(algorithm);
		}
		return result;
	}
}
