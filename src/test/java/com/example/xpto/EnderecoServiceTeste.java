package com.example.xpto;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.xpto.model.Endereco;
import com.example.xpto.repository.EnderecoRepository;
import com.example.xpto.service.EnderecoService;

public class EnderecoServiceTeste {

    @InjectMocks
    private EnderecoService enderecoService;

    @Mock
    private EnderecoRepository enderecoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInserirEnderecoSucesso() {
        Endereco endereco = new Endereco(null, "Cidade Teste", "Bairro Teste", "Rua Teste", "123", "Apto 1", "12345-678", null);
        when(enderecoRepository.save(endereco)).thenReturn(new Endereco(1L, "Cidade Teste", "Bairro Teste", "Rua Teste", "123", "Apto 1", "12345-678", null));

        ResponseEntity<?> response = enderecoService.inserirEndereco(endereco);

        assertThat(response.getStatusCode(), is(equalTo(HttpStatus.OK)));
        verify(enderecoRepository, times(1)).save(endereco);
    }

    @Test
    public void testInserirEnderecoFalha() {
        Endereco endereco = new Endereco(null, null, null, null, null, null, null, null);
        
        when(enderecoRepository.save(endereco)).thenThrow(new IllegalArgumentException("Dados inválidos"));

        ResponseEntity<?> response = enderecoService.inserirEndereco(endereco);

        assertThat(response.getStatusCode(), is(equalTo(HttpStatus.BAD_REQUEST)));
        assertThat(response.getBody(), is("Dados inválidos"));
        verify(enderecoRepository, times(1)).save(endereco);
    }
}
