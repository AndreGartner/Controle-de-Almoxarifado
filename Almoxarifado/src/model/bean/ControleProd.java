/**
 * @author @author Andre Luiz Gärtner, Yuji Faruk Murakami Feles, Alex Oliveira Fernandes, Eduardo Tavares Hauck, João Victor Küster Cardoso
 * INFEM302
 * CEDUPHH
 */
package model.bean;

public class ControleProd {
    private int QtdProd;
    private Controle controle;
    private ProdEstado produto;

    public int getQtdProd() {
        return QtdProd;
    }

    public void setQtdProd(int QtdProd) {
        this.QtdProd = QtdProd;
    }

    /**
     * @return the produto
     */
    public ProdEstado getProduto() {
        return produto;
    }

    /**
     * @param produto the produto to set
     */
    public void setProduto(ProdEstado produto) {
        this.produto = produto;
    }

    /**
     * @return the controle
     */
    public Controle getControle() {
        return controle;
    }

    /**
     * @param controle the controle to set
     */
    public void setControle(Controle controle) {
        this.controle = controle;
    }
}
