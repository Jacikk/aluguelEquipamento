package com.uniamerica.aluguelEquipamento.controller;

import com.uniamerica.aluguelEquipamento.model.Atendentes;
import com.uniamerica.aluguelEquipamento.service.AtendentesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atendentes")

public class AtendentesController {

    @Autowired
    private AtendentesService atendentesService;

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

    @GetMapping()
    public ResponseEntity<?> findAll() throws Exception {
        try{
            List<Atendentes> list = atendentesService.findAll();

            if(!list.isEmpty()) {
                return new ResponseEntity<>(list,null,HttpStatus.FOUND);
            }
            return new ResponseEntity<>(list, null, HttpStatus.NO_CONTENT);

        }
        catch (Exception exception){

            throw new Exception(exception);

        }
    }

    /*@GetMapping("id/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){

        Atendentes atendente = atendentesService.findById(id);

    }
*/
}
