package br.edu.ifsp.kanban.controller.resource;

import br.edu.ifsp.kanban.controller.dto.request.LoginRequestDto;
import br.edu.ifsp.kanban.controller.dto.response.TokenResponseDto;
import br.edu.ifsp.kanban.controller.dto.response.UsuarioResponseDto;
import br.edu.ifsp.kanban.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public TokenResponseDto login(@RequestBody LoginRequestDto dto) {
        return authService.login(dto);
    }

    @GetMapping("/me")
    public UsuarioResponseDto getMe(@AuthenticationPrincipal String email) {
        return authService.getMe(email);
    }
}