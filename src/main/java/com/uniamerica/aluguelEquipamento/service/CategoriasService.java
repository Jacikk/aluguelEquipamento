package com.uniamerica.aluguelEquipamento.service;


import com.uniamerica.aluguelEquipamento.exception.BadRequestException;
import com.uniamerica.aluguelEquipamento.model.Atendentes;
import com.uniamerica.aluguelEquipamento.model.Categorias;

import com.uniamerica.aluguelEquipamento.repository.CategoriasRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Optional;
@Service
public class CategoriasService{

    private final CategoriasRepository categoriasRepository;

    @Autowired
    public CategoriasService(CategoriasRepository categoriasRepository){
        this.categoriasRepository = categoriasRepository;
    }

    public Categorias createCategorias(Categorias categorias) { return categoriasRepository.save(categorias);}

    public List<Categorias> findByName(String name) {return categoriasRepository.findAllByName(name); }

    public List<Categorias> findAll() {return categoriasRepository.findAll();}

    public Categorias findById(Long id) {
        Optional<Categorias> categorias = categoriasRepository.findById(id);

        if(categorias.isPresent()){
            return categorias.get();
        }
        else {
            return null;
        }
    }


    public void delete(long id) {
        categoriasRepository.deleteById(id);
    }

    public Categorias update(Categorias categoria) {
        return categoriasRepository.save(categoria);
    }
}
