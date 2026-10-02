
package lista;

public class ListaEncadeada<TipoElemento> {
    private No<TipoElemento> inicio;
    private No<TipoElemento> fim;
    private int tamanho;

    //--!!--//

    // Metodo construtor.
    public ListaEncadeada() {
        this.tamanho = 0;
    }

    //--!!--//

    // --- Adicionar elemento ---
    public void adiciona(TipoElemento elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("O elemento não pode ser nulo.");
        }

        No<TipoElemento> celula = new No<TipoElemento>(elemento);
        if(this.inicio == null && this.fim == null) {
            this.inicio = celula;
            this.fim = celula;
        }
        else {
            this.fim.setProximo(celula);
            this.fim = celula;
        }
        this.tamanho++;
    }

    //--!!--//

    // --- Metodo GET (Busca) por posição. --
    public No<TipoElemento> busca(int posicao) {
        if (posicao < 0 || posicao >= this.tamanho) {
            throw new IndexOutOfBoundsException("Posição inválida! A lista possui tamanho " + this.tamanho);
        }

        No<TipoElemento> atual = this.inicio;
        for(int i = 0; i < posicao; i++) {
            atual = atual.getProximo();
        }

        return atual;
    }

    //--!!--//

    // --- Remover por dado inserido ---
    public boolean remover(TipoElemento elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("O elemento a ser removido não pode ser nulo.");
        }
        if (this.inicio == null) {
            throw new IllegalStateException("Erro: A lista está vazia.");
        }

        No<TipoElemento> anterior = null;
        No<TipoElemento> atual = this.inicio;

        for(int i = 0; i < this.getTamanho(); i++) {
            if(atual.getElemento().equals(elemento)) {
                if(this.tamanho == 1){
                    this.inicio = null;
                    this.fim = null;
                }
                else if(atual == inicio) {
                    this.inicio = atual.getProximo();
                    atual.setProximo(null);
                }
                else if(atual == fim) {
                    this.fim = anterior;
                    anterior.setProximo(null);
                }
                else {
                    anterior.setProximo(atual.getProximo());
                }

                atual = null;
                this.tamanho--;
                return true;
            }

            // Avança os ponteiros a cada passada do loop.
            anterior = atual;
            atual = atual.getProximo();
        }

        return false;
    }

    //--!!--//

    // --- Buscar Posicao ---
    // Retorna o índice (posição) do elemento, ou -1 se não encontrar.
    public int buscarPosicao(TipoElemento elemento) {
        if (elemento == null) {
            throw new IllegalArgumentException("O elemento buscado não pode ser nulo.");
        }

        No<TipoElemento> atual = this.inicio;
        int posicao = 0;

        while (atual != null) {
            if (atual.getElemento().equals(elemento)) {
                return posicao;
            }
            atual = atual.getProximo();
            posicao++;
        }

        return -1; // Retorna -1 indicando que o elemento não está na lista.
    }

    //--!!--//

    // --- Alterar por dado inserido ---
    // Substitui o dado de um elemento existente (antigo) por um novo.
    public boolean alterar(TipoElemento antigo, TipoElemento novo) {
        if (antigo == null || novo == null) {
            throw new IllegalArgumentException("Os elementos não podem ser nulos.");
        }
        if (this.inicio == null) {
            throw new IllegalStateException("Erro: A lista está vazia.");
        }

        No<TipoElemento> atual = this.inicio;

        while (atual != null) {
            if (atual.getElemento().equals(antigo)) {
                atual.setElemento(novo); // Altera o dado (não precisa alterar os ponteiros inicio/fim/proximo pois o nó físico é o mesmo).
                return true;
            }
            atual = atual.getProximo();
        }

        return false; // Retorna falso se não achar o elemento antigo para alterar.
    }

    //--!!--//

    // --- Alterar por Posição ---
    // Altera baseado no índice.
    public void alterar(int posicao, TipoElemento novo) {
        if (novo == null) {
            throw new IllegalArgumentException("O novo elemento não pode ser nulo.");
        }
        No<TipoElemento> atual = this.busca(posicao); // Aproveita a validação do método busca.
        atual.setElemento(novo);
    }

    //--!!--//

    // Métodos Getters e Setters padrões.
    public No<TipoElemento> getInicio() {
        if(this.inicio == null) {
            throw new IllegalStateException("A lista esta vazia! Nao ha elementos no inicio.");
        }
        return inicio;
    }
    public void setInicio(No<TipoElemento> inicio) {
        this.inicio = inicio;
    }

    public No<TipoElemento> getFim() {
        if(this.fim == null) {
            throw new IllegalStateException("A lista esta vazia! Nao ha elementos no fim.");
        }
        return fim;
    }
    public void setFim(No<TipoElemento> fim) {
        this.fim = fim;
    }

    public int getTamanho() {
        return tamanho;
    }
    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    //--!!--//

    // Sobreposição do método toString() para exibir a lista encadeada de forma legível.
    @Override
    public String toString() {
        if (this.tamanho == 0) {
            return "ListaEncadeada{}";
        }
        StringBuilder builder = new StringBuilder("ListaEncadeada{");
        No<TipoElemento> atual = this.inicio;
        while (atual != null) {
            builder.append(atual.getElemento());
            if (atual.getProximo() != null) builder.append(" -> ");
            atual = atual.getProximo();
        }
        builder.append("}");
        return builder.toString();
    }
}
