package br.com.codesolver.session;

import java.io.Serializable;
import java.util.Set;

/**
 * Métodos para gerenciamento dos parâmetros em objeto de sessão.
 *
 * @author <a href="mailto:luciano@codesolver.com.br">Luciano Vieira
 *         Rodrigues</a>
 * @since 2025-08-26
 */
public interface Session extends Comparable<Session>, Serializable {

	/**
	 * Parâmetro para gerenciamento do ID de sessão.
	 */
	public static final SessionParam<Integer> ID = new SessionParam<Integer>("ID");

	/**
	 * Recupera a chave de identificação da sessão.
	 * 
	 * @return {@link SessionKey}
	 */
	public abstract SessionKey<?> getKey();

	/**
	 * Guarda uma informação na sessão.
	 * 
	 * @param <T> Tipo do valor do parâmetro.
	 * @param param {@link SessionParam}.
	 * @param value Valor do parâmetro para guarda na sessão.
	 */
	public abstract <T> void setParam(SessionParam<T> param, T value);

	/**
	 * Recupera um valor guardado na sessão.
	 * 
	 * @param <T> Tipo do valor do parâmetro.
	 * @param param {@link SessionParam}
	 * @return Valor guardado.
	 */
	public abstract <T> T getParam(SessionParam<T> param);

	/**
	 * Recupera a lista de parâmetros da sessão.
	 * 
	 * @return Lista de {@link SessionParam}.
	 */
	public abstract Set<SessionParam<?>> getParams();

	public abstract int hashCode();

	public abstract boolean equals(Object obj);
}