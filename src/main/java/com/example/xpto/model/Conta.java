package com.example.xpto.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "conta")
public class Conta {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Long valorConta;
  

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    
    
    
	public Conta(Long valorConta, Cliente cliente) {
		super();
		this.valorConta = valorConta;
		this.cliente = cliente;
	}

	public Long getValorConta() {
		return valorConta;
	}

	public void setValorConta(Long valorConta) {
		this.valorConta = valorConta;
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