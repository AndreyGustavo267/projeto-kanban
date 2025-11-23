package br.edu.ifsp.kanban.service;

import br.edu.ifsp.kanban.model.canonical.TarefaCanonical;
import br.edu.ifsp.kanban.model.canonical.BlocoCanonical;
import br.edu.ifsp.kanban.model.canonical.factoryCanonical.TarefaCanonicalFactory;
import br.edu.ifsp.kanban.model.entity.Tarefa;
import br.edu.ifsp.kanban.repository.TarefaRepository;
import br.edu.ifsp.kanban.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TarefaService {
    @Autowired
    private TarefaRepository repository;
    @Autowired
    private BlocoService blocoService;

    public Tarefa buscaPorId(Integer idTarefa) {
        try {
            Optional<Tarefa> tarefa = repository.findById(idTarefa);
            return tarefa.orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar tarefa.", e);
        }
    }

    public Tarefa save(Tarefa tarefa) {
        try {
            return repository.save(tarefa);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar tarefa.", e);
        }
    }

    public void delete(Integer idTarefa) {
        try {
            repository.deleteById(idTarefa);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar tarefa.", e);
        }
    }

    public TarefaCanonical buscaTarefaPorId(Integer idTarefa) {
        TarefaCanonical tarefa = TarefaCanonicalFactory.entityToCanonical(buscaPorId(idTarefa));
        Validator.erroSeNulo(tarefa, "Tarefa não encontrada.");
        return tarefa;
    }

    public void atualizarTarefa(TarefaCanonical tarefaAtualizada) {
        Validator.erroSeNulo(tarefaAtualizada, "Nenhum campo foi atualizado.");

        TarefaCanonical tarefa = buscaTarefaPorId(tarefaAtualizada.getIdTarefa());

        Validator.atribuirSeNaoNulo(tarefaAtualizada.getTexto(), tarefa::setTexto);
        Validator.atribuirSeNaoNulo(tarefaAtualizada.isEstado(), tarefa::setEstado);
        if (tarefaAtualizada.getIdBloco() != null) {
            BlocoCanonical bloco = blocoService.buscaBlocoPorId(tarefaAtualizada.getIdBloco());
            if (bloco != null) {
                Validator.atribuirSeNaoNulo(bloco.getIdBloco(), tarefa::setIdBloco);
            }
        }
        save(TarefaCanonicalFactory.canonicalToEntity(tarefa));
    }

    public void criarTarefa(TarefaCanonical novaTarefa) {
        Validator.erroSeNulo(novaTarefa, "Dados insuficientes para criar uma tarefa.");
        Validator.erroSeNulo(novaTarefa.getTexto(), "Texto é um campo obrigatório.");
        Validator.erroSeNulo(novaTarefa.isEstado(), "Estado é um campo obrigatório.");
        Validator.erroSeNulo(novaTarefa.getIdBloco(), "Id bloco é um campo obrigatório.");

        blocoService.buscaBlocoPorId(novaTarefa.getIdBloco());

        save(TarefaCanonicalFactory.canonicalToEntity(novaTarefa));
    }

    public void deletarTarefa(Integer idTarefa) {
        TarefaCanonical tarefa = buscaTarefaPorId(idTarefa);
        if (tarefa != null) {
            delete(idTarefa);
        }
    }
}
