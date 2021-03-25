package com.uniamerica.aluguelEquipamento.repository;

import com.uniamerica.aluguelEquipamento.model.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientesRepository extends JpaRepository<Clientes,Long> {
    Clientes findByName(String nome);
}
