package com.uniamerica.aluguelEquipamento.service;


import com.uniamerica.aluguelEquipamento.model.Categorias;

import com.uniamerica.aluguelEquipamento.repository.CaracteristicasRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Optional;
@Service
public class CaracteristicasService {

    private final CaracteristicasRepository caracteristicasRepository;

    @Autowired
    public CaracteristicasService(CaracteristicasRepository caracteristicasRepository){
        this.caracteristicasRepository = caracteristicasRepository;
    }

    public Categorias createCategorias(Categorias categorias) { return caracteristicasRepository.save(categorias);}

    public List<Categorias> findByName(String name) {return caracteristicasRepository.findAllByName(name); }

    public List<Categorias> findAll() {return caracteristicasRepository.findAll();}

    public Categorias findById(Long id) {
        Optional<Categorias> categorias = caracteristicasRepository.findById(id);

        if(categorias.isPresent()){
            return categorias.get();
        }
        else {
            return null;
        }
    }


    public void delete(long id) {
        caracteristicasRepository.deleteById(id);
    }

    public Categorias update(Categorias categoria) {
        return caracteristicasRepository.save(categoria);
    }
}
