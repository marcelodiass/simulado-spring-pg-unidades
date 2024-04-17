package br.com.fiap.unidades.service;

import br.com.fiap.unidades.dto.request.UnidadeRequest;
import br.com.fiap.unidades.dto.response.UnidadeResponse;
import br.com.fiap.unidades.entity.Unidade;
import br.com.fiap.unidades.repository.UnidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
public class UnidadeService implements ServiceDTO<Unidade, UnidadeRequest, UnidadeResponse> {

    @Autowired
    UnidadeRepository repo;

    @Override
    public Unidade toEntity(UnidadeRequest r) {
        if (Objects.isNull(r.macro())) {
            return Unidade.builder()
                    .nome(r.nome())
                    .sigla(r.sigla())
                    .descricao(r.descricao())
                    .build();
        }

        var macroId = new UnidadeService().findById(r.macro().id());

        if (Objects.isNull(macroId)) {
            return null;
        }

        return Unidade.builder()
                .nome(r.nome())
                .sigla(r.sigla())
                .descricao(r.descricao())
                .macro(macroId)
                .build();
    }

    @Override
    public UnidadeResponse toResponse(Unidade e) {
        if (Objects.isNull(e)) return null;

        var macro = toResponse(e.getMacro());

        return UnidadeResponse.builder()
                .nome(e.getNome())
                .sigla(e.getSigla())
                .descricao(e.getDescricao())
                .macro(macro)
                .build();

    }


    @Override
    public List<Unidade> findAll() {
        return repo.findAll();
    }

    @Override
    public List<Unidade> findAll(Example<Unidade> example) {
        return repo.findAll( example );
    }

    @Override
    public Unidade findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Unidade save(UnidadeRequest r) {
        if (Objects.isNull(r)) return null;
        return repo.save( toEntity(r) );
    }
}
