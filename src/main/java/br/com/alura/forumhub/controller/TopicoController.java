package br.com.alura.forumhub.controller;

import com.alura.forumhub.dto.*;
import com.alura.forumhub.model.Curso;
import com.alura.forumhub.model.Topico;
import com.alura.forumhub.model.Usuario;
import com.alura.forumhub.repository.CursoRepository;
import com.alura.forumhub.repository.TopicoRepository;
import com.alura.forumhub.service.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoTopico> cadastrar(
            @RequestBody @Valid DadosCadastroTopico dados,
            UriComponentsBuilder uriBuilder,
            Authentication authentication) {

        // Verificar se já existe tópico com mesmo título e mensagem
        topicoService.validarDuplicidade(dados.getTitulo(), dados.getMensagem());

        // Buscar o curso
        Curso curso = cursoRepository.findById(dados.getCursoId())
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado com ID: " + dados.getCursoId()));

        // Obter o usuário autenticado
        Usuario autor = (Usuario) authentication.getPrincipal();

        // Criar o tópico
        Topico topico = new Topico(dados.getTitulo(), dados.getMensagem(), autor, curso);
        topicoRepository.save(topico);

        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listar(
            @PageableDefault(size = 10, sort = {"dataCriacao"}) Pageable paginacao) {

        var page = topicoRepository.findAllOrderByDataCriacaoDesc(paginacao)
                .map(DadosListagemTopico::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoTopico> detalhar(@PathVariable Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico não encontrado com ID: " + id));

        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoTopico> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid DadosAtualizacaoTopico dados,
            Authentication authentication) {

        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico não encontrado com ID: " + id));

        // Verificar se o usuário é o autor do tópico
        Usuario usuarioAutenticado = (Usuario) authentication.getPrincipal();
        topicoService.validarAutoria(topico, usuarioAutenticado);

        // Atualizar os campos se fornecidos
        if (dados.getTitulo() != null) {
            topico.setTitulo(dados.getTitulo());
        }
        if (dados.getMensagem() != null) {
            topico.setMensagem(dados.getMensagem());
        }
        if (dados.getStatus() != null) {
            topico.setStatus(dados.getStatus());
        }

        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id, Authentication authentication) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico não encontrado com ID: " + id));

        // Verificar se o usuário é o autor do tópico
        Usuario usuarioAutenticado = (Usuario) authentication.getPrincipal();
        topicoService.validarAutoria(topico, usuarioAutenticado);

        topicoRepository.delete(topico);
        return ResponseEntity.noContent().build();
    }
}

