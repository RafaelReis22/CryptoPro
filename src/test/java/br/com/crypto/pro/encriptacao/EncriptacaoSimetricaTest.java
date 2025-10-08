package br.com.crypto.pro.encriptacao;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class EncriptacaoSimetricaTest {

    @Test
    void encriptarEDecriptarDevemRetornarMensagemOriginal() {
        byte[] chave = EncriptacaoSimetrica.gerarChave();
        byte[] iv = EncriptacaoSimetrica.gerarIv();
        String mensagem = "Mensagem secreta de teste";

        String cifrada = EncriptacaoSimetrica.encriptar(mensagem, chave, iv);
        String decifrada = EncriptacaoSimetrica.decriptar(cifrada, chave, iv);

        assertThat(decifrada).isEqualTo(mensagem);
    }

    @Test
    void mensagemCifradaNaoDeveIgualarOriginal() {
        byte[] chave = EncriptacaoSimetrica.gerarChave();
        byte[] iv = EncriptacaoSimetrica.gerarIv();
        String mensagem = "Dado sensível";

        String cifrada = EncriptacaoSimetrica.encriptar(mensagem, chave, iv);
        assertThat(cifrada).isNotEqualTo(mensagem);
    }

    @Test
    void gerarChaveDeveRetornar32Bytes() {
        byte[] chave = EncriptacaoSimetrica.gerarChave();
        assertThat(chave).hasSize(32);
    }

    @Test
    void gerarIvDeveRetornar16Bytes() {
        byte[] iv = EncriptacaoSimetrica.gerarIv();
        assertThat(iv).hasSize(16);
    }

    @Test
    void duasChavesDiferentesDevemGerarCifrasDiferentes() {
        byte[] chave1 = EncriptacaoSimetrica.gerarChave();
        byte[] chave2 = EncriptacaoSimetrica.gerarChave();
        byte[] iv = EncriptacaoSimetrica.gerarIv();
        String mensagem = "Teste";

        String cifrada1 = EncriptacaoSimetrica.encriptar(mensagem, chave1, iv);
        String cifrada2 = EncriptacaoSimetrica.encriptar(mensagem, chave2, iv);
        assertThat(cifrada1).isNotEqualTo(cifrada2);
    }
}
