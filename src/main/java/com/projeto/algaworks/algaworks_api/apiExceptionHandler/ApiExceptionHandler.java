package com.projeto.algaworks.algaworks_api.apiExceptionHandler;

import com.projeto.algaworks.algaworks_api.domain.exception.RegraDeNegocioException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.Map;
import java.util.stream.Collectors;

//Essa anotação faz o código ser Global, neste caso, o tratamento da exceção
@RestControllerAdvice

//ResponseEntityExceptionHandler é um adicional que padroniza a maneira que nossos tratamentos dos erros da API vão acontecer, seja melhorando a menssagem deixando-a mais facil de ler e até mesmo escondendo algumas informações desnecessárias pro consumidor da API

public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

//    Aqui eu sobrescrevo a minha exceção que foi lançada no caso de algum elemento da minha api for retornado como blank, o ResponseEntityExceptionHandler cuida da parte de transformar esse ,eu código de erro em um objeto JSON que é manipulável, e eu modifico o que é passado nos parâmetros
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                        HttpHeaders headers, HttpStatusCode status, WebRequest request) {

//      Especifico qual é o erro passando os status para poder modificar os elementos da menssagem de erro
        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setTitle("Um ou mais itens inválidos");
        problemDetail.setType(URI.create("https://algatransito/erros/invalid-components"));


//        Aqui a gente mapeia todos os erros que nós recebemos e transformamos em um objeto para passa-lo junto com o nosso objeto problemDetail
        Map<String , String> fields = ex.getBindingResult().getAllErrors()
                .stream()
                .collect(Collectors.toMap(objectError -> ((FieldError) objectError).getField() , objectError -> objectError.getDefaultMessage())) ;

        problemDetail.setProperty("fields" , fields);

//        Geralmente no handleExceptionInternal ele passa um objeto padrão, então aqui a gente passa o objeto que a gente modificou acima, adicionando um título indicando onde está o erro junto de uma uri em que se espera receber algum tipo de instrução de como resolver o problema.
        return this.handleExceptionInternal(ex, problemDetail , headers, status, request);
    }


    @ExceptionHandler(RegraDeNegocioException.class)
    public ResponseEntity<String> capturar (RegraDeNegocioException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
