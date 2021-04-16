package com.uniamerica.aluguelEquipamento;

import com.uniamerica.aluguelEquipamento.model.Departamentos;
import com.uniamerica.aluguelEquipamento.service.DepartamentosService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class Departamento_teste {

    @Autowired
    private DepartamentosService departamentosService;

    @Test
    void insereDepartamentos(){

        Departamentos departamentos = new Departamentos();
        departamentos.setNome("Thomas");

        Departamentos usrSaved = departamentosService.insereDepartamentos(departamentos);

        Assertions.assertEquals(departamentos, usrSaved);
    }


    @Test
    void listarTodos(){

        Departamentos departamentos = new Departamentos();
        departamentos.setNome("Thomas");

        Departamentos departamentos2 = new Departamentos();
        departamentos2.setNome("Thomas2");

        departamentosService.insereDepartamentos(departamentos);
        departamentosService.insereDepartamentos(departamentos2);

        List<Departamentos> lista = departamentosService.listaTodos();

        Assertions.assertEquals(2, lista.size());
    }

    @Test
    void getByName(){

        Departamentos departamentos = new Departamentos();
        departamentos.setNome("Thomas3");

        departamentosService.insereDepartamentos(departamentos);

        List<Departamentos> result = departamentosService.getByName(departamentos.getNome());

        Assertions.assertEquals(1, result.size());
    }


}
