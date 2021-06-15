package com.uniamerica.aluguelEquipamento.service;

import com.uniamerica.aluguelEquipamento.model.Itens;
import com.uniamerica.aluguelEquipamento.repository.ItensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItensService {

    private final ItensRepository itensRepository;

    @Autowired
    public ItensService(ItensRepository itensRepository) {
        this.itensRepository = itensRepository;
    }

    public Itens createItem(Itens item) {
        return itensRepository.save(item);
    }

    public List<Itens> findAll() {
        return itensRepository.findAll();
    }

    public Itens findById(Long id) {
        Optional<Itens> item = itensRepository.findById(id);

        if(item.isPresent()) return item.get();
        else return null;
    }

    public List<Itens> findByNome(String nome) {
        return itensRepository.findByNome(nome);
    }

    public List<Itens> findAllByDepartamento(String departamento) {
        return itensRepository.findAllByDepartamento(departamento);
    }

    public Itens update(Itens item) {
        return itensRepository.save(item);
    }

    public void delete(Long id) {
        itensRepository.deleteById(id);
    }

    public List<Itens> findAllByNomeContains(String nome) {
        return itensRepository.findAllByNomeContains(nome);
    }
}
