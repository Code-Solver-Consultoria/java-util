/**
 * Informações do módulo de utilitários para aplicativos em Java.
 * 
 * @author <a href="mailto:luciano@codesolver.com.br">Luciano Vieira Rodrigues</a>
 * @since 2025-08-26
 */
module br.com.codesolver.util {
	exports br.com.codesolver.uuid;
	exports br.com.codesolver.session;
	exports br.com.codesolver.util;
	exports br.com.codesolver.digester;

	requires java.base;
	requires java.desktop;
	requires java.logging;
}