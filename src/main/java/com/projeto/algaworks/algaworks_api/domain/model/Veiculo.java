    package com.projeto.algaworks.algaworks_api.domain.model;

import com.projeto.algaworks.algaworks_api.domain.exception.RegraDeNegocioException;
import com.projeto.algaworks.algaworks_api.model.AutuacaoRepresentationModel;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Valid
    @NotNull
    @ManyToOne
    @JoinColumn(name="proprietario_id")
    private Proprietario proprietario;

    private String marca ;
    private String modelo ;
    private String placa ;

    @Enumerated(EnumType.STRING)
    private StatusVeiculo status ;
    private OffsetDateTime dataCadastro ;
    private OffsetDateTime dataApreenssao ;


//    mappedBy indica que é o elemento "veiculo" que faz essa relação, afinal um veículo pode ter várias autuações (ManyToOne) porém somente UM veículo pode ter essas autuações (OneToMany). O padrão é "veiculo"
//    cascade = "CascadeType.ALL" indica que toda mudança que ocorrer dentro dessa lista deve ser sincronizada com a mudança no banco de dados, uma vez que, quando modificada a lista de autuações adicionando uma nova, nós usamos uma função da classe de veículo, e não incluimos nada sobre a entidade de Autuação, ou seja, o Jakarta não sabe onde mudar. Quando adicionamos essa anotação ele entende que assim que houver mudança, ele deve sincronizar com a tabela ligada à Classe original de Autuação.
    @OneToMany(mappedBy = "veiculo" , cascade = CascadeType.ALL)
    private List<Autuacao> autuacoes = new ArrayList<Autuacao>();

    public Autuacao adicionarAutuacao (Autuacao autuacao) {
        autuacao.setDataOcorrencia(OffsetDateTime.now());
        autuacao.setVeiculo(this);
        getAutuacoes().add(autuacao);
        return autuacao;
    }

    public boolean estaApreendido () {
        return StatusVeiculo.APREENDIDO.equals(getStatus()) ;
    }
    public boolean naoEstaApreendido () {
        return !estaApreendido() ;
    }

    public void apreender () {
        if (estaApreendido()) {
            throw new RegraDeNegocioException("Veículo já apreendido!");
        }
        setStatus(StatusVeiculo.APREENDIDO);
        setDataApreenssao(OffsetDateTime.now());
    }

    public void removerApreensao () {
        if (naoEstaApreendido()) {
            throw new RegraDeNegocioException("Veículo não está apreendido!");
        }
        setStatus(StatusVeiculo.REGULAR);
        setDataApreenssao(null);
    }




}
