package com.uniamerica.aluguelEquipamento.controller;

import com.uniamerica.aluguelEquipamento.model.Itens;
import com.uniamerica.aluguelEquipamento.service.ItensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping
    public ResponseEntity<?> createItem(@RequestBody Itens item) throws Exception {
        try{
            Itens itemSaved = itensService.createItem(item);
            return new ResponseEntity<>(itemSaved, null, HttpStatus.CREATED);
        }
        catch(Exception ex) {
            throw new Exception(ex);
        }
    }

}
