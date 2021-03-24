package com.uniamerica.aluguelEquipamento.repository;

import com.uniamerica.aluguelEquipamento.model.Departamentos;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface DepartamentosRepository extends JpaRepository<Departamentos, Long> {

    List<Departamentos> findByNome(String nome);
    List<Departamentos> findById(Long id);

}
