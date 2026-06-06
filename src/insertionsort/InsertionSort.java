public static void insertionsort(int[] v) {
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