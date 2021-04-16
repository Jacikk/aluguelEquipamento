package com.uniamerica.aluguelEquipamento.repository;

import com.uniamerica.aluguelEquipamento.model.Caracteristicas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaracteristicasRepository extends JpaRepository<Caracteristicas, Long> {

    List<Caracteristicas> findAllByName (String name);

    Caracteristicas findByName(String name);
}
