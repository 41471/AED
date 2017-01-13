package series.serie3;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import series.serie2.Node;

import java.security.InvalidParameterException;

import static org.junit.Assert.assertTrue;
import static series.serie3.TreeUtils.createBSTFromRange;

public class CreateBSTFromRange {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void positiveRange() {

        assertTrue(isBST_0(createBSTFromRange(0,2)));
    }
    @Test
    public void negativeRange() {

        assertTrue(isBST_1(createBSTFromRange(-3,-1)));
    }


    @Test
        public void invalidRange() {
        exception.expect(InvalidParameterException.class);
        createBSTFromRange(1,0);
    }

    private boolean isBST_0(Node<Integer> bstFromRange) {
        return bstFromRange.value == 1 && bstFromRange.previous.value == 0 && bstFromRange.next.value == 2;
    }

    private boolean isBST_1(Node<Integer> bstFromRange) {
        return bstFromRange.value == -2 && bstFromRange.previous.value == -3 && bstFromRange.next.value == -1;
    }
}
