
package com.example.TrabalhoFDS.interfaceAdaptadora.repositorios.entidades;


import com.example.TrabalhoFDS.dominio.entidades.AssinaturaModel;
import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Assinatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @ManyToOne
    @JoinColumn(name = "aplicativo_id")
    private Aplicativo aplicativo;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private Date inicioVigencia;
    private Date fimVigencia;

    protected Assinatura() {}

    public Assinatura(Long codigo, Aplicativo aplicativo, Cliente cliente, Date inicioVigencia, Date fimVigencia) {
        this.codigo = codigo;
        this.aplicativo = aplicativo;
        this.cliente = cliente;
        this.inicioVigencia = inicioVigencia;
        this.fimVigencia = fimVigencia;
    }

    public Long getCodigo() {
        return codigo;
    }

    public Aplicativo getAplicativo() {
        return aplicativo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Date getInicioVigencia() {
        return inicioVigencia;
    }

    public Date getFimVigencia() {
        return fimVigencia;
    }

    public static Assinatura fromModel(AssinaturaModel model) {
        return new Assinatura(
            model.getCodigo(),
            Aplicativo.fromModel(model.getAplicativo()),
            Cliente.fromModel(model.getCliente()),
            model.getInicioVigencia(),
            model.getFimVigencia()
        );
    }

    public static AssinaturaModel toModel(Assinatura assinatura) {
        AssinaturaModel model = new AssinaturaModel();
        model.setCodigo(assinatura.getCodigo());
        model.setAplicativo(Aplicativo.toModel(assinatura.getAplicativo()));
        model.setCliente(Cliente.toModel(assinatura.getCliente()));
        model.setInicioVigencia(assinatura.getInicioVigencia());
        model.setFimVigencia(assinatura.getFimVigencia());
        return model;
    }
}
