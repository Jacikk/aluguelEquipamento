package com.uniamerica.aluguelEquipamento.controller;


import com.uniamerica.aluguelEquipamento.model.Clientes;
import com.uniamerica.aluguelEquipamento.service.ClientesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClientesController {
    private ClientesService clientesService;

    public ClientesController(ClientesService clientesService){
        this.clientesService = clientesService;
    }

    @GetMapping(path = "/{nome}")
    public ResponseEntity<Clientes> findByEmail(@PathVariable String nome) {
        return ResponseEntity.ok(clientesService.findByName(nome));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Clientes> findById(@PathVariable Long id) {
        return ResponseEntity.ok(clientesService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Clientes> save(@RequestBody Clientes clientes) {
        return new ResponseEntity<>(clientesService.create(clientes), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientesService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Clientes> replace(@RequestBody Clientes clientes,
                                            @PathVariable Long id) {
        clientesService.update(clientes, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
