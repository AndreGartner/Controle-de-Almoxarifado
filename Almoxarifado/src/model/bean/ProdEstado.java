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
public class ProdEstado {
    
    private int IdProd;
    private String NomeProd;
    private String TipoProd;
    private String OrigemProd;
    private String DataEntradaProd;
    private int Quantidade;
private String StatusProduto;

     public String getStatusProduto(){
    return StatusProduto;   
    }
    public void setStatusProduto(String StatusProduto){
        this.StatusProduto = StatusProduto;
    }

    public int getIdProd() {
        return IdProd;
    }

    public void setIdProd(int IdProd) {
        this.IdProd = IdProd;
    }

    public String getNomeProd() {
        return NomeProd;
    }

    public void setNomeProd(String NomeProd) {
        this.NomeProd = NomeProd;
    }

    public String getTipoProd() {
        return TipoProd;
    }

    public void setTipoProd(String TipoProd) {
        this.TipoProd = TipoProd;
    }
    
     public String getOrigemProd() {
        return OrigemProd;
    }

    public void setOrigemProd(String OrigemProd) {
        this.OrigemProd = OrigemProd;
    }
    
    public String getDataEntradaProd() {
        return DataEntradaProd;
    }

    public void setDataEntradaProd(String DataEntradaProd) {
        this.DataEntradaProd = DataEntradaProd;
    }

    /**
     * @return the Quantidade
     */
    public int getQuantidade() {
        return Quantidade;
    }

    /**
     * @param Quantidade the Quantidade to set
     */
    public void setQuantidade(int Quantidade) {
        this.Quantidade = Quantidade;
    }



    
    
    
    
}
