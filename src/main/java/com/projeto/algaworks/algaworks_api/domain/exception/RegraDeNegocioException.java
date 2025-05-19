package com.projeto.algaworks.algaworks_api.domain.exception;

import com.projeto.algaworks.algaworks_api.domain.model.Veiculo;

public class RegraDeNegocioException extends RuntimeException{

    public RegraDeNegocioException (String message) {
        super(message);
    }


}
