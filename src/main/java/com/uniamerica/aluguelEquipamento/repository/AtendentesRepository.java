package com.uniamerica.aluguelEquipamento.repository;

import com.uniamerica.aluguelEquipamento.model.Atendentes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtendentesRepository extends JpaRepository<Atendentes, Long> {

    List<Atendentes> findAllByNome (String nome);

    Atendentes findByEmail (String email);

    Atendentes findByCpf (String cpf);

    Atendentes findByRg (String rg);
}
