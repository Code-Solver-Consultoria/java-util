package br.com.codesolver.session;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Logger;

/**
 * Os parâmetros da sessão devem possuir nomes únicos e tipos definidos.
 *
 * @author <a href="mailto:luciano@codesolver.com.br">Luciano Vieira Rodrigues</a>
 * @since 2025-08-26
 * @param <T> Tipo de valor para o parâmetro de sessão.
 */
public class SessionParam<T> implements Serializable {
	
	/**
	 * Log da classe.
	 */
	private static final Logger logger = Logger.getLogger(SessionParam.class.getName());
	
	/**
	 * Nomes dos parâmetros.
	 */
	private static final ConcurrentMap<String, Boolean> names = new ConcurrentHashMap<String, Boolean>();
	
	/**
	 * Nome interno do parâmetro.
	 */
	private String name;

	/**
	 * Gerenciamento de nomes de parâmetros.
	 * 
	 * @param name Nome único do parâmetro.
	 */
	public SessionParam(String name) {
		if (name == null) {
			String message = MessageFormat.format("Parâmetro não pode ser nulo.", name);
			logger.severe(message);
			throw new SessionRuntimeException(message);
		} else if (name.isEmpty()) {
			String message = MessageFormat.format("Parâmetro está vazio.", name);
			logger.severe(message);
			throw new SessionRuntimeException(message);
		} else if (names.putIfAbsent(name, Boolean.TRUE) != null) {
			String message = MessageFormat.format("Parâmetro {0} já está em uso.", name);
			logger.severe(message);
			throw new SessionRuntimeException(message);
		}
		this.name = name;
	}

	/**
	 * Nome de identificação do parâmetro de sessão.
	 * 
	 * @return name Nome do parâmetro de Sessão.
	 */
	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		SessionParam other = (SessionParam) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/**
	 * Verifica se o parâmetro foi atribuído corretamente.
	 * @param value Apenas o valor para verificação.
	 */
	public void validate(T value) {
		if (value == null) {
			throw new SessionRuntimeException("Parâmetro nulo");
		}
	}
}
