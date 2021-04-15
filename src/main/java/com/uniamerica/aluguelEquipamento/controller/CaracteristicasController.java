package com.uniamerica.aluguelEquipamento.controller;

import com.uniamerica.aluguelEquipamento.model.Categorias;
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
    public ResponseEntity<?> createCategorias(@RequestBody Categorias categorias) throws Exception{
        try {
            if(caracteristicasService.findByName(categorias.getName()) != null){
                return new ResponseEntity<>("Categoria já existe", null, HttpStatus.BAD_REQUEST);

            }else{
                Categorias categoriasSaved = caracteristicasService.createCategorias(categorias);
                return new ResponseEntity<>(categoriasSaved, null, HttpStatus.CREATED);
            }
        }catch(Exception exception){
            throw new Exception(exception);
        }
    }

    @GetMapping()
    public ResponseEntity<?> findAll() throws  Exception{
        try {
            List<Categorias> list = caracteristicasService.findAll();

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
            Categorias categorias= caracteristicasService.findById(id);
            if (categorias != null) return new ResponseEntity<>(categorias, null, HttpStatus.OK);
            else return new ResponseEntity<>(null, null, HttpStatus.NO_CONTENT);
        }catch(Exception exception){
            throw new Exception(exception);
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name) throws Exception{
        try{
            List<Categorias> categorias = caracteristicasService.findByName(name);
            if(!categorias .isEmpty()) return new ResponseEntity<>(categorias, null, HttpStatus.OK);
            else return new ResponseEntity<>(null, null, HttpStatus.NO_CONTENT);
        }catch (Exception exception){
            throw new Exception(exception);
        }
    }
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        caracteristicasService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categorias> replace(@RequestBody Categorias categoria,
                                              @PathVariable Long id) {
        categoria.setId(id);
        caracteristicasService.update(categoria);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

