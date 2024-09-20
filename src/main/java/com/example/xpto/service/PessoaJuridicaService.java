package com.example.xpto.service;


import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.xpto.model.PessoaJuridica;
import com.example.xpto.repository.PessoaJuridicaRepository;

@Service
public class PessoaJuridicaService {
    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;

    public ResponseEntity<?> criarPJ(PessoaJuridica pessoaJuridica){
        try{
            if(pessoaJuridica.getCnpj().length()<13){
                return new ResponseEntity<>("CPF precisa ser válido!", HttpStatus.NOT_ACCEPTABLE);
            }
            pessoaJuridicaRepository.save(pessoaJuridica);
            return ResponseEntity.ok().build();
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>("Argumentos passados não aceitos, verifique!", HttpStatus.NOT_ACCEPTABLE);
        }

    }

    public ResponseEntity<?> alterarPJ(PessoaJuridica pessoaJuridica, Long id){
        try{
            Optional<PessoaJuridica> optionalPessoaJuridica = pessoaJuridicaRepository.findById(id);
            if(optionalPessoaJuridica.isPresent()){
                if(pessoaJuridica.getCnpj().length()<11){
                    return new ResponseEntity<>("CPF precisa ser válido!", HttpStatus.NOT_ACCEPTABLE);
                }
                pessoaJuridicaRepository.save(pessoaJuridica);
                return ResponseEntity.ok().build();
            }else {
                return ResponseEntity.notFound().build();
            }

        }catch (ConstraintViolationException cve) {
            return new ResponseEntity<>(cve.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>("Argumentos passados não aceitos, verifique!", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    public ResponseEntity<?> get(){
        try{
            return new ResponseEntity<>(pessoaJuridicaRepository.findAll(), HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> getById(Long id){
        try{
            Optional<PessoaJuridica> optionalPessoaJuridica = pessoaJuridicaRepository.findById(id);
            if (optionalPessoaJuridica.isPresent()){
                return ResponseEntity.ok(optionalPessoaJuridica.get());
            }else {
                return ResponseEntity.notFound().build();
            }

        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> delete(Long id){
        try{
            Optional<PessoaJuridica> optionalPessoaJuridica = pessoaJuridicaRepository.findById(id);
            if (optionalPessoaJuridica.isPresent()){
                pessoaJuridicaRepository.delete(optionalPessoaJuridica.get());
                return ResponseEntity.ok("Deletado");
            }else {
                return ResponseEntity.notFound().build();
            }

        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}