package com.projeto.algaworks.algaworks_api.domain.model;

import com.projeto.algaworks.algaworks_api.domain.validation.ValidationGroups;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="proprietarios")

public class Proprietario {



    @NotBlank(groups = ValidationGroups.ProprietarioId.class)
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @NotBlank
    @Size(max = 60)
    private String name;

    @NotBlank
    @Size(max = 255)
    @Email
    private String email ;


    @NotBlank
    @Size(max = 255)
    @Column(name="telefone")
    private String phone ;

    public void setProprietario (Long id , String name , String email , String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}
