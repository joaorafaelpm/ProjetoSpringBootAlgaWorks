package com.projeto.algaworks.algaworks_api.domain.service;

import com.projeto.algaworks.algaworks_api.domain.exception.RegraDeNegocioException;
import com.projeto.algaworks.algaworks_api.domain.model.Proprietario;
import com.projeto.algaworks.algaworks_api.domain.model.StatusVeiculo;
import com.projeto.algaworks.algaworks_api.domain.model.Veiculo;
import com.projeto.algaworks.algaworks_api.domain.repository.VeiculoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@AllArgsConstructor
public class RegistroVeiculoService {

    private VeiculoRepository veiculoRepository ;
    private final RegistroProprietarioService registroProprietarioService ;


    public Veiculo buscarVeiculo (Long veiculoId) {
        return veiculoRepository.findById(veiculoId)
                .orElseThrow(() -> new RegraDeNegocioException("Veiculo não encontrado!"));
    }



    @Transactional
    /**
     * Cadastra um novo veículo no sistema
     * @params novoVeiculo - precisa de um veículo para se cadastrar
     */
    public Veiculo cadastrar (Veiculo novoVeiculo) {
//       Faço verificações de usuário e placa
        if (novoVeiculo.getId() != null) {
            throw new RegraDeNegocioException("Veículo a ser cadastrado não deve possuir um código!");
        }
        boolean placaEmUso = veiculoRepository.findByPlaca(novoVeiculo.getPlaca())
                        .filter(veiculo -> !veiculo.equals(novoVeiculo))
                        .isPresent();
        if (placaEmUso) {
            throw new RegraDeNegocioException("Já existe veículo com essa placa cadastrado!");
        }

//        Busca entre meus usuários pelo id referente ao informado no cadastro do veículo
        Proprietario proprietario = registroProprietarioService.buscarProprietarioVeiculo(novoVeiculo);

//        Defini as informações da classe veículo
        novoVeiculo.setProprietario(proprietario);
        novoVeiculo.setStatus(StatusVeiculo.REGULAR);
        novoVeiculo.setDataCadastro(OffsetDateTime.now());
        return veiculoRepository.save(novoVeiculo);
    }



}
