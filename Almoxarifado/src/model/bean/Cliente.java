/**
 *
 * @author Andre
 */
package model.bean;

public class Cliente {
    private String IdCliente;
    private String NomeCli;
    
    public String getIdCli(){
        return IdCliente;
    }
    
    public void setIdCli(String IdCli){
        this.IdCliente = IdCli;
    }
    
    public String getNomeCli(){
        return NomeCli;
    }
    
    public void setNomeCli(String NomeCli){
        this.NomeCli = NomeCli;
    }
}
