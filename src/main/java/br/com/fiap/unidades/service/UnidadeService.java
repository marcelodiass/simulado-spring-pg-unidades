package br.com.fiap.unidades.service;

import br.com.fiap.unidades.dto.request.UnidadeRequest;
import br.com.fiap.unidades.dto.response.UnidadeResponse;
import br.com.fiap.unidades.entity.Unidade;
import br.com.fiap.unidades.repository.UnidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/*
* TODO:
*  - Corrigir macro em toEntity
 */

@Service
public class UnidadeService implements ServiceDTO<Unidade, UnidadeRequest, UnidadeResponse> {

    @Autowired
    UnidadeRepository repo;

    @Override
    public Unidade toEntity(UnidadeRequest r) {
        return Unidade.builder()
                .nome(r.nome())
                .sigla(r.sigla())
                .descricao(r.descricao())
                .macro(r.macro())
                .build();
    }

    @Override
    public UnidadeResponse toResponse(Unidade e) {
        if (Objects.isNull(e)) return null;

        var macro = toResponse(e.getMacro());

        return new UnidadeResponse (
                e.getId(),
                e.getNome(),
                e.getSigla(),
                e.getDescricao(),
                macro
        );
    }

    @Override
    public Unidade findDatabaseObject(AbstractDTO abstractDTO) {
        return repo.findById(abstractDTO.id()).orElse(null);
    }

    @Override
    public List<Unidade> findAll() {
        return repo.findAll();
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
