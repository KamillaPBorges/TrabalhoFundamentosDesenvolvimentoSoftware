package com.example.TrabalhoFDS.testes.testerIntegrados;

import com.example.TrabalhoFDS.dominio.entidades.ClienteModel;
import com.example.TrabalhoFDS.dominio.interfRepositorios.ClienteRepository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ClienteIntegrationTest {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    void salvarCliente_ComSucesso() {
        // Cria o ClienteModel
        ClienteModel clienteModel = new ClienteModel();
        clienteModel.setCodigo(100L);
        clienteModel.setNome("Teste Cliente");
        clienteModel.setEmail("teste.cliente@email.com");

        // Salva o cliente no repositório
        ClienteModel clienteSalvo = clienteRepository.save(clienteModel);

        assertThat(clienteSalvo).isNotNull();
        assertThat(clienteSalvo.getCodigo()).isEqualTo(100L);
        assertThat(clienteSalvo.getNome()).isEqualTo("Teste Cliente");
        assertThat(clienteSalvo.getEmail()).isEqualTo("teste.cliente@email.com");
    }

    @Test
    void buscarCliente_PeloCodigo() {
        // Cria o ClienteModel
        ClienteModel clienteModel = new ClienteModel();
        clienteModel.setCodigo(101L);
        clienteModel.setNome("Busca Cliente");
        clienteModel.setEmail("busca.cliente@email.com");

        // Salva o cliente no repositório
        clienteRepository.save(clienteModel);

        // Busca o cliente pelo código
        ClienteModel clienteEncontrado = clienteRepository.findById(101L).orElse(null);

        assertThat(clienteEncontrado).isNotNull();
        assertThat(clienteEncontrado.getCodigo()).isEqualTo(101L);
        assertThat(clienteEncontrado.getNome()).isEqualTo("Busca Cliente");
        assertThat(clienteEncontrado.getEmail()).isEqualTo("busca.cliente@email.com");
    }
}
