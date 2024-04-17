package br.com.fiap.unidades.dto.response;

import br.com.fiap.unidades.entity.Tipo;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record PessoaResponse(
        Long id,
        String nome,
        String sobrenome,
        String email,
        Tipo tipo,
        LocalDate nascimento
) {
}
