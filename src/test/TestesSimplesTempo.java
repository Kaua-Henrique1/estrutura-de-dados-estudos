import java.util.Random;
import introsort.IntroSort; // Importa a classe que criamos acima

public class TestesSimplesTempo {
    public static void imprimirVetor(String texto, int [] v) {
        System.out.print(texto);
        for (int i = 0; i < v.length; i++)
            System.out.print(v[i] + " ");
        System.out.print("\n");
    }

    public static void main(String[] args) {
        int n = 100000; // Altere este valor para 10000, 100000, etc., para gerar seu gráfico
        long inicio, fim;
        Random numero = new Random();

        int[] vetor1 = new int[n];
        for (int i = 0; i < vetor1.length; i++)
            vetor1[i] = numero.nextInt(10000);

        // Clones para garantir que todos testem exatamente o mesmo vetor desordenado
        int[] vetor6 = vetor1.clone(); // Para o Mergesort original
        int[] vetor7 = vetor1.clone(); // Para o Quicksort original
        int[] vetor8 = vetor1.clone(); // NOVO: Reservado para o IntroSort

        // --- Teste do Mergesort original ---
        inicio = System.currentTimeMillis();
        Sort.mergesort(vetor6);
        fim = System.currentTimeMillis();
        System.out.println("Mergesort: " + (fim - inicio) + " milisegundos");

        // --- Teste do Quicksort original ---
        inicio = System.currentTimeMillis();
        Sort.quicksort(vetor7);
        fim = System.currentTimeMillis();
        System.out.println("Quicksort: " + (fim - inicio) + " milisegundos");

        // --- NOVO: Teste do IntroSort ---
        inicio = System.currentTimeMillis();
        IntroSort.sort(vetor8); // Chama o método estático simplificado
        fim = System.currentTimeMillis();
        System.out.println("IntroSort: " + (fim - inicio) + " milisegundos");
    }
}