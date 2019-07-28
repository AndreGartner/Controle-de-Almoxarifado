/**
 *
 * @author rosan
 */
package model.bean;

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
