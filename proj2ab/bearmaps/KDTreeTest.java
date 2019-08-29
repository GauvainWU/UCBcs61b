package bearmaps;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {
    @Test
    public void startingTest() {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);
        Point p4 = new Point(3, 3);
        Point p5 = new Point(3, 5);

        KDTree kdt = new KDTree(List.of(p1, p2, p3, p4, p5));
        Point ret = kdt.nearest(0.5, 4.4); // returns p2
        System.out.println(ret.getX());
        System.out.println(ret.getY());
    }
}
