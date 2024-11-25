package com.example.TrabalhoFDS.interfaceAdaptadora.repositorios.implemRepositorios;

import com.example.TrabalhoFDS.dominio.entidades.AssinaturaModel;
import com.example.TrabalhoFDS.dominio.interfRepositorios.AssinaturaRepository;
import com.example.TrabalhoFDS.interfaceAdaptadora.repositorios.entidades.Assinatura;
import com.example.TrabalhoFDS.interfaceAdaptadora.repositorios.interfaceJPA.AssinaturaJPA_ItfRep;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class AssinaturaRepJPA implements AssinaturaRepository {
    private final AssinaturaJPA_ItfRep assinaturaRepository;

    public AssinaturaRepJPA(AssinaturaJPA_ItfRep assinaturaRepository) {
        this.assinaturaRepository = assinaturaRepository;
    }

    @Override
    public Optional<AssinaturaModel> findById(Long id) {
        return assinaturaRepository.findById(id)
                .map(Assinatura::toModel);
    }

    @Override
    public List<AssinaturaModel> findAll() {
        return assinaturaRepository.findAll().stream()
                .map(Assinatura::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<AssinaturaModel> findByClienteCodigo(Long clienteId) {
        return assinaturaRepository.findByClienteCodigo(clienteId).stream()
                .map(Assinatura::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<AssinaturaModel> findByAplicativoCodigo(Long aplicativoId) {
        return assinaturaRepository.findByAplicativoCodigo(aplicativoId).stream()
                .map(Assinatura::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public AssinaturaModel save(AssinaturaModel assinatura) {
        Assinatura assinaturaEntity = Assinatura.fromModel(assinatura);
        Assinatura savedEntity = assinaturaRepository.save(assinaturaEntity);
        return Assinatura.toModel(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        assinaturaRepository.deleteById(id);
    }
}
