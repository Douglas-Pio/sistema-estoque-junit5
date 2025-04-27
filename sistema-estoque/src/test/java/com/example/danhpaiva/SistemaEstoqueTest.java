package com.example.danhpaiva;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class SistemaEstoqueTest {

    private SistemaEstoque sistemaEstoque;

    @Before
    public void setUp() {
        sistemaEstoque = new SistemaEstoque(); 
    }

    
    @Test
    public void adicionarProduto_produtoNovo_adicionaAoEstoque() {
        sistemaEstoque.adicionarProduto("Camiseta", 10);
        assertEquals(10, sistemaEstoque.consultarEstoque("Camiseta"));
        assertEquals(1, sistemaEstoque.obterHistoricoTransacoes().size());
    }

   
    @Test
    public void consultarEstoque_produtoExistente_retornaQuantidade() {
        sistemaEstoque.adicionarProduto("Mochila", 7);
        assertEquals(7, sistemaEstoque.consultarEstoque("Mochila"));
    }

    
    @Test
    public void removerProduto_produtoExistenteComEstoqueSuficiente_decrementaEstoque() {
        sistemaEstoque.adicionarProduto("Sapato", 15);
        sistemaEstoque.removerProduto("Sapato", 7);
        assertEquals(8, sistemaEstoque.consultarEstoque("Sapato"));
    }

    
    @Test
    public void verificarDisponibilidade_produtoExistenteComEstoqueSuficiente_retornaTrue() {
        sistemaEstoque.adicionarProduto("Agenda", 12);
        assertTrue(sistemaEstoque.verificarDisponibilidade("Agenda", 5));
    }

    
    @Test
    public void verificarDisponibilidade_produtoNaoExistente_retornaFalse() {
        assertFalse(sistemaEstoque.verificarDisponibilidade("Teclado", 3));
    }

    
    @Test
    public void obterHistoricoTransacoes_realizaTransacoes_retornaHistorico() {
        sistemaEstoque.adicionarProduto("Caderno", 20);
        assertEquals(1, sistemaEstoque.obterHistoricoTransacoes().size());
        assertEquals("Adicionado 20 unidade(s) de Caderno", sistemaEstoque.obterHistoricoTransacoes().get(0));
    }

    
    @Test
    public void removerProduto_produtoNaoExistente_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> sistemaEstoque.removerProduto("Jaqueta", 2));
        assertEquals(0, sistemaEstoque.obterHistoricoTransacoes().size());
    }

    
    @Test
    public void verificarDisponibilidade_quantidadeZeroOuNegativa_lancaIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> sistemaEstoque.verificarDisponibilidade("Mouse", 0));
        assertThrows(IllegalArgumentException.class, () -> sistemaEstoque.verificarDisponibilidade("Mouse", -1));
    }
}