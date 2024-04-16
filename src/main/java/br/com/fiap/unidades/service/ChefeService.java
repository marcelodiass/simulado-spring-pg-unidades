package br.com.fiap.unidades.service;

import br.com.fiap.unidades.dto.AbstractDTO;
import br.com.fiap.unidades.dto.request.ChefeRequest;
import br.com.fiap.unidades.dto.response.ChefeResponse;
import br.com.fiap.unidades.entity.Chefe;
import br.com.fiap.unidades.repository.ChefeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/*TODO:
 * - Corrigir usuario em toEntity
 * - Corrigir unidade em toEntity
 */

@Service
public class ChefeService implements ServiceDTO<Chefe, ChefeRequest, ChefeResponse, AbstractDTO> {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    UnidadeService unidadeService;

    @Autowired
    ChefeRepository repo;

    @Override
    public Chefe toEntity(ChefeRequest r) {

        var unidade = unidadeService.findDatabaseObject( r.unidade() );
        var usuario = usuarioService.findDatabaseObject( r.usuario() );

        return Chefe.builder()
                .unidade(unidade)
                .inicio(r.inicio())
                .fim(r.fim())
                .usuario(usuario)
                .substituto(r.substituto())
                .build();
    }

    @Override
    public ChefeResponse toResponse(Chefe e) {
        if (Objects.isNull( e )) return null;

        var unidade = unidadeService.toResponse(e.getUnidade());
        var usuario = usuarioService.toResponse(e.getUsuario());

        return new ChefeResponse(
                e.getId(),
                unidade,
                e.getInicio(),
                e.getFim(),
                usuario,
                e.getSubstituto()
        );
    }

    @Override
    public Chefe findDatabaseObject(AbstractDTO abstractDTO) {
        return repo.findById(abstractDTO.id()).orElse(null);
    }

    @Override
    public List<Chefe> findAll() {
        return repo.findAll();
    }

    @Override
    public List<Chefe> findAll(Example<Chefe> example) {
        return repo.findAll( example );
    }

    @Override
    public Chefe findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Chefe save(ChefeRequest r) {
        if (Objects.isNull(r)) return null;
        return repo.save( toEntity(r) );
    }
}
