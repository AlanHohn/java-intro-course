package org.anvard.introtojava.algorithms.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomGraph {

	public Graph generateDirected(int numNodes, int maxWeight) {
		Random r = new Random();

		// Use for finding targets of edges
		List<Node> nodes = new ArrayList<>(numNodes);
		Graph graph = new Graph();

		int current = 1;
		for (int i = 0; i < numNodes; i++) {
			Node node = new Node("N" + current);
			current++;
			nodes.add(node);
			graph.getNodes().add(node);
		}

		for (int i = 0; i < numNodes - 1; i++) {
			Node source = nodes.get(i);

			// Aiming for a pleasing number of edges
			int numEdges = r.nextInt(numNodes - i) + 1;
			for (int j = 0; j < numEdges; j++) {
				int target = r.nextInt(numNodes - 1) + 1;
				if (i != target) {
					Edge edge = new Edge(r.nextInt(maxWeight - 1) + 1, source, nodes.get(target));
					source.getEdges().add(edge);
				}
			}
		}

		return graph;
	}

	public Graph generateAcyclic(int numNodes, int maxWeight) {
		Random r = new Random();

		// Use for ordering to avoid cycles
		List<Node> nodes = new ArrayList<>(numNodes);
		Graph graph = new Graph();

		int current = 1;
		for (int i = 0; i < numNodes; i++) {
			Node node = new Node("N" + current);
			current++;
			nodes.add(node);
			graph.getNodes().add(node);
		}

		for (int i = 0; i < numNodes - 1; i++) {
			Node source = nodes.get(i);

			// Aiming for a pleasing number of edges
			int numEdges = r.nextInt((numNodes - i) / 2) + 1;
			for (int j = 0; j < numEdges; j++) {
				int target = r.nextInt(numNodes - i) + i;
				if (i != target) {
					Edge edge = new Edge(r.nextInt(maxWeight - 1) + 1, source, nodes.get(target));
					source.getEdges().add(edge);
				}
			}
		}

		return graph;
	}

	public static void main(String args[]) {
		RandomGraph rg = new RandomGraph();
		Graph g;
		if (args.length > 1 && args[1].equalsIgnoreCase("acyclic")) {
			g = rg.generateAcyclic(20, 10);
		} else {
			g = rg.generateDirected(20, 10);
		}
		g.printDotGraph();
	}
}
