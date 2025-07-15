package com.gadelha.cruud_produtos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.gadelha.cruud_produtos.dto.ProdutoRequestDTO;
import com.gadelha.cruud_produtos.dto.ProdutoResponseDTO;
import com.gadelha.cruud_produtos.model.Produto;
import com.gadelha.cruud_produtos.repository.ProdutoRepository;

@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    private Produto produto;
    private Produto produto2;
    private ProdutoRequestDTO produtoRequestDTO;
    

    //Given
    @BeforeEach
    void setup(){

        //Model
        produto = new Produto(UUID.randomUUID(), "Tv", new BigDecimal(2000), 10);
        produto2 = new Produto(UUID.randomUUID(), "Celular", new BigDecimal(1000), 10);

        //Request
        produtoRequestDTO = new ProdutoRequestDTO("Tv", new BigDecimal(2000), 10);
       
    }

    @Test
    @DisplayName("Deve salvar um produto com sucesso")
    public void deveSalvarUmProdutoComSucesso(){

        //When
        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);

        ProdutoResponseDTO response = produtoService.save(produtoRequestDTO);

        //Then
        assertEquals(produto.getNome(), response.nome());
        assertEquals(produto.getPreco(), response.preco());
        assertEquals(produto.getQuantidade(), response.quantidade());

    }

    @Test
    @DisplayName("Deve buscar um produto pelo Id com sucesso caso o Id exista")
    public void deveBuscarUmProdutoPeloIdComSucessoCasoOIdExista(){
        UUID id = produto.getId();

        when(produtoRepository.findById(id)).thenReturn(Optional.of(produto));

        Optional<ProdutoResponseDTO> response = produtoService.findById(id);

        //Get para pegar o DTO
        var dto = response.get();

        assertEquals(produto.getId() , id);
        assertEquals(produto.getNome() , dto.nome());
        assertEquals(produto.getPreco(), dto.preco());
        assertEquals(produto.getQuantidade() , dto.quantidade());
    }

    @Test
    @DisplayName("Deve listar todos os produtos caso a lista não esteja vazia")
    public void deveListarTodosOsProdutosCasoAListaNaoEstejaVazia() {
        
        List<Produto> produtos = List.of(produto, produto2); 

        when(produtoRepository.findAll()).thenReturn(produtos);

        
        List<ProdutoResponseDTO> lista = produtoService.listProducts();

        assertEquals(produto.getNome(), lista.get(0).nome());
        assertEquals(produto.getPreco(), lista.get(0).preco());
        assertEquals(produto.getQuantidade(), lista.get(0).quantidade());

        assertEquals(produto2.getNome(), lista.get(1).nome());
        assertEquals(produto2.getPreco(), lista.get(1).preco());
        assertEquals(produto2.getQuantidade(), lista.get(1).quantidade());
    }

    @Test
    @DisplayName("Deve deletar um produtos caso ele esteja cadastrado")
    public void deveDeletarUmProdutoCasoExista(){

        UUID id = produto.getId();

        //Não é necessário o uso do when devido ao método ser VOID
        produtoService.delete(id);

        Mockito.verify(produtoRepository , Mockito.times(1)).deleteById(id);
        
    }

    @Test
    @DisplayName("Deve atualizar um produto caso ele exista")
    public void deveAtualizarUmProdutoCasoExista(){
        
        UUID id = produto.getId();
        ProdutoRequestDTO produtoAtualizado = new ProdutoRequestDTO(
            "Notebook", 
            new BigDecimal(3500), 
            7);
        
        when(produtoRepository.findById(id)).thenReturn(Optional.of(produto));

        produtoService.update(id, produtoAtualizado);

        assertEquals("Notebook", produto.getNome());
        assertEquals(new BigDecimal(3500), produto.getPreco());
        assertEquals(7, produto.getQuantidade());
    }
}
