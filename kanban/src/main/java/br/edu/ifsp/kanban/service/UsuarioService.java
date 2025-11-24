package br.edu.ifsp.kanban.service;

import br.edu.ifsp.kanban.controller.dto.factoryDto.UsuarioDtoFactory;
import br.edu.ifsp.kanban.controller.dto.response.UsuarioResponseDto;
import br.edu.ifsp.kanban.model.canonical.UsuarioCanonical;
import br.edu.ifsp.kanban.model.canonical.factoryCanonical.UsuarioCanonicalFactory;
import br.edu.ifsp.kanban.model.entity.Usuario;
import br.edu.ifsp.kanban.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioResponseDto buscarPorId(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        UsuarioCanonical canonical = UsuarioCanonicalFactory.entityToCanonico(usuario);

        return UsuarioDtoFactory.canonicoParaDto(canonical);
    }
}

