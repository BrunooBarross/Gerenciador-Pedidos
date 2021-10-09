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
public class cliente {
    private Long idcliente;
    private String nome;
    private String cpf;
    
    
    public cliente(){
    
    }

    public cliente(Long idcliente, String nome, String cpf) {
        this.idcliente = idcliente;
        this.nome = nome;
        this.cpf = cpf;
    }

    public cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public Long getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Long idcliente) {
        this.idcliente = idcliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    
}
