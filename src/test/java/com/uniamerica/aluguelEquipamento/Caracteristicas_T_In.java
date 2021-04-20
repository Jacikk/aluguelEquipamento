package com.uniamerica.aluguelEquipamento;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.uniamerica.aluguelEquipamento.model.Caracteristicas;
import com.uniamerica.aluguelEquipamento.service.CaracteristicasService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class Caracteristicas_T_In {
    @Autowired
    private CaracteristicasService caracteristicasService;

    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void createTest() throws  Exception{
        Caracteristicas caracteristicas=new Caracteristicas();
        caracteristicas.setNome("TestInt2");

        caracteristicasService.createCaracteristica(caracteristicas);

        this.mockMvc.perform(MockMvcRequestBuilders

                .post("/caracteristicas/")
                .content(objectMapper.writeValueAsString(caracteristicas))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());


    }

    @Test
    void findAll() throws  Exception{
        Caracteristicas caracteristicas=new Caracteristicas();
        caracteristicas.setNome("TestInt2");

        caracteristicasService.createCaracteristica(caracteristicas);

        this.mockMvc.perform(MockMvcRequestBuilders

                .get("/caracteristicas/")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    void findName() throws  Exception{
        Caracteristicas caracteristicas=new Caracteristicas();
        caracteristicas.setNome("TestInt2");

        caracteristicasService.createCaracteristica(caracteristicas);

        this.mockMvc.perform(MockMvcRequestBuilders

                .get("/caracteristicas/nome/{nome}", "TesteInt2")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void update() throws  Exception{
        Caracteristicas caracteristicas=new Caracteristicas();
        caracteristicas.setNome("TestInt2");

        caracteristicasService.createCaracteristica(caracteristicas);

        this.mockMvc.perform(MockMvcRequestBuilders

            .post("/caracteristicas/{id}", 1)
            .content(objectMapper.writeValueAsString(caracteristicas))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());


    }

    @Test
    void delete() throws  Exception{

        Caracteristicas caracteristicas=new Caracteristicas();
        caracteristicas.setNome("TestInt3");

        caracteristicasService.createCaracteristica(caracteristicas);

        this.mockMvc.perform(MockMvcRequestBuilders

                .delete("/caracteristicas/{id}", 1))
                .andExpect(status().isAccepted());

    }

}