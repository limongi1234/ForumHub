package br.com.alura.forumhub.dto;

import br.com.alura.forumhub.model.StatusTopico;
import br.com.alura.forumhub.model.Topico;
import java.time.LocalDateTime;

public class DadosListagemTopico {

    private Long id;
    private String titulo;
    private LocalDateTime dataCriacao;
    private StatusTopico status;
    private String nomeAutor;
    private String nomeCurso;

    public DadosListagemTopico() {
    }

    public DadosListagemTopico(Topico topico) {
        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.dataCriacao = topico.getDataCriacao();
        this.status = topico.getStatus();
        this.nomeAutor = topico.getAutor().getNome();
        this.nomeCurso = topico.getCurso().getNome();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public StatusTopico getStatus() {
        return status;
    }

    public void setStatus(StatusTopico status) {
        this.status = status;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }
}

