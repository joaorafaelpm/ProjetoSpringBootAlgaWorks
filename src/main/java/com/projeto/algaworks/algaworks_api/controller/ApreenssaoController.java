package com.projeto.algaworks.algaworks_api.controller;

import com.projeto.algaworks.algaworks_api.assembler.VeiculoModelAssembler;
import com.projeto.algaworks.algaworks_api.domain.model.Veiculo;
import com.projeto.algaworks.algaworks_api.domain.service.ApreenssaoVeiculosService;
import com.projeto.algaworks.algaworks_api.domain.service.RegistroVeiculoService;
import com.projeto.algaworks.algaworks_api.model.VeiculoRepresentationModel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/veiculos/{veiculoId}")
public class ApreenssaoController {

    private final RegistroVeiculoService registroVeiculoService ;
    private final ApreenssaoVeiculosService apreenssaoVeiculosService;
    private final VeiculoModelAssembler veiculoModelAssembler ;

    @PutMapping("/apreensao")
    public void apreender (@PathVariable Long veiculoId) {
        apreenssaoVeiculosService.apreender(veiculoId);
    }

    @DeleteMapping("/apreensao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerApreenssao (@PathVariable Long veiculoId) {
        apreenssaoVeiculosService.liberar(veiculoId);
    }

}
