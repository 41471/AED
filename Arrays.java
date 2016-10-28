package series.serie1;


public class Arrays {

    private static final int MAX_IP = 255;
    private static final int CUTOFF = 15;

    /******************************************
     * EX2
     ******************************/
    public static boolean isPermutation(int[] a1, int l1, int r1, int[] a2, int l2, int r2) {

        if ((r1 - l1) != (r2 - l2)) return false;

        mergeSort(a1, l1, r1);
        mergeSort(a2, l2, r2);
        int i = l1, j = l2;
        for (; i <= r1 && j <= r2; i++, j++) {
            if (a1[i] != a2[j]) return false;
        }
        return true;
    }


    static void merge(int[] array, int left, int mid, int right) {
        int i, j;
        // Auxiliary array
        int[] aux = new int[array.length];
        // Copy original array to auxiliary array,
        // but invert the right part (see Figure 8.1),
        // in order to eliminate the need for limit tests
        for (i = mid + 1; i > left; i--)
            aux[i - 1] = array[i - 1];
        for (j = mid; j < right; j++)
            aux[right + mid - j] = array[j + 1];

        for (int k = left; k <= right; k++) {
            if (lesser(aux[j], aux[i]))
                array[k] = aux[j--];
            else
                array[k] = aux[i++];
        }
    }

    static boolean lesser(int v, int w) {
        return v < w;
    }

    static void exchange(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    static void compExch(int[] a, int i, int j) {
        if (lesser(a[j], a[i]))
            exchange(a, i, j);
    }

    public static void mergeSort(int[] a, int l, int r) {
        if (r <= l)
            return;
        int mid = (r + l) / 2;
        // Sort the left part recursively
        mergeSort(a, l, mid);
        // Sort the right part recursively
        mergeSort(a, mid + 1, r);
        // Merge the sorted left and right parts
        merge(a, l, mid, r);
    }


    public static int countEquals(int[] v1, int l1, int r1, int[] v2, int l2, int r2) {
        int count = 0;
        while (l1 <= r1 && l2 <= r2) {
            if (v1[l1] > v2[l2]) l2++;
            else if (v1[l1] < v2[l2]) l1++;
            else {
                count++;
                l1++;
                l2++;
            }
        }
        return count;
    }


    /************************************
     * EX 1
     ***********************************/


    public static int greatestAfterRotate(int[] v, int l, int r) {

        int greater, mid, rigth = r;

        if ((r - l + 1) <= 0)
            return -1;

        if ((r - l + 1) == 1)
            return v[l];

        mid = (l + r) / 2;
        greater = v[mid];

        while (l <= r) {
            mid = (l + r) / 2;

            if (greater < v[mid]) {
                greater = v[mid];
                l = mid + 1;
            } else
                r = mid - 1;
        }

        if (v[rigth] > greater)
            greater = v[rigth];

        return greater;

    }

    /****************************************
     * EX3
     ****************************************/


    private static void increaseKey(int[] array, int k) {
        int parent;
        while (k >= 1) {
            parent = k / 2;
            if (lesser(array[parent], array[k])) {
                exchange(array, parent, k);
                k = parent;
            } else break;
        }
    }

    private static void maxHeapify(int[] array, int hSize, int k) {
        // Get index of left descendant
        int idxLeftChild = (2 * k) + 1;
        int idxRightChild;
        // If there exists a left descendant
        while (idxLeftChild < hSize) {
            // Get index of right descendant
            idxRightChild = idxLeftChild + 1;
            // Get the largest index of the left and right descendants
            if (idxRightChild < hSize && lesser(array[idxLeftChild], array[idxRightChild]))
                idxLeftChild = idxRightChild;
            // If the largest descendant is not greater than the parent, stop (the heap is ordered)
            if (!lesser(array[k], array[idxLeftChild]))
                break;
            // Else, exchange the largest descendant by its parent
            exchange(array, k, idxLeftChild);
            // Set the next parent to be the left descendant
            k = idxLeftChild;
            // Update left descendant
            idxLeftChild = (2 * k) + 1;
        }
    }


    public static void changeValueInMaxHeap(int[] v, int count, int ix, int newValue) {

        if (ix > count || count < 1) throw new IllegalArgumentException();

        v[ix] = newValue;

        increaseKey(v, ix);
        maxHeapify(v, count, ix);

    }

    /************************************* EX4 *************************************************/

    public static void sortIPv4Addresses(String[] v, int l, int r) {
        fillWithZeros(v);
        int n = v.length;
        String[] aux = new String[n];
        sort(v, l, r, 0, aux);

    }

    // return dth character of s, -1 if d = length of string
    private static int charAt(String s, int d) {
        assert d >= 0 && d <= s.length();
        if (d == s.length()) return -1;
        return s.charAt(d);
    }

    // sort from a[lo] to a[hi], starting at the dth character
    private static void sort(String[] a, int lo, int hi, int d, String[] aux) {

        // cutoff to insertion sort for small subarrays
        if (hi <= lo + CUTOFF) {
            insertion(a, lo, hi, d);
            return;
        }

        // compute frequency counts
        int[] count = new int[MAX_IP + 1 + 2];
        for (int i = lo; i <= hi; i++) {
            int c = charAt(a[i], d);
            count[c + 2]++;
        }

        // transform counts to indicies
        for (int r = 0; r < MAX_IP + 1; r++)
            count[r + 1] += count[r];

        // distribute
        for (int i = lo; i <= hi; i++) {
            int c = charAt(a[i], d);
            aux[count[c + 1]++] = a[i];
        }

        // copy back
        for (int i = lo; i <= hi; i++)
            a[i] = aux[i - lo];


        // recursively sort for each character (excludes sentinel -1)
        for (int r = 0; r < MAX_IP; r++)
            sort(a, lo + count[r], lo + count[r + 1] - 1, d + 1, aux);
    }


    // insertion sort a[lo..hi], starting at dth character
    private static void insertion(String[] a, int lo, int hi, int d) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(a[j], a[j - 1], d); j--)
                exch(a, j, j - 1);
    }

    // exchange a[i] and a[j]
    private static void exch(String[] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // is v less than w, starting at character d
    private static boolean less(String v, String w, int d) {
        assert v.substring(0, d).equals(w.substring(0, d));
        for (int i = d; i < Math.min(v.length(), w.length()); i++) {
            if (v.charAt(i) < w.charAt(i)) return true;
            if (v.charAt(i) > w.charAt(i)) return false;
        }
        return v.length() < w.length();
    }

    private static void fillWithZeros(String[] st) {

        int val;
        String[] aux;
        for (int i = 0; i < st.length; i++) {
            aux = st[i].split("[.]");

            for (int j = 0; j < aux.length; j++) {
                val = Integer.parseInt(aux[j]);
                if (val < 10)
                    aux[j] = "00" + val;
                else if (val < 100)
                    aux[j] = "0" + val;

                if (j == 0)
                    st[i] = aux[0];

                st[i] += "." + aux[j];
            }
        }
    }





    public static void main(String[] args) {

        String[] st = {"192.168.1.255", "80.168.1.249", "90.222.222.222", "192.168.1.249" };
        sortIPv4Addresses( st, 0, st.length-1);
    }
}