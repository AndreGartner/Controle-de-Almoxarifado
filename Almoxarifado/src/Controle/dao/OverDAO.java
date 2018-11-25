/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.dao;

/**
 *
 * @author rosan
 * @param <F>
 */
public interface OverDAO<F>{
   
   
   
    public int inserir(F objeto);
    
    public int alterar(F objeto);
    
    public void excluir(F objeto);
    

    
}
