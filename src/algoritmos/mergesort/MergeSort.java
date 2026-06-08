package mergesort;

public class MergeSort {
    public static void mergesort(int[] v) {
        if (v == null || v.length <= 1) return;
        int[] aux = new int[v.length];
        mergesortRecursive(v, aux, 0, v.length - 1);
    }

    private static void mergesortRecursive(int[] a, int[] aux, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergesortRecursive(a, aux, left, mid);
        mergesortRecursive(a, aux, mid + 1, right);
        merge(a, aux, left, mid, right);
    }

    private static void merge(int[] a, int[] aux, int left, int mid, int right) {
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            if (a[i] <= a[j]) aux[k++] = a[i++];
            else aux[k++] = a[j++];
        }
        while (i <= mid) aux[k++] = a[i++];
        while (j <= right) aux[k++] = a[j++];
        for (k = left; k <= right; k++) a[k] = aux[k];
    }
}

