package series.serie3;

/**
 * Created by Pedro on 06/01/2017.
 */
public class NodeT<E> {
    public final int ALPHABET_LEN = 4;
    char[] charArray = {'A','C','T','G'};
    public NodeT[] nodeArray = new NodeT[ALPHABET_LEN];
    boolean isLeaf = false;
    int count;
    E value;

    public NodeT(){
        for (int i = 0; i < ALPHABET_LEN; i++) {
            nodeArray[i]=new NodeT(charArray[i],false,0);
        }
    }

    public NodeT(E value,boolean isLeaf,int count){
        this.value=value;
        this.isLeaf=isLeaf;
        this.count=count;

    }






}
