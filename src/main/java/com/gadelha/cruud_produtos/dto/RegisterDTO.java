package com.gadelha.cruud_produtos.dto;

import com.gadelha.cruud_produtos.model.UserRole;

public record RegisterDTO(String login , String password , UserRole userRole) {
    
}
