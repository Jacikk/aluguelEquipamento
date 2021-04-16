package com.uniamerica.aluguelEquipamento.repository;

import com.uniamerica.aluguelEquipamento.model.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes,Long> {
    List<Clientes> findByNome(String nome);

    Clientes findByCpf (String cpf);

    Clientes findByRg (String rg);
}
