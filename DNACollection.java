package series.serie3;

import series.serie2.Node;

/**
 * Created by Pedro on 09/12/2016.
 */
public class DNACollection<E> {


    public Node<E>[] str;
    public static  int[] countArray;
    public static final int NUM_OF_CHARS=4;

    public static void main(String[] args) {
        DNACollection dna = new DNACollection();
        dna.add("ATTT");
        dna.add("TAAA");
        dna.add("CGGG");
        dna.add("GCCC");
        dna.add("AGGG");
        System.out.println(dna.prefixCount("A"));
        System.out.println(dna.prefixCount("C"));
        System.out.println(dna.prefixCount("T"));
        System.out.println(dna.prefixCount("G"));

    }


    public <E> DNACollection(){
        str = new Node[NUM_OF_CHARS];   // A=0, C=1, T=2, G=3
        initNode(str);
        countArray= new int[NUM_OF_CHARS];
    }

    private void initNode(Node<E>[] str) {
        for (int i = 0; i < str.length; i++) {
            str[i] = new Node<>();
        }
    }

    public void add(String fragment){
        if(fragment.charAt(0)=='A'){
            str[0].add((Node<E>) new Node(fragment));
            countArray[0]++;
        }
        if(fragment.charAt(0)=='C') {
            str[1].add((Node<E>) new Node(fragment));
            countArray[1]++;
        }
        if(fragment.charAt(0)=='T') {
            str[2].add((Node<E>) new Node(fragment));
            countArray[2]++;
        }
        if(fragment.charAt(0)=='G') {
            str[3].add((Node<E>) new Node(fragment));
            countArray[3]++;
        }
    }

    public int prefixCount(String p){
        switch (p){
            case "A":
                return countArray[0];
            case "C":
                return countArray[1];
            case "T":
                return countArray[2];
            case "G":
                return countArray[3];
            default:
                return -1;
        }
    }

}
