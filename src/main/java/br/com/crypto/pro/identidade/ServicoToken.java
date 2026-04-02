package br.com.crypto.pro.identidade;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

/**
 * Serviço de gerenciamento de Tokens utilizando o padrão JWT (JSON Web Token).
 */
public class ServicoToken {

    private static final Key CHAVE_SECRETA = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String gerarToken(String usuarioId, String papel) {
        return Jwts.builder()
                .setSubject(usuarioId)
                .claim("cargo", papel)
                .claim("projeto", "CryptoPro Especialista")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(CHAVE_SECRETA)
                .compact();
    }

    public static Claims decodificarToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(CHAVE_SECRETA)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public static void main(String[] args) {
        String token = gerarToken("usuario_especialista_01", "ADMIN");
        System.out.println("JWT Gerado: " + token);

        Claims claims = decodificarToken(token);
        System.out.println("Usuário (Subject): " + claims.getSubject());
        System.out.println("Cargo: " + claims.get("cargo"));
        System.out.println("Projeto: " + claims.get("projeto"));
    }
}
