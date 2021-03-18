package com.uniamerica.aluguelEquipamento.controller;

import com.uniamerica.aluguelEquipamento.model.Atendentes;
import com.uniamerica.aluguelEquipamento.service.AtendenteService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/atendentes")

public class AtendentesController {

    private AtendenteService atendentesService;

    @PostMapping
    public ResponseEntity<?> postAtendente(@RequestBody Atendentes atentende) throws Exception {
        try {
            Atendentes atendenteSaved = atendentesService.createAtendente(atentende);
            return new ResponseEntity<>(atendenteSaved, null, HttpStatus.CREATED);
        }
        catch (Exception exception) {
            throw new Exception(exception);
        }
    }
}
