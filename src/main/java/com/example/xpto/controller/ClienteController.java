package com.example.xpto.controller;

import com.example.xpto.model.Cliente;
import com.example.xpto.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping
    public ResponseEntity getAll(){
        return clienteService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id){
        return clienteService.getById(id);
    }

    @PostMapping
    public ResponseEntity post(@RequestBody Cliente cliente){
        return clienteService.inserir(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Cliente cliente){
        return clienteService.update(id, cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        return clienteService.delete(id);
    }
}