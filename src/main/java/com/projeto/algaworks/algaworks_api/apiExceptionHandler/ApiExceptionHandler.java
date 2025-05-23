package com.projeto.algaworks.algaworks_api.apiExceptionHandler;

import com.projeto.algaworks.algaworks_api.domain.exception.RegraDeNegocioException;
import com.projeto.algaworks.algaworks_api.domain.exception.VeiculoNaoEncontradoException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
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
@AllArgsConstructor
@RestControllerAdvice
//ResponseEntityExceptionHandler é um adicional que padroniza a maneira que nossos tratamentos dos erros da API vão acontecer, seja melhorando a menssagem deixando-a mais facil de ler e até mesmo escondendo algumas informações desnecessárias pro consumidor da API
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource ;

    //    Aqui eu sobrescrevo a minha exceção que foi lançada no caso de algum elemento da minha api for retornado como blank, o ResponseEntityExceptionHandler cuida da parte de transformar esse ,eu código de erro em um objeto JSON que é manipulável, e eu modifico o que é passado nos parâmetros
    @Override
    /**
     * Usa da biblioteca do ResponseEntityExceptionHandler para customizar a menssagem de erro de algum elemento inválido durante alguma requisição.
     * @params MethodArgumentNotValidException (ex) - Esse é a excessão padrão de quando algum elemento é passado errado, seja nulo ou com algum erro de padronização que fuja do esperado. É um objeto que recebe um parâmetro de procurar por elementos faltando/nulos
     * @params HttpStatusCode (status) - status que vai definir qual é o tipo de erro para lidarmos com ele
     */
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                        HttpHeaders headers, HttpStatusCode status, WebRequest request) {

//      Especifico qual é o erro passando os status para poder modificar os elementos da menssagem de erro
        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setTitle("Um ou mais itens inválidos");
        problemDetail.setType(URI.create("https://algatransito/erros/invalid-components"));


//        Aqui a gente mapeia todos os erros que nós recebemos e transformamos em um objeto para passa-lo junto com o nosso objeto problemDetail
        Map<String , String> fields = ex.getBindingResult().getAllErrors()
                .stream()
                .collect(Collectors.toMap(objectError -> ((FieldError) objectError).getField() ,
                        objectError -> messageSource.getMessage(objectError , LocaleContextHolder.getLocale()))) ;

        problemDetail.setProperty("fields" , fields);

//        Geralmente no handleExceptionInternal ele passa um objeto padrão, então aqui a gente passa o objeto que a gente modificou acima, adicionando um título indicando onde está o erro junto de uma uri em que se espera receber algum tipo de instrução de como resolver o problema.
        return this.handleExceptionInternal(ex, problemDetail , headers, status, request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    /**
     *  Faz o tratamento de conflitos de informações no servidor. Então o exceptionHandler é responsável somente por padronizar a exceção lancada pelo erro de integridade das informações!
     */
    public ProblemDetail handleDataIntegrityValidation (DataIntegrityViolationException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problemDetail.setTitle("Recurso está em uso");
        problemDetail.setType(URI.create("https://algatransito/erros/conflitos-em-recursos"));

        return problemDetail ;
    }

    @ExceptionHandler(RegraDeNegocioException.class)
    /**
     * Faz qualquer tratamento dentro das nossas regras de negócios que já cuida de qualquer validação e deixa o exceptionHandler responsável somente por simplificar e padronizar a excessão
     */
    public ProblemDetail handleNegocio (RegraDeNegocioException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setType(URI.create("https://algatransito/erros/regra-de-negocio"));
        problemDetail.setTitle(e.getMessage());

        return problemDetail;
    }

    @ExceptionHandler(VeiculoNaoEncontradoException.class)

    public ProblemDetail handleVeiculoNaoEncontrado (VeiculoNaoEncontradoException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setType(URI.create("https://algatransito/erros/regra-de-negocio"));
        problemDetail.setTitle(e.getMessage());

        return problemDetail;
    }




}
