package com.uniamerica.aluguelEquipamento;

import com.uniamerica.aluguelEquipamento.model.Atendentes;
import com.uniamerica.aluguelEquipamento.service.AtendenteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AtendentesTeste {

    @Autowired
    private AtendenteService atendenteService;

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

}
