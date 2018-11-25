/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle.dao;

import javax.swing.JOptionPane;
import model.bean.ProdEstado;

/**
 *
 * @author rosan
 */
public class TESTE_PRODDAO {
    
    
 public static void main (String[] a){
     ProdEstado pe = new ProdEstado();
    ProdDAO pd = new ProdDAO();
    int string;
        string =Integer.parseInt(JOptionPane.showInputDialog("Colcoa algo aq")) ;
//         pd.excluir(pe.setIdProd(String.valueOf(string));
    }
}