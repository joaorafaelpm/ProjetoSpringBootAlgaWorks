package com.projeto.algaworks.algaworks_api.assembler;

import com.projeto.algaworks.algaworks_api.domain.model.Veiculo;
import com.projeto.algaworks.algaworks_api.model.CadastrarVeiculo;
import com.projeto.algaworks.algaworks_api.model.VeiculoRepresentationModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class VeiculoModelAssembler {

    private final ModelMapper modelMapper;

    public Veiculo toEntity(CadastrarVeiculo cadastrarVeiculo) {
        return modelMapper.map(cadastrarVeiculo , Veiculo.class) ;
    };

    public VeiculoRepresentationModel toModel (Veiculo veiculo) {
        return modelMapper.map(veiculo , VeiculoRepresentationModel.class);
    };

    public List<VeiculoRepresentationModel> toCollectionModel (List<Veiculo> list_veiculo) {
        return list_veiculo.stream()
                .map(this::toModel)
                .toList();
    }

}
