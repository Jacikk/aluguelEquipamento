package com.uniamerica.aluguelEquipamento.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Emprestimos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Atendentes atendente;

    @ManyToOne
    private Clientes cliente;

    private Date dataInicial;

    private Date dataFinal;

    private Boolean retirado;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Atendentes getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendentes atendente) {
        this.atendente = atendente;
    }

    public Clientes getCliente() {
        return cliente;
    }

    public void setCliente(Clientes cliente) {
        this.cliente = cliente;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Boolean getRetirado() {
        return retirado;
    }

    public void setRetirado(Boolean retirado) {
        this.retirado = retirado;
    }
}
