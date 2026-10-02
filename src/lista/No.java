
package lista;

public class No<TipoElemento> {
    private No<TipoElemento> anterior;
    private TipoElemento elemento;
    private No<TipoElemento> proximo;

    //--!!--//

    // Metodos construtores.
    public No(TipoElemento elemento) {
        this.anterior = null;
        this.elemento = elemento;
        this.proximo = null;
    }
    public No(No<TipoElemento> anterior, TipoElemento elemento, No<TipoElemento> proximo) {
        this.anterior = anterior;
        this.elemento = elemento;
        this.proximo = proximo;
    }

    //--!!--//

    // Métodos Getters e Setters padrões.
    public No<TipoElemento> getAnterior() {
        return anterior;
    }
    public void setAnterior(No<TipoElemento> anterior) {
        this.anterior = anterior;
    }

    public TipoElemento getElemento() {
        return elemento;
    }
    public void setElemento(TipoElemento elemento) {
        this.elemento = elemento;
    }

    public No<TipoElemento> getProximo() {
        return proximo;
    }
    public void setProximo(No<TipoElemento> proximo) {
        this.proximo = proximo;
    }

    //--!!--//

    // Sobreposição do método toString() para exibir o No de forma legível.
    @Override
    public String toString() {
        return "No{" +
                "anterior=" + (anterior != null ? anterior.getElemento() : null) +
                ", elemento=" + elemento +
                ", proximo=" + proximo +
                '}';
    }
}
