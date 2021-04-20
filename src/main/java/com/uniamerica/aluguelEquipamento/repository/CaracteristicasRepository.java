package com.uniamerica.aluguelEquipamento.repository;

import com.uniamerica.aluguelEquipamento.model.Caracteristicas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaracteristicasRepository extends JpaRepository<Caracteristicas, Long> {
    Caracteristicas findByNome(String nome);
}
