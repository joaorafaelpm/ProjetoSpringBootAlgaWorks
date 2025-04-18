package com.projeto.algaworks.algaworks_api.domain.service;

import com.projeto.algaworks.algaworks_api.domain.model.Autuacao;
import com.projeto.algaworks.algaworks_api.domain.model.Veiculo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistroAutuacaoService {

    private final RegistroVeiculoService registroVeiculoService ;

    @Transactional
    public Autuacao registrarAutuacao (Long veiculoId , Autuacao novaAutuacao ) {
//        Valida se o veículo existe
        Veiculo veiculo = registroVeiculoService.buscarVeiculo(veiculoId);
        return veiculo.adicionarAutuacao(novaAutuacao);
    };


}
