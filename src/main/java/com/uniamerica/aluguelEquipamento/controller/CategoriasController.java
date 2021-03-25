package com.uniamerica.aluguelEquipamento.controller;

import com.uniamerica.aluguelEquipamento.model.Categorias;
import com.uniamerica.aluguelEquipamento.service.CategoriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/categorias")
public class CategoriasController {

    @Autowired
    private CategoriasService categoriasService;

    @PostMapping
    public ResponseEntity<?> createCategorias(@RequestBody Categorias categorias) throws Exception{
        try {
            if(categoriasService.findByName(categorias.getName()) != null){
                return new ResponseEntity<>("Categoria já existe", null, HttpStatus.BAD_REQUEST);

            }else{
                Categorias categoriasSaved = categoriasService.createCategorias(categorias);
                return new ResponseEntity<>(categoriasSaved, null, HttpStatus.CREATED);
            }
        }catch(Exception exception){
            throw new Exception(exception);
        }
    }

    @GetMapping()
    public ResponseEntity<?> findAll() throws  Exception{
        try {
            List<Categorias> list = categoriasService.findAll();

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
            Categorias categorias= categoriasService.findById(id);
            if (categorias != null) return new ResponseEntity<>(categorias, null, HttpStatus.OK);
            else return new ResponseEntity<>(null, null, HttpStatus.NO_CONTENT);
        }catch(Exception exception){
            throw new Exception(exception);
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> findByName(@PathVariable String name) throws Exception{
        try{
            List<Categorias> categorias = categoriasService.findByName(name);
            if(!categorias .isEmpty()) return new ResponseEntity<>(categorias, null, HttpStatus.OK);
            else return new ResponseEntity<>(null, null, HttpStatus.NO_CONTENT);
        }catch (Exception exception){
            throw new Exception(exception);
        }
    }
}

