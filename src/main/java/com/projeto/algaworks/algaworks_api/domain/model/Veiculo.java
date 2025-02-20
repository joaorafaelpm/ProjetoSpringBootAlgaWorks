package com.projeto.algaworks.algaworks_api.domain.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="veiculos")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id ;

    @ManyToOne
//    @JoinColumn(name="proprietario_id")
//    Não precisa por que o jakarta já entende que o que liga o proprietário nessa tabela é a chave estrangeira
    private Proprietario proprietario;

    private String marca ;
    private String modelo ;
    private String placa ;

    @Enumerated(EnumType.STRING)
    private StatusVeiculo status ;

//    @Column(name="data_cadastro")
//    Não precisa por que o jakarta entende que dataCadastro e data_cadastro é a mesma coisa!
    private LocalDateTime dataCadastro ;
    private LocalDateTime dataApreenssao ;


}
