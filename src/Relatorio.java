import java.util.ArrayList;

public class Relatorio {

      // Códigos cores no terminal
      public static final String RESET = "\u001B[0m";
      public static final String RED = "\u001B[31m";

    /*
        Função que gera o relatório geral do estoque.
     */
    static void gerarRelatorioGeral(ArrayList<Produto> lista) {
      
        System.out.printf("%-10s %-20s %-12s %-20s %-12s%n", 
                          "Código", "Nome", "Quantidade", "Categoria", "Preço");

        System.out.println("=".repeat(80)); // Linha separadora
        
        for (Produto produto : lista) {
            // Verificando se a quantidade é menor ou igual a 20 para aplicar a cor vermelha
            String quantidadeFormatada = (produto.getQuantidade() <= 20) 
                ? RED + produto.getQuantidade() + RESET 
                : String.valueOf(produto.getQuantidade());

            // Ajuste de formatação para garantir o alinhamento
            System.out.printf("%-10d %-20s %-12s %-20s %-12.2f%n", 
                              produto.getCodigo(), produto.getNome(), 
                              quantidadeFormatada, produto.getCategoria(), 
                              produto.getPrecoUnitario());
        }
        
    }

    /*
        Função que gera o relatório de produtos com baixo estoque.
    */
    static void gerarRelatorioBaixoEstoque(ArrayList<Produto> lista) {
     
        System.out.printf("%-10s %-20s %-12s %-20s %-12s%n", 
                          "Código", "Nome", "Quantidade", "Categoria", "Preço");

        System.out.println("=".repeat(80)); // Linha separadora

        for (Produto produto : lista) {
            if (produto.getQuantidade() <= 20) {
                System.out.printf("%-10d %-20s " + RED + "%-12d" + RESET + " %-20s %-12.2f%n", 
                                  produto.getCodigo(), produto.getNome(), 
                                  produto.getQuantidade(), produto.getCategoria(), 
                                  produto.getPrecoUnitario());
            }
        }

    }

    /*
        Função utilizada em registrarVenda(), para exibir os produtos no estoque
     */
    static void gerarRelatorioVenda(ArrayList<Produto> lista, int codigo) {
      
        System.out.printf("%-10s %-20s %-12s %-20s %-12s%n", 
                          "Código", "Nome", "Quantidade", "Categoria", "Preço");

        System.out.println("=".repeat(80)); // Linha separadora
        
        for (Produto produto : lista) {
         
            String quantidadeFormatada = (produto.getQuantidade() <= 20) 
                ? RED + produto.getQuantidade() + RESET 
                : String.valueOf(produto.getQuantidade());

            System.out.printf("%-10d %-20s %-12s %-20s %-12.2f%n", 
                              produto.getCodigo(), produto.getNome(), 
                              quantidadeFormatada, produto.getCategoria(), 
                              produto.getPrecoUnitario());
        }
    }
}