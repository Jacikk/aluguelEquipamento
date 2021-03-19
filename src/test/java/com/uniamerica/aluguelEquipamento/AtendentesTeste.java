package com.uniamerica.aluguelEquipamento;

import com.uniamerica.aluguelEquipamento.model.Atendentes;
import com.uniamerica.aluguelEquipamento.service.AtendentesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class AtendentesTeste {

    @Autowired
    private AtendentesService atendenteService;


    @Test
    void createAtendente () {
        Atendentes atendente = new Atendentes();
        atendente.setNome("Teste01");
        atendente.setEmail("TesteEmail01");
        atendente.setSenha("TesteSenha01");
        atendente.setCpf("testeCpf01");
        atendente.setRg("testeRg01");
        atendente.setTelefone("telTeste01");
        Atendentes resultDb = atendenteService.createAtendente(atendente);
        Assertions.assertEquals(atendente, resultDb);
    }

    @Test
    void findAll () {
        for (int i = 0; i < 2; i++ ){
            Atendentes atendente = new Atendentes();
            atendente.setNome("Teste0" + (i+1));
            atendente.setEmail("TesteEmail01");
            atendente.setSenha("TesteSenha01");
            atendente.setCpf("testeCpf01");
            atendente.setRg("testeRg01");
            atendente.setTelefone("telTeste01");
            atendenteService.createAtendente(atendente);
        }
        List expectedRead = atendenteService.findAll();
        System.out.println(expectedRead);
        Assertions.assertEquals(3, expectedRead.size());
    }
}
