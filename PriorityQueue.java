package series.serie2.Agency;


import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;
import series.serie2.Node;

import java.lang.reflect.Array;

public class PriorityQueue<E,P extends Comparable<P>>  {


    public static int prioCount = 0;
    public Customer[] pq;
    public HashTables hashTables;
    public int size = 0;





    public class Customer {
        public E element;
        public P prio;
        public int key;
        public String s;

        public Customer() {
        }

        public Customer(E e, P p) {
            element = e;
            prio = p;
            key = ++prioCount;
            s = prio + "" + key;
        }
    }
    public void init(){
        pq = new PriorityQueue.Customer[2];
        hashTables = new HashTables();
    }


    public boolean less(Customer v, Customer w) {
        int temp1 = prioToInt(v.prio), temp2 = prioToInt(w.prio);
        if (temp1 < temp2)
            return true;
        else if (temp1 == temp2)
            if (v.key < w.key)
                return true;
        return false;
    }

    public int prioToInt(P p) {
        switch ((String) p) {
            case "DL":
                return 2;
            case "CA":
                return 5;
            case "CR":
                return 10;
            case "OF":
                return 20;
            default:
                return 0;
        }
    }


    public int hidx=1;
    public boolean isIncreased=false;

    public void add(E elem, P prio) {
        size++;
        if (size == pq.length)
            resize();
        pq[size] = new Customer(elem, prio);
        increaseKey(size);
        if(!isIncreased)hashTables.put(pq[size].key, hidx);
        else hashTables.put(pq[hidx].key, hidx);

    }


    public E pick() {
        return pq[1].element;
    }

    public E poll() {

        E max = pq[1].element;
        // Set the new root to be the last element
        hashTables.put(pq[size].key, hashTables.get(pq[1].key));
        hashTables.remove(pq[1].key);
        pq[1] = pq[size];

        // Remake heap from the root
        maxHeapify(pq,1, size - 1);

        // Decrement size
        --size;
        // Return the maximum
        return max;
    }

    public void update(int key, P prio) {
        int aux = hashTables.get(key);
        if (morePriority(aux, prio) > 0) {
            pq[aux].prio = prio;
            increaseKey(aux);
        }
        if (morePriority(aux, prio) < 0) {
            pq[aux].prio = prio;
            maxHeapify(pq,aux, size);
        }

    }

    public int morePriority(int x, P prio) {
        return (prioToInt(pq[x].prio) < prioToInt(prio)) ? -1 : (prioToInt(pq[x].prio) > prioToInt(prio)) ? 1 : 0;
    }

    public void remove(int key) {
        int aux = hashTables.get(key);
        pq[aux] = pq[size];
        hashTables.remove(key);
//        hashTables.remove(pq[size].key);
//        hashTables.put(pq[aux].key, aux);
        maxHeapify(pq,aux, --size);
    }



    private void increaseKey(int k) {
        int idxParent;

        while (k > 1) {
            // Get parent
            hidx=k;
            idxParent = k / 2;
            // Verify if parent is less than current key index k
            if (less(pq[idxParent], pq[k]))
                break; // The heap is in order
            // and exchange parent with current key index
            exch(pq, k, idxParent);
            exchHash(pq[k], pq[idxParent]);
            // Set current key index to parent index
            k = idxParent;
            hidx=idxParent;
            isIncreased=true;

        }

    }
    public Customer[] aux;
    public void heapSort() {
        aux = (Customer[]) Array.newInstance(Customer.class,size+1);
        // Transform array into a heap
        copyHeap(pq,aux, size);
        int n = size+1;
        // Sort using exchange and heapify operations
        while (n > 0) {
            // Exchange root (the maximum) with the last element
            // Max-heapify the first element (the new root), putting it to order
            maxHeapify(aux,1,--n);
        }
    }

    private void copyHeap(Customer[] src,Customer[]dst,int hSize) {

        for (int i = 1; i <= hSize; i++) {
            dst[i]=src[i];
        }
    }

    public void resize() {

        Customer[] aux = (Customer[]) Array.newInstance(Customer.class, 2 * pq.length);
        for (int i = 1; i < pq.length; i++) {
            if (pq[i] != null) {
                aux[i] = pq[i];
            }
        }
        pq = aux;
    }

    public void exchHash(Customer v, Customer w) {
        int aux;
        aux = hashTables.get(v.key);
        hashTables.put(v.key, hashTables.get(w.key));
        hashTables.put(w.key, aux);

    }

    static <E> void exch(E[] array, int i, int j) {
        E t = array[i];
        array[i] = array[j];
        array[j] = t;
    }

    private void maxHeapify(Customer[] array,int k, int hSize) {
        // Get index of left descendant
        int idxLeftChild = 2 * k;
        int idxRightChild;
        // If there exists a left descendant
        while (idxLeftChild <= hSize) {
            // Get index of right descendant
            idxRightChild = idxLeftChild + 1;
            // Get the largest index of the left and right descendants
            if (idxRightChild <= hSize && less(array[idxRightChild], array[idxLeftChild]))
                idxLeftChild = idxRightChild;
            // If the largest descendant is not greater than the parent, stop (the heap is ordered)
            if (less(array[k], array[idxLeftChild]))
                break;
            // Else, exchange the largest descendant by its parent
            exch(array, k, idxLeftChild);
            exchHash(array[k], array[idxLeftChild]);
            // Set the next parent to be the left descendant
            k = idxLeftChild;
            // Update left descendant
            idxLeftChild = 2 * k;
        }
    }


    public class HashTables {



        public class Pair {
            int ticket;
            int idx;

            public Pair(int e, int p) {
                ticket = e;
                idx = p;
            }


            public String toString() {
                return ticket + "=" + idx;
            }
        }



        public Node<Pair>[] tables;


        HashTables(){
            tables= (Node<Pair>[]) Array.newInstance(Node.class, 2 * pq.length);
        }


        public int n = 0;




        void put(int key, int idx) {
            int i = key;
            isIncreased=false;
            if (i >= tables.length - 1) rehash();
            if (tables[i] == null) {
                tables[i] = new Node<>(new Pair(key, idx));
                n++;
            } else {
                Node<Pair> aux = new Node<>();
                aux.value = new Pair(key, idx);
                tables[i].value=aux.value;
                n++;
            }
        }


        private void rehash() {

            Node<Pair>[] aux = new Node[tables.length * 2];
            for (int i = 0; i < tables.length; i++) {
                aux[i] = tables[i];
            }
            tables = aux;
        }

        public int get(int key) {

            int i = key;
            while (i!=tables.length&&tables[i] != null)
                if (key == tables[i].value.ticket) return tables[i].value.idx;
                else i++;
            tables[i]= new Node<>(new Pair(pq[key].key,key));
            return tables[i].value.idx;

        }

        public void remove(int key) {
            int i = key;
            while (tables[i] != null)
                if (key == tables[i].value.ticket) break;
                else i++;
            if (tables[i] == null)
                return;
            tables[i] = null;
            n--;
//            for (int j = ++i; tables[j] != null; j++) {
//                tables[i].value = tables[j].value;
//                tables[j] = null;
//                put(tables[i].value.ticket, tables[i].value.idx);
//                n--;
//            }
        }


    }
}
