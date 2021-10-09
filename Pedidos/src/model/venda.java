/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import javafx.scene.chart.PieChart.Data;

/**
 *
 * @author GuiManu
 */
public class venda {
    private Long idveneda;
    private String cpf;
    private String cnpj;
    private Long idproduto;
    private Double valorvenda;
    private String estado;
    
    
    
    public venda() {
    
    }

    public venda(Long idveneda, String cpf, String cnpj, Long idproduto,Double valorvenda,String estado  ) {
        this.idveneda = idveneda;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.idproduto = idproduto;
        this.valorvenda = valorvenda;
        this.estado = estado;
    }

    public venda(String cpf, String cnpj, Long idproduto, Double valorvenda, String estado ) {
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.idproduto = idproduto;
        this.valorvenda = valorvenda;
        this.estado = estado;
    }

    public venda(String cpf,String estado) {
        this.cpf = cpf;
        this.estado = estado;
    }




    public Long getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(Long idproduto) {
        this.idproduto = idproduto;
    }

    public Long getIdveneda() {
        return idveneda;
    }

    public void setIdveneda(Long idveneda) {
        this.idveneda = idveneda;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Double getValorvenda() {
        return valorvenda;
    }

    public void setValorvenda(Double valorvenda) {
        this.valorvenda = valorvenda;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
    
}
