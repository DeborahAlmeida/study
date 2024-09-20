package com.example.xpto.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "endereco")
public class Endereco {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;


    @Column(length = 50, nullable = false)
    private String cidade;


    @Column(length = 50, nullable = false)
    private String bairro;


    @Column(length = 50, nullable = false)
    private String logradouro;

    @Column(length = 5)
    private String numero;


    @Column(length = 50)
    private String complemento;

    @Column(length = 11, nullable = false)
    private String cep;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    
    public Endereco(Long id, String cidade, String bairro, String logradouro, String numero, String complemento, String cep, Cliente cliente) {
        this.id = id;
        this.cidade = cidade;
        this.bairro = bairro;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
        this.cliente = cliente;
    }
    
	public Endereco() {
		super();
		this.cidade = "Cidade teste";
		this.bairro = "Bairro teste";
		this.logradouro = "Rua teste";
		this.cep = "0000000";
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}


	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
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