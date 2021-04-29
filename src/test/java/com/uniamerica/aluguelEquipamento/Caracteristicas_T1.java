package com.uniamerica.aluguelEquipamento;

import com.uniamerica.aluguelEquipamento.model.Caracteristicas;

import com.uniamerica.aluguelEquipamento.service.CaracteristicasService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class Caracteristicas_T1 {

    @Autowired
    private CaracteristicasService caracteristicasService;

    @Test
    void createCaracteristicas(){

        Caracteristicas caracteristicas = new Caracteristicas();
        caracteristicas.setNome("Testando1");
        Caracteristicas usrSaved = caracteristicasService.createCaracteristica(caracteristicas);
        Assertions.assertEquals(caracteristicas, usrSaved);
    }

    @Test
    void findAll(){
        Caracteristicas caracteristicas = new Caracteristicas();
        caracteristicas.setNome("Testando2");

        Caracteristicas caracteristicas2 = new Caracteristicas();
        caracteristicas2.setNome("Testando3");

        caracteristicasService.createCaracteristica(caracteristicas);
        caracteristicasService.createCaracteristica(caracteristicas2);

        List<Caracteristicas> listCa = caracteristicasService.findAll();

        Assertions.assertEquals(2, listCa.size());

    }

    /*@Test
    void findByNome(){

        Caracteristicas caracteristicas = new Caracteristicas();
        caracteristicas.setNome("Testeandofindbynmae");

        caracteristicasService.createCaracteristica(caracteristicas);

        List<Caracteristicas> result = caracteristicasService.findByNome(caracteristicas.getNome());

        Assertions.assertEquals(1, result.size());
    }*/

}
