package com.projeto.algaworks.algaworks_api.controller;

import com.projeto.algaworks.algaworks_api.domain.model.Veiculo;
import com.projeto.algaworks.algaworks_api.domain.repository.VeiculoRepository;
import com.projeto.algaworks.algaworks_api.domain.service.RegistroVeiculoService;
import lombok.AllArgsConstructor;
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

    @GetMapping
    public List<Veiculo> listarVeiculos () {
        return veiculoRepository.findAll();
    }

    @GetMapping("/{veiculoId}")
    public ResponseEntity<Veiculo> buscarVeiculo (@PathVariable Long veiculoId) {
        return veiculoRepository.findById(veiculoId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Veiculo adicionarVeiculo (@RequestBody Veiculo veiculo) {
        return registroVeiculoService.cadastrar(veiculo);
    }

}
