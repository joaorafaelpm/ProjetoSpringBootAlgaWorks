    package com.projeto.algaworks.algaworks_api.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.projeto.algaworks.algaworks_api.domain.validation.ValidationGroups;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
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
    @ConvertGroup(from = Default.class ,to = ValidationGroups.ProprietarioId.class )
    @NotNull
    @ManyToOne
    private Proprietario proprietario;

    @NotBlank
    private String marca ;

    @NotBlank
    private String modelo ;

    @NotBlank
    @Pattern(regexp = "[A-Z]{3}[0-9][A-Z0-9]{2}")
    private String placa ;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Enumerated(EnumType.STRING)
    private StatusVeiculo status ;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dataCadastro ;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dataApreenssao ;


}
