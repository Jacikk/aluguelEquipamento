package com.uniamerica.aluguelEquipamento.service;

import com.uniamerica.aluguelEquipamento.model.Clientes;
import com.uniamerica.aluguelEquipamento.model.Emprestimos;
import com.uniamerica.aluguelEquipamento.repository.ClientesRepository;
import com.uniamerica.aluguelEquipamento.repository.EmprestimosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmprestimosService {

    private final EmprestimosRepository emprestimosRepository;

    @Autowired
    public EmprestimosService(EmprestimosRepository emprestimosRepository) {
        this.emprestimosRepository = emprestimosRepository;
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

}
