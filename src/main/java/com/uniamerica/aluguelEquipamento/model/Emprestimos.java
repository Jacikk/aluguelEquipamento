package com.uniamerica.aluguelEquipamento.model;

import javax.persistence.*;

@Entity
public class Emprestimos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Atendentes atendente;

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


}
