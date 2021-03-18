package com.uniamerica.aluguelEquipamento.service;

import com.uniamerica.aluguelEquipamento.model.Atendentes;
import com.uniamerica.aluguelEquipamento.repository.AtendentesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtendenteService {

    private final AtendentesRepository atendentesRepository;

    @Autowired
    public AtendenteService(AtendentesRepository atendentesRepository) {
        this.atendentesRepository = atendentesRepository;
    }

    public Atendentes createAtendente(Atendentes atendente) {
        return atendentesRepository.save(atendente);
    }
}
