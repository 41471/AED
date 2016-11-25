package series.serie2.Agency;


public class PriorityQueue<E extends Comparable<E>,P extends Comparable<P>> implements KeyExtractor {

    public class StructEP implements KeyExtractor<E> {
        public E element;
        public P prio;

        public StructEP(){}
        public StructEP(E e,P p){
            element=e;
            prio=p;
        }


        @Override
        public <E> int getKey(E e1) {
            return 0;
        }
    }


    public boolean less(StructEP v, StructEP w) {
        if(v.prio.compareTo(w.prio)<0)
            return true;
        else if(v.prio.compareTo(w.prio)==0)
                if(v.element.compareTo(w.element)<0)
                    return true;
        return false;
    }



    public StructEP[] pq;
    public int size=0;






    public void add(E elem, P prio){
        size++;
        if(size==pq.length)
            Agency.resize();
        pq[size] = new StructEP(elem,prio);
        increaseKey(size);

    }



    public E pick(){
        return pq[1].element;
    }

    public E poll(){

        E max = pq[1].element;
        // Set the new root to be the last element
        pq[1] = pq[size];
        // Remake heap from the root
        maxHeapify(1, size - 1);
        // Decrement size
        --size;
        // Return the maximum
        return max;
    }

    public void update(int key, P prio){

    }

    public void remove(int key){

    }



    private void increaseKey(int k) {
        int idxParent;
        while (k > 1) {
            // Get parent
            idxParent = k / 2;
            // Verify if parent is less than current key index k
            if (less(pq[idxParent], pq[k]))
                break; // The heap is in order
            // and exchange parent with current key index
            exch(pq, k, idxParent);
            // Set current key index to parent index
            k = idxParent;

        }
    }

    static <E> void exch(E[] array, int i, int j) {
        E t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    private void maxHeapify(int k, int hSize) {
        // Get index of left descendant
        int idxLeftChild = 2 * k;
        int idxRightChild;
        // If there exists a left descendant
        while (idxLeftChild <= hSize) {
            // Get index of right descendant
            idxRightChild = idxLeftChild + 1;
            // Get the largest index of the left and right descendants
            if (idxRightChild <= hSize && less(pq[idxRightChild], pq[idxLeftChild]))
                idxLeftChild = idxRightChild;
            // If the largest descendant is not greater than the parent, stop (the heap is ordered)
            if (less(pq[k], pq[idxLeftChild]))
                break;
            // Else, exchange the largest descendant by its parent
            exch(pq, k, idxLeftChild);
            // Set the next parent to be the left descendant
            k = idxLeftChild;
            // Update left descendant
            idxLeftChild = 2 * k;
        }
    }


    @Override
    public int getKey(Object o) {
        return 0;
    }
}
