package com.uniamerica.aluguelEquipamento.controller;

import com.uniamerica.aluguelEquipamento.service.DepartamentosService;
import com.uniamerica.aluguelEquipamento.model.Departamentos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departamentos")
public class DepartamentosController {

    private final DepartamentosService departamentosService;

    @Autowired
    public DepartamentosController(DepartamentosService departamentosService){
        this.departamentosService = departamentosService;
    }

    @PostMapping
    public ResponseEntity<?> insereDepartamentos(@RequestBody Departamentos departamentos) throws Exception {
        try {
            Departamentos departamentosSaved = departamentosService.createDepartamentos(departamentos);
            return new ResponseEntity<>(departamentosSaved, null, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @GetMapping
    public ResponseEntity<?> listaTodos(){
        List<Departamentos> departamentosList = departamentosService.listaTodos();

        if(!departamentosList.isEmpty()){
            return new ResponseEntity<>(departamentosList, null, HttpStatus.OK);

        }
        return new ResponseEntity<>(departamentosList, null, HttpStatus.NO_CONTENT);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) throws Exception {

        try{
            Departamentos departamento = departamentosService.getById(id);
            if(departamento != null) return new ResponseEntity<>(departamento, null, HttpStatus.OK);
            else return new ResponseEntity<>(null, null, HttpStatus.NO_CONTENT);
        }
        catch (Exception ex){
            throw new Exception(ex);
        }

    }

    @GetMapping("nome/{nome}")
    public ResponseEntity<?> listaPorNome(@PathVariable String nome){

        List<Departamentos> list = departamentosService.getByName(nome);

        if(!list.isEmpty()){
            return new ResponseEntity<>(list, null, HttpStatus.OK);
        }
        return new ResponseEntity<>(list, null, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update (@PathVariable Long id, @RequestBody Departamentos departamentos) throws Exception {
        Optional<Departamentos> user = departamentosService.findById(id);

        Departamentos result = null;

        if(user.isPresent()){
            result = departamentosService.insereDepartamentos(departamentos);
            return new ResponseEntity<>(result,null,HttpStatus.OK);
        }

        return new ResponseEntity<>(result,null,HttpStatus.CONFLICT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception{
        try {
            departamentosService.delete(id);
            return new ResponseEntity<>(null, null, HttpStatus.OK);
        }
        catch(Exception ex) {
            throw new Exception(ex);
        }
    }






}
