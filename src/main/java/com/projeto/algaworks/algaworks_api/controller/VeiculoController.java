package com.projeto.algaworks.algaworks_api.controller;

import com.projeto.algaworks.algaworks_api.assembler.VeiculoModelAssembler;
import com.projeto.algaworks.algaworks_api.domain.model.Veiculo;
import com.projeto.algaworks.algaworks_api.domain.repository.VeiculoRepository;
import com.projeto.algaworks.algaworks_api.domain.service.RegistroVeiculoService;
import com.projeto.algaworks.algaworks_api.model.CadastrarVeiculo;
import com.projeto.algaworks.algaworks_api.model.VeiculoRepresentationModel;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoRepository veiculoRepository ;
    private final RegistroVeiculoService registroVeiculoService;
    private final ModelMapper modelMapper ;
    private final VeiculoModelAssembler veiculoAssembler ;


    @GetMapping
    /**
     * Mostra todos os veículos e retorna uma lista com os veículos na classe de representação deles
     */
    public List<VeiculoRepresentationModel> listarVeiculos () {
        return veiculoAssembler.toCollectionModel(veiculoRepository.findAll());
    }

    @GetMapping("/{veiculoId}")
    /**
     * Busca por um veículo procurando pelo seu id
     * @param veiculoId - id do veículo que passamos como parte da uri
     * @return - retorna uma responseEntity, para passar o status da requisição junto do objeto na classe de representação do veículo
     */
    public ResponseEntity<VeiculoRepresentationModel> buscarVeiculo (@PathVariable Long veiculoId) {
        return veiculoRepository.findById(veiculoId)
                .map(veiculoAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    /**
     * Adiciona um veículo no banco
     * @param cadastrarVeiculo - como parâmetro para passarmos para o body temos a classe que representa o modelo para cadastrar um novo veículo
     */
    public VeiculoRepresentationModel adicionarVeiculo (@Valid @RequestBody CadastrarVeiculo cadastrarVeiculo) {
        Veiculo novoVeiculo = veiculoAssembler.toEntity(cadastrarVeiculo) ;
        return veiculoAssembler.toModel(registroVeiculoService.cadastrar(novoVeiculo)) ;
    }


}
