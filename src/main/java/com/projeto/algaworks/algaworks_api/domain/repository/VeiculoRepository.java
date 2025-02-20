package com.projeto.algaworks.algaworks_api.domain.repository;

import com.projeto.algaworks.algaworks_api.domain.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {


}
