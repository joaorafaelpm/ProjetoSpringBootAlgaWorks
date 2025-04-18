package com.projeto.algaworks.algaworks_api.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CadastrarVeiculo {

    @NotBlank
    @Size(max=20)
    private String marca ;

    @NotBlank
    @Size(max=20)
    private String modelo ;

    @NotBlank
    @Pattern(regexp = "[A-Z]{3}[0-9][A-Z0-9][0-9]{2}")
    private String placa ;

    @Valid
    @NotNull
    private ProprietarioIdInput proprietario ;


}
