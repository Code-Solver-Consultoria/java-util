package br.com.codesolver.util;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Manipulação de texto.
 *
 * @author <a href="mailto:luciano@codesolver.com.br">Luciano Vieira Rodrigues</a>
 * @since 2025-08-26
 */
public final class StringUtil {
	
	/** Log da classe. */
	private static final Logger LOGGER = Logger.getLogger(StringUtil.class.getName());
	
	/**
	 * Expressão regular para identificar nomes de constantes.
	 */
	private static final Pattern CONSTANT_NAME_EXPRESSION = Pattern.compile("^[A-Z][A-Z0-9]*(_[A-Z0-9]+)*$");  //$NON-NLS-1$

    /** Lista de caracteres acentuados que devem ser substituidos. */
    private static final char[] INVALID_CHARS	= {	'á', 'é', 'í', 'ó', 'ú',
                                                    'à', 'è', 'ì', 'ò', 'ù',
                                                    'â', 'ê', 'î', 'ô', 'û',
                                                    'ä', 'ë', 'ï', 'ö', 'ü',
                                                    'ã', 'ẽ', 'ĩ', 'õ', 'ũ',
                                                    'ç',
                                                    'Á', 'É', 'Í', 'Ó', 'Ú',
                                                    'À', 'È', 'Ì', 'Ò', 'Ù',
                                                    'Â', 'Ê', 'Î', 'Ô', 'Û',
                                                    'Ä', 'Ë', 'Ï', 'Ö', 'Ü',
                                                    'Ã', 'Ẽ', 'Ĩ', 'Õ', 'Ũ',
                                                    'Ç'};

    /** Lista de caracteres para substituir a lista de caracteres acentuados. */
    private static final char[] REPLACE_CHARS	= {	'a', 'e', 'i', 'o', 'u',
                                                    'a', 'e', 'i', 'o', 'u',
                                                    'a', 'e', 'i', 'o', 'u',
                                                    'a', 'e', 'i', 'o', 'u',
                                                    'a', 'e', 'i', 'o', 'u',
                                                    'c',
                                                    'A', 'E', 'I', 'O', 'U',
                                                    'A', 'E', 'I', 'O', 'U',
                                                    'A', 'E', 'I', 'O', 'U',
                                                    'A', 'E', 'I', 'O', 'U',
                                                    'A', 'E', 'I', 'O', 'U',
                                                    'C'};

    /**
     * Expressão regular para ignorar os caracteres normais (a..z, A..Z, 0..9)
     * e remover os demais caracteres.
     */
    private static final String INVALID_EXPRESSION	= "[^a-zA-Z0-9 \\-\\_\\.\\,\\/]"; //$NON-NLS-1$

    /**
     * Construtor oculto.
     */
    private StringUtil() {
    }

    /**
     * Remove os caracteres especiais de um texto.
     * @param text Texto que deverá ser normalizado sem acentos.
     * @return Texto sem acentos ou caracteres especiais.
     */
    public static String clean(String text) {
        // Removendo os acentos:
        for (int i = 0; i < INVALID_CHARS.length; i++) {
            text = text.replace(INVALID_CHARS[i], REPLACE_CHARS[i]);
        }
        // Removendo os demais caracteres especiais:
        Pattern pattern = Pattern.compile(INVALID_EXPRESSION);
        Matcher matcher	= pattern.matcher(text);
        return matcher.replaceAll(""); //$NON-NLS-1$
    }

    /**
     * Adiciona espaços em branco a direita do texto.
     * @param text Texto original.
     * @param length Tamanho final do texto.
     * @return Texto com espaços em branco a direita.
     */
    public static String padRight(String text, int length) {
        return StringUtil.padRight(text, " ", length); //$NON-NLS-1$
    }

    /**
     * Adiciona um texto a direita do texto original, até o tamanho informado.
     * @param text Texto original.
     * @param stuff Texto para adicionar.
     * @param length Tamanho final do texto.
     * @return {@link String}
     */
    public static String padRight(String text, String stuff, int length) {
        if (length < text.length()) {
            text = text.substring(0, length);
        } else {
            int difference;
            while ((difference = length - text.length()) > 0) {
                if (stuff.length() > difference) {
                    text = text + stuff.substring(0, difference);
                } else {
                    text = text + stuff;
                }
            }
        }
        return text;
    }

