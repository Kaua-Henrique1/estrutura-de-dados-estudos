package bogosort;

import java.util.Random;

public class BogoSort {
    public static void sort(int[] v) {
        if (v == null || v.length <= 1) return;

        Random rand = new Random();
        boolean ordenado = false;

        // O ciclo continua a rodar enquanto o vetor não estiver ordenado
        while (!ordenado) {

            // 1. PASSO: Verificar se está ordenado
            ordenado = true;
            for (int i = 1; i < v.length; i++) {
                if (v[i] < v[i - 1]) {
                    ordenado = false;
                    break;
                }
            }

            // 2. PASSO: Se não estiver ordenado, baralha tudo
            if (!ordenado) {
                for (int i = 0; i < v.length; i++) {
                    int randomIndex = rand.nextInt(v.length);
                    // Troca os elementos de lugar
                    int temp = v[i];
                    v[i] = v[randomIndex];
                    v[randomIndex] = temp;
                }
            }
        }
    }
}