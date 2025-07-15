package com.gadelha.cruud_produtos.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gadelha.cruud_produtos.dto.ProdutoRequestDTO;
import com.gadelha.cruud_produtos.dto.ProdutoResponseDTO;
import com.gadelha.cruud_produtos.model.Produto;
import com.gadelha.cruud_produtos.repository.ProdutoRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    public ProdutoResponseDTO save(ProdutoRequestDTO produtoRequestDTO){
        Produto produto = new Produto(
            null, 
            produtoRequestDTO.nome(), 
            produtoRequestDTO.preco(), 
            produtoRequestDTO.quantidade());

        var novoProduto = produtoRepository.save(produto);
        return entityToDTO(produto);
    }

    public List<ProdutoResponseDTO> listProducts(){
        List<Produto> produtos = produtoRepository.findAll();
        
        return produtos.stream()
                .map(this::entityToDTO)
                .toList();
    }

    public Optional<ProdutoResponseDTO> findById(UUID id){
        return produtoRepository.findById(id).map(this::entityToDTO);
    }

    public void delete(UUID id){
        produtoRepository.deleteById(id);
    }

    public void update( UUID id, ProdutoRequestDTO produtoRequestDTO){
        var produto = produtoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado!"));
        
        produto.setNome(produtoRequestDTO.nome());
        produto.setPreco(produtoRequestDTO.preco());
        produto.setQuantidade(produtoRequestDTO.quantidade());

        produtoRepository.save(produto);
        
    }

    private ProdutoResponseDTO entityToDTO(Produto produto){
        return new ProdutoResponseDTO(
            produto.getId(), 
            produto.getNome(), 
            produto.getPreco(), 
            produto.getQuantidade());
    }
}
