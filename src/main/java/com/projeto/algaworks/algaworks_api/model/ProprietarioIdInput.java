package com.projeto.algaworks.algaworks_api.model;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProprietarioIdInput {

    @NotNull
    private Long id ;

}
