package com.uniamerica.aluguelEquipamento.controller;

import com.uniamerica.aluguelEquipamento.model.Emprestimos;
import com.uniamerica.aluguelEquipamento.model.VerificarPeriodo;
import com.uniamerica.aluguelEquipamento.service.EmprestimosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimosController {

    private final EmprestimosService emprestimosService;

    @Autowired
    public EmprestimosController(EmprestimosService emprestimosService) {
        this.emprestimosService = emprestimosService;
    }

    @PostMapping
    public ResponseEntity<?> createEmprestimo(@RequestBody Emprestimos emprestimo) throws Exception{
        try{
            Emprestimos saved = emprestimosService.create(emprestimo);
            return new ResponseEntity<>(saved, null, HttpStatus.CREATED);
        }
        catch (Exception ex){
            throw new Exception(ex);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmprestimo (@PathVariable Long id, @RequestBody Emprestimos emprestimo) throws Exception{
        try{
            Emprestimos found = emprestimosService.findById(id);
            if(found != null) {
                emprestimo.setId(id);
                return new ResponseEntity<>(emprestimosService.update(emprestimo), null, HttpStatus.OK);
            }
            else return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
        catch (Exception ex){
            throw new Exception(ex);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmprestimo(@PathVariable Long id) throws Exception {
        try{
            Emprestimos found = emprestimosService.findById(id);
            if(found != null) {
                emprestimosService.delete(id);
                return new ResponseEntity<>(null, null, HttpStatus.OK);
            }
            else return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
        catch (Exception ex){
            throw new Exception(ex);
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll () throws Exception{
        try{
            List<Emprestimos> found = emprestimosService.findAll();
            if(!found.isEmpty()) return new ResponseEntity<>(found, null, HttpStatus.OK);
            else return new ResponseEntity<>(found, null, HttpStatus.NO_CONTENT);
        }
        catch (Exception ex){
            throw new Exception(ex);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> findById (@PathVariable Long id) throws Exception {
        try{
            Emprestimos found = emprestimosService.findById(id);
            if(found != null) return new ResponseEntity<>(found, null, HttpStatus.OK);
            else return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
        catch (Exception ex){
            throw new Exception(ex);
        }
    }

    @GetMapping("/produto/{id}")
    public ResponseEntity<?> findByProduto (@PathVariable Long id) throws Exception{
        try{
            List<Emprestimos> found = emprestimosService.findByProduto(id);
            if(!found.isEmpty()) return new ResponseEntity<>(found, null, HttpStatus.OK);
            else return new ResponseEntity<>(found, null, HttpStatus.NO_CONTENT);
        }
        catch (Exception ex){
            throw new Exception(ex);
        }
    }

    @PostMapping("/Prazo/")
    @Deprecated
    public ResponseEntity<?> verificarPrazo (@RequestBody VerificarPeriodo verificarPeriodo) throws Exception{
        try{
            if(emprestimosService.verificarPeriodo(verificarPeriodo));
            return new ResponseEntity<>(null, null, HttpStatus.OK);

        }
        catch (Exception ex){
            throw new Exception(ex);
        }
    }
}

        /*
        try{

        }
        catch (Exception ex){
            throw new Exception(ex);
        }
        */
