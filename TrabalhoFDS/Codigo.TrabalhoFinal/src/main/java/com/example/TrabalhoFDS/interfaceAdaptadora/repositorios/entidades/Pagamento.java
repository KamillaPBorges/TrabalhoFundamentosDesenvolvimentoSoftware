
package com.example.TrabalhoFDS.interfaceAdaptadora.repositorios.entidades;
import com.example.TrabalhoFDS.dominio.entidades.PagamentoModel;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @ManyToOne
    private Assinatura assinatura;
    
    private double valorPago;
    private Date dataPagamento;
    private String promocao;

    protected Pagamento() {}

    public Pagamento(Long codigo, Assinatura assinatura, double valorPago, Date dataPagamento, String promocao) {
        this.codigo = codigo;
        this.assinatura = assinatura;
        this.valorPago = valorPago;
        this.dataPagamento = dataPagamento;
        this.promocao = promocao;
    }

    public Long getCodigo() {
        return codigo;
    }

    public Assinatura getAssinatura() {
        return assinatura;
    }

    public double getValorPago() {
        return valorPago;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public String getPromocao() {
        return promocao;
    }

    public static Pagamento fromModel(PagamentoModel model) {
        return new Pagamento(
            model.getCodigo(),
            Assinatura.fromModel(model.getAssinatura()),
            model.getValorPago(),
            model.getDataPagamento(),
            model.getPromocao()
        );
    }

    public static PagamentoModel toModel(Pagamento pagamento) {
        PagamentoModel model = new PagamentoModel();
        model.setCodigo(pagamento.getCodigo());
        model.setAssinatura(Assinatura.toModel(pagamento.getAssinatura()));
        model.setValorPago((float) pagamento.getValorPago());  // Conversão de double para float
        model.setDataPagamento(pagamento.getDataPagamento());
        model.setPromocao(pagamento.getPromocao());
        return model;
    }
}
