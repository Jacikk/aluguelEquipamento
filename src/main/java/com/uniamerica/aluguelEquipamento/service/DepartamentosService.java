package com.uniamerica.aluguelEquipamento.service;

import com.uniamerica.aluguelEquipamento.model.Departamentos;
import com.uniamerica.aluguelEquipamento.repository.DepartamentosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartamentosService {

    private final DepartamentosRepository departamentosRepository;

    @Autowired
    public DepartamentosService(DepartamentosRepository departamentosRepository) {
        this.departamentosRepository = departamentosRepository;
    }

    public Departamentos insereDepartamentos(Departamentos departamentos) {
        return departamentosRepository.save(departamentos);
    }

    public List<Departamentos> listaTodos() {
        return departamentosRepository.findAll();
    }

    public List<Departamentos> getByName(String nome) {
        return departamentosRepository.findByNome(nome);
    }

    public Departamentos getById(Long id) {
        Optional<Departamentos> departamento = departamentosRepository.findById(id);

        if(departamento.isPresent()){
            return departamento.get();
        }
        else {
            return null;
        }
    }

    public Departamentos createDepartamentos(Departamentos departamentos) {
        return  departamentosRepository.save(departamentos);
    }

    public Departamentos update(Departamentos departamentos) {
        return departamentosRepository.save(departamentos);
    }

    public Optional<Departamentos> findById(long id){
        return departamentosRepository.findById(id);
    }

    public void delete(Long id) {
        departamentosRepository.deleteById(id);
    }
}
