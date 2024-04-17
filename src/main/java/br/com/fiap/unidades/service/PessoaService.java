package br.com.fiap.unidades.service;

import br.com.fiap.unidades.dto.request.PessoaRequest;
import br.com.fiap.unidades.dto.response.PessoaResponse;
import br.com.fiap.unidades.entity.Pessoa;
import br.com.fiap.unidades.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PessoaService implements ServiceDTO<Pessoa, PessoaRequest, PessoaResponse> {

    @Autowired
    PessoaRepository repo;

    @Override
    public Pessoa toEntity(PessoaRequest r) {
        return Pessoa.builder()
                .nome(r.nome())
                .sobrenome(r.sobrenome())
                .email(r.email())
                .tipo(r.tipo())
                .nascimento(r.nascimento())
                .build();
    }

    @Override
    public PessoaResponse toResponse(Pessoa e) {
        if (Objects.isNull(e)) return null;
        return new PessoaResponse(
                e.getId(),
                e.getNome(),
                e.getSobrenome(),
                e.getEmail(),
                e.getTipo(),
                e.getNascimento()
        );
    }

    @Override
    public List<Pessoa> findAll() {
        return repo.findAll();
    }

    @Override
    public List<Pessoa> findAll(Example<Pessoa> example) {
        return repo.findAll( example );
    }

    @Override
    public Pessoa findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Pessoa save(PessoaRequest r) {
        if (Objects.isNull(r)) return null;
        return repo.save( toEntity(r) );
    }
}