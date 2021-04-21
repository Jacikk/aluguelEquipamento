package com.uniamerica.aluguelEquipamento.repository;

import com.uniamerica.aluguelEquipamento.model.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, Long> {

        Produtos findByNome(String nome);

        List<Produtos> findAllByNome(String nome);

        List<Produtos> findAllByDisponivel(Boolean disponivel);
}
