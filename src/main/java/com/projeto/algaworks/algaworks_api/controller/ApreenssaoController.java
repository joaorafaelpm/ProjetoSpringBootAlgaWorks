package com.projeto.algaworks.algaworks_api.controller;

import com.projeto.algaworks.algaworks_api.domain.model.Veiculo;
import com.projeto.algaworks.algaworks_api.domain.service.ApreenssaoVeiculosService;
import com.projeto.algaworks.algaworks_api.domain.service.RegistroVeiculoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/veiculos/{veiculoId}")
public class ApreenssaoController {

    private final RegistroVeiculoService registroVeiculoService ;
    private final ApreenssaoVeiculosService apreenssaoVeiculosService;

    @PutMapping("/apreenssao")
    public ResponseEntity<Veiculo> apreender (@PathVariable Long veiculoId) {
        apreenssaoVeiculosService.apreender(veiculoId);
        Veiculo veiculo = registroVeiculoService.buscarVeiculo(veiculoId);
        return ResponseEntity.ok(veiculo) ;
    }

    @PutMapping("/remover_apreenssao")
    public ResponseEntity<Veiculo> removerApreenssao (@PathVariable Long veiculoId) {
        apreenssaoVeiculosService.liberar(veiculoId);
        Veiculo veiculo = registroVeiculoService.buscarVeiculo(veiculoId);
        return ResponseEntity.ok(veiculo) ;
    }

}
