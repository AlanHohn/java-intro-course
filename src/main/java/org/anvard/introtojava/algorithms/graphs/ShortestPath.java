package org.anvard.introtojava.algorithms.graphs;

import java.util.Map;

public interface ShortestPath {

	/**
	 * Finds the shortest path from a source node
	 * to all reachable targets in the graph
	 */
	Map<Node,Path> shortestPath(Graph graph, Node source);
	
}
