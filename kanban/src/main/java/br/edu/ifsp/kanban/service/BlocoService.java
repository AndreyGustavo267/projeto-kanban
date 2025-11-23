package br.edu.ifsp.kanban.service;

import br.edu.ifsp.kanban.model.canonical.BlocoCanonical;
import br.edu.ifsp.kanban.model.entity.Bloco;
import br.edu.ifsp.kanban.model.canonical.factoryCanonical.BlocoCanonicalFactory;
import br.edu.ifsp.kanban.repository.BlocoRepository;
import br.edu.ifsp.kanban.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BlocoService {
    @Autowired
    private BlocoRepository repository;
    public Bloco buscaPorId(Integer idBloco) {
        try {
            Optional<Bloco> tarefa = repository.findById(idBloco);
            return tarefa.orElse(null);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar bloco.", e);
        }
    }

    public BlocoCanonical buscaBlocoPorId(Integer idBloco) {
        Validator.erroSeNulo(idBloco, "Um id deve ser informado");
        BlocoCanonical bloco = BlocoCanonicalFactory.entityToCanonical(buscaPorId(idBloco));
        Validator.erroSeNulo(bloco, "Bloco não encontrada.");
        return bloco;
    }
}
