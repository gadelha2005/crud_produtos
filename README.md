
# 🛒 CRUD de Produtos com Spring Boot, Testes Unitários e JWT

Este repositório contém uma aplicação RESTful desenvolvida com **Spring Boot**, que realiza operações de **CRUD de produtos**, integrando autenticação e autorização com **JWT (JSON Web Token)** e **controle de acesso baseado em roles (ADMIN e USER)**.

Além disso, a aplicação conta com **testes unitários utilizando JUnit 5 e Mockito**, reforçando a confiabilidade da lógica de negócio.

---

## ✅ Funcionalidades

- Registro e autenticação de usuários com geração de token JWT
- Criação, leitura, atualização e exclusão (CRUD) de produtos
- Controle de acesso por permissões (administração e leitura)
- Validação de entrada de dados com Bean Validation
- Testes unitários da camada de serviço

---

## 🔐 Segurança e Controle de Acesso

A autenticação é baseada em **JWT**, com dois perfis de usuário:

- **ADMIN**: possui acesso total aos endpoints (GET, POST, PUT, DELETE)
- **USER**: possui acesso somente de leitura (GET)

### Endpoints Públicos:

- `POST /auth/register`: Cadastro de novo usuário
- `POST /auth/login`: Autenticação e geração de token JWT

### Regras de Autorização:

| Endpoint               | Método | ADMIN | USER |
|------------------------|--------|:-----:|:----:|
| `/auth/register`       | POST   | ✅    | ✅   |
| `/auth/login`          | POST   | ✅    | ✅   |
| `/api/produtos`        | GET    | ✅    | ✅   |
| `/api/produtos/{id}`   | GET    | ✅    | ✅   |
| `/api/produtos`        | POST   | ✅    | ❌   |
| `/api/produtos/{id}`   | PUT    | ✅    | ❌   |
| `/api/produtos/{id}`   | DELETE | ✅    | ❌   |

> Todos os demais endpoints requerem o envio do token JWT no cabeçalho `Authorization: Bearer <token>`.

---

## 🧪 Testes Unitários

A camada de serviço (`ProdutoService`) conta com testes unitários utilizando:

- **JUnit 5**: Estrutura de testes moderna e expressiva
- **Mockito**: Simulação de dependências e comportamento
- **Cobertura de testes**: métodos de criação, atualização, listagem e exclusão de produtos são cobertos por testes

---

## 🧰 Tecnologias e Ferramentas

- Java 17
- Spring Boot 3
- Spring Security
- JWT (JSON Web Token)
- Spring Data JPA
- MySQL
- JUnit 5
- Mockito
- Maven

---

## ▶️ Como Executar o Projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git
   ```

2. Acesse a pasta do projeto:
   ```bash
   cd seu-repositorio
   ```

3. Execute a aplicação com Maven:
   ```bash
   ./mvnw spring-boot:run
   ```

> A aplicação estará disponível em: `http://localhost:8080`

---

## 🔄 Fluxo de Autenticação

1. O usuário se registra em `/auth/register`
2. Em seguida, realiza login em `/auth/login`
3. Um token JWT é retornado e deve ser usado no cabeçalho `Authorization` para consumir endpoints protegidos

   ```
   Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR...
   ```

---

## 🧩 Estrutura de Pastas

```
├── controller/          # Controladores REST
├── dto/                 # Data Transfer Objects
├── model/               # Entidades JPA e enums
├── repository/          # Interfaces JPA
├── security/            # Configuração de segurança e JWT
├── service/             # Camada de serviço (lógica de negócio)
├── tests/               # Testes unitários (JUnit + Mockito)
└── application.java     # Classe principal
```

---

## 📌 Considerações Finais

Este projeto é ideal para quem deseja aprender ou praticar:

- Autenticação e autorização segura com JWT
- Boas práticas REST com Spring Boot
- Testes unitários com cobertura significativa
- Controle de acesso baseado em roles
- Separação de responsabilidades por camadas

---

## 🧑‍💻 Autor

**Pedro Gadelha**  
Desenvolvedor Backend • Estudante de Ciência da Computação  
📧 pgpgadelha123@gmail.com  
🔗 [LinkedIn](https://www.linkedin.com/in/pedro-gadelha-b1a05934a) • [GitHub](https://https://github.com/gadelha2005)
