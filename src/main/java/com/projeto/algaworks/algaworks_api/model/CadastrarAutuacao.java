package com.projeto.algaworks.algaworks_api.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CadastrarAutuacao {

    @NotNull
    private String descricao ;

    @NotNull
    @Positive
    private BigDecimal multa ;


}
