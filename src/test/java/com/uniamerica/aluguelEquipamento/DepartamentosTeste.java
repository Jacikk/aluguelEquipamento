package com.uniamerica.aluguelEquipamento;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uniamerica.aluguelEquipamento.model.Departamentos;
import com.uniamerica.aluguelEquipamento.service.DepartamentosService;
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
public class DepartamentosTeste {

        @Autowired
        private DepartamentosService departamentosService;

    @Autowired
    private MockMvc mockMvc;

    @Test
        void createDepartamentos () {
            Departamentos departamentos = new Departamentos();
            departamentos.setNome("Thomas");

            Departamentos resultDb = departamentosService.createDepartamentos(departamentos);
            Assertions.assertEquals(departamentos, resultDb);
        }

    @Autowired
    //utilizado para realizar o parse do objeto para json
    private ObjectMapper objectMapper;

    @Test
    void insereDepartamentos_Test() throws Exception {

        Departamentos departamentos = new Departamentos();
        departamentos.setNome("Thomas");

        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/departamentos")
                .content(objectMapper.writeValueAsString(departamentos))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void listaTodosDepartamentos_Test() throws Exception {

        //DESCOMENTAR LINHAS ABAIXO CASO QUERIA RODAR O TESTE INDIVIDUALMENTE
//        Usuario usuario = new Usuario();
//        usuario.setSexo(SexoEnum.Masculino);
//        usuario.setEmail("asdasd@gmail.com");
//        usuario.setTelefone("12121212");
//        usuario.setNome("user1");
//        usuarioService.insereUsuario(usuario);

        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/departamentos")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }


    @Test
    void getDepartamentosByNome() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/departamentos/nome/{nome}", "user1")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void deleteDepartamentos() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/departamentos/{id}", 1))
                .andExpect(status().isAccepted());
    }

    @Test
    void updateDepartamentos() throws Exception {

        Optional<Departamentos> userBd = departamentosService.findById(1);

        userBd.ifPresent(departamentos -> departamentos.setNome("Thomas"));

        this.mockMvc.perform( MockMvcRequestBuilders
                .put("/departamentos/{id}",1)
                .content(objectMapper.writeValueAsString(userBd.get()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Thomas"));
    }



}
