/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author rosan
 */
public class Funcionario {
    private int IdFunc;
    private String NomeFunc;
    private String SenhaFunc;
    
        public int getIdFunc() {
        return IdFunc;
    }

    public void setIdFunc(int IdFunc) {
        this.IdFunc = IdFunc;
    }

    public String getNomeFunc() {
        return NomeFunc;
    }

    public void setNomeFunc(String NomeFunc) {
        this.NomeFunc = NomeFunc;
    }

    public String getSenhaFunc() {
        return SenhaFunc;
    }

    public void setSenhaFunc(String SenhaFunc) {
        this.SenhaFunc = SenhaFunc;
    }

    
}
