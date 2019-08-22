package hw2;
import edu.princeton.cs.introcs.*;

public class PercolationStats {
    private double[] xdata;

    /**
     * perform T independent experiments on an N-by-N grid
     * @param N
     * @param T
     * @param pf
     */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        xdata = new double[T];
        if (T <= 0 || N <= 0) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                p.open(StdRandom.uniform(N),StdRandom.uniform(N));
            }
            xdata[i] = p.numberOfOpenSites() / N;
        }
    }

    /**
     * sample mean of percolation threshold
     * @return
     */
    public double mean() {
        return StdStats.mean(xdata);
    }

    /**
     * sample standard deviation of percolation threshold
     * @return
     */
    public double stddev() {
        return StdStats.stddev(xdata);
    }

    /**
     * low endpoint of 95% confidence interval
     * @return
     */
    public double confidenceLow() {
        return mean() - 1.96 * Math.sqrt(stddev()) / Math.sqrt(xdata.length);
    }

    /**
     * high endpoint of 95% confidence interval
     * @return
     */
    public double confidenceHigh() {
        return mean() + 1.96 * Math.sqrt(stddev()) / Math.sqrt(xdata.length);
    }
}
