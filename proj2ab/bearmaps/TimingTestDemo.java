package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug. Demonstrates how you can use either
 * System.currentTimeMillis or the Princeton Stopwatch
 * class to time code.
 */
public class TimingTestDemo {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int sum = 0;
        ArrayHeapMinPQ<Integer> test = new ArrayHeapMinPQ<>();
        for (int i = 100000; i > 0; i --) {
            test.add(i,i);
        }
        long end = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end - start)/1000.0 +  " seconds.");

        Stopwatch sw = new Stopwatch();
        NaiveMinPQ<Integer> test2 = new NaiveMinPQ<>();
        for (int i = 100000; i > 0; i--) {
            test2.add(i,i);
        }
        System.out.println("Total time elapsed: " + sw.elapsedTime() +  " seconds.");
    }
}
