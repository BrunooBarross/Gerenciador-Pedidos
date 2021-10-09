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
public class fornecedor {
    private Long idfornecedor;
    private String razao;
    private String cnpj;

    public fornecedor() {
        
    }

    
    public fornecedor(Long idfornecedor, String razao, String cnpj) {
        this.idfornecedor = idfornecedor;
        this.razao = razao;
        this.cnpj = cnpj;
    }

    public fornecedor(String razao, String cnpj) {
        this.razao = razao;
        this.cnpj = cnpj;
    }

     public Long getIdfornecedor() {
        return idfornecedor;
    }

    public void setIdfornecedor(Long idfornecedor) {
        this.idfornecedor = idfornecedor;
    }

    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void mostrafornecedor(){
        System.out.println("ID: "+getIdfornecedor());
        System.out.println("RAZAO SOCIAL: "+getRazao());
        System.out.println("CNPJ: "+getCnpj());
    }
    
}
