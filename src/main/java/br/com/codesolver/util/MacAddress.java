package br.com.codesolver.util;

import java.io.Serializable;
import java.security.InvalidParameterException;
import java.util.Arrays;

/**
 * Representação de um MacAdrress de placa de rede.
 *
 * @author <a href="mailto:luciano@codesolver.com.br">Luciano Vieira
 *         Rodrigues</a>
 * @since 2025-08-26
 */
public class MacAddress implements Serializable {

	/** Mensagem de erro para índice de octeto. */
	private static final String OCTET_RANGE_ERROR = "Índice %d fora do range válido para um octeto.";

	/** Tamanho da estrutura de dados do MacAddress. */
	private static final int SIZE = 6;

	/** Menor índice possível para um octeto. */
	private static final int ZERO = 0;

	/** Lista dos octetos que formam o MacAddress. */
	private final byte[] octets;

	/**
	 * Construtor padrão.
	 */
	public MacAddress() {
		this.octets = new byte[SIZE];
	}

	/**
	 * Construtor com os campos.
	 *
	 * @param octet1 Primeiro octeto.
	 * @param octet2 Segundo octeto.
	 * @param octet3 Terceiro octeto.
	 * @param octet4 Quarto octeto.
	 * @param octet5 Quinto octeto.
	 * @param octet6 Sexto octeto.
	 */
	public MacAddress(byte octet1, byte octet2, byte octet3, byte octet4,
			byte octet5, byte octet6) {
		this.octets = new byte[SIZE];
		int index = SIZE;
		this.octets[--index] = octet6;
		this.octets[--index] = octet5;
		this.octets[--index] = octet4;
		this.octets[--index] = octet3;
		this.octets[--index] = octet2;
		this.octets[--index] = octet1;
	}

	/**
	 * Recupera o valor de um octeto específico.
	 *
	 * @param index Índice do octeto para recuperar.
	 * @return Valor do Octeto.
	 * @throws InvalidParameterException Índice fora do range de um octeto.
	 */
	public byte getOctect(int index) {
		if (index < ZERO || index >= SIZE) {
			throw new InvalidParameterException(String.format(OCTET_RANGE_ERROR, index));
		}
		return octets[index];
	}

	/**
	 * Atribui um novo valor para um octeto na posição específica.
	 *
	 * @param index Índice do octeto para atribuir.
	 * @param value Valor do octeto.
	 * @throws InvalidParameterException Índice fora do range de um octeto.
	 */
	public void setOctect(int index, byte value) {
		if (index < ZERO || index >= SIZE) {
			throw new InvalidParameterException(String.format(OCTET_RANGE_ERROR, index));
		}
		octets[index] = value;
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(octets);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof MacAddress)) {
			return false;
		}
		MacAddress other = (MacAddress) obj;
		return Arrays.equals(this.octets, other.octets);
	}

	@Override
	public String toString() {
		String result = StringUtil.toHexSpaced(octets);
		return result.replaceAll(" ", ":");
	}
}
