package com.uniamerica.aluguelEquipamento.repository;

import com.uniamerica.aluguelEquipamento.model.Itens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItensRepository extends JpaRepository<Itens, Long> {

    List<Itens> findByNome(String nome);

    List<Itens> findAllByDepartamento(String departamento);
}
