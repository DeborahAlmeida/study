package com.example.xpto.reports;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.xpto.model.Endereco;
import com.example.xpto.model.Movimentacao;
import com.example.xpto.repository.ClienteRepository;
import com.example.xpto.repository.EnderecoRepository;
import com.example.xpto.repository.MovimentacaoRepository;

import lombok.Data;
public class SaldoCliente {
    private String nome;
    private Endereco endereco;
    private Long movCredito;
    private Long movDebito;
    private Long totalMov;
    private BigDecimal saldoInicial;
    private BigDecimal saldoAtual;
    private BigDecimal valorTotalMov;


    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;
    
    private Date dataCriacao;

   public void getSaldoClienteMain(Long id) {
	   List<Movimentacao> movimentacao = movimentacaoRepository.findAll();
	   Long saldo = movimentacao.get(0).getValor();
	   System.out.println("Valor da movimentação é:" + saldo);
	   
   }
   
   public void main(String[] args) {
	   getSaldoClienteMain(1l);
	
}
   
   
}
