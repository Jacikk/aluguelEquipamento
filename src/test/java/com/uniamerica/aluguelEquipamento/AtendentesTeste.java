package com.uniamerica.aluguelEquipamento;

import com.uniamerica.aluguelEquipamento.model.Atendentes;
import com.uniamerica.aluguelEquipamento.service.AtendentesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class AtendentesTeste {

    @Autowired
    private AtendentesService atendenteService;

    @Test
    void createAtendente () {
        Atendentes atendente1 = new Atendentes();
        atendente1.setNome("Teste01");
        atendente1.setEmail("TesteEmail01");
        atendente1.setSenha("TesteSenha01");
        atendente1.setCpf("testeCpf01");
        atendente1.setRg("testeRg01");
        atendente1.setTelefone("telTeste01");
        Atendentes resultDb = atendenteService.createAtendente(atendente1);
        Assertions.assertEquals(atendente1, resultDb);
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
        List<Atendentes> expectedRead = atendenteService.findAll();
        System.out.println(expectedRead);
        Assertions.assertEquals(3, expectedRead.size());
    }

    @Test
    void findByID () {
        Atendentes atendente1 = new Atendentes();
        atendente1.setNome("Teste01");
        atendente1.setEmail("TesteEmail01");
        atendente1.setSenha("TesteSenha01");
        atendente1.setCpf("testeCpf01");
        atendente1.setRg("testeRg01");
        atendente1.setTelefone("telTeste01");

        Atendentes searchResult = atendenteService.findById(4L);
        Assertions.assertEquals(atendente1, searchResult);

    }
}
