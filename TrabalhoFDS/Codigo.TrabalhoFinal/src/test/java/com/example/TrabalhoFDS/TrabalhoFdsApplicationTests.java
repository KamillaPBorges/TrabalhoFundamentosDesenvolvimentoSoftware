package com.example.TrabalhoFDS;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.TrabalhoFDS.interfaceAdaptadora.controladores.AssinaturaController;
import com.example.TrabalhoFDS.interfaceAdaptadora.controladores.AssinaturaStatusController;

@SpringBootTest
class TrabalhoFdsApplicationTests {

    @Autowired
    private AssinaturaController assinaturaController;

    @Autowired
    private AssinaturaStatusController assinaturaStatusController;

    @Test
    void contextLoads() {
        // Verifica se ambos os controladores foram carregados no contexto do Spring
        assertThat(assinaturaController).isNotNull();
        assertThat(assinaturaStatusController).isNotNull();
    }
}
