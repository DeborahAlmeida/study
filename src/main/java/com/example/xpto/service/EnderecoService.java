package com.example.xpto.service;

import com.example.xpto.model.Endereco;
import com.example.xpto.repository.EnderecoRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;

    public ResponseEntity<?> inserirEndereco(Endereco endereco){
        try{
            enderecoRepository.save(endereco);
            return ResponseEntity.ok().build();
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> alterarEndereco(Endereco endereco, Long id){
        try{
            Optional<Endereco> optionalEndereco = enderecoRepository.findById(id);
            if(optionalEndereco.isPresent()){
                enderecoRepository.save(endereco);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.notFound().build();

        }catch (ConstraintViolationException cve) {
            return new ResponseEntity<>(cve.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
        catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> get(){
        try{
            return ResponseEntity.ok(enderecoRepository.findAll());
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> getById(Long id){
        try{
            Optional<Endereco> optionalEndereco = enderecoRepository.findById(id);
            if(optionalEndereco.isPresent()){
                return ResponseEntity.ok(optionalEndereco.get());
            }
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> delete(Long id){
        try{
            Optional<Endereco> optionalEndereco = enderecoRepository.findById(id);
            if(optionalEndereco.isPresent()) {
                enderecoRepository.delete(optionalEndereco.get());
                return ResponseEntity.ok().build();
            }

            return ResponseEntity.notFound().build();
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}