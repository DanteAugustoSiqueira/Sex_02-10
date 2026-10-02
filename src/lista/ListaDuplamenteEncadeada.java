
package lista;

import java.util.NoSuchElementException;

/**
 * Classe que implementa uma Lista Duplamente Encadeada genérica.
 * @param <TipoElemento> O tipo de dado que a lista vai armazenar.
 */
public class ListaDuplamenteEncadeada<TipoElemento> {

    // Referência para o primeiro elemento (cabeça) da lista
    private No<TipoElemento> inicio;

    // Referência para o último elemento (cauda) da lista
    private No<TipoElemento> fim;

    // Contador para controlar quantos elementos existem na lista atualmente
    private int tamanho;

    /**
     * Método construtor: inicializa uma lista vazia.
     */
    public ListaDuplamenteEncadeada() {
        this.inicio = null; // A lista começa sem nenhum elemento no início
        this.fim = null;    // A lista começa sem nenhum elemento no fim
        this.tamanho = 0;   // O tamanho inicial é zero
    }

    /**
     * Adiciona um elemento no final da lista.
     * @param elemento O dado a ser inserido.
     */
    public void adiciona(TipoElemento elemento) {
        // Validação defensiva: Impede que valores nulos sejam inseridos na lista
        if (elemento == null) {
            throw new IllegalArgumentException("Erro ao adicionar: O elemento não pode ser nulo.");
        }

        // Cria um novo nó encapsulando o elemento recebido (usando o 1º construtor do No.java)
        No<TipoElemento> celula = new No<TipoElemento>(elemento);

        // Verifica se a lista está totalmente vazia (início e fim são nulos)
        if (this.inicio == null && this.fim == null) {
            this.inicio = celula; // O novo nó passa a ser o início
            this.fim = celula;    // E também passa a ser o fim (único elemento)
        } else {
            // Se já existem elementos, o atual 'fim' aponta para o novo nó como próximo
            this.fim.setProximo(celula);

            // O novo nó aponta seu 'anterior' para o antigo 'fim' (ligação dupla)
            celula.setAnterior(this.fim);

            // O ponteiro 'fim' da lista é atualizado para a nova célula criada
            this.fim = celula;
        }

        // Incrementa a quantidade de elementos armazenados
        this.tamanho++;
    }

    /**
     * Adiciona um elemento especificamente no início da lista.
     * @param elemento O dado a ser inserido.
     */
    public void adicionaNoInicio(TipoElemento elemento) {
        // Validação defensiva contra nulos
        if (elemento == null) {
            throw new IllegalArgumentException("Erro ao adicionar no início: O elemento não pode ser nulo.");
        }

        // Se a lista estiver vazia, adicionar no início é o mesmo que adicionar normalmente
        if (this.tamanho == 0) {
            adiciona(elemento);
        } else {
            // Cria um novo nó para o elemento
            No<TipoElemento> celula = new No<TipoElemento>(elemento);

            // O próximo do novo nó passa a ser o antigo início da lista
            celula.setProximo(this.inicio);

            // O anterior do antigo início passa a apontar para o novo nó
            this.inicio.setAnterior(celula);

            // O ponteiro 'inicio' da lista é atualizado para o novo nó
            this.inicio = celula;

            // Incrementa o tamanho total da lista
            this.tamanho++;
        }
    }

    /**
     * Adiciona um elemento em uma posição (índice) específica da lista.
     * @param posicao Índice onde o elemento será inserido.
     * @param elemento O dado a ser inserido.
     */
    public void adiciona(int posicao, TipoElemento elemento) {
        // Validação defensiva contra elementos nulos
        if (elemento == null) {
            throw new IllegalArgumentException("Erro ao adicionar: O elemento não pode ser nulo.");
        }

        // Validação de limites: A posição deve estar entre 0 e o tamanho atual
        if (posicao < 0 || posicao > this.tamanho) {
            throw new IndexOutOfBoundsException("Posição inválida: " + posicao + ". Tamanho atual da lista: " + this.tamanho);
        }

        // Se a posição for 0, usa o método específico de inserção no início
        if (posicao == 0) {
            adicionaNoInicio(elemento);
        }
        // Se a posição for igual ao tamanho, significa inserir no final
        else if (posicao == this.tamanho) {
            adiciona(elemento);
        }
        // Se for no meio da lista
        else {
            // Busca o nó que está imediatamente antes da posição desejada
            No<TipoElemento> noAnterior = this.pegaNo(posicao - 1);

            // Identifica qual é o nó que atualmente ocupa a posição (será empurrado para a frente)
            No<TipoElemento> noProximo = noAnterior.getProximo();

            // Cria o novo nó utilizando o 2º construtor do No.java na ordem exata: (anterior, elemento, proximo)
            No<TipoElemento> novaCelula = new No<TipoElemento>(noAnterior, elemento, noProximo);

            // Atualiza os ponteiros dos nós vizinhos para abraçar a nova célula
            noAnterior.setProximo(novaCelula);
            noProximo.setAnterior(novaCelula);

            // Incrementa o tamanho da lista
            this.tamanho++;
        }
    }

    /**
     * Retorna o elemento armazenado em uma determinada posição.
     * @param posicao Índice do elemento desejado.
     * @return O dado contido no nó daquela posição.
     */
    public TipoElemento pega(int posicao) {
        // Delega a busca para o método auxiliar que retorna o Nó, e extrai o dado dele
        return this.pegaNo(posicao).getElemento();
    }

