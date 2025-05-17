package com.projeto.algaworks.algaworks_api.domain.service;

import com.projeto.algaworks.algaworks_api.domain.model.Autuacao;
import com.projeto.algaworks.algaworks_api.domain.model.Veiculo;
import com.projeto.algaworks.algaworks_api.domain.repository.VeiculoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RegistroAutuacaoService {

    private final VeiculoRepository veiculoRepository;
    private final RegistroVeiculoService registroVeiculoService ;

    @Transactional
    public Autuacao registrarAutuacao (Long veiculoId , Autuacao novaAutuacao ) {
//        Valida se o veículo existe
        Veiculo veiculo = registroVeiculoService.buscarVeiculo(veiculoId);
        return veiculo.adicionarAutuacao(novaAutuacao);
    };

    @Transactional
    public List<Autuacao> pegarAutuacoes (Long veiculoId) {
        return veiculoRepository.getById(veiculoId).getAutuacoes() ;
    }


}
