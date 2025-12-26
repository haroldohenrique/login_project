# Login Project — Spring Boot + PostgreSQL

Projeto pessoal para **portfólio**: uma aplicação simples de **Login e Cadastro** usando **Java (Spring Boot)**, **Thymeleaf** e **PostgreSQL**. A ideia é demonstrar validações, persistência com JPA e uma estrutura organizada para um fluxo básico de autenticação.

> 🚧 Em desenvolvimento.

---

## ✅ Tecnologias
- Java 21
- Spring Boot
- Spring MVC + Thymeleaf
- Spring Data JPA
- PostgreSQL
- Docker / Docker Compose

---

## ▶️ Como rodar na máquina

### Pré-requisitos
- Java **21**
- Maven
- Docker e Docker Compose

### 1) Configure as variáveis do Postgres
Crie um arquivo `.env` na raiz do projeto:

```env
POSTGRES_DB=login_project
POSTGRES_USER=login_project
POSTGRES_PASSWORD=login
```
### 2) Na raiz do projeto, suba tudo com Docker:

```bash
docker compose up -d --build
```

### 3) A aplicação ficará disponível em:

#### http://localhost:8080

### 4) Para parar de rodar os containers

```bash
docker compose down
```
