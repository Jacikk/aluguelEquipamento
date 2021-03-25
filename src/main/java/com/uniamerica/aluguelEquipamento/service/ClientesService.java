package com.uniamerica.aluguelEquipamento.service;

import com.uniamerica.aluguelEquipamento.exception.BadRequestException;
import com.uniamerica.aluguelEquipamento.model.Clientes;
import com.uniamerica.aluguelEquipamento.repository.ClientesRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientesService {

    private final ClientesRepository clientesRepository;

    public ClientesService(ClientesRepository clientesRepository){
        this.clientesRepository = clientesRepository;
    }

    public Clientes findById(long id){
        Clientes clientes = clientesRepository.findById(id).orElseThrow(() -> new BadRequestException("Cliente não encontrado"));

        return clientes;
    }
    public Clientes findByName(String nome){
        Clientes clientes = clientesRepository.findByName(nome);

        return clientes;
    }
    public Clientes create(Clientes clientes){
        Clientes clientesToSave = clientesRepository.save(clientes);

        return clientesToSave;
    }
    public Clientes update(Clientes clientes, long id){
        Clientes clientesToChange = findById(id);

        clientes.setId(clientesToChange.getId());
        return clientesRepository.save(clientes);
    }
    public void delete(long id){
        clientesRepository.deleteById(id);
    }
}
