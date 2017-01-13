package series.serie3;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import series.serie2.Node;

import java.security.InvalidParameterException;
import java.util.Comparator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static series.serie3.TreeUtils.contains;
import static series.serie3.TreeUtils.createBSTFromRange;

public class ContainsTest {

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
    @Test
    public void contains_T(){
        aux();
        assertTrue(isInContains(n1,0,85));
    }



    @Test
    public void contains_F(){
        aux();
        assertFalse(isInContains( n1,0,10));
    }

    @Test
    public void contains_null(){
        Node aux = null;
        assertFalse(isInContains(aux,0,10));

    }
    private boolean isInContains(Node n1, int x, int y) {
        return contains(n1, x, y,CMP_NATURAL_ORDER);
    }

}
