
package lista;

// Importa a classe JOptionPane para exibir caixas de diálogo (janelas) na tela
import javax.swing.JOptionPane;

/**
 * Classe principal para executar e exibir os resultados da Lista Duplamente Encadeada na tela.
 */
public class MainLDE {

    public static void main(String[] args) {

        // Cria uma nova instância da nossa Lista Duplamente Encadeada especializada em textos (String)
        ListaDuplamenteEncadeada<String> lista = new ListaDuplamenteEncadeada<>();

        // Exibe uma janela de boas-vindas inicial informando que a lista está vazia
        JOptionPane.showMessageDialog(null,
                "Lista Duplamente Encadeada inicializada!\nEstado atual: " + lista,
                "Início do Programa",
                JOptionPane.INFORMATION_MESSAGE);

        // ==========================================
        // 1. TESTANDO INSERÇÕES DE ELEMENTOS
        // ==========================================

        // Adiciona o elemento "Carla" no final da lista
        lista.adiciona("Carla");

        // Adiciona o elemento "Daniel" no final da lista
        lista.adiciona("Daniel");

        // Mostra em tela o resultado parcial após as inserções no fim
        JOptionPane.showMessageDialog(null,
                "Após adicionar 'Carla' e 'Daniel' no fim:\n" + lista,
                "Inserção no Fim",
                JOptionPane.INFORMATION_MESSAGE);

        // Adiciona o elemento "Bia" especificamente no início da lista
        lista.adicionaNoInicio("Bia");

        // Mostra o resultado da inserção no início
        JOptionPane.showMessageDialog(null,
                "Após adicionar 'Bia' no início:\n" + lista,
                "Inserção no Início",
                JOptionPane.INFORMATION_MESSAGE);

        // Adiciona "Ana" na posição 0 (que empurra tudo para a frente, virando o novo início)
        lista.adiciona(0, "Ana");

        // Adiciona "Bruno" na posição 2 (ficando entre Ana/Bia e Carla)
        lista.adiciona(2, "Bruno");

        // Mostra a lista completa com todas as inserções por índice e o tamanho total atual
        JOptionPane.showMessageDialog(null,
                "Após inserções por índice ('Ana' no 0 e 'Bruno' no 2):\n" + lista +
                        "\nTamanho total da lista: " + lista.getTamanho(),
                "Inserções por Índice",
                JOptionPane.INFORMATION_MESSAGE);

        // ==========================================
        // 2. TESTANDO CONSULTA/BUSCA DE ELEMENTOS
        // ==========================================

        // Busca o elemento guardado na posição 0, 2 e 4 utilizando o método pega()
        String primeiro = lista.pega(0);
        String meio = lista.pega(2);
        String ultimo = lista.pega(4);

        // Exibe os elementos encontrados nas respectivas posições
        JOptionPane.showMessageDialog(null,
                "Busca de Elementos por Posição:\n" +
                        "- Posição 0: " + primeiro + "\n" +
                        "- Posição 2: " + meio + "\n" +
                        "- Posição 4: " + ultimo,
                "Consulta (Pega)",
                JOptionPane.INFORMATION_MESSAGE);

        // ==========================================
        // 3. TESTANDO REMOÇÕES DE ELEMENTOS
        // ==========================================

        // Remove o primeiro elemento da lista e guarda o valor removido
        String removidoInicio = lista.removeDoInicio();

        // Mostra o elemento que saiu e como ficou a lista
        JOptionPane.showMessageDialog(null,
                "Elemento removido do início: " + removidoInicio +
                        "\nLista atualizada: " + lista,
                "Remoção do Início",
                JOptionPane.INFORMATION_MESSAGE);

        // Remove o último elemento da lista e guarda o valor removido
        String removidoFim = lista.removeDoFim();

        // Mostra o elemento que saiu do fim e o novo estado da lista
        JOptionPane.showMessageDialog(null,
                "Elemento removido do fim: " + removidoFim +
                        "\nLista atualizada: " + lista,
                "Remoção do Fim",
                JOptionPane.INFORMATION_MESSAGE);

        // Remove o elemento que está na posição 1 (que é o 'Bia')
        String removidoMeio = lista.remove(1);

        // Mostra o resultado da remoção por índice e o tamanho final
        JOptionPane.showMessageDialog(null,
                "Elemento removido da posição 1: " + removidoMeio +
                        "\nLista final após remoções: " + lista +
                        "\nTamanho atual: " + lista.getTamanho(),
                "Remoção por Índice",
                JOptionPane.INFORMATION_MESSAGE);

        // ==========================================
        // 4. TESTANDO O TRATAMENTO DE ERROS NA TELA
        // ==========================================

        // Bloco Try-Catch para testar a inserção de elemento nulo (deve lançar IllegalArgumentException)
        try {
            lista.adiciona(null); // Tentativa inválida
        } catch (IllegalArgumentException e) {
            // Captura o erro customizado e exibe uma janela de alerta (Warning)
            JOptionPane.showMessageDialog(null,
                    "Sucesso ao testar proteção contra nulos!\nMensagem capturada: " + e.getMessage(),
                    "Tratamento de Erro - Elemento Nulo",
                    JOptionPane.WARNING_MESSAGE);
        }

        // Bloco Try-Catch para testar o acesso a uma posição inexistente (deve lançar IndexOutOfBoundsException)
        try {
            lista.pega(50); // Posição que não existe
        } catch (IndexOutOfBoundsException e) {
            // Captura o erro de limite e exibe na tela
            JOptionPane.showMessageDialog(null,
                    "Sucesso ao testar limites da lista!\nMensagem capturada: " + e.getMessage(),
                    "Tratamento de Erro - Índice Inválido",
                    JOptionPane.WARNING_MESSAGE);
        }

        // Esvazia os elementos restantes da lista para testar remoção em lista vazia
        lista.removeDoFim();
        lista.removeDoInicio();

        // Bloco Try-Catch para tentar remover de uma lista totalmente vazia (deve lançar NoSuchElementException)
        try {
            lista.removeDoInicio(); // Tentativa em lista vazia
        } catch (java.util.NoSuchElementException e) {
            // Exibe a mensagem de erro da lista vazia na tela
            JOptionPane.showMessageDialog(null,
                    "Sucesso ao testar lista vazia!\nMensagem capturada: " + e.getMessage(),
                    "Tratamento de Erro - Lista Vazia",
                    JOptionPane.WARNING_MESSAGE);
        }

        // Mensagem final de encerramento do programa
        JOptionPane.showMessageDialog(null,
                "Todos os testes da Lista Duplamente Encadeada foram concluídos com sucesso!",
                "Fim do Programa",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
