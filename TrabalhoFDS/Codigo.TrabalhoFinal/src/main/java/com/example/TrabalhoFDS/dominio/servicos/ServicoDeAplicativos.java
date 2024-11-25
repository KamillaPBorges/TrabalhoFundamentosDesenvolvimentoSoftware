package com.example.TrabalhoFDS.dominio.servicos;

import com.example.TrabalhoFDS.dominio.entidades.AplicativoModel;
import com.example.TrabalhoFDS.dominio.interfRepositorios.AplicativoRepository;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ServicoDeAplicativos {
    
    private final AplicativoRepository aplicativoRepository;

    public ServicoDeAplicativos(AplicativoRepository aplicativoRepository) {
        this.aplicativoRepository = aplicativoRepository;
    }

    // Busca um aplicativo pelo ID
    public AplicativoModel buscarAplicativo(Long idAplicativo) {
        return aplicativoRepository.findById(idAplicativo)
                .orElseThrow(() -> new RuntimeException("Aplicativo não encontrado"));
    }

    // Atualiza o custo de um aplicativo
    public AplicativoModel atualizarAplicativo(AplicativoModel aplicativo) {
        return aplicativoRepository.save(aplicativo);
    }
    public List<AplicativoModel> listarTodosAplicativos() {
        return aplicativoRepository.findAll();
    }
}
