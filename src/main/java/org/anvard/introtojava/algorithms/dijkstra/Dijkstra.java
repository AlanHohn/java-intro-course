package org.anvard.introtojava.algorithms.dijkstra;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.anvard.introtojava.algorithms.graphs.Edge;
import org.anvard.introtojava.algorithms.graphs.Graph;
import org.anvard.introtojava.algorithms.graphs.Node;
import org.anvard.introtojava.algorithms.graphs.Path;
import org.anvard.introtojava.algorithms.graphs.RandomGraph;
import org.anvard.introtojava.algorithms.graphs.ShortestPath;

public class Dijkstra implements ShortestPath {

	@Override
	public Map<Node, Path> shortestPath(Graph graph, Node source) {

		Map<Node, PathState> state = new HashMap<>();
		Set<PathState> unvisited = new HashSet<>();
		PathState current = new PathState(0, source, null);
		state.put(source, current);

		while (current != null) {
			for (Edge edge : current.self.getEdges()) {
				int newDistance = current.currentWeight + edge.getWeight();
				PathState target = state.get(edge.getTargetNode());
				if (null == target) {
					target = new PathState(newDistance, edge.getTargetNode(), current.self);
					state.put(edge.getTargetNode(), target);
				} else if (newDistance < target.currentWeight) {
					target.currentWeight = newDistance;
					target.previous = current.self;
				}
				if (!target.visited) {
					unvisited.add(target);
				}
			}
			current.visited = true;
			unvisited.remove(current);
			current = unvisited.isEmpty() ? null : unvisited.iterator().next();
		}

		Map<Node, Path> paths = new HashMap<>();
		for (Node node : graph.getNodes()) {
			PathState ps = state.get(node);
			Path path = new Path();
			List<Node> list = new ArrayList<>();
			path.setNodes(list);
			if (null != ps) {
				path.setTotalWeight(ps.currentWeight);

				while (ps.previous != null) {
					list.add(ps.previous);
					ps = state.get(ps.previous);
				}
				Collections.reverse(list);
			}
			paths.put(node, path);
		}
		return paths;
	}

	private class PathState {
		private int currentWeight;
		private Node self;
		private Node previous;
		private boolean visited;

		private PathState(int currentWeight, Node self, Node previous) {
			this.currentWeight = currentWeight;
			this.self = self;
			this.previous = previous;
			this.visited = false;
		}

	}
	
	public static void main(String[] args) {
		Dijkstra d = new Dijkstra();

		Graph g = new RandomGraph().generateDirected(20, 10);
		System.out.println("Graph: ");
		g.printDotGraph();

		Node source = g.getRandomNode();
		System.out.println("Source node: " + source.getId());

		Map<Node, Path> paths = d.shortestPath(g, source);
		System.out.println("Paths: ");
		for (Map.Entry<Node, Path> e : paths.entrySet()) {
			System.out.println(e.getKey().getId() + ": " + e.getValue().toString());
		}
	}

}
