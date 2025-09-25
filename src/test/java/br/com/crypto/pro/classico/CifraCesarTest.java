package br.com.crypto.pro.classico;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class CifraCesarTest {

    @Test
    void encriptarDeveDeslocarCaracteres() {
        assertThat(CifraCesar.encriptar("ABC", 3)).isEqualTo("DEF");
    }

    @Test
    void decriptarDeveRetornarMensagemOriginal() {
        String original = "Hello World";
        int deslocamento = 7;
        String cifrada = CifraCesar.encriptar(original, deslocamento);
        assertThat(CifraCesar.decriptar(cifrada, deslocamento)).isEqualTo(original);
    }

    @Test
    void encriptarDevePreservarEspacosENumeros() {
        assertThat(CifraCesar.encriptar("abc 123", 1)).isEqualTo("bcd 123");
    }

    @Test
    void encriptarComDeslocamentoZeroNaoAlteraMensagem() {
        assertThat(CifraCesar.encriptar("Test", 0)).isEqualTo("Test");
    }

    @Test
    void encriptarComDeslocamento26RetornaMensagemOriginal() {
        assertThat(CifraCesar.encriptar("XYZ", 26)).isEqualTo("XYZ");
    }
}
