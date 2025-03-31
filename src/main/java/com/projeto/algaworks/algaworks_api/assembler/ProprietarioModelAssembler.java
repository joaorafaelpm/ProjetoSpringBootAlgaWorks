package com.projeto.algaworks.algaworks_api.assembler;

import com.projeto.algaworks.algaworks_api.domain.model.Proprietario;
import com.projeto.algaworks.algaworks_api.domain.model.Veiculo;
import com.projeto.algaworks.algaworks_api.model.ProprietarioRepresentationModel;
import com.projeto.algaworks.algaworks_api.model.VeiculoRepresentationModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class ProprietarioModelAssembler {

    private final ModelMapper modelMapper ;

    public ProprietarioRepresentationModel toModel (Proprietario proprietario) {
        return modelMapper.map(proprietario , ProprietarioRepresentationModel.class);
    };

    public List<ProprietarioRepresentationModel> toCollectionModel (List<Proprietario> list_proprietario) {
        return list_proprietario.stream()
                .map(this::toModel)
                .toList();
    }

}
