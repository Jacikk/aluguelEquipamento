package com.uniamerica.aluguelEquipamento.model;

import javax.persistence.*;

@Entity
@Table(name = "Departamentos")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Departamentos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
