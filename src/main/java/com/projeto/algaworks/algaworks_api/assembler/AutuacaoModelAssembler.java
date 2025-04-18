package com.projeto.algaworks.algaworks_api.assembler;

import com.projeto.algaworks.algaworks_api.domain.model.Autuacao;
import com.projeto.algaworks.algaworks_api.model.AutuacaoRepresentationModel;
import com.projeto.algaworks.algaworks_api.model.CadastrarAutuacao;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class AutuacaoModelAssembler {

    private final ModelMapper modelMapper ;


    /**
     * Converge o objeto da classe de input de autuações para sua classe comum
     * @param cadastrarAutuacao - Objeto da classe de input de Autuação
     * @return
     */
    public Autuacao toEntity (CadastrarAutuacao cadastrarAutuacao) {
        return modelMapper.map(cadastrarAutuacao , Autuacao.class);
    }

    /**
     * Converge o objeto da classe Autuação para sua versão de representação de autuação
     * @param autuacao - Objeto da classe Autuação
     * @return
     */
    public AutuacaoRepresentationModel toModel (Autuacao autuacao) {
        return modelMapper.map(autuacao , AutuacaoRepresentationModel.class);
    }

    /**
     * Converge uma lista de objetos da classe Autuação para sua versão de representação de autuação
     * @param List<Autuacao> (autuacoes) - Lista de objetos da classe Autuação
     * @return
     */
    public List<AutuacaoRepresentationModel> toCollection (List<Autuacao> autuacoes) {
        return autuacoes.stream()
                .map(this::toModel)
                .toList();
    }

}
