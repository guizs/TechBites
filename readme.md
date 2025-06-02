# 🧪 Techbites

**Techbites** é um projeto backend desenvolvido durante a **Pós Tech em Java da FIAP**, com foco em práticas modernas utilizando **Spring Boot**, **PostgreSQL** e **Docker**. Ele simula um sistema colaborativo para gestão de restaurantes, com funcionalidades como cadastro, login e gerenciamento de usuários.

---

## 📦 Requisitos

Antes de executar, certifique-se de ter instalado:

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)

---

## 🚀 Executando a aplicação

Após clonar o repositório, **não é necessário configurar nenhum arquivo `.env`**. Basta executar:

```bash
docker-compose up --build
```

A aplicação estará disponível em: [http://localhost:8080](http://localhost:8080)

---

## 🧱 Estrutura do Projeto

- `Dockerfile.techbites-api`: Define a imagem da aplicação Spring Boot.
- `Dockerfile.techbites-db`: Define a imagem do banco PostgreSQL.
- `docker-compose.yml`: Orquestração dos serviços (API + Banco).
- `src/`: Código-fonte da aplicação organizada em camadas (Controller, Service, Repository, DTO, etc).

---

## 🔗 Endpoints da API

| Método | Endpoint                      | Descrição                                 |
|--------|-------------------------------|-------------------------------------------|
| POST   | `/users`                      | Criação de usuário                         |
| GET    | `/users`                      | Listar todos os usuários (ativos/inativos)|
| GET    | `/users/{id}`                 | Buscar usuário por ID                      |
| PUT    | `/users/{id}`                 | Atualizar dados do usuário                 |
| DELETE | `/users/{id}`                 | Desabilitar usuário (soft delete)          |
| PATCH  | `/users/enable/{email}`       | Reativar usuário                           |

Para exemplos de requisições e respostas, acesse a [Collection do Postman](https://fiap-team-3629.postman.co/workspace/FIAP-team-Workspace~31d695a1-60ef-4fb0-8c0d-d37e7e2190c5/collection/42979032-1831d148-7563-438f-80cb-3e878814d7b2?action=share&creator=42979032&active-environment=42979032-e6903392-b44c-4514-b7e1-8f5efbf33529).

---

## 🧩 Serviços Docker

| Serviço         | Porta | Container       |
|-----------------|-------|-----------------|
| API (Spring)    | 8080  | techbites-api   |
| Banco (Postgres)| 5432  | techbites-db    |

- **Volume persistente:** `postgres_data`
- **Rede bridge customizada:** `techbites-network`

---

## 🧠 Arquitetura

O projeto segue uma arquitetura em camadas:

- **Controller:** recebe as requisições HTTP.
- **Service:** contém a lógica de negócio.
- **Repository:** integração com o banco via Spring Data JPA.
- **DTO, Mapper, Entity:** abstração e estrutura de dados.
- **Config:** configurações gerais da aplicação.

---

## ✅ Boas práticas aplicadas

- **Spring Boot padrão:** estrutura de pacotes, `application.yml`, tratamento global de exceções.
- **Princípios SOLID e DRY**: código modular, reutilizável e de fácil manutenção.
- **Execução containerizada**: via `Docker Compose`, garantindo reprodutibilidade do ambiente.

---

## 📁 Repositório

🔗 [https://github.com/TechBites-fiap/TechBites](https://github.com/TechBites-fiap/TechBites)
