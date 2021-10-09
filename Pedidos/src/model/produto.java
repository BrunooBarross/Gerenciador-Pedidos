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
public class produto {
    private Long idproduto;
    private String item;
    private Double valorproduto;
    private String descricao;
    private String idfornecedor;
    
    public produto() {
        
    }

    public produto(Long idproduto, String item, Double valorproduto, String descricao, String idfornecedor) {
        this.idproduto = idproduto;
        this.item = item;
        this.valorproduto = valorproduto;
        this.descricao = descricao;
        this.idfornecedor = idfornecedor;
    }

    public produto(String item, Double valorproduto, String descricao, String idfornecedor) {
        this.item = item;
        this.valorproduto = valorproduto;
        this.descricao = descricao;
        this.idfornecedor = idfornecedor;
    }


    public Long getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(Long idproduto) {
        this.idproduto = idproduto;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Double getValorproduto() {
        return valorproduto;
    }

    public void setValorproduto(Double valorproduto) {
        this.valorproduto = valorproduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getIdfornecedor() {
        return idfornecedor;
    }

    public void setIdfornecedor(String idfornecedor) {
        this.idfornecedor = idfornecedor;
    }

    
    
    
    
}
