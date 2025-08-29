package br.com.codesolver.uuid;


/**
 * Informa que o nome de gerador de UUID é invalido.
 *
 * <p>
 * Ocorre quando o nome é nulo, vazio ou quando possui mais de 6 (seis) caracteres.
 *
 * <p>
 * Esta exceção fornece vários construtores para diferentes cenários de uso:
 *
 * <ul>
 *   <li>Construtor sem argumentos para exceções genéricas.</li>
 *   <li>Construtor com mensagem para descrição detalhada do erro.</li>
 *   <li>Construtor com mensagem e causa para encadeamento de exceções.</li>
 *   <li>Construtor com causa para encapsular exceções subjacentes.</li>
 *   <li>Construtor protegido com opções avançadas para supressão e gravabilidade da pilha de chamadas.</li>
 * </ul>
 *
 * @author <a href="mailto:luciano@codesolver.com.br">Luciano Vieira Rodrigues</a>
 * @since 2025-08-26
 */
@SuppressWarnings("checkstyle:AbbreviationAsWordInName")
public class UUIDGeneratorNodeInvalid extends RuntimeException {

    /**
     * Constrói uma nova {@code UUIDGeneratorNodeInvalid} com {@code null} como mensagem de detalhe.
     * A causa não é inicializada e pode ser definida posteriormente através de {@link Throwable#initCause}.
     */
    public UUIDGeneratorNodeInvalid() {
    }

    /**
     * Constrói uma nova {@code UUIDGeneratorNodeInvalid} com a mensagem de detalhe especificada.
     *
     * @param message mensagem explicando o motivo da exceção
     */
    public UUIDGeneratorNodeInvalid(String message) {
        super(message);
    }

    /**
     * Constrói uma nova {@code UUIDGeneratorNodeInvalid} com a mensagem de detalhe e causa especificadas.
     *
     * @param message mensagem de detalhe (recuperada posteriormente pelo método {@link #getMessage()})
     * @param cause causa da exceção (recuperada posteriormente pelo método {@link #getCause()}).
     *              O valor {@code null} indica que a causa é inexistente ou desconhecida.
     */
    public UUIDGeneratorNodeInvalid(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constrói uma nova {@code UUIDGeneratorNodeInvalid} com a causa especificada.
     *
     * @param cause causa subjacente desta exceção
     */
    public UUIDGeneratorNodeInvalid(Throwable cause) {
        super(cause);
    }

    /**
     * Constrói uma nova {@code UUIDGeneratorNodeInvalid} com mensagem de detalhe, causa,
     * supressão e gravabilidade da pilha de chamadas configuráveis.
     *
     * @param message mensagem de detalhe (recuperada posteriormente pelo método {@link #getMessage()})
     * @param cause causa da exceção (recuperada posteriormente pelo método {@link #getCause()}).
     *              O valor {@code null} indica que a causa é inexistente ou desconhecida.
     * @param enableSuppression indica se a supressão está habilitada ou desabilitada
     * @param writableStackTrace indica se a pilha de chamadas pode ser gravada
     */
    protected UUIDGeneratorNodeInvalid(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
