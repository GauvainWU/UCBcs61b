package bearmaps;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    @Test
    public void multipleRandomTests() {
        for (int i = 0; i < 100; i++) {
            randomTest(1000);
        }
    }

    private void randomTest(int num) {
        Random rd = new Random();
        List<Point> ls = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            double p1x = rd.nextDouble();
            double p1y = rd.nextDouble();
            ls.add(new Point(p1x,p1y));
        }
        KDTree kdt = new KDTree(ls);
        NaivePointSet nvp = new NaivePointSet(ls);
        double p1x = rd.nextDouble();
        double p1y = rd.nextDouble();
        assertEquals(nvp.nearest(p1x, p1y), kdt.nearest(p1x, p1y));
    }
}
