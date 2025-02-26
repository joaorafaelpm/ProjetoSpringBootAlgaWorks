package com.projeto.algaworks.algaworks_api.domain.repository;

import com.projeto.algaworks.algaworks_api.domain.exception.RegraDeNegocioException;
import com.projeto.algaworks.algaworks_api.domain.model.Proprietario;
import com.projeto.algaworks.algaworks_api.domain.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    Optional<Veiculo> findByPlaca (String placa);

}
