package br.com.sicredi.votacao.domain.repository;

import br.com.sicredi.votacao.domain.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotoRepository extends JpaRepository<Voto, String> {
    List<Voto> findAllByIdPauta(Long idPauta);

    boolean existsByCpfAndIdPauta(String cpf, Long idPauta);
}
