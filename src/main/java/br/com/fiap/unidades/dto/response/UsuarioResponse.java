package br.com.fiap.unidades.dto.response;

public record UsuarioResponse(
        PessoaResponse pessoa,
        String username,
        Long id
) {
}
