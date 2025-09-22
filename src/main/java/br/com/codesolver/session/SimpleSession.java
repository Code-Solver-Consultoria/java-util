package br.com.codesolver.session;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Objeto que representa a sessão de uma conexão, com as informações do equipamento.
 *
 * @author <a href="mailto:luciano@codesolver.com.br">Luciano Vieira Rodrigues</a>
 * @since 2025-08-26
 */
public class SimpleSession implements Session {

	/**
	 * Guarda os parâmetros da sessão.
	 */
	private ConcurrentMap<SessionParam<?>, Object> params;
	
	/**
	 * Construtor padrão.
	 */
	public SimpleSession() {
		this.params	= new ConcurrentHashMap<SessionParam<?>, Object>();
		this.setParam(ID, 0);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public SessionKey<?> getKey() {
		return new SessionKey(ID, getParam(ID));
	}

	@Override
	public <T> void setParam(SessionParam<T> param, T value) {
		param.validate(value);
		params.put(param, value);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public <T> T getParam(SessionParam<T> param) {
		return (T) params.get(param);
	}

	@Override
	public Set<SessionParam<?>> getParams() {
		return params.keySet(); 
	}

	@Override
	public int compareTo(Session o) {
		if (o == null) {
			throw new SessionRuntimeException("Não pode comparar com valores nulos.");
		}
		Integer id 	= getParam(ID);
		if (id == null) {
			throw new SessionRuntimeException("O parâmetro ID não foi atribuído para a sessão corrente.");
		}
		Integer other	= o.getParam(ID);
		if (other == null) {
			throw new SessionRuntimeException("O parâmetro ID não foi atribuído para a sessão comparada.");
		}
		return id.compareTo(other);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result;
		Integer id = getParam(ID);
		if (id != null) {
			result += id;
		}
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		SimpleSession other = (SimpleSession) obj;
		Integer selfID 	= getParam(ID);
		Integer otherID	= other.getParam(ID);
		if (selfID == null && otherID == null) {
			return true;
		} else if (selfID == null || otherID == null) {
			return false;
		}
		return selfID.intValue() == otherID.intValue();
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("Session[");
		boolean first = true;
		for (SessionParam<?> param : getParams()) {
			if (!first) {
				builder.append(",");
			}
			builder.append(param.getName()).append("=").append(getParam(param));
			first = false;
		}
		builder.append("]");
		
		return builder.toString();
	}
}
