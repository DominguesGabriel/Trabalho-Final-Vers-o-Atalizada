package sistema_doacao;

/**
 * A seguir, implemente uma Lista de Pessoas: Defina os seguintes métodos
 * necessárias para manipular essa lista.
 *
 * - método que cadastra uma pessoa. Não podem ter pessoas com o mesmo CPF nesta
 * lista; - método que consulta uma pessoa pelo CPF; - método que ordena todas
 * as pessoas pelo CPF; - método que ordena todas as pessoas pelo nome; - método
 * que altera o email de uma pessoa, localizando a pessoa pelo CPF; - método que
 * lista todas as Pessoas Cadastradas.
 *
 * @author Gabriel Domingues
 * @version 05/07/2024
 */
public class ListaPessoas {

    private Pessoa[] listaPessoas;
    private int index;
    private int tamanho;

    public ListaPessoas() {
        tamanho = 10;
        listaPessoas = new Pessoa[tamanho];
        index = 0;
    }

    public boolean cadastraPessoa(Pessoa p) {
        if (index == 0) {
            listaPessoas[index] = p;
            index++;
            return true;
        }

        if (index == tamanho) {
            listaPessoas = listaInfinita();
        }

        for (int i = 0; i < index; i++) {
            if (listaPessoas[i].getCpf().equals(p.getCpf())) {
                return false;
            }
        }

        listaPessoas[index] = p;
        index++;
        return true;
    }

    private Pessoa[] listaInfinita() {
        Pessoa[] listaNova = new Pessoa[tamanho * 2];
        for (int i = 0; i < index; i++) {
            listaNova[i] = listaPessoas[i];
        }
        return listaNova;

    }

    public Pessoa buscaPessoaPorCpf(String cpf) {

        cpf = cpf.replaceAll("[^0-9]", "");

        for (int i = 0; i < index; i++) {
            if (listaPessoas[i].getCpf().equals(cpf)) {
                return listaPessoas[i];
            }
        }
        return null;
    }

    public Pessoa[] ordenaPorCpf() {
        Pessoa[] listaOrdenada = new Pessoa[index];
        for (int lista = 0; lista < index; lista++) {
            listaOrdenada[lista] = listaPessoas[lista];
        }
        boolean controle = false;
        Pessoa aux;
        for (int i = 0; i < index; i++) {
            controle = true;
            for (int j = 0; j < (index - 1); j++) {
                if (listaOrdenada[j].getCpf().compareTo(listaOrdenada[j + 1].getCpf()) < 0) {
                    aux = listaOrdenada[j + 1];
                    listaOrdenada[j + 1] = listaOrdenada[j];
                    listaOrdenada[j] = aux;
                    controle = false;
                }
            }
            if (!controle) {
                break;
            }
        }
        return listaOrdenada;

    }

    public Pessoa[] ordenaPorNome() {
        Pessoa[] listaOrdenada = new Pessoa[index];
        for (int lista = 0; lista < index; lista++) {
            listaOrdenada[lista] = listaPessoas[lista];
        }
        boolean controle = false;
        Pessoa aux;
        for (int i = 0; i < index; i++) {
            controle = true;
            for (int j = 0; j < (index - 1); j++) {
                if (listaOrdenada[j].getNome().compareTo(listaOrdenada[j + 1].getNome()) > 0) {
                    aux = listaOrdenada[j + 1];
                    listaOrdenada[j + 1] = listaOrdenada[j];
                    listaOrdenada[j] = aux;
                    controle = false;
                }
            }
            if (!controle) {
                break;
            }
        }
        return listaOrdenada;

    }

    public boolean alteraEmailPorCpf(String cpf, String novoEmail) {
        String cpfSemDigito = cpf.replaceAll("[^0-9]", "");

        for (int i = 0; i < index; i++) {
            if (listaPessoas[i].getCpf().equals(cpfSemDigito)) {
                listaPessoas[i].setEmail(novoEmail);
                return true;
            }
        }
        return false;
    }

    public String toString() {
        String retorno = "";
        for (int i = 0; i < index; i++) {
            retorno = retorno + listaPessoas[i].toString();
        }
        return retorno;
    }
}
