package bearmaps.hw4;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import bearmaps.proj2ab.DoubleMapPQ;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private SolverOutcome outcome;
    private List<Vertex> solution;
    private double solutionWeight;
    private int numStatesExplored;
    private double explorationTime;

    /**
     * Constructor which finds the solution, computing everything necessary
     * for all other methods to return their results in constant time.
     * @param input input graph
     * @param start starting vertex
     * @param end target vertex
     * @param timeout maximum time allowed for the solver
     */
    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        long startTime = System.currentTimeMillis();
        boolean timeExceeded = false;
        solution = new ArrayList<>();
        numStatesExplored = 0;
        solutionWeight = 0;
        ArrayHeapMinPQ<Vertex> queue = new ArrayHeapMinPQ<>();
        HashMap<Vertex, Double> distTo = new HashMap<>();
        HashMap<Vertex, Double> AStarDistTo = new HashMap<>();
        HashMap<Vertex, Vertex> edgeTo = new HashMap<>();
        ArrayList<Vertex> dequed = new ArrayList<>();
        queue.add(start, 0);
        distTo.put(start, 0.);
        AStarDistTo.put(start, 0.);

        while (!(queue.size() == 0) && !queue.getSmallest().equals(end) && !timeExceeded) {
            Vertex v = queue.removeSmallest();
            dequed.add(v);
            numStatesExplored++;
            for (WeightedEdge<Vertex> e : input.neighbors(v)) {
                Vertex neighbor = e.to();
                double distance;
                double distToV = distTo.get(v);
                double edgeWeight = e.weight();
                // In case estimatedDistanceToGoal(end, end) == Infinity

                distance = distToV + edgeWeight + input.estimatedDistanceToGoal(neighbor, end);
                if (!queue.contains(neighbor)) {
                    queue.add(neighbor, distance);
                    AStarDistTo.put(neighbor, distance);
                } else {
                    // Relax
                    if (distance < AStarDistTo.get(neighbor)) {
                        queue.changePriority(neighbor, distance);
                        AStarDistTo.put(neighbor, distance);
                    }
                }
                if (!distTo.containsKey(neighbor)) {
                    edgeTo.put(neighbor, v);
                    distTo.put(neighbor, edgeWeight + distToV);
                } else {
                    // Relax
                    if (edgeWeight + distToV < distTo.get(neighbor)) {
                        distTo.put(neighbor, edgeWeight + distToV);
                        edgeTo.put(neighbor,  v);
                    }
                }
                long endTime = System.currentTimeMillis();
                if ((endTime - startTime) / 1000.0 > timeout) {
                    timeExceeded = true;
                    break;
                }
            }
            while (dequed.contains(queue.getSmallest())) {
                queue.removeSmallest();
            }
            if (timeExceeded) {
                break;
            }
        }

        if (queue.size() == 0 ) {
            outcome = SolverOutcome.UNSOLVABLE;
            solution.clear();
        } else if (queue.getSmallest().equals(end)) {
            this.explorationTime = (System.currentTimeMillis() - startTime) / 1000.0;
            outcome = SolverOutcome.SOLVED;
            solutionWeight = distTo.get(end);
            Vertex current = end;
            ArrayList<Vertex> temp = new ArrayList<>();
            while (!current.equals(start)) {
                temp.add(current);
                current = edgeTo.get(current);
            }
            temp.add(current);
            for (int i = temp.size() - 1; i >= 0; --i) {
                solution.add(temp.get(i));
            }
        } else if (timeExceeded) {
            outcome = SolverOutcome.TIMEOUT;
            solution.clear();
        }
    }

    /**
     * Returns one of SolverOutcome.SOLVED, SolverOutcome.TIMEOUT, or SolverOutcome.UNSOLVABLE.
     * Should be SOLVED if the AStarSolver was able to complete all work in the time given.
     * UNSOLVABLE if the priority queue became empty.
     * TIMEOUT if the solver ran out of time. You should check to see if you have run out of time every time you dequeue.
     * @return Enum type indicating the status of solver
     */
    @Override
    public SolverOutcome outcome() {
        return outcome;
    }

    /** Return a list of vertices corresponding to a solution
     * Should be empty if result was TIMEOUT or UNSOLVABLE.
     * @return A list of vertices corresponding to a solution.
     */
    @Override
    public List<Vertex> solution() {
        return solution;
    }

    /**
     * The total weight of the given solution, taking into account edge weights.
     * Should be 0 if result was TIMEOUT or UNSOLVABLE.
     * @return total weight of the given solution
     */
    @Override
    public double solutionWeight() {
        return solutionWeight;
    }

    /**
     * @return The total number of priority queue dequeue operations.
     */
    @Override
    public int numStatesExplored() {
        return numStatesExplored;
    }

    /**
     * @return The total time spent in seconds by the constructor.
     */
    @Override
    public double explorationTime() {
        return this.explorationTime;
    }
}
