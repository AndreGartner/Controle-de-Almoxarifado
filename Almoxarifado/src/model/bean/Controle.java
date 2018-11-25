/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.util.Date;

/**
 *
 * @author @author Andre Luiz Gärtner, Yuji Faruk Murakami Feles, Alex Oliveira Fernandes, Eduardo Tavares Hauck, João Victor Küster Cardoso
 * INFEM302
 * CEDUPHH
 */
public class Controle {
    private int IdControle;
    private String DataEntrada;
    private String DataSaida;
    private String fkclient;
    private String fkfunc;

    public String getfkclient() {
        return fkclient;
    }

    public void setfkclient(String fkclient) {
        this.fkclient = fkclient;
    }

    public String getfkfunc() {
        return fkfunc;
    }

    public void setfkfunc(String fkfunc) {
        this.fkfunc = fkfunc;
    }

    public int getIdControle() {
        return IdControle;
    }

    public void setIdControle(int IdControle) {
        this.IdControle = IdControle;

    }

    public String getDataEntrada() {
        return DataEntrada;
    }

    public void SetDataEntrada(String DataEntrada) {
        this.DataEntrada = DataEntrada;
    }

    public String getDataSaida() {
        return DataSaida;
    }

    public void setDataSaida(String DataSaida) {
        this.DataSaida = DataSaida;
    }

 
    
}
