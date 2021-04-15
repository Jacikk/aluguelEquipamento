package com.uniamerica.aluguelEquipamento.controller;

import com.uniamerica.aluguelEquipamento.service.ItensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itens")
public class ItensController {

    private final ItensService itensService;

    @Autowired
    public ItensController(ItensService itensService) {
        this.itensService = itensService;
    }
}
