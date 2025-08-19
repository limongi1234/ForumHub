## Fase 1: Análise de requisitos e planejamento da migração
- [x] Entender as implicações da migração para Java 17.
- [x] Pesquisar sobre a integração do Lombok com Spring Boot e JPA.
- [x] Pesquisar sobre a configuração do PostgreSQL com Spring Boot.
- [x] Pesquisar sobre ferramentas de migration (Flyway/Liquibase) e escolher uma (Flyway).
- [x] Planejar as etapas de refatoração das entidades para usar Lombok.
- [x] Planejar as etapas de configuração do banco de dados e migrations.

## Fase 2: Atualização do ambiente e dependências do projeto
- [ ] Atualizar a versão do Java para 17 no `pom.xml`.
- [ ] Adicionar a dependência do Lombok no `pom.xml`.
- [ ] Adicionar a dependência do driver PostgreSQL no `pom.xml`.
- [ ] Remover a dependência do H2 Database no `pom.xml`.
- [ ] Adicionar a dependência da ferramenta de migration (Flyway/Liquibase) no `pom.xml`.
- [ ] Compilar o projeto para verificar a compatibilidade inicial com Java 17.

## Fase 3: Refatoração das entidades com Lombok
- [ ] Refatorar a entidade `Usuario` para usar anotações Lombok (`@Getter`, `@Setter`, `@NoArgsConstructor`, `@AllArgsConstructor`, `@EqualsAndHashCode`, `@ToString`).
- [ ] Refatorar a entidade `Curso` para usar anotações Lombok.
- [ ] Refatorar a entidade `Topico` para usar anotações Lombok.
- [ ] Refatorar a entidade `br.com.alura.forumhub.model.Resposta` para usar anotações Lombok.
- [ ] Remover getters e setters manuais das entidades.
- [ ] Compilar o projeto para verificar a refatoração com Lombok.

## Fase 4: Configuração do PostgreSQL e Flyway/Liquibase
- [ ] Configurar as propriedades de conexão com o PostgreSQL no `application.properties`.
- [ ] Configurar a ferramenta de migration (Flyway/Liquibase) no `application.properties`.
- [ ] Criar scripts de migration iniciais para as tabelas existentes (`cursos`, `usuarios`, `topicos`, `respostas`).
- [ ] Executar as migrations para criar o schema no PostgreSQL.
- [ ] Adaptar o `data.sql` para ser compatível com PostgreSQL ou criar um script de migration para dados iniciais.

## Fase 5: Execução e validação da aplicação e testes
- [ ] Iniciar a aplicação com o PostgreSQL configurado.
- [ ] Testar os endpoints da API (CRUD de tópicos, autenticação) para garantir o funcionamento.
- [ ] Executar os testes unitários e de integração existentes.
- [ ] Verificar a compatibilidade com ambiente Windows (considerando caminhos de arquivo, etc.).

## Fase 6: Atualização da documentação do projeto
- [ ] Atualizar o `README.md` com as novas tecnologias (Java 17, Lombok, PostgreSQL, Flyway/Liquibase).
- [ ] Atualizar as instruções de execução para refletir a nova configuração do banco de dados.
- [ ] Atualizar os exemplos de uso, se necessário.
- [ ] Remover o `INSTRUCOES_TESTE.md` e integrar as informações no `README.md` ou em um novo arquivo de documentação mais abrangente.

