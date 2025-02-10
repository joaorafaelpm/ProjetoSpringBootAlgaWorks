package com.projeto.algaworks.algaworks_api.domain.repository;

import com.projeto.algaworks.algaworks_api.domain.model.Proprietario;
import jakarta.persistence.TypedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ProprietarioRepository extends JpaRepository<Proprietario, Long> {
}
