import java.util.Random;
import introsort.IntroSort;
import quicksort.QuickSort;
import mergesort.MergeSort;
import insertionsort.InsertionSort;
import bogosort.BogoSort;

public class TestesSimplesTempo {
    public static void imprimirVetor(String texto, int [] v) {
        System.out.print(texto);
        for (int i = 0; i < v.length; i++)
            System.out.print(v[i] + " ");
        System.out.print("\n");
    }

    public static void main(String[] args) {
        int n = 100000;
        long inicio, fim;
        Random numero = new Random();

        int[] vetor1 = new int[n];
        for (int i = 0; i < vetor1.length; i++) {
            vetor1[i] = numero.nextInt(10000);
        }

        int[] vetor6 = vetor1.clone(); // Para o MergeSort
        int[] vetor7 = vetor1.clone(); // Para o QuickSort
        int[] vetor8 = vetor1.clone(); // NOVO: Reservado para o IntroSort

        System.out.println("Vetor para ser ordenado de "+vetor1.length);
        // --- Teste do MergeSort ---
        inicio = System.currentTimeMillis();
        MergeSort.mergesort(vetor6);
        fim = System.currentTimeMillis();
        System.out.println("MergeSort: " + (fim - inicio) + " milisegundos");

        // --- Teste do Quicksort ---
        inicio = System.currentTimeMillis();
        QuickSort.quicksort(vetor7);
        fim = System.currentTimeMillis();
        System.out.println("Quicksort: " + (fim - inicio) + " milisegundos");

        // --- Teste do IntroSort ---
        inicio = System.currentTimeMillis();
        IntroSort.sort(vetor8); // Chama o método estático simplificado
        fim = System.currentTimeMillis();
        System.out.println("IntroSort: " + (fim - inicio) + " milisegundos");

        // --- Teste do BogoSort (apenas com 10 números, pois é muito lento) ---
        int[] vetorBogo = new int[10];
        Random numeroBogo = new Random();
        for (int i = 0; i < vetorBogo.length; i++) {
            vetorBogo[i] = numeroBogo.nextInt(100);
        }

        inicio = System.currentTimeMillis();
        BogoSort.sort(vetorBogo);
        fim = System.currentTimeMillis();
        System.out.println("BogoSort (10 elementos): " + (fim - inicio) + " milisegundos");
    }
}