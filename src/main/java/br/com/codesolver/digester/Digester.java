package br.com.codesolver.digester;

/**
 * Métodos para cálculos de HASH.
 *
 * @author <a href="mailto:luciano@codesolver.com.br">Luciano Vieira Rodrigues</a>
 * @since 2025-08-26
 */
public interface Digester {

	/**
	 * Recomeça o processo de cálculo do HASH.
	 */
	void reset();
	
	/**
	 * Atualiza o cálculo do HASH com uma matriz de bytes.
	 *
	 * @param buffer Parte de um todo que deverá ser calculado.
	 */
	void update(byte[] buffer);
	
	/**
	 * Aplica o algorítimo de HASH e extrai o resultado em uma representação 
	 * de texto HEXADECIMAL.
	 *
	 * @return Texto com o valor em HEXADECIMAL.
	 */
	String digest();
}
