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

import java.time.LocalDateTime;

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
//    Como é um objeto eu passo o NotNull ao invés de NotBlank
    @ManyToOne
//    @JoinColumn(name="proprietario_id")
//    Não precisa por que o jakarta já entende que o que liga o proprietário nessa tabela é a chave estrangeira
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

//    @Column(name="data_cadastro")
//    Não precisa por que o jakarta entende que dataCadastro e data_cadastro é a mesma coisa!
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime dataCadastro ;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime dataApreenssao ;


}
