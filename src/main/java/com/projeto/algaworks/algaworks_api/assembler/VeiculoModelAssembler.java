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

    /**
     * Transforma a minha entidade de cadastro da classe veículo em uma entidade da classe veículo
     * @param cadastrarVeiculo - é a entidade de cadastro do veículo
     * @return - retorna o veículo já na sua classe original
     * */
    public Veiculo toEntity(CadastrarVeiculo cadastrarVeiculo) {
        return modelMapper.map(cadastrarVeiculo , Veiculo.class) ;
    };

    /**
     * Transforma a minha entidade de veículo em uma entidade de representação da classe veículo
     * @param veiculo - é a entidade original da classe veículo que vamos transformar no modelo de representação
     * @return - retorna um objeto da classe de representação do veículo
     */
    public VeiculoRepresentationModel toModel (Veiculo veiculo) {
        return modelMapper.map(veiculo , VeiculoRepresentationModel.class);
    };

    /**
     * Transforma cada entidade dentro da lista de objetos do tipo veículos em objetos do tipo representação da classe veículo
     * @param list_veiculo - é a lista que vai ser modificada para a representação da classe veículo
     * @return - retorna a lista com os objetos de dentro modificados
     */
    public List<VeiculoRepresentationModel> toCollectionModel (List<Veiculo> list_veiculo) {
        return list_veiculo.stream()
                .map(this::toModel)
                .toList();
    }

}
