package com.uniamerica.aluguelEquipamento.service;


import com.uniamerica.aluguelEquipamento.model.Caracteristicas;

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

    public Caracteristicas createCategorias(Caracteristicas caracteristicas) { return caracteristicasRepository.save(caracteristicas);}

    public List<Caracteristicas> findByName(String name) {return caracteristicasRepository.findAllByName(name); }

    public List<Caracteristicas> findAll() {return caracteristicasRepository.findAll();}

    public Caracteristicas findById(Long id) {
        Optional<Caracteristicas> categorias = caracteristicasRepository.findById(id);

        if(categorias.isPresent()){
            return categorias.get();
        }
        else {
            return null;
        }
    }

}
