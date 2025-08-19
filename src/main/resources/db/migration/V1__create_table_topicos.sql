CREATE TABLE topicos (
    id BIGSERIAL NOT NULL,
    titulo VARCHAR(255) NOT NULL,
    mensagem TEXT NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50) NOT NULL DEFAULT 'NAO_RESPONDIDO',
    autor VARCHAR(100) NOT NULL,
    curso VARCHAR(100) NOT NULL,

    PRIMARY KEY (id)
);

-- Índice único para evitar tópicos duplicados (mesmo título e mensagem)
CREATE UNIQUE INDEX uk_topicos_titulo_mensagem ON topicos (titulo, mensagem);