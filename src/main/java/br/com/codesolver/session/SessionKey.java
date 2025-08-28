package br.com.codesolver.session;

import java.io.Serializable;

/**
 * Chave de Sessão para o gerenciador.
 *
 * @author <a href="mailto:luciano@codesolver.com.br">Luciano Vieira Rodrigues</a>
 * @since 2025-08-26
 *
 * @param <T> Tipo da chave de sessão.
 */
public class SessionKey<T> implements Serializable {

	/**
	 * Parâmetro identificador da chave.
	 */
	private SessionParam<T> param;
	
	/**
	 * Valor do parâmetro.
	 */
	private T value;

	/**
	 * Construtor da chave de sessão.
	 * 
	 * @param param Parâmetro de identificação da sessão.
	 * @param value Valor do parâmetro.
	 */
	public SessionKey(SessionParam<T> param, T value) {
		super();
		this.param = param;
		this.value = value;
	}

	/**
	 * Recupera o parâmetro de sessão.
	 * 
	 * @return Parâmetro de sessão.
	 */
	public SessionParam<T> getParam() {
		return param;
	}

	/**
	 * Recupera o valor armazenado no parâmetro de sessão.
	 * 
	 * @return Valor do parâmetro.
	 */
	public T getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((param == null) ? 0 : param.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SessionKey other = (SessionKey) obj;
		if (param == null) {
			if (other.param != null)
				return false;
		} else if (!param.equals(other.param))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(getClass());
		builder.append("[");
		builder.append(param.getName()).append("=").append(value);
		builder.append("]");
		
		return builder.toString();
	}
}
