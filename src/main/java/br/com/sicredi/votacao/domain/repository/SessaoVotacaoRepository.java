package br.com.sicredi.votacao.domain.repository;

import br.com.sicredi.votacao.domain.model.SessaoVotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessaoVotacaoRepository extends JpaRepository<SessaoVotacao, Long> {
    Optional<SessaoVotacao> findByIdPauta(Long idPauta);
}
