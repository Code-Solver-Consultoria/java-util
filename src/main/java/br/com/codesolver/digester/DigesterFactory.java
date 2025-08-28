package br.com.codesolver.digester;

/**
 * Cria o processador de HASH adequado para o algorítimo informado.
 *
 * @author <a href="mailto:luciano@codesolver.com.br">Luciano Vieira Rodrigues</a>
 * @since 2025-08-26
 * @see Digester
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
	 * @param algorithm {@link AlgorithmType}
	 * @return {@link Digester}
	 */
	public static Digester getInstance(AlgorithmType algorithm) {
		Digester result = null;
		switch (algorithm) {
			case CRC16: 
				result = new DigesterCRC16();
				break;
			default:
				result = new DigesterSUN(algorithm);
		}
		return result;
	}
}
