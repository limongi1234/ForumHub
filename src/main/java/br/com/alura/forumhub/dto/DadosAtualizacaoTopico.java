package br.com.alura.forumhub.dto;

import com.alura.forumhub.model.StatusTopico;

import javax.validation.constraints.Size;

public class DadosAtualizacaoTopico {

    @Size(min = 5, max = 200, message = "Título deve ter entre 5 e 200 caracteres")
    private String titulo;

    @Size(min = 10, message = "Mensagem deve ter pelo menos 10 caracteres")
    private String mensagem;

    private StatusTopico status;

    public DadosAtualizacaoTopico() {
    }

    public DadosAtualizacaoTopico(String titulo, String mensagem, StatusTopico status) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public StatusTopico getStatus() {
        return status;
    }

    public void setStatus(StatusTopico status) {
        this.status = status;
    }
}

