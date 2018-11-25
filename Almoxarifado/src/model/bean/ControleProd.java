/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author @author Andre Luiz Gärtner, Yuji Faruk Murakami Feles, Alex Oliveira Fernandes, Eduardo Tavares Hauck, João Victor Küster Cardoso
 * INFEM302
 * CEDUPHH
 */
public class ControleProd {
         
          private int QtdProd;
          private int fkIdProd;
          private int fkIdControle;
          
  
    
          
    public int getfkIdProd(){
        return fkIdProd;
    }
    
    public void setfkIdProd(int fkIdProd){
        this.fkIdProd = fkIdProd;
        
    }
    
    public int getfkIdControle(){
        return fkIdControle;
    }
    
    public void setfkIdControle(int fkIdControle){
        this.fkIdControle = fkIdControle;
        
    }

    public int getQtdProd() {
        return QtdProd;
    }

    public void setQtdProd(int QtdProd) {
        this.QtdProd = QtdProd;
    }
}
