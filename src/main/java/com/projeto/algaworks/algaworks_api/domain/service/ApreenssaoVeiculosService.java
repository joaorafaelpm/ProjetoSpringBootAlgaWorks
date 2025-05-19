package com.projeto.algaworks.algaworks_api.domain.service;

import com.projeto.algaworks.algaworks_api.domain.model.Veiculo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ApreenssaoVeiculosService {
//        Crio um serviço só para a apreenssão para evitar de colocar muitas funções na minha classe de veículos

    private final RegistroVeiculoService registroVeiculoService ;

    @Transactional
    public void apreender (Long veiculoId) {
        Veiculo veiculo = registroVeiculoService.buscarVeiculo(veiculoId);
        veiculo.apreender();
    }

    @Transactional
    public void liberar (Long veiculoId) {
        Veiculo veiculo = registroVeiculoService.buscarVeiculo(veiculoId);
        veiculo.removerApreensao();
    }
}
