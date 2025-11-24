package br.edu.ifsp.kanban.controller.dto.factoryDto;

import br.edu.ifsp.kanban.controller.dto.response.PaginaResponseDto;
import br.edu.ifsp.kanban.model.canonical.PaginaCanonical;

public class PaginaDtoFactory {
    public static PaginaResponseDto canonicoParaDto(PaginaCanonical canonical) {
        PaginaResponseDto dto = new PaginaResponseDto();
        dto.setIdPagina(canonical.getIdPagina());
        dto.setNome(canonical.getNome());

        dto.setBlocos(
                canonical.getBlocos().stream()
                        .map(BlocoDtoFactory::canonicoParaDto)
                        .toList()
        );

        return dto;
    }
}
