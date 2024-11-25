package com.example.TrabalhoFDS.dominio.servicos;

import com.example.TrabalhoFDS.dominio.entidades.PagamentoModel;
import com.example.TrabalhoFDS.dominio.entidades.AssinaturaModel;
import com.example.TrabalhoFDS.dominio.interfRepositorios.PagamentoRepository;
import com.example.TrabalhoFDS.dominio.interfRepositorios.AssinaturaRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ServicoDePagamentos {

    private final PagamentoRepository pagamentoRepository;
    private final AssinaturaRepository assinaturaRepository;

    public ServicoDePagamentos(PagamentoRepository pagamentoRepository, AssinaturaRepository assinaturaRepository) {
        this.pagamentoRepository = pagamentoRepository;
        this.assinaturaRepository = assinaturaRepository;
    }

    public PagamentoModel processarPagamento(Long assinaturaId, PagamentoModel pagamento) {
        AssinaturaModel assinatura = assinaturaRepository.findById(assinaturaId)
            .orElseThrow(() -> new RuntimeException("Assinatura não encontrada"));

        boolean isReativacao = assinatura.getFimVigencia() == null || assinatura.getFimVigencia().before(pagamento.getDataPagamento());

        // Verifica se o pagamento é anual
        double valorAnualEsperado = assinatura.getAplicativo().getCusto() * 12;
        boolean isAnual = Math.abs(pagamento.getValorPago() - valorAnualEsperado) < 0.01;

        // Atualizar validade da assinatura
        Date novaDataFimVigencia = calcularNovaValidade(assinatura.getFimVigencia(), pagamento.getDataPagamento(), isReativacao, isAnual);
        assinatura.setFimVigencia(novaDataFimVigencia);
        assinaturaRepository.save(assinatura);

        // Calcular valor estornado e atribuí-lo ao pagamento
        Float valorEstornado = calcularValorEstornado(assinatura, pagamento, isReativacao);
        pagamento.setValorEstornado(valorEstornado);

        pagamento.setAssinatura(assinatura);

        System.out.println("DEBUG - Pagamento salvo com valor estornado: " + valorEstornado);

        return pagamento; // Retorna o objeto pagamento com valorEstornado
    }

    private Float calcularValorEstornado(AssinaturaModel assinatura, PagamentoModel pagamento, boolean isReativacao) {
        double valorEsperado = assinatura.getAplicativo().getCusto();
        float valorPago = pagamento.getValorPago();

        System.out.println("DEBUG - Valor esperado (mensal): " + valorEsperado);
        System.out.println("DEBUG - Valor pago: " + valorPago);
        System.out.println("DEBUG - Reativação: " + isReativacao);

        // Não calcula valor estornado para reativação
        if (isReativacao) {
            System.out.println("DEBUG - Reativação detectada: nenhum valor estornado.");
            return null;
        }

        // Tolerância para comparação de valores devido à precisão de ponto flutuante
        double margemErro = 0.01;

        // Verifica se o valor pago é igual a 12x o valor esperado (anual)
        double valorAnualEsperado = valorEsperado * 12;
        if (Math.abs(valorPago - valorAnualEsperado) < margemErro) {
            Float valorEstornado = valorPago * 0.10f; // 10% do valor pago
            System.out.println("DEBUG - Pagamento anual correto (12x). Valor estornado: " + valorEstornado);
            return valorEstornado;
        }

        // Verifica se o valor pago é igual ao esperado para o pagamento mensal
        if (Math.abs(valorPago - valorEsperado) < margemErro) {
            Float valorEstornado = valorPago * 0.05f; // 5% do valor pago
            System.out.println("DEBUG - Pagamento mensal correto (1x). Valor estornado: " + valorEstornado);
            return valorEstornado;
        }

        // Se o valor não corresponde a nenhum dos casos anteriores, considera incorreto
        System.out.println("DEBUG - Pagamento incorreto. Valor estornado: " + valorPago);
        return valorPago;
    }

    private Date calcularNovaValidade(Date fimVigenciaAtual, Date dataPagamento, boolean isReativacao, boolean isAnual) {
        long diasEmMilissegundos;

        // Define o período de extensão com base no tipo de pagamento e reativação
        if (isReativacao && isAnual) {
            diasEmMilissegundos = 365L * 24 * 60 * 60 * 1000; // 365 dias para pagamento anual em reativação
        } else if (isReativacao) {
            diasEmMilissegundos = 45L * 24 * 60 * 60 * 1000; // 45 dias para reativação com pagamento mensal
        } else if (isAnual) {
            diasEmMilissegundos = 365L * 24 * 60 * 60 * 1000; // 365 dias para pagamento anual
        } else {
            diasEmMilissegundos = 30L * 24 * 60 * 60 * 1000; // 30 dias para pagamento mensal
        }

        // Calcula a nova validade
        if (fimVigenciaAtual != null && fimVigenciaAtual.after(dataPagamento)) {
            return new Date(fimVigenciaAtual.getTime() + diasEmMilissegundos);
        } else {
            return new Date(dataPagamento.getTime() + diasEmMilissegundos);
        }
    }

    public boolean validarValorPago(Long codass, double valorPago) {
        AssinaturaModel assinatura = assinaturaRepository.findById(codass)
                .orElseThrow(() -> new RuntimeException("Assinatura não encontrada"));
        double valorEsperado = assinatura.getAplicativo().getCusto();

        double margemErro = 0.01;

        // Verifica se o valor pago é válido como mensal ou anual
        boolean isMensalValido = Math.abs(valorPago - valorEsperado) < margemErro;
        boolean isAnualValido = Math.abs(valorPago - (valorEsperado * 12)) < margemErro;

        // Retorna verdadeiro se for válido em qualquer dos casos
        return isMensalValido || isAnualValido;
    }
}