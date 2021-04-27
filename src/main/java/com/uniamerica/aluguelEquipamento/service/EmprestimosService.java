package com.uniamerica.aluguelEquipamento.service;

import com.uniamerica.aluguelEquipamento.model.Emprestimos;
import com.uniamerica.aluguelEquipamento.model.Produtos;
import com.uniamerica.aluguelEquipamento.model.VerificarPeriodo;
import com.uniamerica.aluguelEquipamento.repository.EmprestimosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return emprestimosRepository.save(emprestimos);
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

    public boolean verificarPeriodo(VerificarPeriodo verificarPeriodo) {

        Calendar verificarInicio = Calendar.getInstance();
        verificarInicio.setTime(verificarPeriodo.getDataInicial());

        Calendar verificarFinal = Calendar.getInstance();
        verificarFinal.setTime(verificarPeriodo.getDataFinal());

        List<Emprestimos> ListaDeEmprestimos = findByProduto(verificarPeriodo.getProdutoId());

        boolean conflitoDePeriodos = false;

        for (Emprestimos emprestimo : ListaDeEmprestimos) {

            Calendar emprestimoInicio = Calendar.getInstance();
            emprestimoInicio.setTime(emprestimo.getDataInicial());

            Calendar emprestimoFinal = Calendar.getInstance();
            emprestimoFinal.setTime(emprestimo.getDataFinal());

            if (verificarInicio.after(emprestimoInicio) && verificarInicio.before(emprestimoFinal)) {
                conflitoDePeriodos = true;
            }
            if (verificarFinal.after(emprestimoInicio) && verificarFinal.before(emprestimoFinal)) {
                conflitoDePeriodos = true;
            }
            if (emprestimoInicio.after(verificarInicio) && emprestimoFinal.before(verificarFinal)) {
                conflitoDePeriodos = true;
            }
            if (emprestimoFinal.after(verificarInicio) && emprestimoFinal.before(verificarFinal)) {
                conflitoDePeriodos = true;
            }
        }
        if (!conflitoDePeriodos) return true;
        else return false;
    }
}
