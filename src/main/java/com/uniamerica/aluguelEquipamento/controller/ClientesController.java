package com.uniamerica.aluguelEquipamento.controller;
import com.uniamerica.aluguelEquipamento.model.Clientes;
import com.uniamerica.aluguelEquipamento.service.ClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClientesController {

    @Autowired
    private ClientesService clientesService;

    @GetMapping(path = "/nome/{nome}")
    public ResponseEntity<?> findByEmail(@PathVariable String nome) {
        List<Clientes> list = clientesService.findByNome(nome);

        if(!list.isEmpty()){
            return new ResponseEntity<>(list, null, HttpStatus.OK);
        }
        return new ResponseEntity<>(list, null, HttpStatus.NO_CONTENT);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Clientes> findById(@PathVariable Long id) {
        return ResponseEntity.ok(clientesService.findById(id));
    }
    @GetMapping()
    public ResponseEntity<?> findAll() throws Exception {
        try{
            List<Clientes> list = clientesService.findAll();

            if(!list.isEmpty()) {
                return new ResponseEntity<>(list,null,HttpStatus.OK);
            }
            return new ResponseEntity<>(list, null, HttpStatus.NO_CONTENT);

        } catch (Exception exception){

            throw new Exception(exception);

        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Clientes clientes) throws Exception{
        if (clientesService.findByRg(clientes.getRg()) != null) {
            return new ResponseEntity<>("Rg já cadastrado", null, HttpStatus.BAD_REQUEST);
        }else if (clientesService.findByCpf(clientes.getCpf()) != null) {
            return new ResponseEntity<>("CPF já cadastrado", null, HttpStatus.BAD_REQUEST);
        } else {
            Clientes clientesToSave = clientesService.create(clientes);
            return new ResponseEntity<>(clientesToSave, null, HttpStatus.CREATED);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {

        Clientes clientes1 = clientesService.findById(id);

        if(clientes1 != null){
            clientesService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Clientes> replace(@RequestBody Clientes clientes,
                                            @PathVariable long id) {
        Clientes clientes1 = clientesService.findById(id);

        if(clientes1 != null){
            clientesService.update(clientes, id);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
