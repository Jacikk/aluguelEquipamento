package com.uniamerica.aluguelEquipamento.repository;

import com.uniamerica.aluguelEquipamento.model.Itens;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItensRepository extends JpaRepository<Itens, Long> {

}
