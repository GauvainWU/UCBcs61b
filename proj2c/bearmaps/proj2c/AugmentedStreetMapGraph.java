package bearmaps.proj2c;

import bearmaps.hw4.streetmap.Node;
import bearmaps.hw4.streetmap.StreetMapGraph;
import bearmaps.proj2ab.KDTree;
import bearmaps.proj2ab.Point;
import bearmaps.proj2c.utils.MyTrieSet;

import java.util.*;

/**
 * An augmented graph that is more powerful that a standard StreetMapGraph.
 * Specifically, it supports the following additional operations:
 *
 *
 * @author Alan Yao, Josh Hug, ________
 */
public class AugmentedStreetMapGraph extends StreetMapGraph {
    private HashMap<Point, Node> PointToNode;
    private HashMap<String, HashSet<Node>> cleanToNode;
    private MyTrieSet trieSet;

    public AugmentedStreetMapGraph(String dbPath) {
        super(dbPath);
        // You might find it helpful to uncomment the line below:
        List<Node> nodes = this.getNodes();
        trieSet = new MyTrieSet();
        PointToNode = new HashMap<>();
        cleanToNode = new HashMap<>();

        for (Node n : nodes) {
            if (neighbors(n.id()).size() != 0) {
                PointToNode.put(new Point(n.lon(), n.lat()), n);
            }

            String cleanName = clean(n.name());
            if (cleanName == null || cleanName.length() == 0) {
                continue;
            }
            trieSet.add(cleanName);
            if (cleanToNode.containsKey(cleanName)) {
                cleanToNode.get(cleanName).add(n);
            } else {
                HashSet<Node> s = new HashSet<>();
                s.add(n);
                cleanToNode.put(cleanName, s);
            }
        }
    }

    /**
     * For Project Part II
     * Returns the vertex closest to the given longitude and latitude.
     * @param lon The target longitude.
     * @param lat The target latitude.
     * @return The id of the node in the graph closest to the target.
     */
    public long closest(double lon, double lat) {
        List<Point> l = new ArrayList<>(PointToNode.keySet());
        KDTree kdTree = new KDTree(l);
        return PointToNode.get(kdTree.nearest(lon, lat)).id();
    }

    /**
     * For Project Part III (gold points)
     * In linear time, collect all the names of OSM locations that prefix-match the query string.
     * @param prefix Prefix string to be searched for. Could be any case, with our without
     *               punctuation.
     * @return A <code>List</code> of the full names of locations whose cleaned name matches the
     * cleaned <code>prefix</code>.
     */
    public List<String> getLocationsByPrefix(String prefix) {
        LinkedList<String> result = new LinkedList<>();
        for (String cleanName : trieSet.keysWithPrefix(clean(prefix))) {
            for (Node n : cleanToNode.get(cleanName)) {
                result.add(n.name());
            }
        }
        return result;
    }

    private static String clean(String name) {
        if (name == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        String nameToLower = name.toLowerCase();
        for (int i = 0; i < name.length(); ++i) {
            char c = nameToLower.charAt(i);
            if (Character.isLetter(c) || c == ' ') {
                result.append(c);
            }
        }
        return result.toString();
    }

    /**
     * For Project Part III (gold points)
     * Collect all locations that match a cleaned <code>locationName</code>, and return
     * information about each node that matches.
     * @param locationName A full name of a location searched for.
     * @return A list of locations whose cleaned name matches the
     * cleaned <code>locationName</code>, and each location is a map of parameters for the Json
     * response as specified: <br>
     * "lat" -> Number, The latitude of the node. <br>
     * "lon" -> Number, The longitude of the node. <br>
     * "name" -> String, The actual name of the node. <br>
     * "id" -> Number, The id of the node. <br>
     */
    public List<Map<String, Object>> getLocations(String locationName) {
        List<Map<String, Object>> result = new ArrayList<>();
        for (String cleanName : trieSet.keysWithPrefix(clean(locationName))) {
            for (Node n : cleanToNode.get(cleanName)) {
                HashMap<String, Object> h = new HashMap<>();
                h.put("lat", n.lat());
                h.put("lon", n.lon());
                h.put("name", n.name());
                h.put("id", n.id());
                result.add(h);
            }
        }
        return result;
    }

    /**
     * Useful for Part III. Do not modify.
     * Helper to process strings into their "cleaned" form, ignoring punctuation and capitalization.
     * @param s Input string.
     * @return Cleaned string.
     */
    private static String cleanString(String s) {
        return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
    }

}