    /**
     * Adiciona espaços em branco a esquerda do texto.
     * @param text Texto original.
     * @param length Tamanho final do texto.
     * @return Texto com espacos em branco a direita.
     */
    public static String padLeft(String text, int length) {
        return StringUtil.padLeft(text, " ", length); //$NON-NLS-1$
    }

    /**
     * Adiciona um texto a esquerda do texto original, ate o tamanho informado.
     * <p>
     * Quando o tamanho do texto é maior que o tamanho final, será considerado
     * o final do texto como válido, já que o objetivo é adicionar textos à
     * esquerda.
     *
     * @param text Texto original.
     * @param stuff Texto para adicionar.
     * @param length Tamanho final do texto.
     * @return {@link String}
     */
    public static String padLeft(String text, String stuff, int length) {
        if (length < text.length()) {
            text = text.substring(text.length() - length);
        } else {
            int difference;
            while ((difference = length - text.length()) > 0) {
                if (stuff.length() > difference) {
                    text = stuff.substring(difference - 1) + text;
                } else {
                    text = stuff + text;
                }
            }
        }
        return text;
    }

    /**
     * Adiciona zeros a esquerda de um texto, até o tamanho informado.
     * @param value Valor unicial.
     * @param length Tamanho final do texto.
     * @return Texto informando um valor acrescido de zeros a esquerda.
     */
    public static String leftZero(int value, int length) {
        String result 	= String.valueOf(value);
        result			= StringUtil.padLeft(result, "0", length); //$NON-NLS-1$
        return result;
    }

    /**
     * Converte uma matriz de bytes em uma sequência de caracteres hexadecimais.
     * @param value Matriz de bytes
     * @return HASH no formato hexadecimal.
     */
    public static String toHex(byte[] value) {
        StringBuilder builder 	= new StringBuilder();
        boolean canDiscardZero 	= true;
        for(byte x: value) {
            if ((x == 0) && (canDiscardZero)) {
                continue;
            } else {
                canDiscardZero = false;
            }
            String aux = Integer.toHexString(0xFF & x);
            if ((aux.length() == 1) && (builder.length() > 0)) {
                builder.append("0"); //$NON-NLS-1$
            }
            builder.append(aux);
        }

        return builder.toString();
    }

    /**
     * Converte uma matriz de bytes em uma sequência de caracteres hexadecimais,
     * com tamanho máximo definido.
     * @param value Matriz de bytes
     * @param length Tamanho máximo da matriz.
     * @return HASH no formato hexadecimal.
     */
    public static String toHexSpaced(byte[] value, int length) {
        byte[] croped = Arrays.copyOf(value, length);
        return toHexSpaced(croped);
    }

    /**
     * Converte uma matriz de bytes em uma sequência de caracteres hexadecimais,
     * separados por espaços em branco.
     * @param value Matriz de bytes
     * @return HASH no formato hexadecimal.
     */
    public static String toHexSpaced(byte[] value) {
        StringBuilder builder 	= new StringBuilder();
        for(byte x: value) {
            if (builder.length() != 0) {
                builder.append(" "); //$NON-NLS-1$
            }
            String aux = Integer.toHexString(0xFF & x);
            if (aux.length() == 1) {
                builder.append("0"); //$NON-NLS-1$
            }
            builder.append(aux);
        }

        return builder.toString();
    }

    /**
     * Converte uma expressão hexadecimal para uma matriz de bytes.
     * 
     * @param hex HASH no formato hexadecimal.
     * @return byte[] Matriz de bytes extraída.
     */
    public static byte[] hexToBytes(String hex) {
        // Normalizando o tamanho do hash...
        String text = hex.replaceAll(" ", ""); //$NON-NLS-1$ //$NON-NLS-2$
        if ((text.length() % 2) != 0) {
            text = "0" + text; //$NON-NLS-1$
        }
        byte[] result 	= new byte[text.length() / 2];
        char[] hash		= text.toCharArray();
        for (int i = 0; i < hash.length; i += 2) {
            String aux 		= "0x" + hash[i] + hash[i + 1]; //$NON-NLS-1$
            result[i / 2]	= Integer.decode(aux).byteValue();
        }

        return result;
    }


