package com.uniamerica.aluguelEquipamento;

import antlr.build.Tool;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uniamerica.aluguelEquipamento.model.Atendentes;
import com.uniamerica.aluguelEquipamento.service.AtendentesService;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AtendentesTesteDeIntegracao {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AtendentesService atendentesService;

    @Test
    void createAtendente_Test() throws Exception {

        Atendentes atendente0 = new Atendentes();
        atendente0.setNome("Teste00");
        atendente0.setEmail("TesteEmail00");
        atendente0.setSenha("TesteSenha00");
        atendente0.setCpf("testeCpf00");
        atendente0.setRg("testeRg00");
        atendente0.setTelefone("telTeste00");

        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/atendentes")
                .content(objectMapper.writeValueAsString(atendente0))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void getAllAtendentes_Test() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/atendentes")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void getAllAtendenteByNome_Test() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/atendentes/nome/{nome}", "Teste00")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void alteraUsuairo_Test() throws Exception {

        //Optional<Usuario> userBd = usuarioService.findById(1);
        Atendentes atendenteBd = atendentesService.findById(1L);

        //userBd.ifPresent(usuario -> usuario.setNome("alterado"));
        if (atendenteBd != null) {
            atendenteBd.setNome("alterado");
        }
        this.mockMvc.perform( MockMvcRequestBuilders
                .put("/atendentes/{id}",1)
                .content(objectMapper.writeValueAsString(atendenteBd))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("alterado"));
    }
    @Test
    void deleteUsuario_Test() throws Exception {

        Atendentes atendente1 = new Atendentes();
        atendente1.setNome("Teste01");
        atendente1.setEmail("TesteEmail01");
        atendente1.setSenha("TesteSenha01");
        atendente1.setCpf("testeCpf01");
        atendente1.setRg("testeRg01");
        atendente1.setTelefone("telTeste01");

        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/atendentes")
                .content(objectMapper.writeValueAsString(atendente1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/atendentes/{id}", 1))
                .andExpect(status().isOk());
    }
}
