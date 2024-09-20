package com.example.xpto.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.xpto.model.Cliente;
import com.example.xpto.model.Movimentacao;
import com.example.xpto.reports.SaldoMovimentacao;
import com.example.xpto.repository.ClienteRepository;
import com.example.xpto.repository.MovimentacaoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class MovimentacaoService {
    @Autowired
    private MovimentacaoRepository movimentacaoRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;

    @PersistenceContext
    private EntityManager entityManager;
    
    public void relatorioMovimentacoes() {
        SaldoMovimentacao saldoMovimentacao = new SaldoMovimentacao();
        BigDecimal total = saldoMovimentacao.chamarSomaMovimentacoes();
        // Faça o que precisar com o total
    }
    
    public String movimento(char tipo_movimento, Long valor, Cliente cliente){
        try{
            if (('E' == tipo_movimento || 'S' == tipo_movimento)){
                LocalDateTime now = LocalDateTime.now();
                Date sqlDate = Date.valueOf(now.toLocalDate());
             
                BigDecimal lucro = new BigDecimal(0.5);
                Movimentacao movimentacao = new Movimentacao(tipo_movimento,valor,sqlDate,cliente);
                movimentacaoRepository.save(movimentacao);
                clienteRepository.save(cliente);
                return "ok";                
            }else {
                return "Erro na movimentação";
            }

        }catch (Exception e){
            return e.getMessage();
        }
    }
}