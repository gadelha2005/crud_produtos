package com.gadelha.cruud_produtos.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gadelha.cruud_produtos.dto.ProdutoRequestDTO;
import com.gadelha.cruud_produtos.dto.ProdutoResponseDTO;
import com.gadelha.cruud_produtos.service.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    
    @Autowired
    private ProdutoService produtoService;


    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> adicionarProduto(@RequestBody ProdutoRequestDTO produtoRequestDTO){
        var produto = produtoService.save(produtoRequestDTO);
        return ResponseEntity.status(201).body(produto);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listarProdutos(){
        var produtos = produtoService.listProducts();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarProdutoPorId(@PathVariable("id") UUID id){
        return produtoService.findById(id)
                            .map(ResponseEntity::ok)
                            .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Void> atualizarProduto(@PathVariable UUID id , 
        @RequestBody ProdutoRequestDTO produtoRequestDTO){

            produtoService.update(id, produtoRequestDTO);
            return ResponseEntity.noContent().build();
            
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarProdutoPorId(@PathVariable("id") UUID id){
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }








}
