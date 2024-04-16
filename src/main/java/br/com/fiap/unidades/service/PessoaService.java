package br.com.fiap.unidades.service;

import br.com.fiap.unidades.dto.AbstractDTO;
import br.com.fiap.unidades.dto.request.PessoaRequest;
import br.com.fiap.unidades.dto.response.PessoaResponse;
import br.com.fiap.unidades.entity.Pessoa;
import br.com.fiap.unidades.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

//@Service
//public class PessoaService implements ServiceDTO<Pessoa, PessoaRequest, PessoaResponse, AbstractDTO> {
//
//}