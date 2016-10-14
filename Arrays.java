package series.serie1;

/**
 * Created by Pedro on 07/10/2016.
 */
public class Arrays {

    public static void main(String[] args) {
        int[] array1 = {2,4,6,6};
        int[] array2 = {6,2,6,4};
        System.out.println(isPermutation(array1,0,array1.length-1,array2,0,array2.length-1));
    }


    /****************************************** EX2 ******************************/
    public static boolean isPermutation(int[] a1, int l1, int r1, int[] a2, int l2, int r2) {

        if((r1-l1)!=(r2-l2)) return false;

        mergeSort(a1,l1,r1);
        mergeSort(a2,l2,r2);
        int i=l1,j=l2;
        for (; i <= r1 && j <= r2; i++,j++) {
            if(a1[i]!=a2[j]) return false;
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
            if (less(aux[j], aux[i]))
                array[k] = aux[j--];
            else
                array[k] = aux[i++];
        }
    }

    static boolean less(int v, int w) {
        return v < w;
    }

    static void exch(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    static void compExch(int[] a, int i, int j) {
        if (less(a[j], a[i]))
            exch(a, i, j);
    }

    public static void mergeSort(int[] a, int l, int r) {
        if (r <= l)
            return;
        int mid = (r+l)/2;
        // Sort the left part recursively
        mergeSort(a, l, mid);
        // Sort the right part recursively
        mergeSort(a, mid+1, r);
        // Merge the sorted left and right parts
        merge(a, l, mid, r);
    }



    public	static	int	countEquals(int[]	v1,	int	l1,	int	r1,	int[]	v2,	int	l2,	int	r2){
        int	count=0;
        while(l1<=r1	&&	l2<=r2){
            if(v1[l1]>v2[l2])	l2++;
            else
            if(v1[l1]<v2[l2])	l1++;
            else	{	count++;	l1++;	l2++;}
        }
        return	count;
    }



    public static void changeValueInMaxHeap(int[] v, int count, int ix, int newValue){

    }



    /************************************EX 1***********************************/

    public static int greatestAfterRotate (int [] v, int l, int r){

        int greater, mid, rigth = r;

        if ( (r-l+1) <= 0)
            return -1;

        if ((r-l+1) == 1)
            return v[l];

        mid = ( l + r ) / 2;
        greater = v[mid];

        while (l <= r){
            mid = (l + r) / 2;

            if ( greater < v[mid]) {
                greater = v[mid];
                l = mid + 1;
            }
            else
                r = mid -1;
        }

        if (v[rigth] > greater)
            greater= v[rigth];

        return greater;

    }


// teste
    
}
