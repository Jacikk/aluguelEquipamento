package com.uniamerica.aluguelEquipamento.controller;

import com.uniamerica.aluguelEquipamento.model.Atendentes;
import com.uniamerica.aluguelEquipamento.service.AtendenteService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atendentes")

public class AtendentesController {

    @Autowired
    private AtendenteService atendentesService;

    @PostMapping
    public ResponseEntity<?> createAtendente(@RequestBody Atendentes atentende) throws Exception {
        try {
            Atendentes atendenteSaved = atendentesService.createAtendente(atentende);
            return new ResponseEntity<>(atendenteSaved, null, HttpStatus.CREATED);
        }
        catch (Exception exception) {
            throw new Exception(exception);
        }
    }

    @GetMapping("/{nome}")
    public ResponseEntity<?> listaPorNome(@PathVariable String nome) throws Exception {
        try{
            List<Atendentes> list = atendentesService.getByName(nome);

            if(!list.isEmpty()) {
                return new ResponseEntity<>(list, null, HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(list,null,HttpStatus.FOUND);
        }
        catch (Exception exception){

            throw new Exception(exception);

        }

    }



}
