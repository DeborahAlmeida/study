package com.example.xpto.service;

import com.example.xpto.model.PessoaFisica;
import com.example.xpto.repository.PessoaFisicaRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaFisicaService {
    @Autowired
    private PessoaFisicaRepository pessoaFisicaRepository;

    public ResponseEntity<?> criarPF(PessoaFisica pessoaFisica){
        try{
            if(pessoaFisica.getCpf().length()<11){
                return new ResponseEntity<>("CPF precisa ser válido!", HttpStatus.NOT_ACCEPTABLE);
            }
            pessoaFisicaRepository.save(pessoaFisica);
            return ResponseEntity.ok().build();
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>("Argumentos passados não aceitos, verifique!", HttpStatus.NOT_ACCEPTABLE);
        }

    }

    public ResponseEntity<?> alterarPF(PessoaFisica pessoaFisica, Long id){
        try{
            Optional<PessoaFisica> optionalPessoaFisica = pessoaFisicaRepository.findById(id);
            if(optionalPessoaFisica.isPresent()){
                if(pessoaFisica.getCpf().length()<11){
                    return new ResponseEntity<>("CPF precisa ser válido!", HttpStatus.NOT_ACCEPTABLE);
                }
                pessoaFisicaRepository.save(pessoaFisica);
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
            return new ResponseEntity<>(pessoaFisicaRepository.findAll(), HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> getById(Long id){
        try{
            Optional<PessoaFisica> optionalPessoaFisica = pessoaFisicaRepository.findById(id);
            if (optionalPessoaFisica.isPresent()){
                return ResponseEntity.ok(optionalPessoaFisica.get());
            }else {
                return ResponseEntity.notFound().build();
            }

        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> delete(Long id){
        try{
            Optional<PessoaFisica> optionalPessoaFisica = pessoaFisicaRepository.findById(id);
            if (optionalPessoaFisica.isPresent()){
                pessoaFisicaRepository.delete(optionalPessoaFisica.get());
                return ResponseEntity.ok("Deletado");
            }else {
                return ResponseEntity.notFound().build();
            }

        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}