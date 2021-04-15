package com.uniamerica.aluguelEquipamento.repository;

import com.uniamerica.aluguelEquipamento.model.Atendentes;
import com.uniamerica.aluguelEquipamento.model.Categorias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaracteristicasRepository extends JpaRepository<Categorias, Long>{
    List<Categorias> findAllByName (String name);

    Categorias findByName(String name);
}
