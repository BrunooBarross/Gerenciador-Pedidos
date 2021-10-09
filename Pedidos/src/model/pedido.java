/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author GuiManu
 */
public class pedido {
    private Long id;
    private String item;
    private Long quantidade;
    private Long valorvenda;
    private String descricao;
    private Long idfornecedor;

    public pedido(Long id, String item, Long quantidade, Long valorvenda, String descricao) {
        this.id = id;
        this.item = item;
        this.quantidade = quantidade;
        this.valorvenda = valorvenda;
        this.descricao = descricao;
    }

    public pedido(String item, Long quantidade, Long valorvenda, String descricao, Long idfornecedor) {
        this.item = item;
        this.quantidade = quantidade;
        this.valorvenda = valorvenda;
        this.descricao = descricao;
        this.idfornecedor = idfornecedor;
    }

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Long quantidade) {
        this.quantidade = quantidade;
    }

    public Long getValorvenda() {
        return valorvenda;
    }

    public void setValorvenda(Long valorvenda) {
        this.valorvenda = valorvenda;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getIdfornecedor() {
        return idfornecedor;
    }

    public void setIdfornecedor(Long idfornecedor) {
        this.idfornecedor = idfornecedor;
    }
    
    
}
