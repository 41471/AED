package series.serie3;

import org.junit.Test;
import series.serie2.Node;

import java.util.Comparator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
public class DNACollectionTest {

    DNACollection dna = new DNACollection();


    @Test
    public void DNACollection_add(){
        assertTrue(verify());
    }

    private boolean verify() {
        add();
        return dna.root.nodeArray[2].nodeArray[1].nodeArray[0].isLeaf;
    }

    @Test
    public void DNACollection_count(){
        assertTrue(DNACollection_test(true, 2));
    }

    @Test
    public void DNACollection_Null(){
        assertFalse(DNACollection_test(false,2));

    }
    private boolean DNACollection_test(boolean b, int x) {
       if (b) {
           add();
       }
        return dna.prefixCount("AC") == x;

    }
    private void add(){
        dna.add("TCA");
        dna.add("TGT");

        dna.add("ACCAG");
        dna.add("ACCAGCC");
    }

}
