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
    /**
     * Mostra todos os proprietários e retorna uma lista com os proprietários na classe de representação deles
     */
    public List<ProprietarioRepresentationModel> listarProprietarios () {
        return proprietarioModelAssembler.toCollectionModel(proprietarioRepository.findAll());
    }

    /**
     * Busca por um proprietário procurando pelo seu id
     * @param proprietarioId - id do proprietário que passamos como parte da uri
     * @return - retorna uma responseEntity, para passar o status da requisição junto do objeto na classe de representação do proprietário
     */
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
    /**
     * Adiciona um proprietário no banco
     * @param proprietario - como parâmetro para passarmos para o body temos a classe original de proprietário que será transformada em usa versão de representação da classe proprietário
     */
    public ProprietarioRepresentationModel adicionarProprietario (@Valid @RequestBody Proprietario proprietario){
        return proprietarioModelAssembler.toModel(registroProprietarioService.salvar(proprietario)) ;
    }


    @PutMapping("/{proprietarioId}")
    /**
     * Atualiza o proprietário dentro do repositório
     * @param proprietarioId - id do proprietário que vamos atualizar
     * @param proprietario - nova classe de proprietário que vamos substituir pela antiga
     * @return - retorna um responseEntity com o status de OK + a classe de representação de proprietário
     */
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
    /**
     * Exclui um proprietário do repositório
     * @param proprietarioId - como parâmetro passamos o id do usuário que vamos deletar
     * @retunr - retornamos um responseEntity para passar o status para o consumidor
     */
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
