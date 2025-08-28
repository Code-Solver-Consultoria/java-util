package br.com.codesolver.session;

/**
 * Contrato para construiur um gerenciador de Sessão.
 *
 * @author <a href="mailto:luciano@codesolver.com.br">Luciano Vieira Rodrigues</a>
 * @since 2025-08-26
 *
 * @param <T> Tipo de sessão gerenciada.
 */
public interface SessionManager<T extends Session> {

	/**
	 * Recupera um objeto {@link Session}. O parâmetro deve identificar 
	 * um único objeto de sessão.
	 *
	 * <p>
	 * <b>Se for utilizado o objeto {@link SimpleSession}, o parâmetro de Identificação
	 * será {@link Session#ID}</b>. Se o parâmetro não for atribuído, o objeto não poderá
	 * ser gerenciado corretamente. 
	 *
	 * @param <V> Tipo do parâmetro de sessão.
	 * @param param {@link SessionParam} que identifique um único objeto {@link Session}.
	 * @param value Valor do parâmetro consultado.
	 * @return {@link Session}.
	 */
	<V> T get(SessionParam<V> param, V value);
	
	/**
	 * Recupera ou cria um objeto {@link Session}. O parâmetro deve identificar 
	 * um único objeto de sessão.
	 *
	 * <p>
	 * Se o parâmetro não referenciar um objeto de sessão, esse será criado.
	 *
	 * <p>
	 * <b>Se for utilizado o objeto {@link SimpleSession}, o parâmetro de Identificação
	 * será {@link Session#ID}</b>. Se o parâmetro não for atribuído, o objeto não poderá
	 * ser gerenciado corretamente. 
	 *
	 * @param <V> Tipo do parâmetro de sessão.
	 * @param param {@link SessionParam} que identifique um único objeto {@link Session}.
	 * @param value Valor do parâmetro consultado.
	 * @param create Se verdadeiro, pode criar o objeto automaticamente.
	 * @return {@link Session}.
	 */
	<V> T get(SessionParam<V> param, V value, boolean create);	

	/**
	 * Remove um objeto de sessão.
	 *
	 * @param <V> Tipo do parâmetro de sessão.
	 * @param param {@link SessionParam} de identificação únicao do objeto.
	 * @param value Valor atribuído para {@link SessionParam}.
	 * @return {@link Session} se removido, ou nulo se não encontrado.
	 */
	<V> T remove(SessionParam<V> param, V value);

	/**
	 * Limpa a lista de objetos da sessão.
	 */
	void clear();

	/**
	 * Recupera o tamanho da lista de objetos de sessão.
	 *
	 * @return int.
	 */
	int size();

	/**
	 * Recupera a lista de objetos de sessão.
	 *
	 * @return Matriz de {@link Session}.
	 */
	Session[] array();
}
