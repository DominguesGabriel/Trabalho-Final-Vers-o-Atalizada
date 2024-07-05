package sistema_doacao;

/**
 * Uma classe chamada Pessoa: os objetos dessa classe representam pessoas que
 * podem receber doações. Para tal, será necessário armazenar as seguintes
 * informações: CPF, Nome, Email, Telefone, ListaDeDoacoesRecebidas.
 *
 * @author Gabriel Domingues
 * @version 05/07/2024
 */
public class Pessoa {

    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private ListaDoacoes listaDeDoacoesRecebidas;

    public Pessoa(String nome, String cpf, String email, String telefone) {
        setNome(nome);
        setCpf(cpf);
        setEmail(email);
        setTelefone(telefone);
        listaDeDoacoesRecebidas = new ListaDoacoes();

    }

    public boolean adicionaDoacao(Doacao d) {
        if (d == null) {
            return false;
        }

        if (listaDeDoacoesRecebidas.buscaDoacaoPorCodigo(d.getCodigoDaDoacao()) != null) {
            listaDeDoacoesRecebidas.adicionaQuantidade(d.getCodigoDaDoacao(), d.getQuantidade());
            return true;
        }

        return listaDeDoacoesRecebidas.cadastraDoacao(d);
    }

    public void setCpf(String cpf) {
        if (validaCpf(cpf)) {
            this.cpf = cpf;
        }

    }

    public void setEmail(String email) {
        if (email != null && email.contains("@")) {
            this.email = email;
        }
    }

    public void setNome(String nome) {
        if (nome != null && !nome.isEmpty()) {
            this.nome = nome;
        }
    }

    public boolean validaTelefone(String telefone) {
        String telefoneFormatado;
        char primeiroCaracter;
        boolean numerosRepetidos = true;

        telefoneFormatado = telefone.replaceAll("[^0-9]", "");
        // Esta expressão regular substitui todos os caracteres que não são dígitos
        // (0-9) por uma string vazia,
        // removendo assim os caracteres que não são parte do número do telefone.
        if (telefoneFormatado.length() != 11) {
            return false;
        }

        primeiroCaracter = telefoneFormatado.charAt(0);
        for (int i = 0; i < telefoneFormatado.length(); i++) {
            if (telefoneFormatado.charAt(i) != primeiroCaracter) {
                numerosRepetidos = false;
            }
        }

        if (numerosRepetidos) {
            return false;
        }

        return true;
    }

    public void setTelefone(String telefone) {
        if (validaTelefone(telefone)) {
            this.telefone = telefone;
        }
    }

    public String getCpf() {
        return cpf.replaceAll("[^0-9]", "");
    }

    public String getCpfFormatado() {
        String cpfFormatado = cpf.replaceAll("[^0-9]", "");
        String parte1, parte2, parte3, parte4;
        parte1 = cpfFormatado.substring(0, 3);
        parte2 = cpfFormatado.substring(3, 6);
        parte3 = cpfFormatado.substring(6, 9);
        parte4 = cpfFormatado.substring(9, 11);
        return parte1 + "." + parte2 + "." + parte3 + "-" + parte4;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public boolean validaCpf(String cpf) {
        char primeiroDigito;
        boolean numerosIguais = true;
        int soma = 0;
        int digito1;
        int digito2;
        if (cpf == null) {
            return false;
        }
        String cpfFormatado = cpf.replaceAll("[^0-9]", "");

        // Esta expressão regular substitui todos os caracteres que não são dígitos
        // (0-9) por uma string vazia,
        // removendo assim os caracteres que não são parte do número do CPF.
        if (cpfFormatado.length() != 11) {
            return false;
        }

        primeiroDigito = cpfFormatado.charAt(0);
        for (int i = 0; i < cpfFormatado.length(); i++) {
            if (cpfFormatado.charAt(i) != primeiroDigito) {
                numerosIguais = false;
            }
        }
        if (numerosIguais) {
            return false;
        }

        for (int j = 0; j < (cpfFormatado.length() - 2); j++) {
            soma = soma + Character.getNumericValue(cpfFormatado.charAt(j)) * (10 - j);
            // Este método da classe Character converte um caractere em um valor numérico. A
            // classe Character é semelhante ao tipo primitivo char,
            // mas como é uma classe, possui métodos que permitem operações sobre
            // caracteres.
        }
        digito1 = 11 - (soma % 11);
        if (digito1 > 9) {
            digito1 = 0;
        }
        if (Character.getNumericValue(cpfFormatado.charAt(9)) != digito1) {
            return false;
        }
        soma = 0;

        for (int j = 0; j < (cpfFormatado.length() - 1); j++) {
            soma = soma + Character.getNumericValue(cpfFormatado.charAt(j)) * (11 - j);
            // Este método da classe Character converte um caractere em um valor numérico. A
            // classe Character é wrapper ao tipo primitivo char,
            // mas como é uma classe, possui métodos que permitem operações sobre
            // caracteres.
        }

        digito2 = 11 - (soma % 11);
        if (digito2 > 9) {
            digito2 = 0;
        }

        if (Character.getNumericValue(cpfFormatado.charAt(10)) != digito2) {
            return false;
        }

        return true;
    }

    public String formataCpf(String cpf) {
        if (!validaCpf(cpf)) {
            return null;
        }
        String cpfFormatado = cpf.replaceAll("[^0-9]", "");
        String parte1, parte2, parte3, parte4;
        parte1 = cpfFormatado.substring(0, 3);
        parte2 = cpfFormatado.substring(3, 6);
        parte3 = cpfFormatado.substring(6, 9);
        parte4 = cpfFormatado.substring(9, 11);
        return parte1 + "." + parte2 + "." + parte3 + "-" + parte4;

    }

    public String cpfApenasDigitos(String cpf) {
        if (!validaCpf(cpf)) {
            return null;
        }
        String cpfFormatado = cpf.replaceAll("[^0-9]", "");
        return cpfFormatado;
    }

    public String toString() {
        return "Nome: " + getNome() + "\nCPF: " + getCpfFormatado() + "\nemail: " + getEmail() + "\nTelefone: "
                + getTelefone() + "\n";
    }
}
