package br.com.alura.forumhub.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DadosCadastroTopico {

    @NotBlank(message = "Título é obrigatório")
    @Size(min = 5, max = 200, message = "Título deve ter entre 5 e 200 caracteres")
    private String titulo;

    @NotBlank(message = "Mensagem é obrigatória")
    @Size(min = 10, message = "Mensagem deve ter pelo menos 10 caracteres")
    private String mensagem;

    @NotNull(message = "ID do curso é obrigatório")
    private Long cursoId;

    public DadosCadastroTopico() {
    }

    public DadosCadastroTopico(String titulo, String mensagem, Long cursoId) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.cursoId = cursoId;
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

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }
}

