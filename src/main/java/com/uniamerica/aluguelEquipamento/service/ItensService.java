package com.uniamerica.aluguelEquipamento.service;

import com.uniamerica.aluguelEquipamento.model.Itens;
import com.uniamerica.aluguelEquipamento.repository.ItensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
