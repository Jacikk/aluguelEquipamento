package com.uniamerica.aluguelEquipamento;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.uniamerica.aluguelEquipamento.model.Clientes;
import com.uniamerica.aluguelEquipamento.service.ClientesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientesTesteDeIntegracao {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ClientesService clientesService;


    @Test
    void createCliente_Test() throws Exception{

        Clientes clientes = new Clientes();
        clientes.setNome("Lucas");
        clientes.setRg("12345678");
        clientes.setCpf("12345901");
        clientes.setTelefone("987654321");
        clientes.setEndereco("ssssss");

        this.mockMvc.perform(MockMvcRequestBuilders

                .post("/clientes")
                .content(objectMapper.writeValueAsString(clientes))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
    @Test
    void findAllCliente_Test() throws Exception{

        Clientes clientes = new Clientes();
        clientes.setNome("Lucas");
        clientes.setRg("12345678");
        clientes.setCpf("12345901");
        clientes.setTelefone("987654321");
        clientes.setEndereco("ssssss");
        clientesService.create(clientes);
        this.mockMvc.perform(MockMvcRequestBuilders

                .get("/clientes")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    void findByNameCliente_Test() throws Exception{

        Clientes clientes = new Clientes();
        clientes.setNome("Lucas");
        clientes.setRg("12345678");
        clientes.setCpf("12345901");
        clientes.setTelefone("987654321");
        clientes.setEndereco("ssssss");

        clientesService.create(clientes);

        this.mockMvc.perform(MockMvcRequestBuilders

                .get("/clientes/nome/{nome}","Lucas")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    void deleteCliente_Test() throws Exception{

        Clientes clientes = new Clientes();
        clientes.setNome("Lucas");
        clientes.setRg("12345678");
        clientes.setCpf("12345901");
        clientes.setTelefone("987654321");
        clientes.setEndereco("ssssss");
        clientesService.create(clientes);

        this.mockMvc.perform(MockMvcRequestBuilders

                .delete("/clientes/{id}",1))
                .andExpect(status().isAccepted());
    }

    @Test
    void putCliente_Test() throws Exception{

        Clientes clientes = new Clientes();
        clientes.setNome("Lucas");
        clientes.setRg("12345678");
        clientes.setCpf("12345901");
        clientes.setTelefone("987654321");
        clientes.setEndereco("ssssss");
        clientesService.create(clientes);
        this.mockMvc.perform(MockMvcRequestBuilders
                .put("/clientes/{id}", 1)
                .content(objectMapper.writeValueAsString(clientes))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
