package com.uniamerica.aluguelEquipamento.controller;

import com.uniamerica.aluguelEquipamento.model.Itens;
import com.uniamerica.aluguelEquipamento.service.ItensService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<?> findAll() throws Exception {
        try{
            List<Itens> itensFound = itensService.findAll();
            if(!itensFound.isEmpty()) return new ResponseEntity<>(itensFound, null, HttpStatus.OK);
            else return new ResponseEntity<>(itensFound, null, HttpStatus.NO_CONTENT);
        }
        catch (Exception ex){
            throw new Exception(ex);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) throws Exception {
        try{
            Itens itemFound = itensService.findById(id);
            if(itemFound != null) return new ResponseEntity<>(itemFound, null, HttpStatus.OK);
            else return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
        catch (Exception ex){
            throw new Exception(ex);
        }
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<?> findByNome(@PathVariable String nome) throws Exception {
        try{
            Itens itemFound = itensService.findByNome(nome);
            if(itemFound != null) return new ResponseEntity<>(itemFound, null, HttpStatus.OK);
            else return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
        catch (Exception ex){
            throw new Exception(ex);
        }
    }

    @GetMapping("/departamento/{departamento}")
    public ResponseEntity<?> findByDepartamento(@PathVariable String departamento) throws Exception {
        try{
            List<Itens> itensPorDepartamento = itensService.findAllByDepartamento(departamento);
            if(!itensPorDepartamento.isEmpty()) return new ResponseEntity<>(itensPorDepartamento, null, HttpStatus.OK);
            else return new ResponseEntity<>(itensPorDepartamento, null, HttpStatus.NO_CONTENT);
        }
        catch (Exception ex){
            throw new Exception(ex);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Itens item) throws Exception {
        try{
            if(itensService.findById(id) != null) {
                item.setId(id);
                return new ResponseEntity<>(itensService.update(item), null, HttpStatus.OK);
            }
            else return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
        catch (Exception ex){
            throw new Exception(ex);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception{
        try{
            if(itensService.findById(id) != null) {
                itensService.delete(id);
                return new ResponseEntity<>(null, null, HttpStatus.OK);
            }
            else return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
        catch (Exception ex){
            throw new Exception(ex);
        }
    }
}
