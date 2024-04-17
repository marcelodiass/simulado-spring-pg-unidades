package br.com.fiap.unidades.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ChefeRequest(

        @NotNull(message = "Por favor insira a unidade.")
        @Valid
        AbstractRequest unidade,

        @NotNull(message = "Por favor insira a data de início.")
        LocalDateTime inicio,

        @NotNull(message = "Por favor insira a data de fim.")
        LocalDateTime fim,

        @NotNull(message = "Por favor insira o usuario.")
        AbstractRequest usuario,

        @NotNull(message = "Por favor insira o substituto.")
        Boolean substituto
) {
}
