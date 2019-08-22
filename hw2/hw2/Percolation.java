package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private Site[] grid;
    private WeightedQuickUnionUF map;
    private int size;
    private int numOpenSites;
    private boolean percolates;

    /**
     * create N-by-N grid, with all sites initially blocked
     * @param N Size of the grid
     */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("Size of the grid should be positive");
        }
        numOpenSites = 0;
        size = N;
        grid = new Site[N * N];
        map = new WeightedQuickUnionUF(N * N);
        percolates = false;

        for (int i = 0; i < N * N; ++i) {
            grid[i] = new Site(false, false, false);
        }

        for (int i = 0; i < N; ++i) {
            grid[i].setOnTop();
        }

        for (int i = N * (N - 1); i < N * N; ++i) {
            grid[i].setOnBottom();
        }

    }

    private void checkIndex(int index) {
        if (!isValid(index)) {
            throw new IndexOutOfBoundsException();
        }
    }

    private boolean isValid(int index) {
        return (index > -1 && index < size);
    }

    private int arrayIndex(int row, int col) {
        return row * size + col;
    }

    private int[][] neighbours(int row, int col) {
        int[][] result = new int[4][2];
        result[0][0] = row - 1;
        result[0][1] = col;
        result[1][0] = row + 1;
        result[1][1] = col;
        result[2][0] = row;
        result[2][1] = col + 1;
        result[3][0] = row;
        result[3][1] = col - 1;
        return result;
    }

    /**
     * open the site (row, col) if it is not open already
     * @param row row of the site
     * @param col column of the site
     */
    public void open(int row, int col) {
        if (isOpen(row, col)) {
            return;
        }

        int index = arrayIndex(row, col);
        checkIndex(row);
        checkIndex(col);
        grid[index].open();
        for (int[] neighbour : neighbours(row, col)) {
            int nrow = neighbour[0];
            int ncol = neighbour[1];
            if (isValid(nrow) && isValid(ncol) && isOpen(nrow, ncol)) {
                if (isFull(nrow, ncol)) {
                    grid[map.find(index)].setFull();
                }
                if (isBottomConnected(nrow, ncol)) {
                    grid[map.find(index)].setBottomConnected();
                }
                if (isFull(row, col)) {
                    grid[map.find(arrayIndex(nrow, ncol))].setFull();
                }
                if (isBottomConnected(row, col)) {
                    grid[map.find(arrayIndex(nrow, ncol))].setBottomConnected();
                }
                map.union(arrayIndex(nrow, ncol), index);
            }
        }
        numOpenSites++;
        if (isBottomConnected(row, col) && isFull(row, col)) {
            percolates = true;
        }
    }

    public boolean isOpen(int row, int col) {
        return grid[arrayIndex(row, col)].isOpen();
    }

    /**
     * A full site is an open site that can be connected to an open site in the top row
     * via a chain of neighboring (left, right, up, down) open sites.
     * @param row
     * @param col
     * @return is the site (row, col) full?
     */
    public boolean isFull(int row, int col) {
        int index = arrayIndex(row, col);
        return grid[map.find(index)].isFull();
    }

    private boolean isBottomConnected(int row, int col) {
        int index = arrayIndex(row, col);
        return grid[map.find(index)].isBottomConnected();
    }

    /**
     * @return number of open sites
     */
    public int numberOfOpenSites() {
        return numOpenSites;
    }

    /**
     *
     * @return does the system percolate?
     */
    public boolean percolates() {
        return percolates;
    }
    public static void main(String[] args) { }   // use for unit testing (not required, but keep this here for the autograder)
}
