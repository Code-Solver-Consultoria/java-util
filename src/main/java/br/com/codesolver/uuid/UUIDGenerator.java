package br.com.codesolver.uuid;

import java.nio.ByteBuffer;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Gerador de UUID tipo 1 (time based). É um número de 128 bits, conforme a estrutura:
 *
 * <table border="1">
 *     <caption>Estrutura do UUID v1 (time-based)</caption>
 *     <tr>
 *       <td colspan="10">0</td>
 *       <td colspan="10">1</td>
 *       <td colspan="10">2</td>
 *       <td colspan="2">3</td>
 *     </tr>
 *     <tr>
 *       <td>0</td>
 *       <td>1</td>
 *       <td>2</td>
 *       <td>3</td>
 *       <td>4</td>
 *       <td>5</td>
 *       <td>6</td>
 *       <td>7</td>
 *       <td>8</td>
 *       <td>9</td>
 *       <td>0</td>
 *       <td>1</td>
 *       <td>2</td>
 *       <td>3</td>
 *       <td>4</td>
 *       <td>5</td>
 *       <td>6</td>
 *       <td>7</td>
 *       <td>8</td>
 *       <td>9</td>
 *       <td>0</td>
 *       <td>1</td>
 *       <td>2</td>
 *       <td>3</td>
 *       <td>4</td>
 *       <td>5</td>
 *       <td>6</td>
 *       <td>7</td>
 *       <td>8</td>
 *     </tr>
 *     <tr>
 *       <td colspan="32" style="text-align:center;">time_low</td>
 *     </tr>
 *     <tr>
 *       <td colspan="16" style="text-align:center;">time_mid</td>
 *       <td colspan="16" style="text-align:center;">time_hi and version</td>
 *     </tr>
 *     <tr>
 *       <td colspan="8" style="text-align:center;">clock_seq_hi</td>
 *       <td colspan="8" style="text-align:center;">clock_seq_low</td>
 *       <td colspan="16" style="text-align:center;">node (0-1)</td>
 *     </tr>
 *     <tr>
 *       <td colspan="32" style="text-align:center;">node (2-5)</td>
 *     </tr>
 * </table>
 *
 * <ul>
 * 	<li><b>time_low</b>: O menor campo de data e hora, com 4 bytes.</li>
 * 	<li><b>time_mid</b>: O campo do meio da data e hora, com 6 bytes.</li>
 * 	<li><b>time_hi</b>: O maior campo da data e hora, com 2 bytes.</li>
 * 	<li><b>version</b>: São os 4 bits do campo time_hi multiplexado para o valor 1 (número 1).</li>
 *	<li><b>clock_seq_hi</b>: Maior campo do sequencial do relógio multiplexado com a variante do algorítimo. É um byte.</li>
 *	<li><b>clock_seq_low</b>: Menor campo do sequencial do relógio com 1 byte.</li>
 *	<li><b>node</b>: Espaço para identificar a origem de criação do UUID.</li>
 * </ul>
 *
 * <p>
 * <i>Data e Hora é um número de 60 bits.</i>
 *
 * <b>Baseado no documento: http://www.ietf.org/rfc/rfc4122.txt</b>
 *
 * @author Luciano Vieira Rodrigues
 * @version 1.0
 * @since 04/04/2013
 */
@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
public class UUIDGenerator {

    /**
     * Tamanho máximo para o nome do gerador de UUID.
     */
    private static final int NODE_MAX_LENGHT = 6;

    /**
     * A data base do Java é 01/01/1970, mas a data base do UTC e do cálculo do UUID é
     * 15/10/1582. Para ajustar a data, é utilizado uma subtração dos nanosegundos.
     */
    private static final long TIME_OFF_SET = 0x01b21dd213814000L;

    /**
     * Como a precisão do Java é em milisegundos, a multiplicação ajuda a criar uma
     * data com precisão em nanosegundos, como o UTC.
     */
    private static final long TIME_MULTIPLIER = 10000L;

    /**
     * O incremento para desempate de chaves quando são geradas no mesmo milisegundo.
     * Para desempate, é possível criar 10000 no mesmo milisegundo.
     */
    private static final long TIME_ADVANCE = 100L;

    /**
     * Número randômico para gerar a sequência do UUID.
     */
    private static final short CLOCK = (short) new Random(System.currentTimeMillis()).nextInt();

    /** Máscara para remover o último byte na formação do número MSB. */
    private static final int MASK_REMOVE_LAST_BYTE = ~0xF000;

