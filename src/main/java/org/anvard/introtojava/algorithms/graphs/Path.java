package org.anvard.introtojava.algorithms.graphs;

import java.util.List;

public class Path {

	private int totalWeight;
	private List<Node> nodes;
	
	public int getTotalWeight() {
		return totalWeight;
	}
	public void setTotalWeight(int totalWeight) {
		this.totalWeight = totalWeight;
	}
	public List<Node> getNodes() {
		return nodes;
	}
	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Weight = ");
		sb.append(totalWeight);
		sb.append("; Path = { ");
		for (Node node: nodes) {
			sb.append(node.getId());
			sb.append(" ");
		}
		sb.append("}");
		return sb.toString();
	}
	
}
