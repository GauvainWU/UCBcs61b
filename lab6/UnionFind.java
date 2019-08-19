public class UnionFind {
    private int[] data;
    private int size;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        size = n;
        data = new int[n];
        for (int i = 0; i < n; ++i){
            data[i] = i;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex < 0 || vertex > size) {
            throw new RuntimeException("Invalid Index");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        if (data[v1] == v1) {
            return 1;
        } else {
            return Math.abs(data[find(v1)]);
        }
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        return data[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        int v1Root = find(v1);
        int v2Root = find(v2);
        int v1Size = sizeOf(v1);
        int v2Size = sizeOf(v2);

        if (v1Root == v2Root) {
            return;
        } else {
            if (v1Size > v2Size) {
                int newSize = v1Size + v2Size;
                data[v2Root] = v1Root;
                data[v1Root] = -newSize;
            } else {
                int newSize = v1Size + v2Size;
                data[v1Root] = v2Root;
                data[v2Root] = -newSize;
            }
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        if (data[vertex] == vertex){
            return vertex;
        } else {
            int prevVertex = vertex;
            while (data[vertex] > 0) {
                vertex = data[vertex];
                if (data[vertex] > 0) {
                    data[prevVertex] = data[vertex];
                    prevVertex = vertex;
                }
            }
            return vertex;
        }
    }
}
