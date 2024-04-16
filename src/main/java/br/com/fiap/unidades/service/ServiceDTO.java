package br.com.fiap.unidades.service;

import br.com.fiap.unidades.dto.AbstractDTO;
import org.springframework.data.domain.Example;

import java.util.List;

/**
 * @param <Entity>
 * @param <Request>
 * @param <Response>
 */

public interface ServiceDTO<Entity, Request, Response, abstractDTO> {

    Entity toEntity(Request r);

    Response toResponse(Entity e);

    List<Entity> findAll();

    Entity findDatabaseObject(AbstractDTO abstractDTO);

    List<Entity> findAll(Example<Entity> example);

    Entity findById(Long id);

    Entity save(Request r);
}