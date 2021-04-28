package com.uniamerica.aluguelEquipamento.controller;

import com.uniamerica.aluguelEquipamento.model.Emprestimos;
import com.uniamerica.aluguelEquipamento.model.Produtos;
import com.uniamerica.aluguelEquipamento.service.EmprestimosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
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

            if(saved != null) return new ResponseEntity<>(saved, null, HttpStatus.CREATED);
            else return new ResponseEntity<>("Emprestimo não disponivel na data ou conflito com bando de dados", null, HttpStatus.BAD_REQUEST);
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

    @PostMapping("/{dataInicial}/{dataFinal}")
    @Deprecated
    public ResponseEntity<?> verificarPrazo (@PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Date dataInicio,
                                             @PathVariable @DateTimeFormat(pattern="yyyy-MM-dd")Date dataFinal) throws Exception{
        try{
            Calendar inicio = Calendar.getInstance();
            inicio.setTime(dataInicio);

            Calendar fim= Calendar.getInstance();
            fim.setTime(dataFinal);

            List<Produtos> listaDeProdutosDisponiveis = emprestimosService.verificarPeriodo(inicio, fim);
            if(!listaDeProdutosDisponiveis.isEmpty() ) return new ResponseEntity<>(listaDeProdutosDisponiveis, null , HttpStatus.OK);
            else return new ResponseEntity<>(listaDeProdutosDisponiveis, null , HttpStatus.NO_CONTENT);
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