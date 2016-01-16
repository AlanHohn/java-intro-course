package org.anvard.introtojava.algorithms.graphs;

public class Edge {

	private int weight;
	private Node sourceNode;
	private Node targetNode;
	
	public Edge(int weight, Node sourceNode, Node targetNode) {
		this.weight = weight;
		this.sourceNode = sourceNode;
		this.targetNode = targetNode;
	}
	
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public Node getSourceNode() {
		return sourceNode;
	}
	public void setSourceNode(Node sourceNode) {
		this.sourceNode = sourceNode;
	}
	public Node getTargetNode() {
		return targetNode;
	}
	public void setTargetNode(Node targetNode) {
		this.targetNode = targetNode;
	}
	
}
