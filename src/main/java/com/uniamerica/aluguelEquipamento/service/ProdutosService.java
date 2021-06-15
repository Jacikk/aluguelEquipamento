package com.uniamerica.aluguelEquipamento.service;

import com.uniamerica.aluguelEquipamento.model.Produtos;
import com.uniamerica.aluguelEquipamento.repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutosService {

    private final ProdutosRepository produtosRepository;


    @Autowired
    public ProdutosService(ProdutosRepository produtosRepository) {
        this.produtosRepository = produtosRepository;

    }

    public Produtos createProduto(Produtos produto) {
        return produtosRepository.save(produto);
    }

    public List<Produtos> findAll() {
        return produtosRepository.findAll();
    }

    public Produtos findById(Long id) {
        Optional<Produtos> found= produtosRepository.findById(id);
        if(found.isPresent()) return found.get();
        else return null;
    }

    public List<Produtos> findAllByNome(String nome) {
        return produtosRepository.findAllByNome(nome);
    }

    public List<Produtos> findAllByDisponivel() {
        return produtosRepository.findAllByDisponivel(true);
    }

    public Produtos update(Produtos produto) {
        return produtosRepository.save(produto);
    }

    public void delete(Long id) {
        produtosRepository.deleteById(id);
    }

    public List<Produtos> verificarProdutosNoPeriodo(Date inicio, Date fim) {
        return produtosRepository.produtosDisponiveisPorPeriodo(inicio, fim);
    }
}
