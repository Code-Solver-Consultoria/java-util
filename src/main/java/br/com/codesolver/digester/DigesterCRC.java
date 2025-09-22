package br.com.codesolver.digester;

/** 
 * Adiciona a funcionalidade de calculo de valor de CRC (Cyclic Redundancy Check).
 *
 * @author <a href="mailto:luciano@codesolver.com.br">Luciano Vieira Rodrigues</a>
 * @since 2025-08-26
 */
public interface DigesterCRC extends Digester {

	/**
	 * Recupera o valor de um CRC.
	 *
	 * @return short
	 */
	short getValue();
}
