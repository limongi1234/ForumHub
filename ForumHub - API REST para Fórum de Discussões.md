# ForumHub - API REST para Fórum de Discussões

## Descrição

O ForumHub é uma API REST desenvolvida em Spring Boot que replica o funcionamento de um fórum de discussões, similar ao utilizado na plataforma Alura. A API permite que usuários criem, visualizem, atualizem e excluam tópicos de discussão, implementando um sistema completo de CRUD com autenticação e autorização.

## Funcionalidades

### Operações CRUD para Tópicos
- **CREATE**: Criar um novo tópico
- **READ**: Listar todos os tópicos ou buscar um tópico específico
- **UPDATE**: Atualizar um tópico existente
- **DELETE**: Excluir um tópico

### Sistema de Autenticação e Autorização
- Autenticação via JWT (JSON Web Token)
- Proteção de rotas sensíveis
- Controle de acesso baseado em autoria dos tópicos

### Validações de Negócio
- Validação de campos obrigatórios
- Prevenção de tópicos duplicados (mesmo título e mensagem)
- Validação de autoria para operações de modificação

### Documentação da API
- Documentação automática com Swagger/OpenAPI
- Interface interativa para testes da API

## Tecnologias Utilizadas

- **Java 11**
- **Spring Boot 2.7.18**
- **Spring Security** - Para autenticação e autorização
- **Spring Data JPA** - Para persistência de dados
- **H2 Database** - Banco de dados em memória para desenvolvimento
- **JWT (JSON Web Token)** - Para autenticação stateless
- **Bean Validation** - Para validação de dados
- **Swagger/OpenAPI** - Para documentação da API
- **JUnit 5** - Para testes unitários
- **Maven** - Para gerenciamento de dependências

## Estrutura do Projeto

```
src/
├── main/
│   ├── java/com/alura/forumhub/
│   │   ├── config/          # Configurações (Swagger, Tratamento de Erros)
│   │   ├── controller/      # Controllers REST
│   │   ├── dto/            # Data Transfer Objects
│   │   ├── model/          # Entidades JPA
│   │   ├── repository/     # Repositories JPA
│   │   ├── security/       # Configurações de Segurança
│   │   └── service/        # Serviços de Negócio
│   └── resources/
│       ├── application.properties
│       └── data.sql        # Dados iniciais
└── test/
    └── java/com/alura/forumhub/
        └── controller/     # Testes dos Controllers
```

## Modelo de Dados

### Entidades Principais

1. **Usuario**
   - id, nome, email, senha, ativo, dataCriacao

2. **Curso**
   - id, nome, categoria

3. **Topico**
   - id, titulo, mensagem, dataCriacao, status, autor, curso

4. **br.com.alura.forumhub.model.Resposta**
   - id, mensagem, dataCriacao, solucao, autor, topico

### Relacionamentos
- Usuario 1:N Topico (um usuário pode criar vários tópicos)
- Curso 1:N Topico (um curso pode ter vários tópicos)
- Usuario 1:N br.com.alura.forumhub.model.Resposta (um usuário pode criar várias respostas)
- Topico 1:N br.com.alura.forumhub.model.Resposta (um tópico pode ter várias respostas)

## Como Executar

### Pré-requisitos
- Java 11 ou superior
- Maven 3.6 ou superior

### Passos para Execução

1. **Clone o repositório**
   ```bash
   git clone <url-do-repositorio>
   cd forumhub
   ```

2. **Compile o projeto**
   ```bash
   ./mvnw clean compile
   ```

3. **Execute a aplicação**
   ```bash
   ./mvnw spring-boot:run
   ```

4. **Acesse a aplicação**
   - API: http://localhost:8080
   - Documentação Swagger: http://localhost:8080/swagger-ui.html
   - Console H2: http://localhost:8080/h2-console

### Configuração do Banco H2
- **URL**: jdbc:h2:mem:forumhub
- **Username**: sa
- **Password**: (vazio)

## Endpoints da API

### Autenticação
- `POST /login` - Realizar login e obter token JWT

### Tópicos
- `GET /topicos` - Listar todos os tópicos (paginado)
- `GET /topicos/{id}` - Buscar tópico por ID
- `POST /topicos` - Criar novo tópico (requer autenticação)
- `PUT /topicos/{id}` - Atualizar tópico (requer autenticação e autoria)
- `DELETE /topicos/{id}` - Excluir tópico (requer autenticação e autoria)

### Exemplos de Uso

#### 1. Realizar Login
```bash
curl -X POST http://localhost:8080/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "usuario@email.com",
    "senha": "123456"
  }'
```

#### 2. Criar Tópico
```bash
curl -X POST http://localhost:8080/topicos \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <seu-token-jwt>" \
  -d '{
    "titulo": "Dúvida sobre Spring Boot",
    "mensagem": "Como configurar o Spring Security?",
    "cursoId": 1
  }'
```

#### 3. Listar Tópicos
```bash
curl -X GET http://localhost:8080/topicos \
  -H "Authorization: Bearer <seu-token-jwt>"
```

## Testes

Para executar os testes:

```bash
./mvnw test
```

Os testes incluem:
- Testes de integração dos controllers
- Validação de endpoints
- Testes de autenticação e autorização

## Validações Implementadas

### Validações de Entrada
- **Título**: Obrigatório, entre 5 e 200 caracteres
- **Mensagem**: Obrigatória, mínimo 10 caracteres
- **Email**: Formato válido de email
- **Senha**: Obrigatória

### Regras de Negócio
- Não é possível criar tópicos com título e mensagem idênticos
- Apenas o autor pode modificar ou excluir seus próprios tópicos
- Todos os endpoints (exceto login) requerem autenticação

## Tratamento de Erros

A API implementa um tratamento global de exceções que retorna respostas padronizadas:

- **400 Bad Request**: Dados inválidos ou violação de regras de negócio
- **401 Unauthorized**: Token JWT inválido ou ausente
- **403 Forbidden**: Usuário sem permissão para a operação
- **404 Not Found**: Recurso não encontrado
- **500 Internal Server Error**: Erro interno do servidor

## Segurança

- Autenticação stateless com JWT
- Senhas criptografadas com BCrypt
- Proteção CSRF desabilitada (adequado para APIs REST)
- Headers de segurança configurados
- Validação de autorização em operações sensíveis

## Contribuição

Este projeto foi desenvolvido como parte do Challenge Back End da Alura, implementando as melhores práticas de desenvolvimento de APIs REST com Spring Boot.

## Licença

Este projeto está licenciado sob a MIT License.

