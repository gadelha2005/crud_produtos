package com.gadelha.cruud_produtos.dto;

import java.math.BigDecimal;

public record ProdutoRequestDTO(String nome , BigDecimal preco , Integer quantidade) {
    
}
