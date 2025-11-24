package br.edu.ifsp.kanban.controller.dto.factoryDto;

import br.edu.ifsp.kanban.controller.dto.response.BlocoResponseDto;
import br.edu.ifsp.kanban.model.canonical.BlocoCanonical;

public class BlocoDtoFactory {
    public static BlocoResponseDto canonicoParaDto(BlocoCanonical canonical) {
        BlocoResponseDto dto = new BlocoResponseDto();
        dto.setIdBloco(canonical.getIdBloco());
        dto.setNome(canonical.getNome());
        dto.setEstado(canonical.getEstado());
        dto.setTarefas(
                canonical.getTarefas().stream()
                        .map(TarefaDtoFactory::canonicoParaDto)
                        .toList()
        );
        return dto;
    }
}
