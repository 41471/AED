package series.serie3;

public class DNACollection<E> {


    public NodeT root = new NodeT();
    public static final int NUM_OF_CHARS=4;
    public static int[] count = new int[NUM_OF_CHARS];

    public static void main(String[] args) {
        DNACollection dna = new DNACollection();
        dna.add("TCA");
        dna.add("TGT");

        dna.add("ACCAG");
        dna.add("ACCAGCC");
        System.out.println(dna.countPre(dna.root));

        System.out.println(dna.prefixCount("AC"));

    }

    public void add(String fragment){

        int c;
        NodeT newroot = root;
        for (int i = 0; i < fragment.length(); i++) {
            c=getIdx(fragment.charAt(i));

            if(c<0){
                break;
            }
            if(newroot.nodeArray[c]==null) {
                if (i == fragment.length() - 1) {
                    newroot.nodeArray[c] = new NodeT(fragment.charAt(i), true, 0);
                } else
                    newroot.nodeArray[c] = new NodeT(fragment.charAt(i), false, 0);

            }
            newroot = newroot.nodeArray[c];
        }
        count[getIdx(fragment.charAt(0))]++;
    }


    public int getIdx(char c){
        switch(c){
            case 'A':return 0;
            case 'C':return 1;
            case 'T':return 2;
            case 'G':return 3;
            default: return -1;
        }
    }

    public int prefixCount(String p){
        int c;
        NodeT newroot = root;
        for (int i = 0; i < p.length(); i++) {
            c=getIdx(p.charAt(i));

            if(c<0){
                break;
            }

            newroot = newroot.nodeArray[c];
        }
        return countPre(newroot);
    }


    public int countPre(NodeT node){

        if(node==null) return 0;
        int count = 0;
        if(node.isLeaf) count++;
        for (int i = 0; i < NUM_OF_CHARS; i++) {
            count+=countPre(node.nodeArray[i]);
        }
        return count;
    }

}