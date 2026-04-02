package br.com.crypto.pro.classico;

/**
 * Implementação profissional da Cifra de César.
 * Este algoritmo realiza o deslocamento modular de caracteres no alfabeto ASCII.
 * Suporta preservação de caracteres especiais e distinção entre maiúsculas/minúsculas.
 */
public class CifraCesar {

    /**
     * Cifra uma mensagem utilizando um deslocamento específico.
     *
     * @param mensagem A string original a ser cifrada.
     * @param deslocamento O número de posições a deslocar.
     * @return A mensagem cifrada resultante.
     */
    public static String encriptar(String mensagem, int deslocamento) {
        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < mensagem.length(); i++) {
            char caractere = mensagem.charAt(i);

            if (caractere >= 'A' && caractere <= 'Z') {
                char cifrado = (char) (((caractere - 'A' + deslocamento) % 26) + 'A');
                resultado.append(cifrado);
            }
            else if (caractere >= 'a' && caractere <= 'z') {
                char cifrado = (char) (((caractere - 'a' + deslocamento) % 26) + 'a');
                resultado.append(cifrado);
            }
            else {
                resultado.append(caractere);
            }
        }
        return resultado.toString();
    }

    /**
     * Decifra uma mensagem cifrada invertendo o deslocamento.
     *
     * @param mensagem A string cifrada.
     * @param deslocamento O número de posições original do deslocamento.
     * @return A mensagem decifrada original.
     */
    public static String decriptar(String mensagem, int deslocamento) {
        return encriptar(mensagem, 26 - (deslocamento % 26));
    }

    public static void main(String[] args) {
        String mensagemOriginal = "the answer is forty two";
        int deslocamento = 5;

        String mensagemCifrada = encriptar(mensagemOriginal, deslocamento);
        System.out.println("Mensagem Original: " + mensagemOriginal);
        System.out.println("Cifrada: " + mensagemCifrada);

        String mensagemDecifrada = decriptar(mensagemCifrada, deslocamento);
        System.out.println("Decifrada: " + mensagemDecifrada);
    }
}
