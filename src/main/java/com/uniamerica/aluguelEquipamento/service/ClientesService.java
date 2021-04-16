package com.uniamerica.aluguelEquipamento.service;

import com.uniamerica.aluguelEquipamento.exception.BadRequestException;
import com.uniamerica.aluguelEquipamento.model.Clientes;
import com.uniamerica.aluguelEquipamento.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientesService {

    private final ClientesRepository clientesRepository;

    @Autowired
    public ClientesService(ClientesRepository clientesRepository){
        this.clientesRepository = clientesRepository;
    }

    public Clientes findById(Long id){
        Clientes clientes = clientesRepository.findById(id).orElseThrow(() -> new BadRequestException("Cliente não encontrado"));

        return clientes;
    }
    public List<Clientes> findByNome(String nome){
        return clientesRepository.findByNome(nome);
    }

    public List<Clientes> findAll() {
        return clientesRepository.findAll();
    }

    public Clientes create(Clientes clientes){
        Clientes clientesToSave = clientesRepository.save(clientes);

        return clientesToSave;
    }
    public Clientes update(Clientes clientes, Long id){
        Clientes clientesToChange = findById(id);

        clientes.setId(clientesToChange.getId());
        return clientesRepository.save(clientes);
    }
    public void delete(Long id){
        clientesRepository.deleteById(id);
    }

    public Clientes findByCpf(String cpf) {
        return clientesRepository.findByCpf(cpf);
    }

    public Clientes findByRg(String rg) {
        return clientesRepository.findByRg(rg);
    }
}
