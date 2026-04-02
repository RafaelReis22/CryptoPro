package br.com.crypto.pro.identidade;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

/**
 * Implementação de Assinatura Digital utilizando RSA com SHA-256.
 */
public class AssinaturaDigital {

    private static final String ALGORITMO = "SHA256withRSA";

    public static String assinar(String dados, PrivateKey chavePrivada) {
        try {
            Signature signature = Signature.getInstance(ALGORITMO);
            signature.initSign(chavePrivada);
            signature.update(dados.getBytes());
            byte[] dadosAssinados = signature.sign();
            return Base64.getEncoder().encodeToString(dadosAssinados);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao assinar digitalmente", e);
        }
    }

    public static boolean verificar(String dados, String assinaturaBase64, PublicKey chavePublica) {
        try {
            Signature signature = Signature.getInstance(ALGORITMO);
            signature.initVerify(chavePublica);
            signature.update(dados.getBytes());
            byte[] bytesAssinatura = Base64.getDecoder().decode(assinaturaBase64);
            return signature.verify(bytesAssinatura);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao verificar assinatura digital", e);
        }
    }

    public static void main(String[] args) throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048);
        KeyPair parChaves = generator.generateKeyPair();

        String dados = "Mensagem com assinatura digital para verificação de procedência.";
        String assinatura = assinar(dados, parChaves.getPrivate());
        
        System.out.println("Dados: " + dados);
        System.out.println("Assinatura (Base64): " + assinatura);
        System.out.println("Assinatura Válida: " + verificar(dados, assinatura, parChaves.getPublic()));
    }
}
