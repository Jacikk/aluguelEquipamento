package com.uniamerica.aluguelEquipamento;

import com.uniamerica.aluguelEquipamento.model.Departamentos;
import com.uniamerica.aluguelEquipamento.service.DepartamentosService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DepartamentosTeste {

        @Autowired
        private DepartamentosService departamentosService;

        @Test
        void createDepartamentos () {
            Departamentos departamentos = new Departamentos();
            departamentos.setNome("Teste01");

            Departamentos resultDb = departamentosService.createDepartamentos(departamentos);
            Assertions.assertEquals(departamentos, resultDb);
        }


}