    /**
     * Codifica uma string contendo um IPv4 para um array de bytes.<br>
     *
     * @param ip IPv4 no formato 255.255.255.255
     * @return byte[] array de bytes onde cada uma das quatro posições do array é um octeto do IP.
     * @throws IllegalArgumentException Não é um IP válido.
     */
    public static byte[] encodeIPv4(String ip) {

        if(ip == null) {
            throw new IllegalArgumentException("Parâmetro de entrada não deve ser nulo."); //$NON-NLS-1$
        }

        Pattern p = Pattern.compile("\\b(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b"); //$NON-NLS-1$
        Matcher m = p.matcher(ip);

        if(!m.matches()) {
            throw new IllegalArgumentException("Formato esperado 255.255.255.255"); //$NON-NLS-1$
        }

        String [] octetosStr = ip.split("\\."); //$NON-NLS-1$

        byte[]octetos = new byte[4];

        octetos[0] = (byte)(Integer.valueOf(octetosStr[0]).intValue() & 0xFF);
        octetos[1] = (byte)(Integer.valueOf(octetosStr[1]).intValue() & 0xFF);
        octetos[2] = (byte)(Integer.valueOf(octetosStr[2]).intValue() & 0xFF);
        octetos[3] = (byte)(Integer.valueOf(octetosStr[3]).intValue() & 0xFF);

        return octetos;
    }


    /**
     * Formata um array de 4 bytes, para uma string no formato IPv4.<br>
     *
     * @param ip array de 4 bytes correspondendo aos 4 octetos do IPv4
     * @return string formatada no padrão 255.255.255.255
     * @throws IllegalArgumentException Não foi fornecido um IP válido.
     */
    public static String decodeIPv4(byte[] ip) {

        if((ip == null) || (ip.length < 4)) {
            throw new IllegalArgumentException("Cada um dos quatro bytes deve corresponder a um octeto do IP."); //$NON-NLS-1$
        }

        short octeto1 = ((short) (ip[0] & 0xff));
        short octeto2 = ((short) (ip[1] & 0xff));
        short octeto3 = ((short) (ip[2] & 0xff));
        short octeto4 = ((short) (ip[3] & 0xff));

        StringBuilder strFormatada = new StringBuilder();
        strFormatada.append(octeto1).append("."); //$NON-NLS-1$
        strFormatada.append(octeto2).append("."); //$NON-NLS-1$
        strFormatada.append(octeto3).append("."); //$NON-NLS-1$
        strFormatada.append(octeto4);

        return strFormatada.toString();
    }

	/**
	 * Lê as propriedades de um objeto e gera texto descrevendo-o com seus
	 * valores. Pode ser utilizado para tratar os métodos toString de
	 * objetos POJO.
	 *    
	 * @param value Objeto para decodificar.
	 * @return @{link String}.
	 */
	public static String toString(Object value) {
		StringBuilder builder = new StringBuilder();
		
		Class<?> clazz = value.getClass();
		builder.append(clazz.getName());
		builder.append("["); //$NON-NLS-1$
		
		boolean first = true;
		for (Field field: clazz.getDeclaredFields()) {
			// Se for o campo de versão serial da classe.
			if ("serialVersionUID".equals(field.getName())) { //$NON-NLS-1$
				continue;
			}
			// Se for uma constante.
			Matcher matcher = CONSTANT_NAME_EXPRESSION.matcher(field.getName());
			if (matcher.matches()) {
				continue;
			}
			// Se não for o primeiro campo, adicionar uma vírgula.
			if (!first) {
				builder.append(","); //$NON-NLS-1$
			}
			try {
				// Tratando campos de coleções:
				try {
					if (field.getType().asSubclass(Collection.class) != null) {
						builder.append(field.getName()).append("=(").append(field.getType().getName()).append(")");  //$NON-NLS-1$ //$NON-NLS-2$
					}
				} catch (ClassCastException e) {
					field.setAccessible(true);
					builder.append(field.getName()).append("=").append(field.get(value)); //$NON-NLS-1$					
				}
			} catch (IllegalArgumentException e) {
				LOGGER.warning(e.getMessage());
			} catch (IllegalAccessException e) {
				LOGGER.warning(e.getMessage());
			}
			first = false;
		}
		
		builder.append("]"); //$NON-NLS-1$
		
		return builder.toString();
	}    
}
