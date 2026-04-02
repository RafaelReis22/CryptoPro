package br.com.crypto.pro.simulacao;

import br.com.crypto.pro.hash.ServicoHash;
import br.com.crypto.pro.modelo.Usuario;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Simulador de Vulnerabilidades e Vetores de Ataque.
 */
public class SimulacaoSeguranca {

    public static void executarAtaqueDicionario(Usuario usuario, List<String> dicionario) {
        System.out.println("\n[INICIANDO ATAQUE DE DICIONÁRIO]");
        for (String candidato : dicionario) {
            if (ServicoHash.autenticar(usuario, candidato)) {
                System.out.println("SENHA ENCONTRADA: " + candidato);
                return;
            }
        }
        System.out.println("Ataque falhou.");
    }

    public static void executarForcaBruta4Digitos(Usuario usuario) {
        System.out.println("\n[INICIANDO ATAQUE DE FORÇA BRUTA (4 dígitos)]");
        for (int i = 0; i < 10000; i++) {
            String candidato = String.format("%04d", i);
            if (ServicoHash.autenticar(usuario, candidato)) {
                System.out.println("SENHA ENCONTRADA: " + candidato);
                return;
            }
        }
        System.out.println("Ataque falhou.");
    }

    public static void executarAtaqueRainbowTable(List<String> hashesVazados, List<String> senhasComuns) {
        System.out.println("\n[INICIANDO ATAQUE POR RAINBOW TABLE]");
        
        Map<String, String> rainbowTable = new HashMap<>();
        for (String senha : senhasComuns) {
            rainbowTable.put(ServicoHash.gerarSha256(senha), senha);
        }

        for (String hash : hashesVazados) {
            if (rainbowTable.containsKey(hash)) {
                System.out.println("Hash " + hash + " quebrado! Senha: " + rainbowTable.get(hash));
            }
        }
    }

    public static void main(String[] args) {
        Usuario vitima1 = new Usuario("victor", ServicoHash.gerarSha256("qwerty123"));
        Usuario vitima2 = new Usuario("admin_teste", ServicoHash.gerarSha256("0765"));

        List<String> dicionario = Arrays.asList("senha", "123456", "qwerty123", "admin", "brasil");
        executarAtaqueDicionario(vitima1, dicionario);

        executarForcaBruta4Digitos(vitima2);

        List<String> hashesVazados = Arrays.asList(
            ServicoHash.gerarSha256("admin"),
            ServicoHash.gerarSha256("senha123")
        );
        List<String> senhasComuns = Arrays.asList("admin", "12345", "senha123");
        executarAtaqueRainbowTable(hashesVazados, senhasComuns);
    }
}
