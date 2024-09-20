package com.example.xpto.service;

import com.example.xpto.model.Cliente;
import com.example.xpto.model.Conta;
import com.example.xpto.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ContaService {
    @Autowired
    private ContaRepository contaRepository;

    private MovimentacaoService movimentacaoService;

    public ResponseEntity<?> abrirConta(Conta conta){
        try{
            BigDecimal zero = new BigDecimal(0);
               movimentacaoService.movimento('E', 10L, conta.getCliente());
            

            return ResponseEntity.ok().build();
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> transferir(Long id, Long valor){
        Optional<Conta> optional = contaRepository.findById(id);

        if(optional.isPresent()){
            Conta conta = optional.get();
            conta.setValorConta(conta.getValorConta() - valor);
            movimentacaoService.movimento('S', valor, conta.getCliente());

            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> receber(Long id, Long valor){
        Optional<Conta> optional = contaRepository.findById(id);

        if(optional.isPresent()){
            Conta conta = optional.get();
            conta.setValorConta(conta.getValorConta() + valor);
            movimentacaoService.movimento('E', valor , conta.getCliente());

            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    
    
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.ok(contaRepository.findAll());
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> getById(Long id){
        try{
            Optional<Conta> optionalConta = contaRepository.findById(id);
            if(optionalConta.isPresent()){
                return ResponseEntity.ok(optionalConta.get());
            }else {
                return ResponseEntity.notFound().build();
            }
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> update(Long id, Conta conta) {
        try{
            Optional<Conta> optionalConta = contaRepository.findById(id);
            if(optionalConta.isPresent()){
                contaRepository.save(conta);
                return ResponseEntity.ok().build();
            }else {
                return ResponseEntity.notFound().build();
            }
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> delete(Long id) {
        try{
            Optional<Conta> optionalConta = contaRepository.findById(id);
            if(optionalConta.isPresent()){
                contaRepository.delete(optionalConta.get());
                return ResponseEntity.ok().build();
            }else {
                return ResponseEntity.notFound().build();
            }
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}