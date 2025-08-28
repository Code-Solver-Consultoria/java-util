package br.com.codesolver.session;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.logging.Logger;

/**
 * Gerenciamento básico de objetos de sessão.
 * <p>
 * Os Maps do pacote <b>java.util.concurrent</b> não foram adequados para o 
 * gerenciamento da sessão.
 *
 * @author <a href="mailto:luciano@codesolver.com.br">Luciano Vieira Rodrigues</a>
 * @since 2025-08-26
 * 
 * @param <T> Herança de {@link Session}.
 */
public class SimpleSessionManager<T extends Session> implements SessionManager<T> {

	/** Log da classe. */
	private static final Logger logger = Logger.getLogger(SimpleSessionManager.class.getName());
	
	/**
	 * Lista de objetos de sessão.
	 */
	private List<T> sessions;
	
	/**
	 * Classe para inializar um novo objeto de sessão.
	 */
	private Class<T> clazz;
	
	/**
	 * Gerenciador de acesso concorrente.
	 */
	private Semaphore semaphore;
	
	/**
	 * Construtor padrão.
	 * 
	 * @param clazz Classe para inicializar um novo objeto de sessão.
	 */
	public SimpleSessionManager(Class<T> clazz) {
		this.sessions 	= new ArrayList<T>();
		this.clazz		= clazz;
		this.semaphore	= new Semaphore(1);
	}
	
	/**
	 * Cria um novo objeto de sessão para uso.
	 * 
	 * @param <V> Tipo de dados para o campo de sessão.
	 * @param param {@link SessionParam} que identifique um único objeto {@link Session}.
	 * @param value Valor do {@link SessionParam}.
	 * @return {@link Session}
	 */
	private <V> T newSession(SessionParam<V> param, V value) {
		T session = null;
		try {
			session = clazz.getConstructor().newInstance();
			session.setParam(param, value);
		} catch (Exception e) {
			String error = "Erro ao criar o objeto de sessão.";
			logger.severe(error);
			throw new SessionRuntimeException(error, e);
		}
		return session;
	}	
	
	@Override
	public <V> T get(SessionParam<V> param, V value) {
		return get(param, value, false);
	}
	
	@Override
	public <V> T get(SessionParam<V> param, V value, boolean create) {
		T session = newSession(param, value);
		// Adiciona um novo objeto de sessão ou recupera o objeto já existente.
		if (sessions.contains(session)) {
			// Recupera o objeto da sessão para atualizar todos os parâmetros:
			session = sessions.get(sessions.indexOf(session));
		} else {
			// Se não encontrou, adicionar na lista.
			if (create) {
				try {
					semaphore.acquire();
					sessions.add(session);
				} catch (InterruptedException e) {
					String error = "Não foi possível conseguir acesso exclusivo no gerenciador de sessão.";
					logger.severe(error);
					throw new SessionRuntimeException(error, e);
				} finally {
					semaphore.release();
				}
			}
		}
		return session;
	}

	@Override
	public <V> T remove(SessionParam<V> param, V value) {
		T session = newSession(param, value);
		if (sessions.contains(session)) {
			session = sessions.get(sessions.indexOf(session));
			try {
				semaphore.acquire();
				sessions.remove(session);
			} catch (InterruptedException e) {
				String error = "Não foi possível conseguir acesso exclusivo no gerenciador de sessão.";
				logger.severe(error);
				throw new SessionRuntimeException(error, e);
			} finally {
				semaphore.release();
			}
		} else {
			session = null;
		}
		return session;
	}
	
	@Override
	public void clear() {
		sessions.clear();
	}
	
	@Override
	public int size() {
		return sessions.size();
	}
	
	@Override
	public Session[] array() {
		return sessions.toArray(new Session[sessions.size()]);
	}
}
