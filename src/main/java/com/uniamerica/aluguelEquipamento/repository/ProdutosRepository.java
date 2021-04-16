package com.uniamerica.aluguelEquipamento.repository;

import com.uniamerica.aluguelEquipamento.model.Clientes;
import com.uniamerica.aluguelEquipamento.model.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutosRepository extends JpaRepository<Clientes,Long> {
        List<Produtos> findByNome(String nome);
//
//        Produtos findByDisponivel (Boolean disponivel);
//
//        Produtos findByItem (Itens itens);

}
