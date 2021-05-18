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

    public Emprestimos create(Emprestimos emprestimos) {

        Calendar dataInicial = Calendar.getInstance();
        dataInicial.setTime(emprestimos.getDataInicial());

        Calendar dataFinal = Calendar.getInstance();
        dataFinal.setTime(emprestimos.getDataFinal());

        List<Emprestimos> emprestimosDoBd = emprestimosRepository.findAll();

        boolean conflictComEmprestimoNoBd = false;

        for (Emprestimos emprestimosAVerificar :emprestimosDoBd) {

            if(emprestimos.getProduto().getId() == emprestimosAVerificar.getProduto().getId()){

                Calendar dataInicialNoDb = Calendar.getInstance();
                dataInicialNoDb.setTime(emprestimosAVerificar.getDataInicial());

                Calendar dataFinalNoDb = Calendar.getInstance();
                dataFinalNoDb.setTime(emprestimosAVerificar.getDataFinal());

                if(dataFinal.equals(dataFinalNoDb) || dataInicial.equals(dataInicialNoDb) || dataFinal.equals(dataInicialNoDb) || dataInicial.equals(dataFinalNoDb)) {

                    conflictComEmprestimoNoBd = true;

                }else if(dataFinal.after(dataInicialNoDb) && dataFinal.before(dataFinalNoDb)){

                    conflictComEmprestimoNoBd = true;

                } else if(dataInicial.after(dataInicialNoDb) && dataInicial.before(dataFinalNoDb)){

                    conflictComEmprestimoNoBd = true;

                } else if(dataInicialNoDb.after(dataInicial) && dataInicialNoDb.before(dataFinal)){

                    conflictComEmprestimoNoBd = true;

                } else if(dataFinalNoDb.after(dataInicial) && dataFinalNoDb.before(dataFinal)){

                    conflictComEmprestimoNoBd = true;

                }
            }
        }

        if(conflictComEmprestimoNoBd) return null;
        else return emprestimosRepository.save(emprestimos);
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
