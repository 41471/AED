package series.serie2.Agency;


import com.sun.org.apache.bcel.internal.generic.DCMPG;
import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

import java.lang.reflect.Array;

public class PriorityQueue<E extends Comparable<E>,P extends Comparable<P>> implements KeyExtractor<E>{

    public static int prioCount=0;
    public Hashtable table = new Hashtable(2);



    public class StructEP {
        public E element;
        public P prio;
        public int key;

        public StructEP() {}

        public StructEP(E e, P p) {
            element = e;
            prio=p;
            key = ++prioCount;
        }
    }





    public boolean less(StructEP v, StructEP w) {
        int temp1 = PrioToInt(v.prio), temp2 = PrioToInt(w.prio);
        if (temp1 < temp2)
            return true;
        else if (temp1 == temp2)
            if (v.key < w.key)
                return true;
        return false;
    }

    private int PrioToInt( P p){
        switch ((String)p) {
            case "DL": return 1;
            case "CA": return 2;
            case "CR": return 3;
            case "OF": return 4;
            default: return -1;
        }
    }



    public StructEP[] pq;
    public int size=0;






    public void add(E elem, P prio){
        size++;
        if(size==pq.length)
            resize();
        pq[size] = new StructEP(elem,prio);
        table.put(pq[size].key,size);
        increaseKey(size);

    }



    public E pick(){
        return pq[1].element;
    }

    public E poll(){

        E max = pq[1].element;
        // Set the new root to be the last element
        table.put(pq[size].key,table.get(pq[1].key));
        table.remove(pq[1].key);
        pq[1] = pq[size];

        // Remake heap from the root
        maxHeapify(1, size - 1);

        // Decrement size
        --size;
        // Return the maximum
        return max;
    }

    public void update(int key, P prio){
        int aux = (int)table.get(key);
        if(morePriority(aux,prio)>0) {
            pq[aux].prio=prio;
            increaseKey(aux);
        }
        if(morePriority(aux,prio)<0){
            pq[aux].prio=prio;
            maxHeapify(aux,size);
        }

    }

    public int morePriority(int x,P prio){
        return (PrioToInt(pq[x].prio)<PrioToInt(prio))?-1:(PrioToInt(pq[x].prio)>PrioToInt(prio))?1:0;
    }

    public void remove(int key){
        int aux = (int)table.get(key);
        pq[aux] = pq[size];
        table.remove(key);
        table.remove(pq[size].key);
        table.put(pq[aux].key,aux);
        maxHeapify(aux,--size);
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
            exchHash(pq[k],pq[idxParent]);
            // Set current key index to parent index
            k = idxParent;

        }

    }

    public void resize() {

        StructEP[] aux = (StructEP[]) Array.newInstance(StructEP.class,2*pq.length);
        for (int i = 1; i < pq.length; i++) {
            if(pq[i]!=null) {
                aux[i]= pq[i];
            }
        }
        pq=aux;
    }

    public void exchHash(StructEP v ,StructEP w){
        Object aux;
        aux=table.get(v.key);
        table.put(v.key,table.get(w.key));
        table.put(w.key,aux);

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
            exchHash(pq[k],pq[idxLeftChild]);
            // Set the next parent to be the left descendant
            k = idxLeftChild;
            // Update left descendant
            idxLeftChild = 2 * k;
        }
    }

    @Override
    public <E1> int getKey(E1 e1) {
        return -1;
    }


}
