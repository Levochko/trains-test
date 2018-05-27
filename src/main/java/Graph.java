import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
/*
This class describes how to create a graph by obtaining two neighboring points
 */
public class Graph {
    private Map<Integer, LinkedHashSet<Integer>> map = new HashMap();

    public void addEdge(Integer node1, Integer node2) {
        LinkedHashSet<Integer> adjacent = map.get(node1);
        if(adjacent==null) {
            adjacent = new LinkedHashSet();
            map.put(node1, adjacent);
        }
        adjacent.add(node2);
    }


    public LinkedList<Integer> adjacentNodes(Integer last) {
        LinkedHashSet<Integer> adjacent = map.get(last);
        if(adjacent==null) {
            return new LinkedList();
        }
        return new LinkedList<Integer>(adjacent);
    }
}