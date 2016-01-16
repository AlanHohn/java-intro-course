package org.anvard.introtojava.algorithms.graphs;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class Graph {

	private Set<Node> nodes;

	public Graph() {
		this.nodes = new HashSet<>();
	}
	
	public Set<Node> getNodes() {
		return nodes;
	}
	
	public Node getRandomNode() {
		// Only with small graphs, for testing, please. This is O(n)
		int node = new Random().nextInt(nodes.size());
		Iterator<Node> iter = nodes.iterator();
		for (int i = 0; i < node; i++) {
		    iter.next();
		}
		return iter.next();
	}
	
	public void printDotGraph() {
		System.out.println("digraph G {");
		for (Node node: nodes) {
			for (Edge edge: node.getEdges()) {
				StringBuilder edges = new StringBuilder();
				edges.append("  ");
				edges.append(node.getId());
				edges.append(" -> ");
				edges.append(edge.getTargetNode().getId());
				edges.append(" [ label=\"");
				edges.append(edge.getWeight());
				edges.append("\" ];");
				System.out.println(edges.toString());
			}
		}
		System.out.println("}");
	}

}
