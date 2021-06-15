package com.uniamerica.aluguelEquipamento.service;

import com.uniamerica.aluguelEquipamento.model.Emprestimos;
import com.uniamerica.aluguelEquipamento.model.Produtos;
import com.uniamerica.aluguelEquipamento.repository.EmprestimosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmprestimosService {

    private final EmprestimosRepository emprestimosRepository;
    private final ProdutosService produtosService;

    @Autowired
    public EmprestimosService(EmprestimosRepository emprestimosRepository, ProdutosService produtosService) {
        this.emprestimosRepository = emprestimosRepository;
        this.produtosService = produtosService;
    }

    public Emprestimos create(Emprestimos emprestimo) {
        return emprestimosRepository.save(emprestimo);
    }

    public Emprestimos findById(Long id) {
        Optional<Emprestimos> found = emprestimosRepository.findById(id);
        if(found.isPresent()){
            return found.get();
        } else return null;
    }

    public Emprestimos update(Emprestimos emprestimo) {
        return emprestimosRepository.save(emprestimo);
    }

    public void delete(Long id) {
        emprestimosRepository.deleteById(id);
    }

    public List<Emprestimos> findAll() {
        return emprestimosRepository.findAll();
    }

    public List<Emprestimos> findByProduto(Long produtoId) {

        Produtos produto = produtosService.findById(produtoId);

        return emprestimosRepository.findByProduto(produto);
    }

    public List<Emprestimos> verificarPeriodo(Date inicio, Date fim) {
        return emprestimosRepository.emprestimosPorPeriodo(inicio, fim);
    }
}
