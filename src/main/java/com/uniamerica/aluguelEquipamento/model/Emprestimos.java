package com.uniamerica.aluguelEquipamento.model;

import javax.persistence.*;

@Entity
public class Emprestimos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Atendentes atendente;
}
