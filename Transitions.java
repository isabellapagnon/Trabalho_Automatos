import java.util.ArrayList;

public class Transitions {

    public void efetuaTransicao() {
        ReadRules rules = new ReadRules();
        rules.lerRules();
        ReadInput input = new ReadInput();
        input.lerInput();

        ArrayList<String> estadoFinal = rules.finalStates;

        // Seguindo o padrão de 2 simbolos, cada estado deverá ter 2 duas transiçoes,
        // caso tenha mais do que isso, o automato estará com transições a mais
        if (rules.linhas.size() > ((rules.TotEstados.size() * 2) + 2)) {
            System.out.println("AFD tem mais transições do que deveria");
        }

        // Seguindo o padrão de 2 simbolos, cada estado deverá ter 2 duas transiçoes,
        // caso tenha menos que isso, o automato estará incompleto
        else if (rules.linhas.size() < ((rules.TotEstados.size() * 2) + 2)) {
            System.out.println("AFD não está completo");
        }

        for (String entrada : input.inputLine) { // ira percorrer todo array que possui as entradas do arquivo input
            Boolean acceptWord = null;

            String posicaoAtual = rules.TotEstados.get(0);// posicaoAtual inicia com o primeiro caracter da linha 1 do
                                                          // rules

            for (int i = 1; i <= entrada.length(); i++) { // ira percorrer uma palavra do inicio ao final.

                String simbolInput = entrada.substring(i - 1, i); // ira separar cada caracter da palavra

                // keyToSearch ira receber a concatenacao da posicao atual + caracter da palavra
                // de entrada
                // Ex: z 1 y. ira virar z1 (que é como as keys estao montadas no rules)
                String keyToSearch = posicaoAtual.concat(simbolInput);

                if (rules.transicoes.containsKey(keyToSearch)) { // Esse ira procurar por essa key

                    posicaoAtual = rules.transicoes.get(keyToSearch); // posicaoAtual ira receber o value da key
                    // System.out.println("Posicao atual " + posicaoAtual);

                } else {
                    if (!rules.transicoes.containsValue(simbolInput)) {
                        System.out.println("Entrada inválida: Estado ou símbolo não fazem parte do alfabeto");
                    }
                    acceptWord = false; // Se nao encontrar a key, é porque a entrada é invalida.
                    break;
                }

                if (i == entrada.length()) { // Estando no ultimo caracter da palavra de entrada serao tomadas as
                                             // decisoes de Accept ou Reject da palavra

                    if (estadoFinal.contains(posicaoAtual.toString())) { // Se a posição final está dentro do ArrayList
                                                                         // de estados de aceitação então validamos que
                                                                         // a entrada é válida
                        acceptWord = true;
                    } else {
                        acceptWord = false;
                    }

                }

            }

            System.out.println("Palavra:" + entrada + " ==> " + acceptWord);

        }

    }

}
