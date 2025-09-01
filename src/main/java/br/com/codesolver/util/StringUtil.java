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

    /** Máscara para decomposição dos octetos de endereço IP. */
    private static final int BYTE_MASK = 0xFF;

    /** Máscara para identificar um espaço em um texto.. */
    private static final String TEXT_SPACE = " ";

    /** Zero em modo texto. */
    private static final String TEXT_ZERO = "0";

    /** Tamanho de uma estrutura de dados para IP em Bytes. */
    private static final int IP_LENGTH = 4;

    /** Ponto para separação de informações em textos (IP, por exemplo). */
    private static final String TEXT_PERIOD = ".";

    /**
     * Expressão regular para identificar nomes de constantes.
     */
    private static final Pattern CONSTANT_NAME_EXPRESSION = Pattern.compile("^[A-Z][A-Z0-9]*(_[A-Z0-9]+)*$"); 

    /** Lista de caracteres acentuados que devem ser substituidos. */
    private static final char[] INVALID_CHARS = {
        'á', 'é', 'í', 'ó', 'ú',
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
        'Ç' };

    /** Lista de caracteres para substituir a lista de caracteres acentuados. */
    private static final char[] REPLACE_CHARS = {
        'a', 'e', 'i', 'o', 'u',
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
        'C' };

    /**
     * Expressão regular para ignorar os caracteres normais (a..z, A..Z, 0..9)
     * e remover os demais caracteres.
     */
    private static final String INVALID_EXPRESSION = "[^a-zA-Z0-9 \\-\\_\\.\\,\\/]"; 

    /**
     * Construtor oculto.
     */
    private StringUtil() {
    }

    /**
     * Remove os caracteres especiais de um texto.
     *
     * @param text Texto que deverá ser normalizado sem acentos.
     * @return Texto sem acentos ou caracteres especiais.
     */
    public static String clean(String text) {
        String aux = text;
        // Removendo os acentos:
        for (int i = 0; i < INVALID_CHARS.length; i++) {
            aux = aux.replace(INVALID_CHARS[i], REPLACE_CHARS[i]);
        }
        // Removendo os demais caracteres especiais:
        Pattern pattern = Pattern.compile(INVALID_EXPRESSION);
        Matcher matcher = pattern.matcher(aux);
        return matcher.replaceAll(""); 
    }

    /**
     * Adiciona espaços em branco a direita do texto.
     *
     * @param text   Texto original.
     * @param length Tamanho final do texto.
     * @return Texto com espaços em branco a direita.
     */
    public static String padRight(String text, int length) {
        return padRight(text, TEXT_SPACE, length); 
    }

    /**
     * Adiciona um texto a direita do texto original, até o tamanho informado.
     *
     * @param text   Texto original.
     * @param stuff  Texto para adicionar.
     * @param length Tamanho final do texto.
     * @return {@link String}
     */
    public static String padRight(String text, String stuff, int length) {
        String aux = text;
        if (length < aux.length()) {
            aux = aux.substring(0, length);
        } else {
            int difference;
            while ((difference = length - aux.length()) > 0) {
                if (stuff.length() > difference) {
                    aux = aux + stuff.substring(0, difference);
                } else {
                    aux = aux + stuff;
                }
            }
        }
        return aux;
    }

    /**
     * Adiciona espaços em branco a esquerda do texto.
     *
     * @param text   Texto original.
     * @param length Tamanho final do texto.
     * @return Texto com espacos em branco a direita.
     */
    public static String padLeft(String text, int length) {
        return padLeft(text, TEXT_SPACE, length); 
    }

    /**
     * Adiciona um texto a esquerda do texto original, ate o tamanho informado.
     *
     * <p>
     * Quando o tamanho do texto é maior que o tamanho final, será considerado
     * o final do texto como válido, já que o objetivo é adicionar textos à
     * esquerda.
     *
     * @param text   Texto original.
     * @param stuff  Texto para adicionar.
     * @param length Tamanho final do texto.
     * @return {@link String}
     */
    public static String padLeft(String text, String stuff, int length) {
        String aux = text;
        if (length < aux.length()) {
            aux = aux.substring(aux.length() - length);
        } else {
            int difference;
            while ((difference = length - aux.length()) > 0) {
                if (stuff.length() > difference) {
                    aux = stuff.substring(difference - 1) + aux;
                } else {
                    aux = stuff + aux;
                }
            }
        }
        return aux;
    }

    /**
     * Adiciona zeros a esquerda de um texto, até o tamanho informado.
     *
     * @param value  Valor unicial.
     * @param length Tamanho final do texto.
     * @return Texto informando um valor acrescido de zeros a esquerda.
     */
    public static String leftZero(int value, int length) {
        String result = String.valueOf(value);
        result = padLeft(result, TEXT_ZERO, length); 
        return result;
    }

    /**
     * Converte uma matriz de bytes em uma sequência de caracteres hexadecimais.
     *
     * @param value Matriz de bytes
     * @return HASH no formato hexadecimal.
     */
    public static String toHex(byte[] value) {
        StringBuilder builder = new StringBuilder();
        boolean canDiscardZero = true;
        for (byte x : value) {
            if (x == 0 && canDiscardZero) {
                continue;
            } else {
                canDiscardZero = false;
            }
            String aux = Integer.toHexString(BYTE_MASK & x);
            if (aux.length() == 1 && !builder.isEmpty()) {
                builder.append(TEXT_ZERO); 
            }
            builder.append(aux);
        }
        return builder.toString();
    }

    /**
     * Converte uma matriz de bytes em uma sequência de caracteres hexadecimais,
     * com tamanho máximo definido.
     *
     * @param value  Matriz de bytes
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
     *
     * @param value Matriz de bytes
     * @return HASH no formato hexadecimal.
     */
    public static String toHexSpaced(byte[] value) {
        StringBuilder builder = new StringBuilder();
        for (byte x : value) {
            if (builder.length() != 0) {
                builder.append(TEXT_SPACE); 
            }
            String aux = Integer.toHexString(BYTE_MASK & x);
            if (aux.length() == 1) {
                builder.append(TEXT_ZERO); 
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
        String text = hex.replaceAll(TEXT_SPACE, "");  
        if ((text.length() % 2) != 0) {
            text = TEXT_ZERO + text; 
        }
        byte[] result = new byte[text.length() / 2];
        char[] hash = text.toCharArray();
        for (int i = 0; i < hash.length; i += 2) {
            String aux = "0x" + hash[i] + hash[i + 1]; 
            result[i / 2] = Integer.decode(aux).byteValue();
        }

        return result;
    }

    /**
     * Codifica uma string contendo um IPv4 para um array de bytes.
     *
     * @param ip IPv4 no formato 255.255.255.255.
     * @return byte[] array de bytes onde cada uma das quatro posições do array é um
     *         octeto do IP.
     * @throws IllegalArgumentException Não é um IP válido.
     */
    public static byte[] encodeIPv4(String ip) {
        if (ip == null) {
            throw new IllegalArgumentException("Parâmetro de entrada não deve ser nulo."); 
        }

        Pattern p = Pattern.compile(
                "\\b(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b"); 
        Matcher m = p.matcher(ip);
        if (!m.matches()) {
            throw new IllegalArgumentException("Formato esperado 255.255.255.255"); 
        }

        String[] text = ip.split("\\."); 
        byte[] octets = new byte[IP_LENGTH];
        int index = 0;
        octets[index]   = (byte) (Integer.parseInt(text[index]) & BYTE_MASK);
        octets[++index] = (byte) (Integer.parseInt(text[index]) & BYTE_MASK);
        octets[++index] = (byte) (Integer.parseInt(text[index]) & BYTE_MASK);
        octets[++index] = (byte) (Integer.parseInt(text[index]) & BYTE_MASK);
        return octets;
    }

    /**
     * Formata um array de 4 bytes, para uma string no formato IPv4.
     *
     * @param ip array de 4 bytes correspondendo aos 4 octetos do IPv4.
     * @return string formatada no padrão 255.255.255.255.
     * @throws IllegalArgumentException Não foi fornecido um IP válido.
     */
    public static String decodeIPv4(byte[] ip) {
        if (ip == null || ip.length < IP_LENGTH) {
            throw new IllegalArgumentException("Cada um dos quatro bytes deve corresponder a um octeto do IP.");
        }

        int index = 0;
        StringBuilder strFormatada = new StringBuilder();
        strFormatada.append((short) (ip[index++] & BYTE_MASK)).append(TEXT_PERIOD);
        strFormatada.append((short) (ip[index++] & BYTE_MASK)).append(TEXT_PERIOD);
        strFormatada.append((short) (ip[index++] & BYTE_MASK)).append(TEXT_PERIOD);
        strFormatada.append((short) (ip[index++] & BYTE_MASK));

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
        builder.append("["); 

        boolean first = true;
        for (Field field : clazz.getDeclaredFields()) {
            // Se for o campo de versão serial da classe.
            if ("serialVersionUID".equals(field.getName())) { 
                continue;
            }
            // Se for uma constante.
            Matcher matcher = CONSTANT_NAME_EXPRESSION.matcher(field.getName());
            if (matcher.matches()) {
                continue;
            }
            // Se não for o primeiro campo, adicionar uma vírgula.
            if (!first) {
                builder.append(","); 
            }
            try {
                // Tratando campos de coleções:
                try {
                    if (field.getType().asSubclass(Collection.class) != null) {
                        builder.append(field.getName()).append("=(").append(field.getType().getName()).append(")");  
                    }
                } catch (ClassCastException e) {
                    field.setAccessible(true);
                    builder.append(field.getName()).append("=").append(field.get(value)); 
                }
            } catch (IllegalArgumentException e) {
                LOGGER.warning(e.getMessage());
            } catch (IllegalAccessException e) {
                LOGGER.warning(e.getMessage());
            }
            first = false;
        }

        builder.append("]"); 

        return builder.toString();
    }
}
