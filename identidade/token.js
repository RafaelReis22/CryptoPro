import jwt from 'jsonwebtoken';
import crypto from 'crypto';

const SECRET_KEY = process.env.JWT_SECRET || crypto.randomBytes(32).toString('hex');
const EXPIRATION = process.env.JWT_EXPIRATION || '1h';

/**
 * Gera um token JWT com os dados do usuário.
 * @param {string} usuarioId - ID do usuário
 * @param {string} papel - Papel/role do usuário
 * @returns {string} Token JWT assinado
 */
export function gerarToken(usuarioId, papel) {
    return jwt.sign(
        {
            sub: usuarioId,
            cargo: papel,
            projeto: 'CryptoPro Especialista'
        },
        SECRET_KEY,
        { expiresIn: EXPIRATION }
    );
}

/**
 * Valida e decodifica um token JWT.
 * @param {string} token - Token JWT a ser validado
 * @returns {object} Payload decodificado
 * @throws {Error} Se o token for inválido ou expirado
 */
export function decodificarToken(token) {
    return jwt.verify(token, SECRET_KEY);
}
