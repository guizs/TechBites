# 🧪 Techbites

**Techbites** é um projeto desenvolvido durante a **Pós Tech em Java da FIAP**, com foco em práticas modernas de backend usando **Spring Boot** e **Docker**. Ele simula uma aplicação real com API e banco de dados integrados.

## 🚀 Como executar com Docker Compose

### Pré-requisitos
- Docker instalado
- Docker Compose instalado

### Passo a passo

1. Crie um arquivo `.env` com as seguintes variáveis:

```env
SPRING_DATASOURCE_URL=jdbc:postgresql://techbites-db:5432/techbitesdb
SPRING_DATASOURCE_USERNAME=techbites
SPRING_DATASOURCE_PASSWORD=senha123
POSTGRES_USER=techbites
POSTGRES_PASSWORD=senha123
POSTGRES_DB=techbitesdb
```

### Execute o comando:

```env
docker-compose up --build
```

## 🧱 Estrutura do projeto

- `Dockerfile.techbites-api`: imagem da aplicação Spring Boot
- `Dockerfile.techbites-db`: imagem do banco de dados PostgreSQL
- `.env`: arquivo com variáveis de ambiente
- `docker-compose.yml`: orquestração dos serviços

## 🗃️ Volumes e Rede

- **Volume persistente**: `postgres_data`
- **Rede bridge**: `techbites-network`

## 🧩 Serviços

| Serviço         | Porta | Container       |
|-----------------|-------|-----------------|
| API (Spring)    | 8080  | techbites-api   |
| Banco (Postgres)| 5432  | techbites-db    |
