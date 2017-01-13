package series.serie3;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import series.serie2.Node;

import java.util.Comparator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static series.serie3.TreeUtils.isBST;

public class isBSTTest {
    Node n1 = new Node(100);
    Node n2 = new Node(90);
    Node n3 = new Node(200);
    Node n4 = new Node(80);
    Node n5 = new Node(95);
    Node n6 = new Node(150);
    Node n7 = new Node(400);
    Comparator<Integer> CMP_NATURAL_ORDER;

    private void aux() {
        n1.previous = n2;
        n1.next = n3;
        n2.previous = n4;
        n2.next = n5;
        n3.previous = n6;
        n3.next = n7;
         CMP_NATURAL_ORDER = new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                return i1.compareTo(i2);
            }
        };
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void BST_T() {
        aux();

        assertTrue(isBST_0());
    }
    @Test
    public void BST_F() {
        aux();

        assertFalse(isBST_1());
    }
    @Test
    public void BST_NULL() {
        Node aux = null;
        assertTrue(isBSTNull(aux));
    }


    private boolean isBSTNull(Node aux){
        return isBST(aux,CMP_NATURAL_ORDER)== false;
    }
    private boolean isBST_0() {
        return  (isBST(n1, CMP_NATURAL_ORDER) == true);
    }

    private boolean isBST_1(){
        n1 = new Node(85);
        return (isBST(n1,CMP_NATURAL_ORDER) == false);

    }
}