    /** Máscara para configuar o UUID como tipo 1. */
    private static final int MASK_UUID_TYPE_1 = 0x1000;

    /**
     * Último nanosegundo usado para gerar uma chave UUID.
     */
    private AtomicLong lastNanos;

    /**
     * Nome de identificação do gerador de UUID.
     */
    private final String node;

    /**
     * Construtor padrão.
     *
     * @param node Nome de identificação para o gerador de UUID. Não pode ser nulo, vazio ou possuir
     *             mais que 6 caracteres.
     * @throws UUIDGeneratorNodeInvalid Nome de identificação inválido.
     */
    public UUIDGenerator(String node) throws UUIDGeneratorNodeInvalid {
        if (node == null || node.isEmpty() || node.length() > NODE_MAX_LENGHT) {
            throw new UUIDGeneratorNodeInvalid();
        }
        this.node 		= node;
        this.lastNanos 	= new AtomicLong(Long.MIN_VALUE);
    }

    /**
     * Gera uma chave Universal.
     *
     * @return UUID versão 1.
     */
    public UUID generate() {
        return new UUID(makeMSB(), makeLSB());
    }

    /**
     * É o primeiro parâmetro para o construtor do UUID.
     *
     * <p>
     * Possui os campos:
     *
     * <ul>
     * 	<li>time_low</li>
     * 	<li>time_mid</li>
     *  <li>time_hi and version</li>
     * </ul>
     *
     * @return long
     */
    private long makeMSB() {
        long result = 0L;
        long time = makeTime();
        // Trocando a posição dos bytes.
        int timeHigh	= (int) (time >>> Integer.SIZE);
        int timeLow		= (int) time;
        int timeMidHigh	= (timeHigh << Short.SIZE) | (timeHigh >>> Short.SIZE);
        // Adicionando a versão...
        timeMidHigh &= MASK_REMOVE_LAST_BYTE; 	// Remove o ultimo byte...
        timeMidHigh |= MASK_UUID_TYPE_1; 		// Configura o UUID para o tipo 1 (time-based)
        // Reconstruindo o número:
        result = (long) timeMidHigh;
        result = result << Integer.SIZE >>> Integer.SIZE; 		// Removendo o sinal...
        result = ((long) timeLow) << Integer.SIZE | result;
        return result;
    }

    /**
     * Cria um data no formato UTC, em nanosegundos.
     *
     * @return long
     */
    private long makeTime() {
        long result = 0L;

        result = System.currentTimeMillis();
        // Multiplicando para conseguir os nanosegundos.
        result *= TIME_MULTIPLIER;
        // Diferença dos nanosegundos para a data UTC (15/10/1582).
        result += TIME_OFF_SET;

        // Se houve empate nos momento de geração de dois UUIDs, incrementa-se
        // para gerar um valor único.
        do {
	        if (result > lastNanos.get()) {
	        	lastNanos.set(result);
	        } else {
	        	result = lastNanos.addAndGet(TIME_ADVANCE);
	        }
        } while (lastNanos.get() > result);

        return result;
    }

    /**
     * É o segundo parâmetro passado para o construtor do UUID.
     *
     * <p>
     * Representando:
     *
     * <ul>
     * 	<li>clock_seq_hi</li>
     * 	<li>clock_seq_low</li>
     * 	<li>node</li>
     * </ul>
     *
     * @return long
     */
    @SuppressWarnings("checkstyle:MagicNumber")
    private long makeLSB() {
        long result			= 0L;
        Random random		= new Random();
        ByteBuffer buffer 	= ByteBuffer.allocate(Long.SIZE / Byte.SIZE);
        buffer.putShort(CLOCK);
        for (int i = 0; i < (buffer.limit() - (Short.SIZE / Byte.SIZE) - node.length()); i++) {
            buffer.put((byte) random.nextInt());
        }
        buffer.put(node.getBytes());
        buffer.flip();
        result  = buffer.getLong();
        result  = result << 2 >>> 2; 	    // remove dois bits MSB
        result |= 2L << 62; 				// Configura os dois bits MSB para 10
        return result;
    }

    /**
     * Recupera a data de geração.
     *
     * @param uuid UUID
     * @return Date
     */
    public static Date getDate(UUID uuid) {
        if (uuid.version() != 1) {
            throw new UUIDGeneratorVersionInvalid();
        }
        long time = (uuid.timestamp() - TIME_OFF_SET) / TIME_MULTIPLIER;
        return new Date(time);
    }
}
