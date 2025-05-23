package com.projeto.algaworks.algaworks_api.model;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class AutuacaoRepresentationModel {

    private Long id ;
    private String descricao;
    private BigDecimal multa ;
    private OffsetDateTime dataOcorrencia ;

}
