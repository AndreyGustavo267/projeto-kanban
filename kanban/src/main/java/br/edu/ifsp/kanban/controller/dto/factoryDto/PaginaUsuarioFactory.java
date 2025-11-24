package br.edu.ifsp.kanban.controller.dto.factoryDto;

import br.edu.ifsp.kanban.controller.dto.response.PaginaUsuarioResponseDto;
import br.edu.ifsp.kanban.model.canonical.PaginaUsuarioCanonical;

public class PaginaUsuarioFactory {
    public static PaginaUsuarioResponseDto canonicoParaDto(PaginaUsuarioCanonical canonical) {
        PaginaUsuarioResponseDto dto = new PaginaUsuarioResponseDto();
        dto.setPapel(canonical.getPapel());

        dto.setPagina(PaginaDtoFactory.canonicoParaDto(canonical.getPagina()));

        return dto;
    }
}
