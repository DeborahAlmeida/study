package com.example.xpto.controller;

import com.example.xpto.model.Cliente;
import com.example.xpto.model.Conta;
import com.example.xpto.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/contas")
public class ContaController {
    @Autowired
    ContaService contaService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return contaService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return contaService.getById(id);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody Conta conta){

        return contaService.abrirConta(conta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Conta conta){
        return contaService.update(id, conta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return contaService.delete(id);
    }

    @PostMapping("/receber/{id}")
    public ResponseEntity<?> receber(@PathVariable Long id, @RequestBody Long valor){
        return contaService.receber(id, valor);
    }

    @PostMapping("/transferir/{id}")
    public ResponseEntity<?> transferir(@PathVariable Long id, @RequestBody Long valor){
        return contaService.transferir(id, valor);
    }

   
}