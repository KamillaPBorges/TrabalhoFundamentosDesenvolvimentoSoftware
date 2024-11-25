package com.example.TrabalhoFDS.interfaceAdaptadora.repositorios.implemRepositorios;

import com.example.TrabalhoFDS.dominio.entidades.PagamentoModel;
import com.example.TrabalhoFDS.dominio.interfRepositorios.PagamentoRepository;
import com.example.TrabalhoFDS.interfaceAdaptadora.repositorios.entidades.Pagamento;
import com.example.TrabalhoFDS.interfaceAdaptadora.repositorios.interfaceJPA.PagamentoJPA_ItfRep;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PagamentoRepJPA implements PagamentoRepository {
    private final PagamentoJPA_ItfRep pagamentoRepository;

    public PagamentoRepJPA(PagamentoJPA_ItfRep pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    @Override
    public Optional<PagamentoModel> findById(Long id) {
        return pagamentoRepository.findById(id)
                .map(Pagamento::toModel);
    }

    @Override
    public List<PagamentoModel> findAll() {
        return pagamentoRepository.findAll().stream()
                .map(Pagamento::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public PagamentoModel save(PagamentoModel pagamento) {
        Pagamento pagamentoEntity = Pagamento.fromModel(pagamento);
        Pagamento savedEntity = pagamentoRepository.save(pagamentoEntity);
        return Pagamento.toModel(savedEntity);
    }

    @Override
    public List<PagamentoModel> findByAssinaturaCodigo(Long assinaturaId) {
        return pagamentoRepository.findByAssinaturaCodigo(assinaturaId).stream()
                .map(Pagamento::toModel)
                .collect(Collectors.toList());
    }
}

