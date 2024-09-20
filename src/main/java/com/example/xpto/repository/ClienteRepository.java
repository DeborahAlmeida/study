package com.example.xpto.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.xpto.model.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

    public List<Cliente> findAll();
}