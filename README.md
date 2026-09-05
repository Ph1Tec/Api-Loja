# Loja API

API REST para gerenciamento de produtos e categorias de uma loja, desenvolvida em Java com Spring Boot. Projeto de estudo com foco em relacionamento entre entidades e persistência em banco de dados real.

## Tecnologias

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- PostgreSQL
- Lombok
- Maven

## Como rodar o projeto

1. Clone o repositório:
```bash
git clone https://github.com/Ph1Tec/loja-api.git
cd loja-api
```

2. Crie o banco de dados no PostgreSQL:
```sql
CREATE DATABASE lojadb;
```

3. Configure a variável de ambiente `DB_PASSWORD` com a senha do seu usuário do PostgreSQL (na sua IDE, em "Edit Configurations" > "Environment variables", ou no sistema operacional).

4. Rode a aplicação:
```bash
./mvnw spring-boot:run
```

5. A aplicação sobe em `http://localhost:8080`

## Modelo de dados

- Uma **Categoria** pode ter vários **Produtos**
- Um **Produto** pertence a uma única **Categoria** (relacionamento `@ManyToOne`)

## Endpoints

### Categorias

Base URL: `http://localhost:8080/categories`

| Método | Rota | Descrição |
|--------|------|-----------|
| POST | `/categories` | Cria uma nova categoria |
| GET | `/categories` | Lista todas as categorias |
| GET | `/categories/{id}` | Busca uma categoria pelo id |
| PUT | `/categories/{id}` | Atualiza uma categoria |
| DELETE | `/categories/{id}` | Remove uma categoria |

**Exemplo de criação:**
```json
{
    "name": "Eletrônicos"
}
```

### Produtos

Base URL: `http://localhost:8080/produtos`

| Método | Rota | Descrição |
|--------|------|-----------|
| POST | `/produtos` | Cria um novo produto |
| GET | `/produtos` | Lista todos os produtos |
| GET | `/produtos/{id}` | Busca um produto pelo id |
| GET | `/produtos/categoria/{categoryId}` | Lista produtos de uma categoria específica |
| PUT | `/produtos/{id}` | Atualiza um produto (inclusive a categoria) |
| DELETE | `/produtos/{id}` | Remove um produto |

**Exemplo de criação:**
```json
{
    "name": "Notebook",
    "description": "Notebook 15 polegadas",
    "price": 3500.00,
    "categoryId": 1
}
```

**Exemplo de resposta:**
```json
{
    "id": 1,
    "name": "Notebook",
    "description": "Notebook 15 polegadas",
    "price": 3500.00,
    "category": {
        "id": 1,
        "name": "Eletrônicos"
    }
}
```

## Tratamento de erros

| Situação | Status HTTP |
|----------|-------------|
| Categoria não encontrada | 404 Not Found |
| Produto não encontrado | 404 Not Found |

## Boas práticas aplicadas

- Senha do banco de dados protegida via variável de ambiente (`${DB_PASSWORD}`), nunca exposta em texto puro no código
- DTOs de entrada (create) separados das entidades, evitando que o cliente envie campos indevidos
- DTOs de resposta (response), incluindo DTO aninhado para o relacionamento, prevenindo problemas de serialização circular
- Exceções customizadas + `@RestControllerAdvice` para respostas de erro com status HTTP corretos

## Estrutura do projeto

```
src/main/java/com/loja/produtos/
 ├── model/         # Entidades JPA (Product, Category)
 ├── dto/           # DTOs de entrada e saída
 ├── repository/    # Interfaces de acesso ao banco
 ├── service/       # Regras de negócio
 ├── controller/    # Endpoints REST
 └── exception/     # Exceções customizadas e tratamento global de erros
```

## Próximos passos

- [ ] Validação de campos (preço não negativo, nome obrigatório)
- [ ] Paginação na listagem de produtos
- [ ] Filtros adicionais (por faixa de preço, por nome)
