package br.com.crypto.pro.encriptacao;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Implementação de Criptografia Simétrica utilizando o padrão AES-256-CBC.
 */
public class EncriptacaoSimetrica {

    private static final String ALGORITMO = "AES/CBC/PKCS5Padding";

    /**
     * Encripta um texto utilizando uma chave secreta e um IV.
     */
    public static String encriptar(String entrada, byte[] chave, byte[] iv) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITMO);
            SecretKeySpec keySpec = new SecretKeySpec(chave, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            byte[] textoCifrado = cipher.doFinal(entrada.getBytes());
            
            return Base64.getEncoder().encodeToString(textoCifrado);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao encriptar dados (AES)", e);
        }
    }

    /**
     * Decripta um texto cifrado em Base64 utilizando a chave e o IV correspondentes.
     */
    public static String decriptar(String textoCifrado, byte[] chave, byte[] iv) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITMO);
            SecretKeySpec keySpec = new SecretKeySpec(chave, "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            byte[] textoPlano = cipher.doFinal(Base64.getDecoder().decode(textoCifrado));
            
            return new String(textoPlano);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao decriptar dados (AES)", e);
        }
    }

    public static byte[] gerarChave() {
        byte[] chave = new byte[32];
        new SecureRandom().nextBytes(chave);
        return chave;
    }

    public static byte[] gerarIv() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return iv;
    }

    public static void main(String[] args) {
        String mensagem = "Demonstração de encriptação simétrica (AES-256-CBC)";
        byte[] chave = gerarChave();
        byte[] iv = gerarIv();

        String cifrada = encriptar(mensagem, chave, iv);
        System.out.println("Mensagem Original: " + mensagem);
        System.out.println("Cifrada (Base64): " + cifrada);

        String decifrada = decriptar(cifrada, chave, iv);
        System.out.println("Decifrada: " + decifrada);
    }
}
