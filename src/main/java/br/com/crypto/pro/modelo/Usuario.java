package br.com.crypto.pro.modelo;

/**
 * Entidade que representa um usuário no sistema de segurança.
 * Armazena informações de autenticação com foco em integridade e sigilo.
 */
public class Usuario {
    private final String nome;
    private final String hash;
    private final String sal; // Opcional, usado apenas em senhas com salting

    public Usuario(String nome, String hash) {
        this(nome, hash, null);
    }

    public Usuario(String nome, String hash, String sal) {
        this.nome = nome;
        this.hash = hash;
        this.sal = sal;
    }

    public String getNome() {
        return nome;
    }

    public String getHash() {
        return hash;
    }

    public String getSal() {
        return sal;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", hash='" + hash + '\'' +
                ", sal='" + (sal != null ? sal : "N/A") + '\'' +
                '}';
    }
}
