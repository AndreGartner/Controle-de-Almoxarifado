/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author Andre
 */
public class Cliente {
    
    private int IdCliente;
    private String NomeCli;
    
    public int getIdCli(){
        return IdCliente;
    }
    
    public void setIdCli(int IdCli){
        this.IdCliente = IdCli;
    }
    
    public String getNomeCli(){
        return NomeCli;
    }
    
    public void setNomeCli(String NomeCli){
        this.NomeCli = NomeCli;
    }
   
    
    
    
    
    
}
