package com.projeto.algaworks.algaworks_api.controller;

import com.projeto.algaworks.algaworks_api.assembler.ProprietarioModelAssembler;
import com.projeto.algaworks.algaworks_api.domain.model.Proprietario;
import com.projeto.algaworks.algaworks_api.domain.repository.ProprietarioRepository;
import com.projeto.algaworks.algaworks_api.domain.service.RegistroProprietarioService;
import com.projeto.algaworks.algaworks_api.model.ProprietarioRepresentationModel;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/proprietarios")
public class ProprietarioController {

    private final RegistroProprietarioService registroProprietarioService ;
    private final ProprietarioRepository proprietarioRepository ;
    private final ProprietarioModelAssembler proprietarioModelAssembler ;

    @GetMapping
    public List<ProprietarioRepresentationModel> listarProprietarios () {
        return proprietarioModelAssembler.toCollectionModel(proprietarioRepository.findAll());
    }

    @GetMapping("/{proprietarioId}")
    public ResponseEntity<ProprietarioRepresentationModel> buscar (@PathVariable Long proprietarioId) {
        return proprietarioRepository.findById(proprietarioId)
                .map(proprietarioModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }



//    Uma alternativa melhor do que criar um ResponseEntity neste caso que eu só quero modificar o código http e nada mais, eu passo o status como um novo atributo criado!
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ProprietarioRepresentationModel adicionarProprietario (@Valid @RequestBody Proprietario proprietario) {
        return proprietarioModelAssembler.toModel(registroProprietarioService.salvar(proprietario)) ;
    }


    @PutMapping("/{proprietarioId}")
    public ResponseEntity<ProprietarioRepresentationModel> modificarProprietario (@PathVariable Long proprietarioId , @Valid @RequestBody Proprietario proprietario) {

//        Verifica a existência do objeto:
        if (!proprietarioRepository.existsById(proprietarioId)) {
            return ResponseEntity.notFound().build() ;
        }

//      Para evitar que o Spring tente atualizar um proprietario com o id nulo, já que não passamos ele na hora de inserir dados, a gente adiciona manualmente neste momento!
        proprietario.setId(proprietarioId);
        ProprietarioRepresentationModel proprietarioAtualizado = proprietarioModelAssembler.toModel(registroProprietarioService.salvar(proprietario));

//        Retorno o código 200 junto do objeto em json
        return ResponseEntity.ok(proprietarioAtualizado);
    }

    @DeleteMapping("/{proprietarioId}")
    public ResponseEntity<Void> excluirProprietario (@PathVariable Long proprietarioId) {
//        Verifico se o objeto existe:
        if (!proprietarioRepository.existsById(proprietarioId)) {
            return ResponseEntity.notFound().build() ;
        }
        registroProprietarioService.deletarProprietario(proprietarioId);

//        Retorno o código 204 (deu certo, mas nada foi encontrado/retornado)
        return ResponseEntity.noContent().build();
    }


}
