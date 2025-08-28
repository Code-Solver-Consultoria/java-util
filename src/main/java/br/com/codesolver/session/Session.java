package br.com.codesolver.session;

import java.io.Serializable;
import java.util.Set;

/**
 * Métodos para gerenciamento dos parâmetros em objeto de sessão.
 *
 * @author <a href="mailto:luciano@codesolver.com.br">Luciano Vieira Rodrigues</a>
 * @since 2025-08-26
 */
public interface Session extends Comparable<Session>, Serializable {

	/**
	 * Parâmetro para gerenciamento do ID de sessão.
	 */
	SessionParam<Integer> ID = new SessionParam<Integer>("ID");

	/**
	 * Recupera a chave de identificação da sessão.
	 *
	 * @return {@link SessionKey}
	 */
	SessionKey<?> getKey();

	/**
	 * Guarda uma informação na sessão.
	 *
	 * @param <T> Tipo do valor do parâmetro.
	 * @param param {@link SessionParam}.
	 * @param value Valor do parâmetro para guarda na sessão.
	 */
	<T> void setParam(SessionParam<T> param, T value);

	/**
	 * Recupera um valor guardado na sessão.
	 *
	 * @param <T> Tipo do valor do parâmetro.
	 * @param param {@link SessionParam}
	 * @return Valor guardado.
	 */
	<T> T getParam(SessionParam<T> param);

	/**
	 * Recupera a lista de parâmetros da sessão.
	 *
	 * @return Lista de {@link SessionParam}.
	 */
	Set<SessionParam<?>> getParams();

	int hashCode();

	boolean equals(Object obj);
}
