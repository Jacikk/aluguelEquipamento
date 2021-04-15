package com.uniamerica.aluguelEquipamento;

import com.uniamerica.aluguelEquipamento.model.Atendentes;
import com.uniamerica.aluguelEquipamento.service.AtendentesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class AtendentesTesteUnitario {

    @Autowired
    private AtendentesService atendenteService;

    @Test
    void createAtendente () {
        Atendentes atendente0 = new Atendentes();
        atendente0.setNome("Teste00");
        atendente0.setEmail("TesteEmail00");
        atendente0.setSenha("TesteSenha00");
        atendente0.setCpf("testeCpf00");
        atendente0.setRg("testeRg00");
        atendente0.setTelefone("telTeste00");
        Atendentes resultDb = atendenteService.createAtendente(atendente0);
        Assertions.assertEquals(atendente0, resultDb);
    }

    @Test
    void findAll () {
        for (int i = 0; i < 2; i++ ){
            Atendentes atendente = new Atendentes();
            atendente.setNome("Teste0" + (i+1) );
            atendente.setEmail("TesteEmail0"+ (i+1) );
            atendente.setSenha("TesteSenha0"+ (i+1) );
            atendente.setCpf("testeCpf0"+ (i+1) );
            atendente.setRg("testeRg0"+ (i+1) );
            atendente.setTelefone("telTeste0"+ (i+1) );
            atendenteService.createAtendente(atendente);
        }
        List<Atendentes> expectedRead = atendenteService.findAll();
        System.out.println(expectedRead);
        Assertions.assertEquals(3, expectedRead.size());
    }

    @Test
    void findByID () {
        Atendentes atendente1 = new Atendentes();
        atendente1.setNome("Teste00");
        atendente1.setEmail("TesteEmail00");
        atendente1.setSenha("TesteSenha00");
        atendente1.setCpf("testeCpf00");
        atendente1.setRg("testeRg00");
        atendente1.setTelefone("telTeste00");

        Atendentes searchResult = atendenteService.findById(1L);
        Assertions.assertEquals(atendente1.getCpf(), searchResult.getCpf());

    }
}
