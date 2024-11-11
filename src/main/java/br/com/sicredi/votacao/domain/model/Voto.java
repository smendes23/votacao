package br.com.sicredi.votacao.domain.model;

import br.com.sicredi.votacao.domain.enums.Decisao;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.EnumType.STRING;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "VOTO")
public class Voto {

    @Id
    @Column(name = "CPF")
    private String cpf;

    @Column(name = "ID_PAUTA")
    private Long idPauta;

    @Column(name = "VOTO")
    @Enumerated(STRING)
    private Decisao voto;
}