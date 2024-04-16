package br.com.fiap.unidades.dto.request;

import java.time.LocalDateTime;

public record ChefeRequest(
        AbstractRequest unidade,
        LocalDateTime inicio,
        LocalDateTime fim,
        AbstractRequest usuario,
        Boolean substituto
) {
}
