import java.text.DecimalFormat;

import static javax.swing.JOptionPane.*;
import static java.lang.Integer.parseInt;
import static java.lang.Double.parseDouble;

public class Util {
    private Produto[] produto = new Produto[5];
    private Fornecedor[] fornecedor = new Fornecedor[5];
    private int idxProduto = 0;
    private int idxFornecedor = 0;

    public void menu() {
        int opcao;
        String msg = "1. Cadastrar Produto \n2. Pesquisar produto" +
                     "\n3. Pesquisar fornecedor\n4. Finalzar";

        while(true) {
            opcao = parseInt(showInputDialog(msg));
            if(opcao == 4) {
                return;
            }
            switch (opcao) {
                case 1:
                    cadastrarProduto();
                    break;
                case 2:
                    pesquisarProduto();
                    break;
                case 3:
                    pesquisar();
                    break;
                default:
                    showMessageDialog(null,"Opção Inválida!");
            }
        }

    }

    private void pesquisar() {
        Fornecedor fornecedor = pesquisarFornecedor();
        if(fornecedor != null) {
            String aux = "";
            aux += "Fornecedor: " + fornecedor.getNome() + "\n";
            aux += "CNPJ: " + fornecedor.getCnpj() + "\n";
        }
    }

    private void cadastrarProduto() {
        String nome;
        double valor;
        int qtdEstoque;
        Fornecedor fornecedor = pesquisarFornecedor();

        if(fornecedor == null) {
            fornecedor = cadastrarFornecedor();
        }

        nome = showInputDialog("Nome do produto");
        valor = parseDouble(showInputDialog("Valor unitário"));
        qtdEstoque = parseInt(showInputDialog("Quantidade em estoque"));
        produto[idxProduto] = new Produto(nome, valor, qtdEstoque, fornecedor);
        idxProduto++;
    }

    private Produto pesquisarProduto() {
        DecimalFormat df = new DecimalFormat("0.00");
        String aux = "Produto não encontrado";
        String nome = showInputDialog("Nome do Produto");
        for(int i = 0; i < idxProduto; i++) {
            if(produto[i].getNome().equalsIgnoreCase(nome)) {
                aux = "";
                aux += "Nome do Produto: " + nome + "\n";
                aux += "Valor unitário: R$ " + df.format(produto[i].getValor()) + "\n";
                aux += "Nome do Fornecedor: " + produto[i].getFornecedor().getNome() + "\n";
                aux += "Quantidade em estoque: " + produto[i].getQtdEstoque() + "\n";
            }
        }
        showMessageDialog(null, aux);
        return null;
    }

    private Fornecedor pesquisarFornecedor(){
        int cnpj = parseInt(showInputDialog("CNPJ do fornecedor"));
        for(int i = 0; i < idxFornecedor; i++) {
            if(fornecedor[i].getCnpj() == cnpj){
                return fornecedor[i];
            }
        }
        showMessageDialog(null, cnpj + " não cadastrado");
        return null;
    }
    private Fornecedor cadastrarFornecedor() {
        String nome;
        int cnpj;

        nome = showInputDialog("Nome do Fornecedor");
        cnpj = parseInt(showInputDialog("CNPJ do Fornecedor"));

        fornecedor[idxFornecedor] = new Fornecedor(nome, cnpj);
        idxFornecedor++;
        return fornecedor[idxFornecedor - 1];
    }
}
