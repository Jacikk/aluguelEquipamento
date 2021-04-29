package com.uniamerica.aluguelEquipamento.service;

import com.uniamerica.aluguelEquipamento.model.Emprestimos;
import com.uniamerica.aluguelEquipamento.model.Produtos;
import com.uniamerica.aluguelEquipamento.repository.EmprestimosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

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

    public List<Produtos> verificarPeriodo(Calendar inicio, Calendar fim) {

        List<Emprestimos> emprestimos = emprestimosRepository.findAll();
        List<Produtos> produtosEmprestadosNoPeriodo = new ArrayList<>();
        List<Produtos> produtosDisponiveis = produtosService.findAll();

        for (Emprestimos emprestimosAVerificar :emprestimos) {

            Calendar dataInicial = Calendar.getInstance();
            dataInicial.setTime(emprestimosAVerificar.getDataInicial());

            Calendar dataFinal = Calendar.getInstance();
            dataFinal.setTime(emprestimosAVerificar.getDataFinal());

            if(fim.equals(dataFinal) || inicio.equals(dataInicial)) {
                produtosEmprestadosNoPeriodo.add(emprestimosAVerificar.getProduto());
            }else if(fim.after(dataInicial) && fim.before(dataFinal)){
                produtosEmprestadosNoPeriodo.add(emprestimosAVerificar.getProduto());
            } else if(inicio.after(dataInicial) && inicio.before(dataFinal)){
                produtosEmprestadosNoPeriodo.add(emprestimosAVerificar.getProduto());
            } else if(dataInicial.after(inicio) && dataInicial.before(dataFinal)){
                produtosEmprestadosNoPeriodo.add(emprestimosAVerificar.getProduto());
            } else if(dataFinal.after(inicio) && dataFinal.before(fim)){
                produtosEmprestadosNoPeriodo.add(emprestimosAVerificar.getProduto());
            }

        }
            for (Produtos produtos : produtosEmprestadosNoPeriodo) {
            produtosDisponiveis.remove(produtos);
        }
            return produtosDisponiveis;
    }
}
