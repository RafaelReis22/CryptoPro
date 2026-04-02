package br.com.crypto.pro.hash;

import br.com.crypto.pro.modelo.Usuario;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

/**
 * Serviço especializado em algoritmos de hashing.
 * Contém implementações para SHA-256 e PBKDF2 (Hashing com Sal).
 */
public class ServicoHash {

    /**
     * Gera um hash SHA-256 simples em formato hexadecimal.
     * Utilizado para integridade de dados não-sensíveis.
     */
    public static String gerarSha256(String entrada) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(entrada.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao gerar hash SHA-256", e);
        }
    }

    /**
     * Gera um sal (salt) aleatório e seguro utilizando SecureRandom.
     */
    public static String gerarSal() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    /**
     * Implementa Hashing com Sal utilizando PBKDF2WithHmacSHA256.
     * Este é o padrão recomendado para armazenamento seguro de senhas em Java.
     */
    public static String gerarHashComSal(String senha, String sal) {
        try {
            char[] passwordChars = senha.toCharArray();
            byte[] saltBytes = Base64.getDecoder().decode(sal);
            
            // PBKDF2 com 10.000 iterações e chave de 256 bits
            PBEKeySpec spec = new PBEKeySpec(passwordChars, saltBytes, 10000, 256);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Erro ao gerar salted hash", e);
        }
    }

    /**
     * Realiza a autenticação de um usuário comparando o hash de entrada com o armazenado.
     */
    public static boolean autenticar(Usuario usuario, String senha) {
        if (usuario.getSal() != null) {
            String hashEntrada = gerarHashComSal(senha, usuario.getSal());
            return hashEntrada.equals(usuario.getHash());
        } else {
            return gerarSha256(senha).equals(usuario.getHash());
        }
    }

    public static void main(String[] args) {
        // Exemplo SHA-256
        Usuario user1 = new Usuario("admin", gerarSha256("admin"));
        System.out.println("Autenticação Simples (SHA-256):");
        System.out.println("Sucesso: " + autenticar(user1, "admin"));
        System.out.println("Falha: " + autenticar(user1, "errado"));

        // Exemplo PBKDF2 (Salted)
        String sal = gerarSal();
        String hashComSal = gerarHashComSal("xxcoquinhageladaxx", sal);
        Usuario user2 = new Usuario("Rodorfo", hashComSal, sal);
        
        System.out.println("\nAutenticação com Sal (PBKDF2):");
        System.out.println("Sucesso: " + autenticar(user2, "xxcoquinhageladaxx"));
        System.out.println("Usuário: " + user2);
    }
}
