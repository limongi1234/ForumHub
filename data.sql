-- Inserindo cursos
INSERT INTO cursos (nome, categoria) VALUES ('Java Básico', 'Programação');
INSERT INTO cursos (nome, categoria) VALUES ('Spring Boot', 'Programação');
INSERT INTO cursos (nome, categoria) VALUES ('JavaScript', 'Programação');
INSERT INTO cursos (nome, categoria) VALUES ('HTML e CSS', 'Front-end');
INSERT INTO cursos (nome, categoria) VALUES ('React', 'Front-end');

-- Inserindo usuários (senha: 123456)
INSERT INTO usuarios (nome, email, senha, data_criacao, ativo) VALUES 
('João Silva', 'joao@email.com', '$2a$10$HKGl7.z7zfHlWS4IuB69Q.Ilsqz9p8.qQq8QzQzQzQzQzQzQzQzQz', NOW(), true);

INSERT INTO usuarios (nome, email, senha, data_criacao, ativo) VALUES 
('Maria Santos', 'maria@email.com', '$2a$10$HKGl7.z7zfHlWS4IuB69Q.Ilsqz9p8.qQq8QzQzQzQzQzQzQzQzQz', NOW(), true);

INSERT INTO usuarios (nome, email, senha, data_criacao, ativo) VALUES 
('Pedro Oliveira', 'pedro@email.com', '$2a$10$HKGl7.z7zfHlWS4IuB69Q.Ilsqz9p8.qQq8QzQzQzQzQzQzQzQzQz', NOW(), true);

-- Inserindo tópicos
INSERT INTO topicos (titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES 
('Dúvida sobre variáveis em Java', 'Olá pessoal, estou com dúvida sobre como declarar variáveis em Java. Alguém pode me ajudar?', NOW(), 'NAO_RESPONDIDO', 1, 1);

INSERT INTO topicos (titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES 
('Como configurar Spring Boot?', 'Preciso de ajuda para configurar um projeto Spring Boot do zero. Quais são os primeiros passos?', NOW(), 'NAO_RESPONDIDO', 2, 2);

INSERT INTO topicos (titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES 
('Problema com CSS Grid', 'Estou tentando criar um layout com CSS Grid mas não está funcionando como esperado.', NOW(), 'NAO_SOLUCIONADO', 3, 4);

-- Inserindo respostas
INSERT INTO respostas (mensagem, data_criacao, solucao, topico_id, autor_id) VALUES 
('Em Java, você pode declarar variáveis usando o tipo seguido do nome: int idade = 25;', NOW(), false, 1, 2);

INSERT INTO respostas (mensagem, data_criacao, solucao, topico_id, autor_id) VALUES 
('Para configurar Spring Boot, você pode usar o Spring Initializr em start.spring.io', NOW(), true, 2, 1);

