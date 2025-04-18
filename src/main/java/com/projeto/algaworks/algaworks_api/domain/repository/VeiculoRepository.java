package com.projeto.algaworks.algaworks_api.domain.repository;

import com.projeto.algaworks.algaworks_api.domain.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
/**
 * Repositório de veículos, o Jpa ajuda a gente a conectar diretamente com o banco, ele reconhece que existe uma tabela com esse nome e defini que todos os elementos dela precisam receber uma classe referente a ela junto do tipo de identificador, neste caso a classe veículo e o identificador do tipo Long
 */
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    Optional<Veiculo> findByPlaca (String placa);

}
