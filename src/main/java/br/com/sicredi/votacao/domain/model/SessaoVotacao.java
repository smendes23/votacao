package br.com.sicredi.votacao.domain.model;

import br.com.sicredi.votacao.domain.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import static jakarta.persistence.EnumType.STRING;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "SESSAO_VOTACAO")
public class SessaoVotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sessao_seq_gen")
    @SequenceGenerator(name = "sessao_seq_gen", sequenceName = "sessao_seq", allocationSize = 1)
    @Column(name = "ID_SESSAO")
    private Long id;

    @Column(name = "ID_PAUTA")
    private Long idPauta;

    @Column(name = "STATUS")
    @Enumerated(STRING)
    private Status status;

    @Column(name = "DTH_INICIO")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime inicio;

    @Column(name = "DTH_FIM")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime fim;
}
