package sistema_doacao;

/**
 * Uma classe chamada Doação: os objetos desta classe representam todos as
 * doaçõescadastrados e que poderão ser doados as pessoas necessitadas.Para tal,
 * é necessário armazenar as seguintes informações: Código da doação,
 * nome/descrição da doação, quantidade de itens doados.
 *
 * @author Gabriel Domingues
 * @version 05/07/2024
 */
public class Doacao {

    private String codigoDaDoacao;
    private String descricao;
    private int quantidade;

    public Doacao(String codigoDaDoacao, String descricao, int quantidade) {
        setCodigoDaDoacao(codigoDaDoacao);
        setDescricao(descricao);
        setQuantidade(quantidade);
    }

    public String getCodigoDaDoacao() {
        return codigoDaDoacao;
    }

    public void setCodigoDaDoacao(String codigoDaDoacao) {
        if (codigoDaDoacao != null) {
            this.codigoDaDoacao = codigoDaDoacao;
        } else {
            codigoDaDoacao = "";
        }
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        if (descricao != null) {
            this.descricao = descricao;
        } else {
            descricao = "";
        }
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade > 0) {
            this.quantidade = quantidade;
        } else {
            quantidade = 0;
        }
    }

    public String toString() {
        return "Descrição: " + getDescricao() + "\nCódigo da doação: " + getCodigoDaDoacao() + "\nQuantidade: "
                + getQuantidade() + "\n";
    }

}
