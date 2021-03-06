package com.uniamerica.aluguelEquipamento.service;

import com.uniamerica.aluguelEquipamento.model.Atendentes;
import com.uniamerica.aluguelEquipamento.repository.AtendentesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtendentesService {

    private final AtendentesRepository atendentesRepository;

    @Autowired
    public AtendentesService(AtendentesRepository atendentesRepository) {
        this.atendentesRepository = atendentesRepository;
    }

    public Atendentes createAtendente(Atendentes atendente) {
        return atendentesRepository.save(atendente);
    }

    public List<Atendentes> findAll() {
        return atendentesRepository.findAll();
    }

    public List<Atendentes> findAllByNome(String nome) {
        return atendentesRepository.findAllByNome(nome);
    }

    public Atendentes findById(Long id) {
        Optional<Atendentes> atendente = atendentesRepository.findById(id);

        if(atendente.isPresent()){
            return atendente.get();
        }
        else {
            return null;
        }
    }

    public Atendentes findByEmail(String email) {
        return atendentesRepository.findByEmail(email);
    }

    public Atendentes findByCpf(String cpf) {
        return atendentesRepository.findByCpf(cpf);
    }

    public Atendentes findByRg(String rg) {
        return atendentesRepository.findByRg(rg);
    }

    public Atendentes update(Atendentes atendente) {
        return atendentesRepository.save(atendente);
    }

    public void delete(Long id) {
        atendentesRepository.deleteById(id);
    }
}
