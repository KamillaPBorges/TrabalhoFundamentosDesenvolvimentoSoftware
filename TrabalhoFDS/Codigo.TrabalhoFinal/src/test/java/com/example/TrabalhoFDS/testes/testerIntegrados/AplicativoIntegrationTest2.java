package com.example.TrabalhoFDS.testes.testerIntegrados;

import com.example.TrabalhoFDS.dominio.entidades.AplicativoModel;
import com.example.TrabalhoFDS.dominio.interfRepositorios.AplicativoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AplicativoIntegrationTest2 {

    @Autowired
    private AplicativoRepository aplicativoRepository;

    @Test
    void verificarDadosIniciais() {
        // Verifica se todos os aplicativos do arquivo data.sql foram carregados
        List<AplicativoModel> aplicativos = aplicativoRepository.findAll();
        assertThat(aplicativos).hasSize(6); // Deve haver 6 aplicativos no banco
    }

    @Test
    void buscarAplicativoPorId() {
        // Busca um aplicativo pelo ID
        Optional<AplicativoModel> aplicativo = aplicativoRepository.findById(1L);
        assertThat(aplicativo).isPresent(); // O aplicativo deve existir
        assertThat(aplicativo.get().getNome()).isEqualTo("App de Música");
    }

    @Test
    void salvarNovoAplicativo() {
        // Salva um novo aplicativo no banco
        AplicativoModel novoAplicativo = new AplicativoModel();
        novoAplicativo.setCodigo(7L);
        novoAplicativo.setNome("App de Fitness");
        novoAplicativo.setCusto(19.99f);

        AplicativoModel aplicativoSalvo = aplicativoRepository.save(novoAplicativo);

        // Verifica se o aplicativo foi salvo corretamente
        assertThat(aplicativoSalvo).isNotNull();
        assertThat(aplicativoSalvo.getCodigo()).isEqualTo(7L);
        assertThat(aplicativoSalvo.getNome()).isEqualTo("App de Fitness");
    }

    @Test
    void deletarAplicativoPorId() {
        // Deleta um aplicativo pelo ID
        aplicativoRepository.deleteById(1L);

        // Verifica se o aplicativo foi removido
        Optional<AplicativoModel> aplicativo = aplicativoRepository.findById(1L);
        assertThat(aplicativo).isNotPresent(); // O aplicativo não deve mais existir
    }

    @Test
    void salvarAplicativo_ComSucesso() {
        // Salva um aplicativo com identificador manual
        AplicativoModel aplicativo = new AplicativoModel();
        aplicativo.setCodigo(100L); // Defina manualmente o identificador
        aplicativo.setNome("Aplicativo de Streaming");
        aplicativo.setCusto(19.99f);

        AplicativoModel aplicativoSalvo = aplicativoRepository.save(aplicativo);

        // Verifica se foi salvo corretamente
        assertThat(aplicativoSalvo).isNotNull();
        assertThat(aplicativoSalvo.getCodigo()).isEqualTo(100L); // Verifique o identificador
        assertThat(aplicativoSalvo.getNome()).isEqualTo("Aplicativo de Streaming");
    }

    @Test
    void buscarAplicativo_PeloCodigo() {
        // Salva e busca um aplicativo pelo ID
        AplicativoModel aplicativo = new AplicativoModel();
        aplicativo.setCodigo(200L); // Defina manualmente o identificador
        aplicativo.setNome("Aplicativo de Música");
        aplicativo.setCusto(29.99f);

        AplicativoModel aplicativoSalvo = aplicativoRepository.save(aplicativo);

        AplicativoModel aplicativoEncontrado = aplicativoRepository.findById(aplicativoSalvo.getCodigo()).orElse(null);

        // Verifica se foi encontrado corretamente
        assertThat(aplicativoEncontrado).isNotNull();
        assertThat(aplicativoEncontrado.getNome()).isEqualTo("Aplicativo de Música");
    }

    @Test
    void atualizarAplicativo() {
        // Atualiza os dados de um aplicativo existente
        Optional<AplicativoModel> aplicativoOptional = aplicativoRepository.findById(2L);
        assertThat(aplicativoOptional).isPresent();

        AplicativoModel aplicativo = aplicativoOptional.get();
        aplicativo.setNome("App de Vídeo Atualizado");
        aplicativo.setCusto(59.99f);

        AplicativoModel aplicativoAtualizado = aplicativoRepository.save(aplicativo);

        // Verifica se os dados foram atualizados corretamente
        assertThat(aplicativoAtualizado.getNome()).isEqualTo("App de Vídeo Atualizado");
        assertThat(aplicativoAtualizado.getCusto()).isEqualTo(59.99f);
    }

    @Test
    void buscarAplicativosComCustoMenorQue() {
        // Busca aplicativos com custo menor que 30.00
        List<AplicativoModel> aplicativos = aplicativoRepository.findAll(); // Simulando busca por custo
        List<AplicativoModel> aplicativosFiltrados = aplicativos.stream()
                .filter(app -> app.getCusto() < 30.00f)
                .toList();

        // Verifica se há resultados e se todos têm custo menor que 30.00
        assertThat(aplicativosFiltrados).isNotEmpty();
        assertThat(aplicativosFiltrados).allMatch(app -> app.getCusto() < 30.00f);
    }

}
