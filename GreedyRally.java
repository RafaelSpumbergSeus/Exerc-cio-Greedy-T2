import java.util.ArrayList;
import java.util.List;

public class GreedyRally {

    // Método que retorna a lista de paradas escolhidas e conta iterações
    public static List<Integer> calcularParadas(int L, int d, List<Integer> paradas, int[] iteracoes) {
        paradas.add(L);  // Adiciona o final como parada
        List<Integer> paradasEscolhidas = new ArrayList<>();

        int posicaoAtual = 0;
        int i = 0;
        int n = paradas.size();

        while (i < n) {
            iteracoes[0]++;  

            int proximaParada = i;
            // Avança até o ponto mais distante possível sem ultrapassar 'd'
            while (proximaParada < n && paradas.get(proximaParada) - posicaoAtual <= d) {
                proximaParada++;
            }

            if (proximaParada - 1 == i - 1) {
                System.out.println("Não é possível chegar ao final com as paradas fornecidas.");
                return null;
            }

            int paradaEscolhida = paradas.get(proximaParada - 1);
            paradasEscolhidas.add(paradaEscolhida);
            posicaoAtual = paradaEscolhida;

            i = proximaParada;
        }

        return paradasEscolhidas;
    }

    public static void main(String[] args) {
        int L = 25;
        int d = 10;
        List<Integer> paradas = new ArrayList<>();
        paradas.add(4);
        paradas.add(9);
        paradas.add(13);
        paradas.add(18);
        paradas.add(22);
        paradas.add(24);
        paradas.add(26);  
        paradas.add(30);  
        //paradas.add(45);  

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

