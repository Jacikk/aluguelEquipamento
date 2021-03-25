package com.uniamerica.aluguelEquipamento.controller;

import com.uniamerica.aluguelEquipamento.model.Atendentes;
import com.uniamerica.aluguelEquipamento.service.AtendentesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atendentes")
public class AtendentesController {

    @Autowired
    private AtendentesService atendentesService;

    @PostMapping
    public ResponseEntity<?> createAtendente(@RequestBody Atendentes atentende) throws Exception {
        try {

            if (atendentesService.findByRg(atentende.getRg()) != null) {
                return new ResponseEntity<>("Rg já cadastrado", null, HttpStatus.BAD_REQUEST);
            } else if (atendentesService.findByEmail(atentende.getEmail()) != null) {
                return new ResponseEntity<>("Email já cadastrado", null, HttpStatus.BAD_REQUEST);
            } else if (atendentesService.findByCpf(atentende.getCpf()) != null) {
                return new ResponseEntity<>("CPF já cadastrado", null, HttpStatus.BAD_REQUEST);
            } else {
                Atendentes atendenteSaved = atendentesService.createAtendente(atentende);
                return new ResponseEntity<>(atendenteSaved, null, HttpStatus.CREATED);
            }
        } catch (Exception exception) {
            throw new Exception(exception);
        }
    }

    @GetMapping()
    public ResponseEntity<?> findAll() throws Exception {
        try{
            List<Atendentes> list = atendentesService.findAll();

            if(!list.isEmpty()) {
                return new ResponseEntity<>(list,null,HttpStatus.OK);
            }
            return new ResponseEntity<>(list, null, HttpStatus.NO_CONTENT);

        } catch (Exception exception){

            throw new Exception(exception);

        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) throws Exception {

        try{
            Atendentes atendente = atendentesService.findById(id);
            if(atendente != null) return new ResponseEntity<>(atendente,null,HttpStatus.OK);
            else return new ResponseEntity<>(null,null,HttpStatus.NO_CONTENT);
        } catch(Exception exception){

            throw new Exception(exception);
        }
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<?> findByNome(@PathVariable String nome) throws Exception {

        try{
            List<Atendentes> atendentes = atendentesService.findAllByNome(nome);
            if(!atendentes.isEmpty()) return new ResponseEntity<>(atendentes, null, HttpStatus.OK);
            else return new ResponseEntity<>(null, null, HttpStatus.NO_CONTENT);
        } catch(Exception exception){
            throw new Exception(exception);
        }
    }

    @GetMapping("email/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable String email) throws Exception {

        try {
            Atendentes atendente = atendentesService.findByEmail(email);
            if(atendente != null) return new ResponseEntity<>(atendente, null, HttpStatus.OK);
            else return new ResponseEntity<>(null, null, HttpStatus.NO_CONTENT);

        } catch (Exception exception) {
            throw new Exception(exception);
        }
    }

    @GetMapping("cpf/{cpf}")
    public ResponseEntity<?> findByCpf(@PathVariable String cpf) throws Exception {
        try{
            Atendentes atendente = atendentesService.findByCpf(cpf);
            if(atendente != null) return new ResponseEntity<>(atendente, null, HttpStatus.OK);
            else return new ResponseEntity<>(null, null, HttpStatus.NO_CONTENT);

        } catch (Exception exception) {
            throw new Exception(exception);
        }
    }

    @GetMapping("rg/{rg}")
    public ResponseEntity<?> findByRg (@PathVariable String rg) throws Exception {
        try{
            Atendentes atendente = atendentesService.findByRg(rg);

            if(atendente != null) return new ResponseEntity<>(atendente, null, HttpStatus.OK);
            else return new ResponseEntity<>(null, null, HttpStatus.NO_CONTENT);

        } catch (Exception exception) {
            throw new Exception(exception);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update (@PathVariable Long id, @RequestBody Atendentes atendente) throws Exception {
        try {
            atendente.setId(id);
            return new ResponseEntity<>(atendentesService.update(atendente), null, HttpStatus.OK);
        }
        catch(Exception ex) {
            throw new Exception(ex);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception{
        try {
            atendentesService.delete(id);
            return new ResponseEntity<>(null, null, HttpStatus.OK);
        }
        catch(Exception ex) {
            throw new Exception(ex);
        }
    }
}
