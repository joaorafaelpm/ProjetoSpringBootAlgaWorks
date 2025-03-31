package com.projeto.algaworks.algaworks_api.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProprietarioRepresentationModel {

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