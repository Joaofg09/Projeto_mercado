import java.util.ArrayList;
import java.util.Scanner;

public class Sistema_de_controle_de_estoque {

    // Códigos cores no terminal
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";

    /*
        Função para selecionar uma opção do menu.
     */
    static void menu(ArrayList<Produto> lista, int codigo) {
        System.out.print("""
                \n[1] Adicionar produto
                
                [2] Acessar relatórios
                
                [3] Registrar venda
                
                [4] Finalizar Sistema
                
                --> """);

        Scanner sc = new Scanner(System.in);
        int scMenu = sc.nextInt();
        
        /*
        Switch case para o usuario escolher qual metodo realizar.
        */
        switch (scMenu) {
            case 1:
                System.out.println("Iniciando adição de produto\n");
                adicionar(lista, codigo);
                break;
            case 2:
                escolherRelatorio(lista);
                break;
            case 3:
                registrarVenda(lista, codigo);
                break;
            default:
                if (scMenu == 4) {
                    System.out.println("Sistema Finalizado");
                    return;
                }
                System.out.println("Opção inválida");
                menu(lista, codigo);
        }
    }
    /*
        Função para adicionar um produto novo ao estoque ou apenas aumentar a sua quantidade
     */
    static void adicionar(ArrayList<Produto> lista, int codigo) {
        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.print("Nome: ");
        String nome = sc.nextLine();

        // Verifica se o produto já existe
        boolean produtoExiste = false;
        for (Produto p : lista) {
            if (p.getNome().equalsIgnoreCase(nome)) { // Ignora maiúsculas e minúsculas
                System.out.println("Produto já cadastrado!\nAdicionando ao estoque existente.");

                System.out.print("Quantidade a adicionar: ");
                int quantidadeAdicional = sc.nextInt();
                sc.nextLine();

                p.setQuantidade(p.getQuantidade() + quantidadeAdicional); // Soma a quantidade
                produtoExiste = true;
                break; // Sai do loop, pois já encontrou o produto
            }
        }

        // Se o produto não existe, cadastra um novo
        if (!produtoExiste) {
            codigo++; // Incrementa o código para o novo item

            System.out.print("Quantidade: ");
            int quantidade = sc.nextInt();
            sc.nextLine();

            System.out.print("Categoria: ");
            String categoria = sc.nextLine();

            System.out.print("Preço unitário: ");
            double precoUnitario = sc.nextDouble();
            sc.nextLine();

            Produto novoProduto = new Produto(codigo, nome, quantidade, categoria, precoUnitario);
            lista.add(novoProduto);
            System.out.println("\nProduto cadastrado com sucesso!\n");
        }

        System.out.print("Continuar cadastrando? (Y/n)\n--> ");
        String sc_continuar = sc.nextLine();
        if (!sc_continuar.equalsIgnoreCase("Y")) {
            break;
        }

    }

    menu(lista, codigo);
}
    /*
        Função para escolher entro os dois relatórios: produtos gerais ou produtos com baixo estoque.
     */
    static void escolherRelatorio(ArrayList<Produto> lista) {
        Scanner sc = new Scanner(System.in);

        System.out.println("1 - Relatório geral");
        System.out.print("2 - Relatório de produtos com baixo estoque\n--> ");
        int scRelatorio = sc.nextInt();
    
        switch (scRelatorio) {
            case 1:
                Relatorio.gerarRelatorioGeral(lista);
                menu(lista, lista.size());
                break;
            case 2:
                Relatorio.gerarRelatorioBaixoEstoque(lista);
                menu(lista, lista.size());
                break;
            default:
                menu(lista, lista.size());
        }
    }
 
    /*
        Função para registrar uma venda caso o produto exista e tenha estoque suficiente dele.
     */
    static void registrarVenda(ArrayList<Produto> lista, int codigo) {
        Scanner sc = new Scanner(System.in);

        Relatorio.gerarRelatorioVenda(lista, codigo);

        System.out.print("\nInforme o código do produto que deseja vender:\n--> ");
        int codigoProduto = sc.nextInt();
        Produto produtoSelecionado = null;

        for (Produto produto : lista) {
            if (produto.getCodigo() == codigoProduto) {
                produtoSelecionado = produto;
                break;
            }
        }

        if (produtoSelecionado == null) {
            System.out.println("\nProduto não encontrado.");
            menu(lista, codigo);
            return;
        }

        System.out.print("Informe a quantidade a ser vendida:\n--> ");
        int quantidadeVendida = sc.nextInt();

        if (quantidadeVendida > produtoSelecionado.getQuantidade()) {
            System.out.println("\nQuantidade insuficiente em estoque!!.\nEstoque disponível: "
                               + produtoSelecionado.getQuantidade());
        } else {
       
            produtoSelecionado.setQuantidade(produtoSelecionado.getQuantidade() - quantidadeVendida);
            System.out.println("\nVenda registrada com sucesso!");
        }

        for (Produto produto : lista) {
            if (produto.getQuantidade() <= 20) {
                System.out.printf("\nProduto %d - %s com baixo estoque:" + RED + " %d \n" + RESET,
                                  produto.getCodigo(),
                                  produto.getNome(),
                                  produto.getQuantidade());
            }
        }

        menu(lista, codigo);
    }

    public static void main(String[] args) {
        ArrayList<Produto> listaDeProdutos = new ArrayList<>();

        listaDeProdutos.add(new Produto(1, "Arroz", 30, "Alimentos", 5.50));
        listaDeProdutos.add(new Produto(2, "Feijão", 25, "Alimentos", 7.80));
        listaDeProdutos.add(new Produto(3, "Detergente", 28, "Limpeza", 2.30));

        int codigo = listaDeProdutos.size();

        menu(listaDeProdutos, codigo);
    }
}
