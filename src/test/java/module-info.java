/**
 * Informações para o módulo de testes. 
 *
 * @author <a href="mailto:luciano@codesolver.com.br">Luciano Vieira Rodrigues</a>
 * @since 2025-09-01
 */
module br.com.codesolver.util.test {
    requires br.com.codesolver.util;
    requires java.logging;
    requires org.junit.jupiter.api;
    requires org.easymock;

    opens br.com.codesolver.digester.test to org.junit.platform.commons;
    opens br.com.codesolver.session.test to org.junit.platform.commons;
    opens br.com.codesolver.util.test to org.junit.platform.commons;
    opens br.com.codesolver.uuid.test to org.junit.platform.commons;
}