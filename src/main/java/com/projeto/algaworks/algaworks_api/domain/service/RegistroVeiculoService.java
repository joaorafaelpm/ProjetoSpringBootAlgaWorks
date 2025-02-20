package com.projeto.algaworks.algaworks_api.domain.service;

import com.projeto.algaworks.algaworks_api.domain.model.StatusVeiculo;
import com.projeto.algaworks.algaworks_api.domain.model.Veiculo;
import com.projeto.algaworks.algaworks_api.domain.repository.VeiculoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistroVeiculoService {

    private VeiculoRepository veiculoRepository ;

    @Transactional
    public Veiculo cadastrar (Veiculo novoVeiculo) {
        novoVeiculo.setStatus(StatusVeiculo.REGULAR);
        novoVeiculo.setDataCadastro(LocalDateTime.now());
        return veiculoRepository.save(novoVeiculo);
    }

}
