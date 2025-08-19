package br.com.alura.forumhub.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "respostas")
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Mensagem é obrigatória")
    @Size(min = 10, message = "Mensagem deve ter pelo menos 10 caracteres")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String mensagem;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(nullable = false)
    private Boolean solucao = false;

    @NotNull(message = "Tópico é obrigatório")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id", nullable = false)
    private Topico topico;

    @NotNull(message = "Autor é obrigatório")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id", nullable = false)
    private Usuario autor;

    // Construtores
    public Resposta() {
        this.dataCriacao = LocalDateTime.now();
    }

    public Resposta(String mensagem, Topico topico, Usuario autor) {
        this();
        this.mensagem = mensagem;
        this.topico = topico;
        this.autor = autor;
    }





    @Override
    public String toString() {
        return "br.com.alura.forumhub.model.Resposta{" +
                "id=" + id +
                ", dataCriacao=" + dataCriacao +
                ", solucao=" + solucao +
                ", topico=" + (topico != null ? topico.getTitulo() : null) +
                ", autor=" + (autor != null ? autor.getNome() : null) +
                '}';
    }
}

