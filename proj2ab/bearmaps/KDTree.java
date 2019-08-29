package bearmaps;

import java.util.List;

public class KDTree implements PointSet {

    private Node root;

    private class Node {
        private Node left;
        private Node right;
        private Point p;

        public Node(Point p) {
            this.p = p;
        }
    }

    public KDTree(List<Point> points) {
        for (Point p : points) {
            this.add(p);
        }
    }

    public void add(Point p) {
        root = add(p, root, 0);
    }

    private Node add(Point p, Node n, int i) {
        if (n == null) {
            return new Node(p);
        }
        double cpd;
        if (i % 2 == 0) {
            cpd = p.getX() - n.p.getX();
        } else {
            cpd = p.getY() - n.p.getY();
        }
        if (cpd > 0) {
            n.right = add(p, n.right, i + 1);
        } else {
            n.left = add(p, n.left, i + 1);
        }
        return n;
    }

    @Override
    public Point nearest(double x, double y) {
        return nearest(new Point(x, y), root.p, root, 0);
    }

    private Point nearest(Point target, Point currentBest, Node n, int i) {
        if (n == null) {
            return currentBest;
        }
        double dist = Point.distance(n.p, target);
        double cpd;
        if (i % 2 == 0) {
            cpd = target.getX() - n.p.getX();
        } else {
            cpd = target.getY() - n.p.getY();
        }

        // If the distance of the current node is smaller than the current smallest distance,
        // update the smallest node
        if (dist < Point.distance(currentBest, target)) {
            currentBest = n.p;
        }

        // Search the good side
        if (cpd > 0) {
            currentBest =  nearest(target, currentBest, n.right, i + 1);
            // If the comparison distance is smaller than the best distance, search the bad side
            if (Math.abs(cpd) < dist) {
                currentBest = nearest(target, currentBest, n.left, i + 1);
            }
        } else {
            currentBest =  nearest(target, currentBest, n.left, i + 1);
            if (Math.abs(cpd) < dist) {
                currentBest = nearest(target, currentBest, n.right, i + 1);
            }
        }
        return currentBest;
    }
}
