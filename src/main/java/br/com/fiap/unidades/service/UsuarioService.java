package br.com.fiap.unidades.service;

import br.com.fiap.unidades.dto.request.UsuarioRequest;
import br.com.fiap.unidades.dto.response.UsuarioResponse;
import br.com.fiap.unidades.entity.Usuario;
import br.com.fiap.unidades.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UsuarioService implements ServiceDTO<Usuario, UsuarioRequest, UsuarioResponse, AbstractDTO> {

    @Autowired
    UsuarioRepository repo;

    @Autowired
    PessoaService pessoaService;

    @Override
    public Usuario toEntity(UsuarioRequest r) {

        var pessoa = pessoaService.findDatabaseObject( r.pessoa() );

        return Usuario.builder()
                .username(r.username())
                .password(r.password())
                .pessoa(pessoa)
                .build();
    }

    @Override
    public UsuarioResponse toResponse(Usuario e) {
        if (Objects.isNull(e)) return null;

        var pessoa = pessoaService.toResponse(e.getPessoa());

        return new UsuarioResponse(
                e.getId(),
                e.getUsername(),
                pessoa
        );
    }

    @Override
    public Usuario findDatabaseObject(AbstractDTO abstractDTO) {
        return repo.findById(abstractDTO.id()).orElse(null);
    }

    @Override
    public List<Usuario> findAll() {
        return repo.findAll();
    }

    @Override
    public Usuario findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Usuario save(UsuarioRequest r) {
        if (Objects.isNull(r)) return null;
        return repo.save( toEntity(r) );
    }
}
