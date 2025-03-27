package com.projeto.algaworks.algaworks_api.domain.service;

import com.projeto.algaworks.algaworks_api.domain.exception.RegraDeNegocioException;
import com.projeto.algaworks.algaworks_api.domain.model.Proprietario;
import com.projeto.algaworks.algaworks_api.domain.model.StatusVeiculo;
import com.projeto.algaworks.algaworks_api.domain.model.Veiculo;
import com.projeto.algaworks.algaworks_api.domain.repository.ProprietarioRepository;
import com.projeto.algaworks.algaworks_api.domain.repository.VeiculoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class RegistroVeiculoService {

    private VeiculoRepository veiculoRepository ;
    private final RegistroProprietarioService registroProprietarioService ;


    @Transactional
    public Veiculo cadastrar (Veiculo novoVeiculo) {
        if (novoVeiculo.getId() != null) {
            throw new RegraDeNegocioException("Veículo a ser cadastrado não deve possuir um código!");
        }

        boolean placaEmUso = veiculoRepository.findByPlaca(novoVeiculo.getPlaca())
                        .filter(veiculo -> !veiculo.equals(novoVeiculo))
                        .isPresent();

        if (placaEmUso) {
            throw new RegraDeNegocioException("Já existe veículo com essa placa cadastrado!");
        }

        Proprietario proprietario = registroProprietarioService.buscarProprietarioVeiculo(novoVeiculo);

        novoVeiculo.setProprietario(proprietario);
        novoVeiculo.setStatus(StatusVeiculo.REGULAR);
        novoVeiculo.setDataCadastro(OffsetDateTime.now());
        return veiculoRepository.save(novoVeiculo);
    }



}
