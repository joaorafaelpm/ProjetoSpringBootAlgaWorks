package com.projeto.algaworks.algaworks_api.controller;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.projeto.algaworks.algaworks_api.domain.model.Proprietario;
import com.projeto.algaworks.algaworks_api.domain.repository.ProprietarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/proprietarios")
public class ProprietarioController {

    private final ProprietarioRepository proprietarioRepository ;

    @GetMapping
    public List<Proprietario> listarProprietarios () {
        return proprietarioRepository.findAll();
    }

    @GetMapping("/{proprietarioId}")
    public ResponseEntity<Proprietario> buscar (@PathVariable Long proprietarioId) {
        return proprietarioRepository.findById(proprietarioId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

//    Uma alternativa melhor do que criar um ResponseEntity neste caso que eu só quero modificar o código http e nada mais, eu passo o status como um novo atributo criado!
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Proprietario adicionarProprietario (@RequestBody Proprietario proprietario) {

        return proprietarioRepository.save(proprietario);
    }


    @PutMapping
    public Proprietario modificarProprietario (@RequestBody Proprietario proprietario) {
        return proprietarioRepository.save(proprietario);
    }


}
