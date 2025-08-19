package br.com.alura.forumhub.service;


import br.com.alura.forumhub.model.Topico;
import br.com.alura.forumhub.model.Usuario;
import br.com.alura.forumhub.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    public void validarDuplicidade(String titulo, String mensagem) {
        if (topicoRepository.existsByTituloAndMensagem(titulo, mensagem)) {
            throw new IllegalArgumentException("Já existe um tópico com o mesmo título e mensagem");
        }
    }

    public void validarAutoria(Topico topico, Usuario usuario) {
        if (!topico.getAutor().getId().equals(usuario.getId())) {
            throw new IllegalArgumentException("Usuário não tem permissão para modificar este tópico");
        }
    }

    public void validarTituloUnico(String titulo, Long topicoId) {
        // Verificar se existe outro tópico com o mesmo título (exceto o próprio tópico sendo atualizado)
        boolean existeOutroTopico = topicoRepository.findAll().stream()
                .anyMatch(t -> t.getTitulo().equalsIgnoreCase(titulo) && !t.getId().equals(topicoId));
        
        if (existeOutroTopico) {
            throw new IllegalArgumentException("Já existe outro tópico com este título");
        }
    }
}

