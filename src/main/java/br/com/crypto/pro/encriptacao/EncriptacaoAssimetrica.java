package br.com.crypto.pro.encriptacao;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

/**
 * Implementação de Criptografia Assimétrica utilizando o padrão RSA.
 */
public class EncriptacaoAssimetrica {

    private static final String ALGORITMO = "RSA";

    public static String encriptar(String entrada, PublicKey chavePublica) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITMO);
            cipher.init(Cipher.ENCRYPT_MODE, chavePublica);
            byte[] textoCifrado = cipher.doFinal(entrada.getBytes());
            return Base64.getEncoder().encodeToString(textoCifrado);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao encriptar com RSA", e);
        }
    }

    public static String decriptar(String textoCifrado, PrivateKey chavePrivada) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITMO);
            cipher.init(Cipher.DECRYPT_MODE, chavePrivada);
            byte[] textoPlano = cipher.doFinal(Base64.getDecoder().decode(textoCifrado));
            return new String(textoPlano);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao decriptar com RSA", e);
        }
    }

    public static KeyPair gerarParChaves() throws NoSuchAlgorithmException {
        KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGORITMO);
        generator.initialize(2048);
        return generator.generateKeyPair();
    }

    public static void main(String[] args) throws Exception {
        KeyPair parChaves = gerarParChaves();
        String mensagem = "Mensagem ultra secreta monitorada por RSA!";
        
        String cifrada = encriptar(mensagem, parChaves.getPublic());
        System.out.println("Mensagem Original: " + mensagem);
        System.out.println("Cifrada (RSA): " + cifrada);

        String decifrada = decriptar(cifrada, parChaves.getPrivate());
        System.out.println("Decifrada: " + decifrada);
    }
}
