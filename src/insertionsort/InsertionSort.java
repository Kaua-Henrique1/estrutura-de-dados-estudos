package insertionsort;

// Small utility class that provides insertion sort.
// The file previously contained only a free-standing method which caused
// "class, interface, or enum expected" compilation errors. Wrap it in a class.
public class InsertionSort {
    public static void insertionsort(int[] v) {
        if (v == null || v.length <= 1) return;
        int n = v.length;
        for (int i = 1; i <= n - 1; i++) {
            int aux = v[i];
            int j = i - 1;
            while (j >= 0 && aux < v[j]) {
                v[j + 1] = v[j];
                j = j - 1;
            }
            v[j + 1] = aux;
            //System.out.println(Arrays.toString(v)); // passo-a-passo
        }
    }
}