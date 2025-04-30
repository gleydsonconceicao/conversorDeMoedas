import java.util.Map;
import java.util.Scanner;

import com.conversordemoedas.Conversao;
import com.conversordemoedas.ConversorMoeda;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConversorMoeda conversor = new ConversorMoeda();

        Map<Integer, Conversao> opcoes = Map.of(
                1, new Conversao("USD", "ARS"),
                2, new Conversao("ARS", "USD"),
                3, new Conversao("USD", "BRL"),
                4, new Conversao("BRL", "USD"),
                5, new Conversao("USD", "COP"),
                6, new Conversao("COP", "USD")
        );

        while (true) {
            System.out.println("**********************************************************************");
            System.out.println("Seja bem-vindo/a ao Conversor de Moeda =]");
            System.out.println();
            System.out.println("1) Dólar =>> Peso argentino");
            System.out.println("2) Peso argentino =>> Dólar");
            System.out.println("3) Dólar =>> Real brasileiro");
            System.out.println("4) Real brasileiro =>> Dólar");
            System.out.println("5) Dólar =>> Peso colombiano");
            System.out.println("6) Peso colombiano =>> Dólar");
            System.out.println("7) Sair");
            System.out.print("Escolha uma opção válida: ");

            int escolha;
            try {
                escolha = Integer.parseInt(scanner.nextLine());
                System.out.println("**********************************************************************");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Por favor, digite um número de 1 a 7.");
                continue;
            }

            if (escolha == 7) {
                System.out.println("Programa encerrado.");
                break;
            }

            if (!opcoes.containsKey(escolha)) {
                System.out.println("Opção inválida! Por favor, tente novamente.");
                continue;
            }

            System.out.print("Digite o valor a ser convertido: ");
            double valor;
            try {
                valor = Double.parseDouble(scanner.nextLine());
                if (valor <= 0) {
                    System.out.println("Valor deve ser maior que zero.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido! Digite um número válido.");
                continue;
            }

            Conversao conversao = opcoes.get(escolha);
            try {
                double taxa = conversor.obterTaxa(conversao);
                double resultado = valor * taxa;
                System.out.printf("Valor convertido: %.2f %s%n", resultado, conversao.moedaDestino());
            } catch (Exception e) {
                System.out.println("Erro ao buscar a taxa de câmbio: " + e.getMessage());
            }
        }

        scanner.close();
    }

}
