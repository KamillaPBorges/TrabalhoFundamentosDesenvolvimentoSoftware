package com.example.TrabalhoFDS.interfaceAdaptadora.repositorios.implemRepositorios;

import com.example.TrabalhoFDS.dominio.entidades.AplicativoModel;
import com.example.TrabalhoFDS.dominio.interfRepositorios.AplicativoRepository;
import com.example.TrabalhoFDS.interfaceAdaptadora.repositorios.entidades.Aplicativo;
import com.example.TrabalhoFDS.interfaceAdaptadora.repositorios.interfaceJPA.AplicativoJPA_ItfRep;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class AplicativoRepJPA implements AplicativoRepository {
    private final AplicativoJPA_ItfRep aplicativoRepository;

    public AplicativoRepJPA(AplicativoJPA_ItfRep aplicativoRepository) {
        this.aplicativoRepository = aplicativoRepository;
    }

    @Override
    public Optional<AplicativoModel> findById(Long id) {
        return aplicativoRepository.findById(id)
                .map(Aplicativo::toModel);
    }

    @Override
    public List<AplicativoModel> findAll() {
        return aplicativoRepository.findAll().stream()
                .map(Aplicativo::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public AplicativoModel save(AplicativoModel aplicativo) {
        Aplicativo aplicativoEntity = Aplicativo.fromModel(aplicativo);
        Aplicativo savedEntity = aplicativoRepository.save(aplicativoEntity);
        return Aplicativo.toModel(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        aplicativoRepository.deleteById(id);
    }
}
