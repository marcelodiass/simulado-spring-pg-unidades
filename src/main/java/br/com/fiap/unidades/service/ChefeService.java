package br.com.fiap.unidades.service;

import br.com.fiap.unidades.dto.request.ChefeRequest;
import br.com.fiap.unidades.dto.response.ChefeResponse;
import br.com.fiap.unidades.entity.Chefe;
import br.com.fiap.unidades.repository.ChefeRepository;
import br.com.fiap.unidades.repository.UnidadeRepository;
import br.com.fiap.unidades.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
public class ChefeService implements ServiceDTO<Chefe, ChefeRequest, ChefeResponse> {

    @Autowired
    private ChefeRepository repo;

    @Autowired
    private UnidadeRepository repoUnidade;

    @Autowired
    private UsuarioRepository repoUsuario;

    @Override
    public Chefe toEntity(ChefeRequest r) {

        var unidade = repoUnidade.findById(r.unidade().id()).orElse(null);
        var usuario = repoUsuario.findById(r.usuario().id()).orElse(null);

        if (Objects.isNull(unidade) || Objects.isNull(usuario)) {
            return null;
        }

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
        if (Objects.isNull( e )) {
            return null;
        }

        var unidade = new UnidadeService().toResponse(e.getUnidade());
        var usuario = new UsuarioService().toResponse(e.getUsuario());

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
