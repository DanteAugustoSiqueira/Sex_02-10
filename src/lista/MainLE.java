
package lista;

public class MainLE {
    public static void main(String[] args) {
        try {
            ListaEncadeada<String> lista = new ListaEncadeada<String>();

            System.out.println("Tamanho inicial da Lista = " + lista.getTamanho());

            lista.adiciona("Dan 0");
            lista.adiciona("Dan 1");
            lista.adiciona("Dan 2");
            lista.adiciona("Dan 3");
            lista.adiciona("Dan 4");
            lista.adiciona("Dan 5");

            System.out.println("\nLista após inserções: " + lista);
            System.out.println("Tamanho da Lista = " + lista.getTamanho());
            System.out.println("Inicio da Lista = " + lista.getInicio().getElemento());
            System.out.println("Fim da Lista = " + lista.getFim().getElemento());

            // Testando o método GET (Busca) com validação.
            System.out.println("\nElemento na posicao 2 = " + lista.busca(2).getElemento());

            // Testando o Método: BuscarPosicao.
            System.out.println("\n--- Testando BuscarPosicao ---");
            System.out.println("Posição do 'Dan 3': " + lista.buscarPosicao("Dan 3"));
            System.out.println("Posição do 'Dan 10' (não existe): " + lista.buscarPosicao("Dan 10"));

            // Testando o Método: Alterar (por valor).
            System.out.println("\n--- Testando Alterar ---");
            System.out.println("Alterar 'Dan 2' por 'Dante Alterado': " + lista.alterar("Dan 2", "Dante Alterado"));
            System.out.println(lista);

            // Testando o Método: Alterar (por posicao).
            lista.alterar(4, "Dan 4 Alterado");
            System.out.println("Alterar posição 4: \n" + lista);

            // Testando a Remoção.
            System.out.println("\n--- Testando Remoção ---");
            System.out.println("Remover 'Dan 5' (Fim) = " + lista.remover("Dan 5"));
            System.out.println(lista);
            System.out.println("Novo Fim da Lista = " + lista.getFim().getElemento());

            // --- TESTANDO TRATAMENTO DE ERROS (Validações) ---
            System.out.println("\n--- Testando Validações de Erro ---");
            // A linha abaixo vai gerar um IndexOutOfBoundsException.
            System.out.println(lista.busca(10).getElemento());

            // A linha abaixo vai gerar um IllegalArgumentException.
            lista.adiciona(null);

        } catch (Exception e) {
            System.err.println("Erro capturado: " + e.getMessage());
        }
    }
}
