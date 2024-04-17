package br.com.fiap.unidades.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Table(name = "TB_CHEFE", uniqueConstraints = {
        @UniqueConstraint(name = "UK_USUARIO", columnNames = "ID_USUARIO"),
        @UniqueConstraint(name = "UK_UNIDADE_FIM", columnNames = {"UNIDADE", "DT_FIM_CHEFE"})
})
public class Chefe {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CHEFE")
    @SequenceGenerator(
            name = "SQ_CHEFE",
            sequenceName = "SQ_CHEFE",
            initialValue = 1,
            allocationSize = 1
    )
    @Column(name = "ID_CHEFE")
    private Long id;

    @Column(name = "SUBS_CHEFE")
    private Boolean substituto;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "USUARIO",
            referencedColumnName = "ID_USUARIO",
            foreignKey = @ForeignKey(name = "FK_CHEFE_USUARIO")
    )
    private Usuario usuario;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "UNIDADE",
            referencedColumnName = "ID_UNIDADE",
            foreignKey = @ForeignKey(name = "FK_CHEFE_UNIDADE")
    )
    private Unidade unidade;

    @Column(name = "DT_INICIO_CHEFE")
    private LocalDateTime inicio;

    @Column(name = "DT_FIM_CHEFE")
    private LocalDateTime fim;
}

