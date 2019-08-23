package hw3.hash;

import java.util.HashMap;
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        int N = oomages.size();
        HashMap<Integer, Integer> bucketStats = new HashMap<>();
        for (Oomage om : oomages) {
            int bucketNum =  (om.hashCode() & 0x7FFFFFFF) % M;
            if (bucketStats.containsKey(bucketNum)) {
                int count = bucketStats.get(bucketNum);
                bucketStats.put(bucketNum, count + 1);
            } else {
                bucketStats.put(bucketNum, 1);
            }
        }
        for (int bucketNum : bucketStats.keySet()) {
            int codeCount = bucketStats.get(bucketNum);
            if (codeCount < N / (double) 50 || codeCount > N / (double) 2.5) {
                return false;
            }
        }
        return true;
    }
}
