package br.com.sicredi.votacao.domain.model;

import br.com.sicredi.votacao.domain.enums.Status;
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

import static jakarta.persistence.EnumType.STRING;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "PAUTA")
public class Pauta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pauta_seq_gen")
    @SequenceGenerator(name = "pauta_seq_gen", sequenceName = "pauta_seq", allocationSize = 1)
    @Column(name = "ID_PAUTA")
    private Long id;

    @Column(name = "TITULO")
    private String titulo;

    @Column(name = "STATUS")
    @Enumerated(STRING)
    private Status status;
}
