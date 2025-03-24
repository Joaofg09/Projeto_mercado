public class Produto {
    private int codigo;
    private String nome;
    private int quantidade;
    private String categoria;
    private double precoUnitario; 
 
    public Produto(int codigo, String nome, int quantidade, String categoria, double precoUnitario) {
       this.codigo = codigo;
       this.nome = nome;
       this.quantidade = quantidade;
       this.categoria = categoria;
       this.precoUnitario = precoUnitario;
    }
 
    public int getCodigo() {
       return codigo;
    }
 
    public void setCodigo(int codigo) {
       this.codigo = codigo;
    }
 
    public String getNome() {
       return nome;
    }
 
    public void setNome(String nome) {
       this.nome = nome;
    }
 
    public int getQuantidade() {
       return quantidade;
    }
 
    public void setQuantidade(int quantidade) {
       this.quantidade = quantidade;
    }
 
    public double getPrecoUnitario() { // Nome atualizado
       return precoUnitario;
    }
 
    public void setPrecoUnitario(double precoUnitario) { // Nome atualizado
       this.precoUnitario = precoUnitario;
    }
 
    public String getCategoria() {
       return categoria;
    }
 
    public void setCategoria(String categoria) {
       this.categoria = categoria;
    }
 
    @Override
    public String toString() {
       return String.format("%-10d %-20s %-12d %-15s %-12.2f", 
                            codigo, nome, quantidade, categoria, precoUnitario);
    }
 }
 