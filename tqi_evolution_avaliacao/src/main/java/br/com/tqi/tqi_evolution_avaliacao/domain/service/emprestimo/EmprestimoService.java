package br.com.tqi.tqi_evolution_avaliacao.domain.service.emprestimo;

import br.com.tqi.tqi_evolution_avaliacao.api.dto.mapper.EmprestimoMapper;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.EmprestimoDTO;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.model.imput.EmprestimoDTOImput;
import br.com.tqi.tqi_evolution_avaliacao.api.dto.response.MessageResponse;
import br.com.tqi.tqi_evolution_avaliacao.domain.entity.Emprestimo;
import br.com.tqi.tqi_evolution_avaliacao.domain.exception.EmprestimoNaoEncontradoException;
import br.com.tqi.tqi_evolution_avaliacao.domain.exception.NegocioException;
import br.com.tqi.tqi_evolution_avaliacao.domain.repository.EmprestimoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class EmprestimoService {

    private EmprestimoMapper emprestimoMapper;
    private EmprestimoRepository emprestimoRepository;

    public List<Emprestimo> listarEmprestimo(){
        return emprestimoRepository.findAll();
    }



/*    @Transactional
    public MessageResponse create (EmprestimoDTOImput emprestimoDTOImput){
        Emprestimo novoEmprestimo = emprestimoMapper.toEntity(emprestimoDTOImput);
        Emprestimo emprestimoCadastrado = emprestimoRepository.save(novoEmprestimo);
        MessageResponse messageResponse = createMessage("Emprestimo cadastrado com sucesso ", emprestimoCadastrado.getCodigoEmprestimo(), " - " + emprestimoCadastrado.getCliente().getNome());
        return messageResponse;
    }*/



    public MessageResponse update (Integer id, EmprestimoDTO emprestimoDTO) throws EmprestimoNaoEncontradoException {
        emprestimoRepository.findById(id).orElseThrow(() -> new EmprestimoNaoEncontradoException(id));
        Emprestimo emprestimoAtualizado = emprestimoMapper.toEntity2(emprestimoDTO);
        Emprestimo emprestimoSalvo = emprestimoRepository.save(emprestimoAtualizado);

        MessageResponse messageResponse =
                createMessage("Emprestimo atualizado com sucesso ",
                emprestimoSalvo.getCodigoEmprestimo()," - " + emprestimoSalvo.getCliente().getNome());
        return messageResponse;

    }
    @Transactional
    public void delete (Integer id) throws EmprestimoNaoEncontradoException {
        emprestimoRepository.findById(id).orElseThrow(() -> new EmprestimoNaoEncontradoException(id));
        emprestimoRepository.deleteById(id);
    }

    private MessageResponse createMessage (String msm, Integer id, String name){
        return MessageResponse.builder().message(msm + id + name).build();

    }



}
