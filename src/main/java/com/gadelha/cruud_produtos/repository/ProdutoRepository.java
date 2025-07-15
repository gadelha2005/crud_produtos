package com.gadelha.cruud_produtos.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gadelha.cruud_produtos.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto , UUID> {
    
}
