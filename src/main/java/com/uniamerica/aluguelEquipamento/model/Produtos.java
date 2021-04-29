package com.uniamerica.aluguelEquipamento.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Produtos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;

    private String descricao;

    @ManyToOne
    private Itens item;

    private Boolean disponivel; //disponivel = está no estoque

    @ManyToMany
    private List<Caracteristicas> caracteristicas;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return nome;
    }

    public void setName(String name) {
        this.nome = name;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Itens getItem() {
        return item;
    }

    public void setItem(Itens item) {
        this.item = item;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Caracteristicas> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<Caracteristicas> caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
}
