package br.edu.ifsp.kanban.controller.resource;

import br.edu.ifsp.kanban.controller.dto.response.UsuarioResponseDto;
import br.edu.ifsp.kanban.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/{id}")
    public UsuarioResponseDto getById(@PathVariable Integer id) {
        return usuarioService.buscarPorId(id);
    }
}
