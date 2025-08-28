package br.com.codesolver.util;

import java.io.IOException;
import java.io.InputStream;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Métodos para auxiliar nos projetos com Swing.
 * 
 * @author <a href="mailto:luciano@codesolver.com.br">Luciano Vieira Rodrigues</a>
 * @since 2025-08-26
 */
public final class SwingUtil {

	/**
	 * Construtor oculto.
	 */
	private SwingUtil() {
	}

	/**
	 * Recupera um ícone arquivado como recurso do sistema.
	 * @param name Nome do recurso.
	 * @return Icon
	 */
	@SuppressWarnings("exports")
	public static Icon getIcon(String name) {
		Icon result			= null;
		InputStream stream 	= SwingUtil.class.getResourceAsStream(name);
		byte[] buffer		= null;
		try {
			buffer	= new byte[stream.available()];
			stream.read(buffer);
			result	= new ImageIcon(buffer);
		} catch (IOException e) {
		}
		return result;
	}
}
