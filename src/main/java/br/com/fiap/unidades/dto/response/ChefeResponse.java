package br.com.fiap.unidades.dto.response;

import java.time.LocalDateTime;

public record ChefeResponse(
        Long id,
        UnidadeResponse unidade,
        LocalDateTime inicio,
        LocalDateTime fim,
        UsuarioResponse usuario,
        Boolean substituto
) {
}
