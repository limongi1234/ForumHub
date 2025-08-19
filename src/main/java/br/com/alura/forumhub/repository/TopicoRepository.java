package br.com.alura.forumhub.repository;

import com.alura.forumhub.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {

    @Query("SELECT t FROM Topico t ORDER BY t.dataCriacao DESC")
    Page<Topico> findAllOrderByDataCriacaoDesc(Pageable pageable);

    boolean existsByTituloAndMensagem(String titulo, String mensagem);
}

