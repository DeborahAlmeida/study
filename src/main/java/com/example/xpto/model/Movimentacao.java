package com.example.xpto.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "movimentacao")
public class Movimentacao {


	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private char tipo_movimento; // E - Entrada , S - Saida

    @Column(nullable = false)
    private Long valor;


    private Date dataMovimentacao;
    
    

    public Movimentacao(char tipo_movimento, Long valor, Date dataMovimentacao, Cliente cliente) {
		super();
		this.tipo_movimento = tipo_movimento;
		this.valor = valor;
		this.dataMovimentacao = dataMovimentacao;
		this.cliente = cliente;
	}

	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

	public char getTipo_movimento() {
		return tipo_movimento;
	}

	public void setTipo_movimento(char tipo_movimento) {
		this.tipo_movimento = tipo_movimento;
	}


	public Long getValor() {
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}


	public Date getDataMovimentacao() {
		return dataMovimentacao;
	}

	public void setDataMovimentacao(Date dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    
    
    
    
}