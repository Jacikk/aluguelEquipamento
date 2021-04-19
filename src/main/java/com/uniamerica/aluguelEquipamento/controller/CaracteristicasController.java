package com.uniamerica.aluguelEquipamento.controller;

import com.uniamerica.aluguelEquipamento.model.Caracteristicas;
import com.uniamerica.aluguelEquipamento.service.CaracteristicasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/caracteristicas")
public class CaracteristicasController {

    private final CaracteristicasService caracteristicasService;

    @Autowired
    public CaracteristicasController(CaracteristicasService caracteristicasService) {
        this.caracteristicasService = caracteristicasService;
    }

    @PostMapping
    public ResponseEntity<?> createCaracteristica(@RequestBody Caracteristicas caracteristica) throws Exception {
        try{
            Caracteristicas caracteristicasSaved = caracteristicasService.createCaracteristica(caracteristica);
            return new ResponseEntity<>(caracteristicasSaved, null, HttpStatus.CREATED);
        }
        catch (Exception ex){
            throw new Exception(ex);
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll () throws Exception {
        try{
            List<Caracteristicas> allCaracteristicas = caracteristicasService.findAll();
            if(!allCaracteristicas.isEmpty()) {
                return new ResponseEntity<>(allCaracteristicas, null, HttpStatus.OK);
            }
            else return new ResponseEntity<>(allCaracteristicas, null, HttpStatus.NO_CONTENT);
        }
        catch (Exception ex){
            throw new Exception(ex);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) throws Exception{
        try{
            Caracteristicas carFound = caracteristicasService.findById(id);
            if(carFound != null) return new ResponseEntity<>(carFound, null, HttpStatus.OK);
            else return new ResponseEntity<>(carFound, null, HttpStatus.NOT_FOUND);
        }
        catch (Exception ex){
            throw new Exception(ex);
        }
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<?> findByNome(@PathVariable String nome) throws Exception{
        try{
            Caracteristicas carFound = caracteristicasService.findByNome(nome);
            if(carFound != null) return new ResponseEntity<>(carFound, null, HttpStatus.OK);
            else return new ResponseEntity<>(carFound, null, HttpStatus.NOT_FOUND);
        }
        catch (Exception ex){
            throw new Exception(ex);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Caracteristicas caracteristica) throws Exception{
        try{
            if(caracteristicasService.findById(id) != null) {
                caracteristica.setId(id);
                return new ResponseEntity<>(caracteristicasService.update(caracteristica), null, HttpStatus.OK);
            }
            else return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
        catch (Exception ex){
            throw new Exception(ex);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {
        try{
            if(caracteristicasService.findById(id) != null) {
                caracteristicasService.delete(id);
                return new ResponseEntity<>(null, null, HttpStatus.OK);
            }
            else return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
        catch (Exception ex){
            throw new Exception(ex);
        }
    }
}
