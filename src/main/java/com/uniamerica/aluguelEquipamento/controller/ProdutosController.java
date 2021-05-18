package com.uniamerica.aluguelEquipamento.controller;

import com.uniamerica.aluguelEquipamento.model.Produtos;
import com.uniamerica.aluguelEquipamento.service.ProdutosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutosController {

    private final ProdutosService produtosService;

    @Autowired
    public ProdutosController(ProdutosService produtosService) {
        this.produtosService = produtosService;
    }

    @PostMapping
    public ResponseEntity<?> createProduto(@RequestBody Produtos produto) throws Exception {
        try {
            Produtos saved= produtosService.createProduto(produto);
            return new ResponseEntity<>(saved, null, HttpStatus.OK);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
    }
    //validar se produtos.caracteristicas esta dentro de itens.caracteristicas

    @GetMapping
    public ResponseEntity<?> findAll() throws Exception{
        try {
            List<Produtos> found = produtosService.findAll();
            if(!found.isEmpty()) return new ResponseEntity<>(found, null, HttpStatus.OK);
            else return new ResponseEntity<>(found, null, HttpStatus.NO_CONTENT);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) throws Exception{
        try {
            Produtos found= produtosService.findById(id);
            if(found != null) return new ResponseEntity<>(found, null, HttpStatus.OK);
            else return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<?> findAllByNome(@PathVariable String nome) throws Exception{
        try {
            List<Produtos> found= produtosService.findAllByNome(nome);
            if(!found.isEmpty()) return new ResponseEntity<>(found, null, HttpStatus.OK);
            else return new ResponseEntity<>(found, null, HttpStatus.NO_CONTENT);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
    }

    @GetMapping("/disponivel")
    public ResponseEntity<?> findAllDisponivel () throws Exception {
        try {
            List<Produtos> found= produtosService.findAllByDisponivel();
            if(!found.isEmpty()) return new ResponseEntity<>(found, null, HttpStatus.OK);
            else return new ResponseEntity<>(found, null, HttpStatus.NO_CONTENT);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Produtos produto) throws Exception {
        try {
            if(produtosService.findById(id) != null){
                produto.setId(id);
                Produtos saved = produtosService.update(produto);
                return new ResponseEntity<>(saved, null, HttpStatus.OK);
            }
            else return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {
        try {
            if(produtosService.findById(id) != null){
                produtosService.delete(id);
                return new ResponseEntity<>(null, null, HttpStatus.OK);
            }
            else return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
        catch(Exception ex){
            throw new Exception(ex);
        }
    }

    @GetMapping("/{dataInicial}/{dataFinal}")
    public ResponseEntity<?> verificarPrazo (@PathVariable @DateTimeFormat(pattern="yyyy-MM-dd") Date dataInicial,
                                             @PathVariable @DateTimeFormat(pattern="yyyy-MM-dd")Date dataFinal) throws Exception{
        try{

            List<Produtos> listaDeProdutosDisponiveis = produtosService.verificarProdutosNoPeriodo(dataInicial, dataFinal);
            if(!listaDeProdutosDisponiveis.isEmpty() ) return new ResponseEntity<>(listaDeProdutosDisponiveis, null , HttpStatus.OK);
            else return new ResponseEntity<>(listaDeProdutosDisponiveis, null , HttpStatus.NO_CONTENT);
        }
        catch (Exception ex){
            throw new Exception(ex);
        }
    }
}


        /*
        try {

        }
        catch(Exception ex){
            throw new Exception(ex);
        }
        */