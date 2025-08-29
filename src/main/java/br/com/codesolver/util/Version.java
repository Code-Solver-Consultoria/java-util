package br.com.codesolver.util;

import java.io.Serializable;
import java.util.Objects;

/**
 * Utilitário para decomposição da versão de um pacote Java.
 *
 * <p>
 * Isso é dependente do arquivo MANIFEST.MF, com as informações:
 * 
 * <ul>
 * <li>Specification-Vendor: Code Solver</li>
 * <li>Specification-Title: java-util</li>
 * <li>Specification-Version: 1.0.0</li>
 * </ul>
 *
 * <p>
 * Onde a versão deve sempre ser composta por três identificadores separados por ponto.
 *
 * @author <a href="mailto:luciano@codesolver.com.br">Luciano Vieira Rodrigues</a>
 * @since 2025-08-26
 */
public class Version implements Serializable {

	/** Nome para versão desconhecida. */
	private static final String DEFAULT_NAME = "<unknown>";

	/** Valor padrão para os campos de versões. */
	private static final String DEFUALT_VALUE = "0";

	/** Tamanho da versão contendo o campo maior. */
	private static final int VERSION_MAJOR_SIZE = 1;

	/** Tamanho da versão contendo o campo menor. */
	private static final int VERSION_MINOR_SIZE = 2;

	/** Tamanho da versão contendo o campo construção. */
	private static final int VERSION_BUILD_SIZE = 3;

	/**
	 * Nome do aplicativo, retirado do arquivo MANIFEST.MF, entrada <b>Specification-Title</b>.
	 */
	private String name;

	/**
	 * Maior número da versão do aplicativo, retirado do arquivo MANIFEST.MF, entrda <b>Specification-Version</b>.
	 */
	private String major;

	/**
	 * Menor número da versão do aplicativo, retirado do arquivo MANIFEST.MF, entrda <b>Specification-Version</b>.
	 */
	private String minor;

	/**
	 * Identificador da construção da versão do aplicativo, retirado do arquivo MANIFEST.MF, entrda
	 * <b>Specification-Version</b>.
	 */
	private String build;

	/**
	 * Construtor padrão.
	 */
	public Version() {
		this.name	= DEFAULT_NAME;
		this.major	= DEFUALT_VALUE;
		this.minor 	= DEFUALT_VALUE;
		this.build 	= DEFUALT_VALUE;
	}

	/**
	 * Recupera o valor do campo {@link #name}.
	 *
	 * @return O {@link #name}.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Atribui o valor no campo {@link #name}.
	 *
	 * @param name O {@link #name} para configurar.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Recupera o valor do campo {@link #major}.
	 *
	 * @return O {@link #major}.
	 */
	public String getMajor() {
		return major;
	}

	/**
	 * Atribui o valor no campo {@link #major}.
	 *
	 * @param major O {@link #major} para configurar.
	 */
	public void setMajor(String major) {
		this.major = major;
	}

	/**
	 * Recupera o valor do campo {@link #minor}.
	 *
	 * @return O {@link #minor}.
	 */
	public String getMinor() {
		return minor;
	}

	/**
	 * Agribui o valor no campo {@link #minor}.
	 *
	 * @param minor O {@link #minor} para configurar.
	 */
	public void setMinor(String minor) {
		this.minor = minor;
	}

	/**
	 * Recupera o valor do campo {@link #build}.
	 *
	 * @return O {@link #build}.
	 */
	public String getBuild() {
		return build;
	}

	/**
	 * Atribui o valor no campo {@link #build}.
	 *
	 * @param build O {@link #build} para configurar.
	 */
	public void setBuild(String build) {
		this.build = build;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	// CHECKSTYLE:OFF
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((build == null) ? 0 : build.hashCode());
		result = prime * result + ((major == null) ? 0 : major.hashCode());
		result = prime * result + ((minor == null) ? 0 : minor.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	// CHECKSTYLE:ON

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Version)) {
			return false;
		}
		Version other = (Version) obj;
		return Objects.equals(build, other.build) && Objects.equals(major, other.major)
				&& Objects.equals(minor, other.minor) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return StringUtil.toString(this);
	}

	/**
	 * Extrai informações de versão do pacote fornecido.
	 *
	 * @param pack Pacote com informações.
	 * @throws VersionRuntimeException Falha na decomposição da versão extraída do pacote.
	 * @throws NullPointerException    Versão inválida.
	 */
	public void decode(Package pack) {
		try {
			name = pack.getSpecificationTitle();
			String[] values = pack.getImplementationVersion().split("\\.");
			if (values.length >= VERSION_MAJOR_SIZE) {
				major = values[0];
			}
			if (values.length >= VERSION_MINOR_SIZE) {
				minor = values[1];
			}
			if (values.length >= VERSION_BUILD_SIZE) {
				build = values[2];
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new VersionRuntimeException(
					"Formato de versão incorreto. Deve possuir 3 identificadores separados por ponto (.).", 
					e);
		} catch (NullPointerException e) {
			throw new VersionRuntimeException("Nenhuma informação pode ser extraída do pacote fornecido.", 
					e);
		}
	}
}
