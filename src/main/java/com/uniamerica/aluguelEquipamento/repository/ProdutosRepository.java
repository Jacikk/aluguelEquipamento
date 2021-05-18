package com.uniamerica.aluguelEquipamento.repository;

import com.uniamerica.aluguelEquipamento.model.Emprestimos;
import com.uniamerica.aluguelEquipamento.model.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, Long> {

        Produtos findByNome(String nome);

        List<Produtos> findAllByNome(String nome);

        List<Produtos> findAllByDisponivel(Boolean disponivel);

        @Query(
                value= "select p from Produtos p where p not in " +
                        "(SELECT e.produto FROM Emprestimos e WHERE e.dataInicial between ?1 and ?2 " +
                        "or e.dataFinal between ?1 and ?2 or ?1 between e.dataInicial and e.dataFinal " +
                        "or ?2 between e.dataInicial and e.dataFinal)"
        )
        List<Produtos> produtosDisponiveisPorPeriodo(Date dataInicial, Date dataFinal);
}
