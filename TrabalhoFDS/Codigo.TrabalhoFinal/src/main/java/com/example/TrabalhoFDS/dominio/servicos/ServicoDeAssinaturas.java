package com.example.TrabalhoFDS.dominio.servicos;

import com.example.TrabalhoFDS.dominio.entidades.AssinaturaModel;
import com.example.TrabalhoFDS.dominio.entidades.ClienteModel;
import com.example.TrabalhoFDS.dominio.entidades.AplicativoModel;
import com.example.TrabalhoFDS.dominio.interfRepositorios.AssinaturaRepository;
import com.example.TrabalhoFDS.dominio.interfRepositorios.ClienteRepository;
import com.example.TrabalhoFDS.dominio.interfRepositorios.AplicativoRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicoDeAssinaturas {

    private final AssinaturaRepository assinaturaRepository;
    private final ClienteRepository clienteRepository;
    private final AplicativoRepository aplicativoRepository;

    public ServicoDeAssinaturas(AssinaturaRepository assinaturaRepository, ClienteRepository clienteRepository, AplicativoRepository aplicativoRepository) {
        this.assinaturaRepository = assinaturaRepository;
        this.clienteRepository = clienteRepository;
        this.aplicativoRepository = aplicativoRepository;
    }

    // Cadastra uma nova assinatura para um cliente e aplicativo
    public AssinaturaModel cadastrarAssinatura(Long clienteId, Long aplicativoId, AssinaturaModel novaAssinatura) {
        ClienteModel cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        AplicativoModel aplicativo = aplicativoRepository.findById(aplicativoId).orElseThrow(() -> new RuntimeException("Aplicativo não encontrado"));

        novaAssinatura.setCliente(cliente);
        novaAssinatura.setAplicativo(aplicativo);
        return assinaturaRepository.save(novaAssinatura);
    }

    // Verifica se uma assinatura de um cliente é válida
    public boolean validarAssinatura(Long clienteId, Long assinaturaId) {
        AssinaturaModel assinatura = assinaturaRepository.findById(assinaturaId).orElse(null);
        if (assinatura != null && assinatura.getCliente().getCodigo().equals(clienteId)) {
            return assinatura.getFimVigencia().after(new Date());
        }
        return false;
    }

    // Lista todas as assinaturas de um cliente
    public List<AssinaturaModel> listarAssinaturasCliente(Long clienteId) {
        return assinaturaRepository.findByClienteCodigo(clienteId);
    }

    // Lista os clientes que possuem assinaturas de um aplicativo específico
    public List<ClienteModel> listarClientesPorAplicativo(Long aplicativoId) {
        return clienteRepository.findClientesByAplicativoCodigo(aplicativoId);
    }
    public List<AssinaturaModel> listarAssinaturasPorAplicativo(Long aplicativoId) {
        return assinaturaRepository.findByAplicativoCodigo(aplicativoId);
    }

    // Lista assinaturas por tipo: TODAS, ATIVAS, CANCELADAS
    public List<AssinaturaModel> listarAssinaturasPorTipo(String tipo) {
        Date currentDate = new Date();
        switch (tipo.toUpperCase()) {
            case "ATIVAS":
                return assinaturaRepository.findAll().stream()
                        .filter(assinatura -> assinatura.getFimVigencia().after(currentDate))
                        .collect(Collectors.toList());
            case "CANCELADAS":
                return assinaturaRepository.findAll().stream()
                        .filter(assinatura -> assinatura.getFimVigencia().before(currentDate))
                        .collect(Collectors.toList());
            case "TODAS":
            default:
                return assinaturaRepository.findAll();
        }
    }
    public boolean isAssinaturaValida(Long codass) {
        AssinaturaModel assinatura = assinaturaRepository.findById(codass).orElse(null);
        if (assinatura == null) {
            return false;
        }
        // Verifica se a assinatura está ativa comparando a data de fim com a data atual
        return assinatura.getFimVigencia().after(new Date());
    }
      public AssinaturaModel cadastrarAssinatura(Long clienteId, Long aplicativoId) {
        ClienteModel cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        AplicativoModel aplicativo = aplicativoRepository.findById(aplicativoId).orElseThrow(() -> new RuntimeException("Aplicativo não encontrado"));

        AssinaturaModel novaAssinatura = new AssinaturaModel();
        novaAssinatura.setCliente(cliente);
        novaAssinatura.setAplicativo(aplicativo);
        novaAssinatura.setInicioVigencia(new Date());

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 7); // Define fim de vigência para 7 dias depois
        novaAssinatura.setFimVigencia(calendar.getTime());


        return assinaturaRepository.save(novaAssinatura);
    }





}
