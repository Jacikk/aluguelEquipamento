package com.uniamerica.aluguelEquipamento.service;

import com.uniamerica.aluguelEquipamento.model.Caracteristicas;
import com.uniamerica.aluguelEquipamento.repository.CaracteristicasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CaracteristicasService {

    private final CaracteristicasRepository caracteristicasRepository;

    @Autowired
    public CaracteristicasService(CaracteristicasRepository caracteristicasRepository) {
        this.caracteristicasRepository = caracteristicasRepository;
    }

    public Caracteristicas createCaracteristica(Caracteristicas caracteristica) {
        return caracteristicasRepository.save(caracteristica);
    }

    public List<Caracteristicas> findAll() {
        return caracteristicasRepository.findAll();
    }

    public Caracteristicas findById(Long id) {
        Optional<Caracteristicas> carFound = caracteristicasRepository.findById(id);

        if(carFound.isPresent()) {
            return carFound.get();
        }
        else {
            return null;
        }
    }

    public List<Caracteristicas> findByNome(String nome) {
        return caracteristicasRepository.findByNome(nome);
    }

    public Caracteristicas update(Caracteristicas caracteristica) {
       return caracteristicasRepository.save(caracteristica);
    }

    public void delete(Long id) {
        caracteristicasRepository.deleteById(id);
    }
}
