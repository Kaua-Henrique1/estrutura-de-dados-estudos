package introsort;

public class IntroSort {

    public static void sort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        // Calcula o limite de profundidade teórico: 2 * floor(log2(n))
        int depthLimit = 2 * (int) (Math.log(array.length) / Math.log(2));
        introSortHelper(array, 0, array.length - 1, depthLimit);
    }

    private static void introSortHelper(int[] a, int lo, int hi, int depthLimit) {
        if (lo >= hi) return;

        // Se o subarray for pequeno, usa Insertion Sort (limite clássico de 16)
        if (hi - lo + 1 <= 16) {
            insertionSort(a, lo, hi);
        }
        // Se a recursão estourar o limite, muda para HeapSort para garantir O(n log n)
        else if (depthLimit == 0) {
            heapSort(a, lo, hi);
        }
        // Caso contrário, continua com o QuickSort convencional
        else {
            int p = partition(a, lo, hi);
            introSortHelper(a, lo, p - 1, depthLimit - 1);
            introSortHelper(a, p + 1, hi, depthLimit - 1);
        }
    }

    private static int partition(int[] a, int lo, int hi) {
        // Pivô simples no final (estilo Lomuto tradicional de sala de aula)
        int pivot = a[hi];
        int i = lo - 1;
        for (int j = lo; j < hi; j++) {
            if (a[j] <= pivot) {
                i++;
                swap(a, i, j);
            }
        }
        swap(a, i + 1, hi);
        return i + 1;
    }

    private static void insertionSort(int[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= lo && a[j] > key) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }

    private static void heapSort(int[] a, int lo, int hi) {
        int n = hi - lo + 1;
        for (int i = n / 2 - 1; i >= 0; i--) {
            siftDown(a, i, n - 1, lo);
        }
        for (int i = n - 1; i > 0; i--) {
            swap(a, lo, lo + i);
            siftDown(a, 0, i - 1, lo);
        }
    }

    private static void siftDown(int[] a, int root, int end, int lo) {
        while (true) {
            int largest = root;
            int left = 2 * root + 1;
            int right = 2 * root + 2;

            if (left <= end && a[lo + left] > a[lo + largest]) largest = left;
            if (right <= end && a[lo + right] > a[lo + largest]) largest = right;
            if (largest == root) break;

            swap(a, lo + root, lo + largest);
            root = largest;
        }
    }

    private static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}