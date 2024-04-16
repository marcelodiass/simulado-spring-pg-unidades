package br.com.fiap.unidades.dto.response;

import br.com.fiap.unidades.entity.Tipo;

import java.time.LocalDate;

public record PessoaResponse(
        Long id,
        String email,
        Tipo tipo,
        LocalDate nascimento,
        String nome,
        String sobrenome
) {
}
