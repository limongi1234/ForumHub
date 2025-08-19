CREATE TABLE respostas (
    id BIGSERIAL NOT NULL,
    mensagem TEXT NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    autor VARCHAR(100) NOT NULL,
    solucao BOOLEAN NOT NULL DEFAULT false,
    topico_id BIGINT NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (topico_id) REFERENCES topicos(id)
);