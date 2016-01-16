package org.anvard.introtojava.algorithms.graphs;

import java.util.HashSet;
import java.util.Set;

public class Node {

	private String id;
	private Set<Edge> outgoing;

	public Node(String id) {
		this.id = id;
		this.outgoing = new HashSet<>();
	}
	
	public String getId() {
		return id;
	}

	public Set<Edge> getEdges() {
		return outgoing;
	}

}
