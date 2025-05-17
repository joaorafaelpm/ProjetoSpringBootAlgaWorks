package com.projeto.algaworks.algaworks_api.model;

import com.projeto.algaworks.algaworks_api.domain.model.Autuacao;
import com.projeto.algaworks.algaworks_api.domain.model.StatusVeiculo;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter

public class VeiculoRepresentationModel {

    private Long id ;
    private ProprietarioResumoModel proprietario ;
    private String marca ;
    private String modelo ;
    private String placa ;
    private StatusVeiculo status ;
    private OffsetDateTime dataCadastro ;
    private OffsetDateTime dataApreenssao ;
    private List<AutuacaoRepresentationModel> autuacoes;

    public void setVeiculoRepresentation (Long id ,ProprietarioResumoModel proprietario ,String marca ,String modelo ,String placa ,StatusVeiculo status ,OffsetDateTime dataCadastro ,OffsetDateTime dataApreenssao , List<AutuacaoRepresentationModel> autuacoes) {
        this.id = id;
        this.proprietario = proprietario;
        this.marca = marca;
        this.placa = placa;
        this.status = status;
        this.dataCadastro = dataCadastro;
        this.dataApreenssao = dataApreenssao;
        this.autuacoes = autuacoes ;

    }
}
