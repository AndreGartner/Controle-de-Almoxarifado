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
    private String DataDevolucao;
    private String DataEmprestimo;
//    private int IdFunc; 
    private Cliente cliente;
    private Funcionario funcionario;



    public int getIdControle() {
        return IdControle;
    }

    public void setIdControle(int IdControle) {
        this.IdControle = IdControle;

    }

    /**
     * @return the DataDevolucao
     */
    public String getDataDevolucao() {
        return DataDevolucao;
    }

    /**
     * @param DataDevolucao the DataDevolucao to set
     */
    public void setDataDevolucao(String DataDevolucao) {
        this.DataDevolucao = DataDevolucao;
    }

    /**
     * @return the DataEmprestimo
     */
    public String getDataEmprestimo() {
        return DataEmprestimo;
    }

    /**
     * @param DataEmprestimo the DataEmprestimo to set
     */
    public void setDataEmprestimo(String DataEmprestimo) {
        this.DataEmprestimo = DataEmprestimo;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the funcionario
     */
    public Funcionario getFuncionario() {
        return funcionario;
    }

    /**
     * @param funcionario the funcionario to set
     */
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

   

    

   

   

 
    
}
