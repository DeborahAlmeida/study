package com.example.xpto.service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.xpto.model.Cliente;
import com.example.xpto.model.Conta;
import com.example.xpto.model.Endereco;
import com.example.xpto.model.PessoaFisica;
import com.example.xpto.model.PessoaJuridica;
import com.example.xpto.repository.ClienteRepository;

@Service
public class ClienteService{
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ContaService contaService;

    @Autowired
    private MovimentacaoService movimentacaoService;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private PessoaFisicaService pessoaFisicaService;

    @Autowired
    private PessoaJuridicaService pessoaJuridicaService;

    

    public ResponseEntity<?> inserir(Cliente cliente){
        try{
            PessoaJuridica pessoaJuridica = new PessoaJuridica();
            PessoaFisica pessoaFisica = new PessoaFisica();
            LocalDateTime now = LocalDateTime.now();
            Date sqlDate = Date.valueOf(now.toLocalDate());
            cliente.setDataCriacao(sqlDate);
            

            Endereco endereco = new Endereco();
            endereco.setCliente(cliente);
            enderecoService.inserirEndereco(endereco);

            if(cliente.getPessoaFisica() != null) {
	            if(!(cliente.getPessoaFisica().getId() == null)){
	                pessoaFisica = cliente.getPessoaFisica();
	                pessoaFisica.setCliente(cliente);
	                pessoaFisicaService.criarPF(pessoaFisica);
	            }
            
	
	            if(!(cliente.getPessoaJuridica().getId() == null)){
	                pessoaJuridica = cliente.getPessoaJuridica();
	                pessoaJuridica.setCliente(cliente);
	                pessoaJuridicaService.criarPJ(pessoaJuridica);
	            }
            }
                          
            Conta conta = new Conta(10L, cliente);
            contaService.abrirConta(conta);
          
            movimentacaoService.movimento('E', 10L, cliente);

            clienteRepository.save(cliente);
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }

    public ResponseEntity<?> update(Long id, Cliente cliente){

        try{
            Optional<Cliente> clienteOptional = clienteRepository.findById(id);
            if(clienteOptional.isPresent()){
                Cliente clienteAccept = new Cliente();
                if(cliente.getNome().length() < 2){
                    return new ResponseEntity<>("O nome precisa ser válido", HttpStatus.BAD_REQUEST);
                } else if (cliente.getTelefone().length() < 10) {
                    return new ResponseEntity<>("O telefone precisa ser válido!", HttpStatus.BAD_REQUEST);
                } else if(!cliente.isAtivo()){
                    return new ResponseEntity<>("Para excluir um cliente, você deve ir na rota de excluir", HttpStatus.BAD_REQUEST);
                }

                Endereco endereco = new Endereco();
                endereco.setCliente(cliente);
                enderecoService.alterarEndereco(endereco, endereco.getId());


                if(!cliente.getContas().isEmpty()){
                    for(Conta conta1 : cliente.getContas()){
                        Conta conta = cliente.getContas().get(0);
                        conta.setCliente(cliente);
                        contaService.abrirConta(conta);
                    }
                }

                clienteRepository.save(cliente);
                return ResponseEntity.ok().build();
            }else {
                return new ResponseEntity<>("Cliente não encontrado", HttpStatus.NOT_FOUND);
            }

        }catch (ConstraintViolationException cve){
            return new ResponseEntity<>(cve.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }catch (IllegalArgumentException iae){
            return new ResponseEntity<>(iae.getMessage(), HttpStatus.BAD_GATEWAY);
        }
    }

    public ResponseEntity<?> delete(Long id){
        try {
            Optional<Cliente> optional = clienteRepository.findById(id);
            if (optional.isPresent()){
                Cliente cliente = optional.get();
                cliente.setAtivo(false);
                clienteRepository.save(cliente);
                return ResponseEntity.ok().build();
            }else {
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.ok(clienteRepository.findAll());
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> getById(Long id){
        try{
            Optional<Cliente> optional = clienteRepository.findById(id);
            if(optional.isPresent()){
                return ResponseEntity.ok(optional.get());
            }else {
                return ResponseEntity.notFound().build();
            }
        }catch (RuntimeException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}