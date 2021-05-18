package com.uniamerica.aluguelEquipamento.repository;

import com.uniamerica.aluguelEquipamento.model.Emprestimos;
import com.uniamerica.aluguelEquipamento.model.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EmprestimosRepository extends JpaRepository<Emprestimos, Long>{

    List<Emprestimos> findByProduto(Produtos produto);

    @Query(
        value= "SELECT e FROM Emprestimos e WHERE e.dataInicial between ?1 and ?2 " +
                "or e.dataFinal between ?1 and ?2 " +
                "or ?1 between e.dataInicial and e.dataFinal " +
                "or ?2 between e.dataInicial and e.dataFinal"
    )
    List<Emprestimos> emprestimosPorPeriodo(Date dataInicial, Date dataFinal);

}
