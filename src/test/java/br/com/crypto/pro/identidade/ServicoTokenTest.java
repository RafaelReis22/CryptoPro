package br.com.crypto.pro.identidade;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ServicoTokenTest {

    @Test
    void gerarTokenDeveRetornarStringNaoVazia() {
        String token = ServicoToken.gerarToken("user_01", "ADMIN");
        assertThat(token).isNotBlank();
    }

    @Test
    void decodificarTokenDeveRetornarSubjectCorreto() {
        String token = ServicoToken.gerarToken("user_42", "USER");
        Claims claims = ServicoToken.decodificarToken(token);
        assertThat(claims.getSubject()).isEqualTo("user_42");
    }

    @Test
    void decodificarTokenDeveRetornarCargoCorreto() {
        String token = ServicoToken.gerarToken("user_01", "MANAGER");
        Claims claims = ServicoToken.decodificarToken(token);
        assertThat(claims.get("cargo")).isEqualTo("MANAGER");
    }

    @Test
    void tokenDeveConterClaimDeProjeto() {
        String token = ServicoToken.gerarToken("user_01", "ADMIN");
        Claims claims = ServicoToken.decodificarToken(token);
        assertThat(claims.get("projeto")).isEqualTo("CryptoPro Especialista");
    }

    @Test
    void tokenDeveConterDataDeExpiracao() {
        String token = ServicoToken.gerarToken("user_01", "ADMIN");
        Claims claims = ServicoToken.decodificarToken(token);
        assertThat(claims.getExpiration()).isNotNull().isInTheFuture();
    }
}
