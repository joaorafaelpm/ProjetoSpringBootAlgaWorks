package com.projeto.algaworks.algaworks_api.assembler;

import com.projeto.algaworks.algaworks_api.domain.model.Proprietario;
import com.projeto.algaworks.algaworks_api.model.ProprietarioRepresentationModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class ProprietarioModelAssembler {

    private final ModelMapper modelMapper ;

    /**
     * função para tranformar a classe original de proprietário na classe de representação da classe priprietário
     * @param proprietario - proprietário original que será transformado em um objeto da classe de representação da classe proprietário
     * @return - retorna essa classe de representação da classe proprietário
     */
    public ProprietarioRepresentationModel toModel (Proprietario proprietario) {
        return modelMapper.map(proprietario , ProprietarioRepresentationModel.class);
    };

    /**
     * função para transformar cada elemento em uma lista de objetos da classe original de proprietários em objetos da calsse de representação da classe de proprietário
     * @param list_proprietario - como parâmetro eu recebo a lista de objetos da classe proprietário
     * @return - retorno a lista com os objetos modificados
     */
    public List<ProprietarioRepresentationModel> toCollectionModel (List<Proprietario> list_proprietario) {
        return list_proprietario.stream()
                .map(this::toModel)
                .toList();
    }

}
