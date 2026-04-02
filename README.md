# CryptoPro Especialista: Plataforma de Criptografia em Java

![Java 17](https://img.shields.io/badge/Java-17-orange)
![Maven](https://img.shields.io/badge/Maven-3.x-blue)
![Security](https://img.shields.io/badge/Security-Especialista-red)

Plataforma unificada de criptografia em Java, totalmente localizada em Português (PT-BR) e seguindo padrões de arquitetura corporativa.

## 🛡️ Arquitetura de Segurança por Domínio

O projeto está organizado no namespace `br.com.crypto.pro`, garantindo coesão e integridade:

### 1. Encriptação Simétrica (`EncriptacaoSimetrica`)
Implementação de **AES-256-CBC**.
- **Chave de 256 bits**: O mais alto padrão comercial disponível.
- **Vetor de Inicialização (IV)**: Entropia e unicidade para cada operação de cifragem.

### 2. Encriptação Assimétrica (`EncriptacaoAssimetrica`)
Padrão **RSA-2048**.
- Utiliza par de chaves (Pública/Privada) para comunicação segura.

### 3. Hashing de Alta Segurança (`ServicoHash`)
Utiliza **PBKDF2WithHmacSHA256** para senhas.
- **Sal Aleatório (Salt)**: Proteção definitiva contra Rainbow Tables.
- **Fator de Custo**: 10.000 iterações.

### 4. Assinatura Digital (`AssinaturaDigital`)
Uso de **SHA256withRSA** para garantir integridade e não-repúdio.

### 5. Gestão de Tokens (`ServicoToken`)
Emissão de **JWT (JSON Web Tokens)** utilizando a biblioteca **JJWT**.
- Suporte a Claims customizadas (cargo, projeto, etc).
- Validação de integridade e expiração.

---

## 🧪 Laboratório de Simulação (`SimulacaoSeguranca`)
Componente dedicado a testes e demonstração de vulnerabilidades:
- **Ataque de Dicionário**, **Força Bruta** e **Rainbow Table**.

---

## 🚀 Guia de Início Rápido

### Compilação
```bash
mvn clean compile
```

### Executar Demonstração (Tokens JWT)
```bash
mvn exec:java -Dexec.mainClass="br.com.crypto.pro.identidade.ServicoToken"
```

---

> [!CAUTION]
> **Arquivos JavaScript Descontinuados**: Os arquivos originais `.js` foram marcados como obsoletos (deprecated) e sua lógica foi removida para evitar confusão. O foco total do projeto agora é a implementação em **Java**. Sinta-se à vontade para deletar as pastas antigas manualmente se desejar uma limpeza completa do diretório.

> [!IMPORTANT]
> **Padrões Técnicos**: Este projeto utiliza estritamente o **JCA (Java Cryptography Architecture)**. Todo o código foi escrito seguindo as melhores práticas de **Clean Code** e está 100% em Português.
