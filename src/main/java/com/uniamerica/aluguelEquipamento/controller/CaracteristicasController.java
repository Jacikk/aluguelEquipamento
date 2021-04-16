package com.uniamerica.aluguelEquipamento.controller;

import com.uniamerica.aluguelEquipamento.model.Caracteristicas;
import com.uniamerica.aluguelEquipamento.service.CaracteristicasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/categorias")
public class CaracteristicasController {

    @Autowired
    private CaracteristicasService caracteristicasService;

    @PostMapping
    public ResponseEntity<?> createCategorias(@RequestBody Caracteristicas caracteristicas) throws Exception{
        try {
            if(caracteristicasService.findByName(caracteristicas.getName()) != null){
                return new ResponseEntity<>("Categoria já existe", null, HttpStatus.BAD_REQUEST);

            }else{
                Caracteristicas caracteristicasSaved = caracteristicasService.createCategorias(caracteristicas);
                return new ResponseEntity<>(caracteristicasSaved, null, HttpStatus.CREATED);
            }
        }catch(Exception exception){
            throw new Exception(exception);
        }
    }

    @GetMapping()
    public ResponseEntity<?> findAll() throws  Exception{
        try {
            List<Caracteristicas> list = caracteristicasService.findAll();

            if(!list.isEmpty()){
                return  new ResponseEntity<>(list,null, HttpStatus.OK);
            }
            return new ResponseEntity<>(list, null, HttpStatus.NO_CONTENT);

        }catch (Exception exception){
            throw new Exception(exception);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) throws Exception{
        try {
            Caracteristicas caracteristicas = caracteristicasService.findById(id);
            if (caracteristicas != null) return new ResponseEntity<>(caracteristicas, null, HttpStatus.OK);
            else return new ResponseEntity<>(null, null, HttpStatus.NO_CONTENT);
        }catch(Exception exception){
            throw new Exception(exception);
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name) throws Exception{
        try{
            List<Caracteristicas> categorias = caracteristicasService.findByName(name);
            if(!categorias .isEmpty()) return new ResponseEntity<>(categorias, null, HttpStatus.OK);
            else return new ResponseEntity<>(null, null, HttpStatus.NO_CONTENT);
        }catch (Exception exception){
            throw new Exception(exception);
        }
    }
}

