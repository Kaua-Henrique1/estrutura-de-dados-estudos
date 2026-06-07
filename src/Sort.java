/* Utility Sort facade used by the test harness.
   Provides mergesort and quicksort static methods so existing tests that
   call Sort.mergesort(...) and Sort.quicksort(...) continue to work.
   mergesort is implemented here; quicksort delegates to the QuickSort class
   (which exists in the project).
*/
public class Sort {
    // Public mergesort API
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

    // Public quicksort API that delegates to existing QuickSort implementation
    public static void quicksort(int[] v) {
        if (v == null || v.length <= 1) return;
        QuickSort.quicksort(v);
    }
}

