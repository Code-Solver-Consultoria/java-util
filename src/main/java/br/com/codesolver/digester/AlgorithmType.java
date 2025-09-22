package br.com.codesolver.digester;

/** 
 * Algoritimos para utilizacao do calculo de HASH.
 *
 * @author <a href="mailto:luciano@codesolver.com.br">Luciano Vieira Rodrigues</a>
 * @since 2025-08-26
 */
public enum AlgorithmType {
	
	/** Algorítimo para cálculo de CRC 16. */
	CRC16("CRC16"),
	/** Hash MD2. */
	MD2("MD2"),
	/** Hash MD5. */
	MD5("MD5"),
	/** Hash SHA-1. */
	SHA_1("SHA-1"),
	/** Hash SHA-224. */
	SHA_224("SHA-224"),
	/** Hash SHA-256. */
	SHA_256("SHA-256"),
	/** Hash SHA-384. */
	SHA_384("SHA-384"),
	/** Hash SHA-512. */
	SHA_512("SHA-512"),
	/** Hash SHA3-224. */
	SHA3_224("SHA3-224"),
	/** Hash SHA3-256. */
	SHA3_256("SHA3-256"),
	/** Hash SHA3-384. */
	SHA3_384("SHA3-384"),
	/** Hash SHA3-512. */
	SHA3_512("SHA3-512"),
	/** Hash SHAKE128-256. */
	SHAKE128_256("SHAKE128-256"),
	/** Hash SHAKE256-512. */
	SHAKE256_512("SHAKE256-512");
	
	/** Nome do algorítimo. */
	private String value;
	
	/**
	 * Construtor oculto.
	 *
	 * @param value Nome do algorítimo.
	 */
	AlgorithmType(String value) {
		this.value = value;
	}
	
	/**
	 * Recupera o nome do algorítimo.
	 *
	 * @return Nome do algorítimo.
	 */
	public String getValue() {
		return value;
	}
}
