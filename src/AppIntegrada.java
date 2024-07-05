
import java.util.Scanner;
import sistema_doacao.Doacao;
import sistema_doacao.ListaDoacoes;
import sistema_doacao.ListaPessoas;
import sistema_doacao.Pessoa;

/**
 * @author Gabriel Domingues
 * @version 05/07/2024
 */
public class AppIntegrada {

    private static final ListaPessoas LISTAPESSOAS = new ListaPessoas();
    private static final ListaDoacoes LISTADOACOES = new ListaDoacoes();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int opcao;
        do {
            imprimeMenuPrincipal();
            opcao = Integer.parseInt(in.nextLine());
            switch (opcao) {
                case 1:
                    imprimeMenuPessoa();
                    int opcaoPessoa = Integer.parseInt(in.nextLine());
                    processaOpcaoPessoa(opcaoPessoa, in);
                    break;
                case 2:
                    imprimeMenuDoacao();
                    int opcaoDoacao = Integer.parseInt(in.nextLine());
                    processaOpcaoDoacao(opcaoDoacao, in);
                    break;
                case 0:
                    System.out.println("FIM");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static void processaOpcaoPessoa(int opcao, Scanner in) {
        switch (opcao) {
            case 1:
                cadastraPessoa(in);
                break;
            case 2:
                consultarPessoaPeloCpf(in);
                break;
            case 3:
                alterarEmailPorCpf(in);
                break;
            case 4:
                System.out.println(LISTAPESSOAS.ordenaPorCpf().toString());
                break;
            case 5:
                System.out.println(LISTAPESSOAS.ordenaPorNome().toString());
                break;
            case 6:
                System.out.println(LISTAPESSOAS.toString());
                break;
            case 7:
                pessoaEscolheDoacao(in);
                break;
            case 8:
                System.out.println("VOLTANDO");
                break;
            case 0:
                System.out.println("FIM");
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private static void processaOpcaoDoacao(int opcao, Scanner in) {
        switch (opcao) {
            case 1:
                cadastraDoacao(in);
                break;
            case 2:
                consultarDoacaoPorDescricao(in);
                break;
            case 3:
                adicionarQuantidadeItens(in);
                break;
            case 4:
                removerQuantidadeItens(in);
                break;
            case 5:
                listarDoacoesComQuantidadeSuperior(in);
                break;
            case 6:
                System.out.println(LISTADOACOES.toString());
                break;
            case 7:
                System.out.println("VOLTANDO");
                break;
            case 0:
                System.out.println("FIM");
                break;
            default:
                System.err.println("Opção inválida!");
        }
    }

    private static void cadastraPessoa(Scanner in) {
        System.out.println("Digite o nome da pessoa:");
        String nome = in.nextLine();
        System.out.println("Digite o CPF da pessoa:");
        String cpf = in.nextLine();
        System.out.println("Digite o email da pessoa:");
        String email = in.nextLine();
        System.out.println("Digite o telefone da pessoa:");
        String telefone = in.nextLine();

        Pessoa p = new Pessoa(nome, cpf, email, telefone);
        LISTAPESSOAS.cadastraPessoa(p);
        System.out.println("Pessoa cadastrada com sucesso!");
    }

    private static void consultarPessoaPeloCpf(Scanner in) {
        System.out.println("Digite o CPF da pessoa:");
        String cpf = in.nextLine();
        Pessoa p = LISTAPESSOAS.buscaPessoaPorCpf(cpf);
        if (p != null) {
            System.out.println(p);
        } else {
            System.err.println("Pessoa não encontrada!");
        }
    }

    private static void alterarEmailPorCpf(Scanner in) {
        System.out.println("Digite o CPF da pessoa:");
        String cpf = in.nextLine();
        System.out.println("Digite o novo email:");
        String email = in.nextLine();
        LISTAPESSOAS.alteraEmailPorCpf(cpf, email);
        System.out.println("Email alterado com sucesso!");
    }

    private static void pessoaEscolheDoacao(Scanner in) {
        System.out.println("Digite o CPF da pessoa:");
        String cpf = in.nextLine();
        Pessoa p = LISTAPESSOAS.buscaPessoaPorCpf(cpf);
        if (p != null) {
            System.out.println("Digite o nome da doação:");
            String nomeDoacao = in.nextLine();
            Doacao d = LISTADOACOES.buscaDoacaoPorDescricao(nomeDoacao);
            if (d != null) {
                System.out.println("Digite a quantidade:");
                int quantidade = Integer.parseInt(in.nextLine());
                if (d.getQuantidade() >= quantidade) {
                    LISTADOACOES.removeQuantidade(d.getDescricao(), quantidade);
                    System.out.println("Doação realizada com sucesso!");
                } else {
                    System.err.println("Quantidade insuficiente!");
                }
            } else {
                System.err.println("Doação não encontrada!");
            }
        } else {
            System.err.println("Pessoa não encontrada!");
        }
    }

    private static void cadastraDoacao(Scanner in) {
        System.out.println("Digite a descrição da doação:");
        String descricao = in.nextLine();
        System.out.println("Digite a quantidade:");
        int quantidade = Integer.parseInt(in.nextLine());
        System.out.println("Digite o código da doação:");
        String codigoDaDoacao = in.nextLine();
        Doacao d = new Doacao(codigoDaDoacao, descricao, quantidade);
        LISTADOACOES.cadastraDoacao(d);
        System.out.println("Doação cadastrada com sucesso!");
    }

    private static void consultarDoacaoPorDescricao(Scanner in) {
        System.out.println("Digite a descrição da doação:");
        String descricao = in.nextLine();
        Doacao d = LISTADOACOES.buscaDoacaoPorDescricao(descricao);
        if (d != null) {
            System.out.println(d);
        } else {
            System.err.println("Doação não encontrada!");
        }
    }

    private static void adicionarQuantidadeItens(Scanner in) {
        System.out.println("Digite a descrição da doação:");
        String descricao = in.nextLine();
        System.out.println("Digite a quantidade a adicionar:");
        int quantidade = Integer.parseInt(in.nextLine());
        LISTADOACOES.adicionaQuantidade(descricao, quantidade);
        System.out.println("Quantidade adicionada com sucesso!");
    }

    private static void removerQuantidadeItens(Scanner in) {
        System.out.println("Digite a descrição da doação:");
        String descricao = in.nextLine();
        System.out.println("Digite a quantidade a remover:");
        int quantidade = Integer.parseInt(in.nextLine());
        LISTADOACOES.removeQuantidade(descricao, quantidade);
        System.out.println("Quantidade removida com sucesso!");
    }

    private static void listarDoacoesComQuantidadeSuperior(Scanner in) {
        System.out.println("Digite a quantidade mínima:");
        int quantidade = Integer.parseInt(in.nextLine());
        System.out.println(LISTADOACOES.retornaDoacoesXItens(quantidade).toString());
    }

    private static void imprimeMenuPrincipal() {
        System.out.println("-----------------------MENU PRINCIPAL---------------------------");
        System.out.println("[1] Pessoas");
        System.out.println("[2] Doações");
        System.out.println("[0] Sair");
        System.out.println("----------------------------------------------------------------");
    }

    private static void imprimeMenuPessoa() {
        System.out.println("-----------------------MENU PESSOA---------------------------");
        System.out.println("[1] Cadastrar pessoa");
        System.out.println("[2] Consultar pessoa pelo CPF");
        System.out.println("[3] Alterar email, buscando através do CPF");
        System.out.println("[4] Lista pessoas ordenado pelo CPF");
        System.out.println("[5] Lista pessoas ordenado pelo nome");
        System.out.println("[6] Listar todas as pessoas cadastradas no programa");
        System.out.println("[7] Pessoa escolhe doação");
        System.out.println("[8] Voltar");
        System.out.println("[0] Sair");
        System.out.println("-------------------------------------------------------------");
    }

    private static void imprimeMenuDoacao() {
        System.out.println("-----------------------MENU DOAÇÃO---------------------------");
        System.out.println("[1] Cadastrar doação");
        System.out.println("[2] Consultar doação pela descrição");
        System.out.println("[3] Adicionar quantidade de itens");
        System.out.println("[4] Remover quantidade de itens");
        System.out.println("[5] Obter doações com quantidade em estoque superior a X");
        System.out.println("[6] Listar todas as doações cadastradas no programa");
        System.out.println("[7] Voltar");
        System.out.println("[0] Sair");
        System.out.println("-------------------------------------------------------------");
    }
}
