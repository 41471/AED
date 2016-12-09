package series.serie3;

import series.serie2.Node;

import java.util.Comparator;

/**
 * Created by Pedro on 09/12/2016.
 */
public class TreeUtils {

    public static void main(String[] args) {
        Node n1 = new Node(100);
        Node n2 = new Node(90);
        Node n3 = new Node(200);
        Node n4 = new Node(80);
        Node n5 = new Node(95);
        Node n6 = new Node(150);
        Node n7 = new Node(400);
        n1.previous=n2;
        n1.next=n3;
        n2.previous=n4;
        n2.next=n5;
        n3.previous=n6;
        n3.next=n7;
        Comparator<Integer> CMP_NATURAL_ORDER = new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                return i1.compareTo(i2);
            }
        };

        //System.out.println(contains(n1,50,79,CMP_NATURAL_ORDER));
        System.out.println(isBST(n1,CMP_NATURAL_ORDER));
    }
    public static boolean bool = false;

    public static <E> boolean contains(Node<E> root, E min, E max, Comparator<E> cmp){


        if(cmp.compare(root.value,min)>= 0 && (cmp.compare(root.value,max))<= 0) {
            return true;
        }

        if(cmp.compare(root.value,max)>0 && root.previous!=null)
            bool=contains(root.previous,min,max,cmp);
        else if(root.next!=null)
            bool=contains(root.next,min,max,cmp);

       return bool;
    }

    public static Node<Integer> createBSTFromRange(int start, int end){
        return null;
    }

    public static <E> boolean isBST(Node<E> root, Comparator<E> cmp){

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

}
