package com.uniamerica.aluguelEquipamento;

import com.uniamerica.aluguelEquipamento.model.Clientes;
import com.uniamerica.aluguelEquipamento.service.ClientesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ClientesTeste {

    @Autowired
    private ClientesService clientesService;

    @Test
    void createCliente(){
        Clientes clientes = new Clientes();
        clientes.setNome("Lucas");
        clientes.setRg("12345678");
        clientes.setCpf("12345678901");
        clientes.setTelefone("987654321");
        clientes.setEndereco("ssssss");

        Clientes clientesToSave = clientesService.create(clientes);

        Assertions.assertEquals(clientes, clientesToSave);
    }

    @Test
    void findAll(){
        Clientes clientes = new Clientes();
        clientes.setNome("Lucas");
        clientes.setRg("12345678");
        clientes.setCpf("12345901");
        clientes.setTelefone("987654321");
        clientes.setEndereco("ssssss");

        Clientes clientes2 = new Clientes();
        clientes2.setNome("Timm");
        clientes2.setRg("87654321");
        clientes2.setCpf("09876543212");
        clientes2.setTelefone("987654321");
        clientes2.setEndereco("zzzzzzz");

        clientesService.create(clientes);
        clientesService.create(clientes2);

        List<Clientes> lista = clientesService.findAll();

        Assertions.assertEquals(2, lista.size());
    }
    @Test
    void getByName(){
        Clientes clientes = new Clientes();
        clientes.setNome("Lucas");
        clientes.setRg("12345678");
        clientes.setCpf("12345678901");
        clientes.setTelefone("987654321");
        clientes.setEndereco("ssssss");

        clientesService.create(clientes);

        List<Clientes> result = clientesService.findByNome(clientes.getNome());

        Assertions.assertEquals(1, result.size());
    }

}
