package com.example.xpto.controller;

import com.example.xpto.model.Endereco;
import com.example.xpto.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
    @Autowired
    EnderecoService enderecoService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return enderecoService.get();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return enderecoService.getById(id);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody Endereco endereco){

        return enderecoService.inserirEndereco(endereco);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Endereco endereco){
        return enderecoService.alterarEndereco(endereco, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return enderecoService.delete(id);
    }

}