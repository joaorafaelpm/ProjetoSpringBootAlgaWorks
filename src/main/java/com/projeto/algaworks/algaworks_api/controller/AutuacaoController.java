package com.projeto.algaworks.algaworks_api.controller;

import com.projeto.algaworks.algaworks_api.assembler.AutuacaoModelAssembler;
import com.projeto.algaworks.algaworks_api.domain.model.Autuacao;
import com.projeto.algaworks.algaworks_api.domain.service.RegistroAutuacaoService;
import com.projeto.algaworks.algaworks_api.model.AutuacaoRepresentationModel;
import com.projeto.algaworks.algaworks_api.model.CadastrarAutuacao;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("veiculos/{veiculoId}/autuacoes")
public class AutuacaoController {

    private final RegistroAutuacaoService registroAutuacaoService ;
    private final AutuacaoModelAssembler autuacaoModelAssembler ;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AutuacaoRepresentationModel cadastrarAutuacoes (@PathVariable Long veiculoId ,
                                           @Valid @RequestBody CadastrarAutuacao cadastrarAutuacao) {
        Autuacao autuacaoCadastrada = autuacaoModelAssembler.toEntity(cadastrarAutuacao);
        Autuacao autuacaoRegistrada = registroAutuacaoService.registrarAutuacao(veiculoId , autuacaoCadastrada);
        return autuacaoModelAssembler.toModel(autuacaoRegistrada);
    }

}
