package com.uniamerica.aluguelEquipamento.model;

import javax.persistence.*;

@Entity
@Table(name = "Atendentes")
public class Atendente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
