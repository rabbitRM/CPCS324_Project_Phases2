
package GraphFramework;

import java.io.FileNotFoundException;
import java.util.*;

public class SingleSourceSPAlg extends ShortestPathAlgorithm {
   public SingleSourceSPAlg (Graph graph){
        this.graph = graph ;
    }
     


public static void dijkstra(Vertex source) {
    // Initialize the distances from the source vertex to all other vertices to infinity.
    Map<Integer, Integer> distances = new HashMap<>();
    for (Vertex vertex : graph.getVertices()) {
        distances.put(vertex.getLabel(), Integer.MAX_VALUE);
    }
    distances.put(source.getLabel(), 0);

    // Initialize the set of visited vertices to the empty set.
    Set<Vertex> visited = new HashSet<>();

    // Initialize a map to store the paths from the source vertex to each vertex.
    Map<Vertex, List<Edge>> paths = new HashMap<>();
    for (Vertex vertex : graph.getVertices()) {
        paths.put(vertex, new ArrayList<>());
    }

    // While there are still vertices that have not been visited:
    while (visited.size() < graph.getVertices().size()) {
        Vertex currentVertex = null;
int shortestDistance = Integer.MAX_VALUE;

// Find the first unvisited vertex and initialize currentVertex to it.
for (Vertex vertex : graph.getVertices()) {
    if (!visited.contains(vertex)) {
        currentVertex = vertex;
        shortestDistance = distances.get(vertex.getLabel());
        break;
    }
}
       // Search for the vertex with the shortest distance that has not been visited yet.
for (Vertex vertex : graph.getVertices()) {
    if (!visited.contains(vertex) && distances.get(vertex.getLabel()) < shortestDistance){
        currentVertex = vertex;
        shortestDistance = distances.get(vertex.getLabel());
    }
}
        // For each neighbor of the current vertex:
        for (Edge edge : currentVertex.getAdjLists()) {
            Vertex neighbor = edge.getTarget();
            // If the distance to the neighbor through the current vertex is shorter than the current distance to the neighbor:
            int distanceToNeighbor = distances.get(currentVertex.getLabel()) + edge.getWeight();
            if (distanceToNeighbor < distances.get(neighbor.getLabel())) {
                // Update the distance to the neighbor.
                distances.put(neighbor.getLabel(), distanceToNeighbor);
                // Update the path to the neighbor.
                List<Edge> path = new ArrayList<>(paths.get(currentVertex));
                path.add(edge);
                paths.put(neighbor, path);
                // Set the parent vertex object of the edge to the current vertex.
                edge.setParent(currentVertex);
            }
        }

        visited.add(currentVertex);

        }
    
    for (Vertex vertex : graph.getVertices()) {
         // Call dijkstra to get the shortest path from the source to the destination vertex.
       List<Edge> path = paths.get(graph.getVertices().get(vertex.getLabel()));
       if (path.isEmpty()) {
            System.out.println("No path exists.");
        } 
       else {
           System.out.print("Shortest path from vertex " + source.displayInfo() + " to vertex " + vertex.displayInfo() + ": ");
           for (Edge edge : path) {
           System.out.print(edge.getSource().displayInfo()+ "->");
          }
          System.out.println(vertex.displayInfo() + " (distance: " + distances.get(vertex.getLabel()) + ")");
       }
    
    }
}
  



}

