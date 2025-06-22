import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GreedyRally {
    public static List<Integer> calcularParadas(int L, int d, List<Integer> paradas, int[] iteracoes) {
       if (L <= 0 || d <= 0 || paradas == null) {
            throw new IllegalArgumentException("Parâmetros inválidos");
        }

        List<Integer> paradasOrdenadas = new ArrayList<>(paradas);
        
        Collections.sort(paradasOrdenadas);
        
        paradasOrdenadas.add(L);
        
        List<Integer> paradasEscolhidas = new ArrayList<>();
        int posicaoAtual = 0;
        int i = 0;
        int n = paradasOrdenadas.size();

        while (i < n) {
            iteracoes[0]++;  

            int proximaParada = i;
            while (proximaParada < n && paradasOrdenadas.get(proximaParada) - posicaoAtual <= d) {
                proximaParada++;
            }

            if (proximaParada == i) {
                System.err.println("Não é possível chegar ao ponto " + paradasOrdenadas.get(i) + 
                                 " a partir da posição atual " + posicaoAtual + 
                                 " com distância máxima diária de " + d);
                return null;
            }

            int paradaEscolhida = paradasOrdenadas.get(proximaParada - 1);
            paradasEscolhidas.add(paradaEscolhida);
            posicaoAtual = paradaEscolhida;

            if (posicaoAtual == L) {
                break;
            }

            i = proximaParada;
        }

        if (posicaoAtual != L) {
            System.err.println("Não foi possível completar o percurso com as paradas fornecidas.");
            return null;
        }

        return paradasEscolhidas;
    }

    public static void main(String[] args) {
        // Caso de teste 1: Caso normal
        int L = 25;
        int d = 10;
        List<Integer> paradas = new ArrayList<>();
        
       //Caso de teste 1: Paradas ordenadas
        /* 
        paradas.add(4);
        paradas.add(9);
        paradas.add(13);
        paradas.add(18);
        paradas.add(22);
        paradas.add(24);
        */

        // Caso de teste 2: Paradas não ordenadas
        /* 
        System.out.println("Teste com paradas não ordenadas:");
        paradas.add(18);
        paradas.add(4);
        paradas.add(24);
        paradas.add(13);
        paradas.add(9);
        paradas.add(22);
        */

        // Caso de teste 3: Caso impossível 
        System.out.println("Teste com caso impossível:");
        paradas.add(4);
        paradas.add(9);
        paradas.add(13);
        paradas.add(30); // Ponto inalcançável

        int[] iteracoes = {0};  

        long startTime = System.nanoTime();  

        List<Integer> resultado = calcularParadas(L, d, paradas, iteracoes);

        long endTime = System.nanoTime();  
        long durationNano = endTime - startTime;
        double durationMillis = durationNano / 1_000_000.0;

        if (resultado != null) {
            System.out.println("Paradas escolhidas:");
            for (int parada : resultado) {
                System.out.print(parada + " ");
            }
            System.out.println();
            System.out.println("Número de iterações no loop principal: " + iteracoes[0]);
            System.out.printf("Tempo de execução: %.4f ms%n", durationMillis);
        }
    }
}