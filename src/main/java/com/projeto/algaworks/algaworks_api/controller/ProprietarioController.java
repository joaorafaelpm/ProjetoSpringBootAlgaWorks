package com.projeto.algaworks.algaworks_api.controller;

import com.projeto.algaworks.algaworks_api.domain.exception.RegraDeNegocioException;
import com.projeto.algaworks.algaworks_api.domain.model.Proprietario;
import com.projeto.algaworks.algaworks_api.domain.repository.ProprietarioRepository;
import com.projeto.algaworks.algaworks_api.domain.service.RegistroProprietarioService;
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
    public Proprietario adicionarProprietario (@Valid @RequestBody Proprietario proprietario) {
        return registroProprietarioService.salvar(proprietario) ;
    }


    @PutMapping("/{proprietarioId}")
    public ResponseEntity<Proprietario> modificarProprietario (@PathVariable Long proprietarioId , @Valid @RequestBody Proprietario proprietario) {

//        Verifica a existência do objeto:
        if (!proprietarioRepository.existsById(proprietarioId)) {
            return ResponseEntity.notFound().build() ;
        }

//      Para evitar que o Spring tente atualizar um proprietario com o id nulo, já que não passamos ele na hora de inserir dados, a gente adiciona manualmente neste momento!
        proprietario.setId(proprietarioId);
        Proprietario proprietarioAtualizado = registroProprietarioService.salvar(proprietario);

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
