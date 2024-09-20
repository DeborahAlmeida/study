package com.example.xpto.repository;

import com.example.xpto.model.Cliente;
import com.example.xpto.model.Conta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContaRepository extends CrudRepository<Conta, Long> {

}