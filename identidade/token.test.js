import { describe, it, expect } from '@jest/globals';
import { gerarToken, decodificarToken } from './token.js';

describe('ServicoToken JS', () => {
    it('deve gerar um token não vazio', () => {
        const token = gerarToken('user_01', 'ADMIN');
        expect(token).toBeTruthy();
        expect(typeof token).toBe('string');
    });

    it('deve decodificar o subject corretamente', () => {
        const token = gerarToken('user_42', 'USER');
        const payload = decodificarToken(token);
        expect(payload.sub).toBe('user_42');
    });

    it('deve decodificar o cargo corretamente', () => {
        const token = gerarToken('user_01', 'MANAGER');
        const payload = decodificarToken(token);
        expect(payload.cargo).toBe('MANAGER');
    });

    it('deve conter o nome do projeto no payload', () => {
        const token = gerarToken('user_01', 'ADMIN');
        const payload = decodificarToken(token);
        expect(payload.projeto).toBe('CryptoPro Especialista');
    });

    it('deve lançar erro para token inválido', () => {
        expect(() => decodificarToken('token_invalido')).toThrow();
    });
});
