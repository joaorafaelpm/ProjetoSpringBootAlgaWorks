package com.projeto.algaworks.algaworks_api.domain.service;

import com.projeto.algaworks.algaworks_api.assembler.ProprietarioModelAssembler;
import com.projeto.algaworks.algaworks_api.domain.exception.RegraDeNegocioException;
import com.projeto.algaworks.algaworks_api.domain.model.Proprietario;
import com.projeto.algaworks.algaworks_api.domain.model.Veiculo;
import com.projeto.algaworks.algaworks_api.domain.repository.ProprietarioRepository;
import com.projeto.algaworks.algaworks_api.model.ProprietarioRepresentationModel;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

//crio uma classe de serviço para implementar todas as funções, junto das regras de negócio da aplicação, caso haja alguma validação necessária que não pode ser resolvida no banco (receber algum token para continuar a aplicação), é aqui que vamos implementa-la!
@Service
@AllArgsConstructor
public class RegistroProprietarioService {

    private final ProprietarioRepository proprietarioRepository  ;
    private final ProprietarioModelAssembler proprietarioModelAssembler;

    public Proprietario buscarProprietarioVeiculo (Veiculo veiculo) {
        return proprietarioRepository.findById(veiculo.getProprietario().getId())
                .orElseThrow(() -> new RegraDeNegocioException("Proprietario não encontrado"));
    }

//   (@Transactional) Isso indica que caso haja um erro na aplicação ela vai parar todas as execuções do banco de dados, o chamado row back
    @Transactional
    public Proprietario salvar (Proprietario proprietario) {
//        Traduzindo: a variável emailEmUso será true quando, o email existir (.isPresent()) e se o email for diferente do proprietario que está sendo salvo (por que se o email for igual simplesmente não lança o erro, já que ele já existe).
        boolean emailEmUso = proprietarioRepository.findByEmail(proprietario.getEmail())
                .filter(currentPoprietario -> !currentPoprietario.equals(proprietario))
                .isPresent();

        if (emailEmUso) {
            throw new RegraDeNegocioException("Email está em uso");
        }

        return proprietarioRepository.save(proprietario);
    }

    @Transactional
    public void deletarProprietario (Long proprietarioId) {
        proprietarioRepository.deleteById(proprietarioId);
    }

}
