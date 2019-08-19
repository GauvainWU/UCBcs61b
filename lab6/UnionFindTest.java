import org.junit.Test;
import static org.junit.Assert.*;

public class UnionFindTest {

    @Test
    public void testUnionFind () {
        UnionFind test = new UnionFind(8);
        test.union(1,2);
        test.union(3,4);
        test.union(2,5);
        test.union(4,7);
        test.union(7,6);
        assertEquals(test.sizeOf(1), 3);
        assertEquals(test.sizeOf(3), 4);
        assertEquals(test.sizeOf(7), 4);
        assertTrue(test.connected(2,5));
    }
}
