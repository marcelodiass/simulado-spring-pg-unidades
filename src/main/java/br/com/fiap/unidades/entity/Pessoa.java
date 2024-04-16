package br.com.fiap.unidades.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "TB_PESSOA", uniqueConstraints = {
        @UniqueConstraint(name = "UK_EMAIL", columnNames = "EMAIL_PESSOA")
})
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_PESSOA")
    @SequenceGenerator(
            name = "SQ_PESSOA",
            sequenceName = "SQ_PESSOA",
            initialValue = 1,
            allocationSize = 1
    )
    @Column(name = "ID_PESSOA")
    private Long id;


    private String nome;

    private String sobrenome;

    private String email;

    private LocalDate nascimento;

    @Enumerated(EnumType.STRING)
    @Column(name = "TP_PESSOA", nullable = false)
    private Tipo tipo;

}
