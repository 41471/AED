package series.serie3;

import series.serie2.Node;

import java.security.InvalidParameterException;
import java.util.Comparator;

public class TreeUtils {

    static Node<Integer> aux;
    public static boolean bool = false;

    public static <E> boolean contains(Node<E> root, E min, E max, Comparator<E> cmp) {

        if (root == null)
            return false;
        if(cmp.compare(root.value,min)>= 0 && (cmp.compare(root.value,max))<= 0) {
            return true;
        }

        if(cmp.compare(root.value,max)>0 && root.previous!=null)
            bool=contains(root.previous,min,max,cmp);
        else if(root.next!=null)
            bool=contains(root.next,min,max,cmp);

        return bool;
    }

    public static Node<Integer> createBSTFromRange(int start, int end) {

        if (end < start)
            throw new InvalidParameterException();
        int mid = (start + end) / 2;

        Node<Integer> midNode = new Node<>(mid);

        if (mid != start)
            midNode.previous = createBSTFromRange(start, mid - 1);
        if (mid != end)
            midNode.next = createBSTFromRange(mid + 1, end);

        return midNode;
    }

    public static <E> boolean isBST(Node<E> root, Comparator<E> cmp) {

        if (root == null)
            return false;
        if(root.previous==null && root.next==null)
            return true;

        if(root.previous!=null) {
            if (cmp.compare(root.value, root.previous.value) > 0)
                bool = isBST(root.previous, cmp);
            else return false;
        }

        else if(root.next!=null){
            if(cmp.compare(root.value,root.next.value) < 0)
                bool = isBST(root.next,cmp);
            else return false;
        }

        return bool;
    }


    public static void main(String[] args) {
        aux = createBSTFromRange(-100,100);
        print();
    }

    private static<E> void printNode(E e, int level) {
        for (int i = 0; i < level; ++i)
            System.out.print("\t");
        System.out.println(e);
    }
    private static<E> void printR(Node<E> t, int level) {
        if (t == null)
            return;
        printR(t.next, level+1);
        printNode(t.value, level);
        printR(t.previous, level+1);
    }
    public static void print() {
        printR(aux, 0);
    }
}