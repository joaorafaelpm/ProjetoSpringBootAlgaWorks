    package com.projeto.algaworks.algaworks_api.domain.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="veiculos")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id ;

    @Valid
    @NotNull
    @ManyToOne
    private Proprietario proprietario;

    private String marca ;
    private String modelo ;
    private String placa ;

    @Enumerated(EnumType.STRING)
    private StatusVeiculo status ;
    private OffsetDateTime dataCadastro ;
    private OffsetDateTime dataApreenssao ;

}
