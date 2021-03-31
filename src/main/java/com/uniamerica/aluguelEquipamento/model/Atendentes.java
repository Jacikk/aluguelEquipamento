package com.uniamerica.aluguelEquipamento.model;

import javax.persistence.*;

@Entity
public class Atendentes extends Usuarios {

    private String senha;

    @Column(unique=true)
    private String email;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
