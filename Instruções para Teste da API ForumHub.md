# Instruções para Teste da API ForumHub

## Pré-requisitos

1. **Aplicação em execução**
   ```bash
   ./mvnw spring-boot:run
   ```

2. **Ferramenta para testes** (escolha uma):
   - Postman
   - Insomnia
   - cURL (linha de comando)
   - Swagger UI (http://localhost:8080/swagger-ui.html)

## Dados Iniciais

A aplicação já vem com dados pré-carregados:

### Usuários
- **Email**: admin@forumhub.com | **Senha**: admin123
- **Email**: usuario@forumhub.com | **Senha**: user123

### Cursos
- **ID 1**: Java Básico (Programação)
- **ID 2**: Spring Framework (Programação)
- **ID 3**: React Fundamentals (Frontend)

## Fluxo de Teste Completo

### 1. Autenticação

**Endpoint**: `POST /login`

**Payload**:
```json
{
  "email": "admin@forumhub.com",
  "senha": "admin123"
}
```

**br.com.alura.forumhub.model.Resposta esperada**:
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

**⚠️ Importante**: Copie o token retornado para usar nos próximos requests.

### 2. Criar Tópico

**Endpoint**: `POST /topicos`

**Headers**:
```
Authorization: Bearer <seu-token-aqui>
Content-Type: application/json
```

**Payload**:
```json
{
  "titulo": "Como configurar Spring Security?",
  "mensagem": "Estou com dificuldades para configurar a autenticação JWT no Spring Security. Alguém pode me ajudar com um exemplo prático?",
  "cursoId": 2
}
```

**br.com.alura.forumhub.model.Resposta esperada**: Status 201 Created com os dados do tópico criado.

### 3. Listar Tópicos

**Endpoint**: `GET /topicos`

**Headers**:
```
Authorization: Bearer <seu-token-aqui>
```

**Parâmetros opcionais**:
- `page=0` (página, padrão 0)
- `size=10` (tamanho da página, padrão 10)
- `sort=dataCriacao` (campo para ordenação)

**br.com.alura.forumhub.model.Resposta esperada**: Lista paginada de tópicos.

### 4. Buscar Tópico por ID

**Endpoint**: `GET /topicos/{id}`

**Headers**:
```
Authorization: Bearer <seu-token-aqui>
```

**Exemplo**: `GET /topicos/1`

**br.com.alura.forumhub.model.Resposta esperada**: Dados detalhados do tópico.

### 5. Atualizar Tópico

**Endpoint**: `PUT /topicos/{id}`

**Headers**:
```
Authorization: Bearer <seu-token-aqui>
Content-Type: application/json
```

**Payload** (todos os campos são opcionais):
```json
{
  "titulo": "Como configurar Spring Security? [RESOLVIDO]",
  "mensagem": "Estou com dificuldades para configurar a autenticação JWT no Spring Security. Alguém pode me ajudar com um exemplo prático?\n\nEDIT: Problema resolvido!",
  "status": "FECHADO"
}
```

**⚠️ Importante**: Só é possível atualizar tópicos criados pelo usuário autenticado.

### 6. Excluir Tópico

**Endpoint**: `DELETE /topicos/{id}`

**Headers**:
```
Authorization: Bearer <seu-token-aqui>
```

**br.com.alura.forumhub.model.Resposta esperada**: Status 204 No Content.

**⚠️ Importante**: Só é possível excluir tópicos criados pelo usuário autenticado.

## Cenários de Teste

### Teste de Validações

#### 1. Tópico com dados inválidos
```json
{
  "titulo": "abc",
  "mensagem": "muito curta",
  "cursoId": 999
}
```
**Resultado esperado**: Status 400 com detalhes dos erros de validação.

#### 2. Tópico duplicado
Tente criar dois tópicos com exatamente o mesmo título e mensagem.
**Resultado esperado**: Status 400 com mensagem de erro sobre duplicação.

#### 3. Acesso sem autenticação
Faça qualquer request sem o header `Authorization`.
**Resultado esperado**: Status 401 Unauthorized.

#### 4. Tentar modificar tópico de outro usuário
1. Faça login com `usuario@forumhub.com`
2. Tente atualizar um tópico criado pelo admin
**Resultado esperado**: Status 403 Forbidden.

### Teste de Paginação

**Endpoint**: `GET /topicos?page=0&size=2&sort=dataCriacao,desc`

Teste diferentes valores de paginação para verificar o funcionamento.

### Teste de Busca por ID Inexistente

**Endpoint**: `GET /topicos/999`

**Resultado esperado**: Status 404 Not Found.

## Usando o Swagger UI

1. Acesse: http://localhost:8080/swagger-ui.html
2. Clique em "Authorize" no topo da página
3. Digite: `Bearer <seu-token-jwt>`
4. Teste os endpoints diretamente pela interface

## Verificação do Banco de Dados

1. Acesse: http://localhost:8080/h2-console
2. Use as configurações:
   - **JDBC URL**: jdbc:h2:mem:forumhub
   - **Username**: sa
   - **Password**: (deixe vazio)
3. Execute queries para verificar os dados:
   ```sql
   SELECT * FROM USUARIOS;
   SELECT * FROM CURSOS;
   SELECT * FROM TOPICOS;
   ```

## Códigos de Status HTTP

- **200 OK**: Operação bem-sucedida (GET, PUT)
- **201 Created**: Recurso criado com sucesso (POST)
- **204 No Content**: Recurso excluído com sucesso (DELETE)
- **400 Bad Request**: Dados inválidos ou regra de negócio violada
- **401 Unauthorized**: Token JWT ausente ou inválido
- **403 Forbidden**: Usuário sem permissão para a operação
- **404 Not Found**: Recurso não encontrado
- **500 Internal Server Error**: Erro interno do servidor

## Dicas para Teste

1. **Sempre inclua o token JWT** nos headers após fazer login
2. **Teste cenários de erro** além dos casos de sucesso
3. **Verifique os códigos de status HTTP** retornados
4. **Use diferentes usuários** para testar as permissões
5. **Teste a paginação** com diferentes parâmetros
6. **Verifique as validações** enviando dados inválidos

## Troubleshooting

### Token JWT Expirado
Se receber erro 401, faça login novamente para obter um novo token.

### Erro 403 ao Modificar Tópico
Verifique se você está tentando modificar um tópico criado pelo usuário autenticado.

### Erro de Conexão
Verifique se a aplicação está rodando na porta 8080.