    /**
     * Método auxiliar privado para localizar e retornar um Nó específico por índice.
     * Otimizado para percorrer a lista a partir do início ou do fim (o que estiver mais perto).
     */
    private No<TipoElemento> pegaNo(int posicao) {
        // Valida se a posição solicitada realmente existe na lista
        if (!this.posicaoOcupada(posicao)) {
            throw new IndexOutOfBoundsException("Posição inexistente: " + posicao + ". Tamanho atual da lista: " + this.tamanho);
        }

        No<TipoElemento> atual;

        // Otimização da Lista Dupla: Se a posição está na primeira metade, começa do 'inicio'
        if (posicao < this.tamanho / 2) {
            atual = this.inicio;
            for (int i = 0; i < posicao; i++) {
                atual = atual.getProximo(); // Avança para o próximo nó
            }
        }
        // Se a posição está na segunda metade, começa do 'fim' voltando para trás
        else {
            atual = this.fim;
            for (int i = this.tamanho - 1; i > posicao; i--) {
                atual = atual.getAnterior(); // Retrocede para o nó anterior
            }
        }

        // Retorna o nó encontrado na posição
        return atual;
    }

    /**
     * Remove o primeiro elemento da lista.
     * @return O elemento que foi removido.
     */
    public TipoElemento removeDoInicio() {
        // Validação: Se a lista estiver vazia, não há o que remover
        if (this.tamanho == 0) {
            throw new NoSuchElementException("Erro ao remover: A lista está vazia.");
        }

        // Guarda o elemento do início para poder retorná-lo no final
        TipoElemento elementoRemovido = this.inicio.getElemento();

        // O novo início passa a ser o segundo elemento da lista atual
        this.inicio = this.inicio.getProximo();

        // Reduz o tamanho lógico da lista
        this.tamanho--;

        // Se a lista ficou totalmente vazia após a remoção
        if (this.tamanho == 0) {
            this.fim = null; // O fim também deve ser nulo
        } else {
            // Como agora há um novo primeiro elemento, seu ponteiro 'anterior' deve ser anulado
            this.inicio.setAnterior(null);
        }

        return elementoRemovido;
    }

    /**
     * Remove o último elemento da lista.
     * @return O elemento que foi removido.
     */
    public TipoElemento removeDoFim() {
        // Validação se a lista está vazia
        if (this.tamanho == 0) {
            throw new NoSuchElementException("Erro ao remover: A lista está vazia.");
        }

        // Se houver apenas 1 elemento, remover do fim é equivalente a remover do início
        if (this.tamanho == 1) {
            return this.removeDoInicio();
        }

        // Salva o elemento do fim para retornar depois
        TipoElemento elementoRemovido = this.fim.getElemento();

        // O novo fim passa a ser o penúltimo elemento (o anterior do atual fim)
        this.fim = this.fim.getAnterior();

        // O novo fim perde a referência para o elemento que foi excluído à frente
        this.fim.setProximo(null);

        // Reduz o tamanho da lista
        this.tamanho--;

        return elementoRemovido;
    }

    /**
     * Remove um elemento de uma posição específica da lista.
     * @param posicao Índice do elemento a ser removido.
     * @return O elemento removido.
     */
    public TipoElemento remove(int posicao) {
        // Valida se a posição é válida para remoção
        if (!this.posicaoOcupada(posicao)) {
            throw new IndexOutOfBoundsException("Posição inválida para remoção: " + posicao + ". Tamanho atual: " + this.tamanho);
        }

        // Se for a primeira posição, delega para a remoção no início
        if (posicao == 0) {
            return this.removeDoInicio();
        }
        // Se for a última posição, delega para a remoção no fim
        if (posicao == this.tamanho - 1) {
            return this.removeDoFim();
        }

        // Se estiver no meio, localiza o nó que será removido
        No<TipoElemento> noAtual = this.pegaNo(posicao);
        No<TipoElemento> noAnterior = noAtual.getAnterior();
        No<TipoElemento> noProximo = noAtual.getProximo();

        // Religa os nós vizinhos, contornando e isolando o nó atual
        noAnterior.setProximo(noProximo);
        noProximo.setAnterior(noAnterior);

        // Diminui o tamanho da lista
        this.tamanho--;

        // Retorna o dado guardado no nó excluído
        return noAtual.getElemento();
    }

    /**
     * Verifica se uma dada posição está dentro dos limites válidos da lista.
     * @param posicao O índice a ser testado.
     * @return true se a posição for válida, false caso contrário.
     */
    private boolean posicaoOcupada(int posicao) {
        return posicao >= 0 && posicao < this.tamanho;
    }

    /**
     * Retorna a quantidade atual de elementos na lista.
     */
    public int getTamanho() {
        return this.tamanho;
    }

    /**
     * Verifica se a lista se encontra vazia.
     */
    public boolean estaVazia() {
        return this.tamanho == 0;
    }

    /**
     * Converte os elementos da lista em uma string legível no formato [A, B, C].
     */
    @Override
    public String toString() {
        // Se a lista estiver vazia, retorna colchetes vazios
        if (this.tamanho == 0) {
            return "[]";
        }

        StringBuilder builder = new StringBuilder("[");
        No<TipoElemento> atual = this.inicio;

        // Percorre todos os elementos exceto o último para formatar com vírgulas
        for (int i = 0; i < this.tamanho - 1; i++) {
            builder.append(atual.getElemento());
            builder.append(", ");
            atual = atual.getProximo();
        }

        // Adiciona o último elemento sem vírgula no final e fecha o colchete
        builder.append(atual.getElemento());
        builder.append("]");

        return builder.toString();
    }
}
