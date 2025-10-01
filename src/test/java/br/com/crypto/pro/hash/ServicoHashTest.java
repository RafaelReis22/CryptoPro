package br.com.crypto.pro.hash;

import br.com.crypto.pro.modelo.Usuario;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ServicoHashTest {

    @Test
    void gerarSha256DeveRetornarHashHexadecimalDe64Caracteres() {
        String hash = ServicoHash.gerarSha256("senha123");
        assertThat(hash).hasSize(64).matches("[a-f0-9]+");
    }

    @Test
    void gerarSha256DeveSerDeterministico() {
        String hash1 = ServicoHash.gerarSha256("entrada");
        String hash2 = ServicoHash.gerarSha256("entrada");
        assertThat(hash1).isEqualTo(hash2);
    }

    @Test
    void gerarSha256EntradasDiferentesDevemGerarHashesDiferentes() {
        assertThat(ServicoHash.gerarSha256("a")).isNotEqualTo(ServicoHash.gerarSha256("b"));
    }

    @Test
    void gerarSalDeveRetornarStringNaoVazia() {
        assertThat(ServicoHash.gerarSal()).isNotBlank().hasSizeGreaterThan(10);
    }

    @Test
    void doisSaisDevemSerDiferentes() {
        assertThat(ServicoHash.gerarSal()).isNotEqualTo(ServicoHash.gerarSal());
    }

    @Test
    void autenticarComSenhaCertaDeveRetornarTrue() {
        String sal = ServicoHash.gerarSal();
        String hash = ServicoHash.gerarHashComSal("minhasenha", sal);
        Usuario usuario = new Usuario("rafael", hash, sal);
        assertThat(ServicoHash.autenticar(usuario, "minhasenha")).isTrue();
    }

    @Test
    void autenticarComSenhaErradaDeveRetornarFalse() {
        String sal = ServicoHash.gerarSal();
        String hash = ServicoHash.gerarHashComSal("minhasenha", sal);
        Usuario usuario = new Usuario("rafael", hash, sal);
        assertThat(ServicoHash.autenticar(usuario, "outrasenha")).isFalse();
    }

    @Test
    void autenticarComSha256SenhaCertaDeveRetornarTrue() {
        Usuario usuario = new Usuario("admin", ServicoHash.gerarSha256("admin123"));
        assertThat(ServicoHash.autenticar(usuario, "admin123")).isTrue();
    }
}
