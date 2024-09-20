package com.example.xpto.repository;

import com.example.xpto.model.PessoaJuridica;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaJuridicaRepository extends CrudRepository<PessoaJuridica, Long> {
}
