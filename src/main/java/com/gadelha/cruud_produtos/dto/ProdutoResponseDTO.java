package com.gadelha.cruud_produtos.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProdutoResponseDTO(UUID id ,String nome , BigDecimal preco , Integer quantidade ) {
    
}
