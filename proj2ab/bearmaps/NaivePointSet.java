package bearmaps;

import java.util.List;

public class NaivePointSet implements PointSet{
    private List<Point> points;

    public NaivePointSet(List<Point> points) {
        this.points = points;
    }

    public Point nearest(double x, double y) {
        Point target = new Point(x, y);
        Point closest = new Point(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        for (Point p : points) {
            if (Point.distance(target, p) < Point.distance(target, closest)) {
                closest = p;
            }
        }
        return closest;
    }
}
