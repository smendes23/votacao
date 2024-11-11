package br.com.sicredi.votacao.domain.repository;

import br.com.sicredi.votacao.domain.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {
    boolean existsByTitulo(String titulo);
}