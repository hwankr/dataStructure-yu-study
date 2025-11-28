package dataStructure.lesson.chap6;

public class Edge {
	int from, to, weight;
	
	public Edge (int from, int to, int weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
	
	public Edge(Edge e) {
		this.from = e.from;
		this.to = e.to;
		this.weight = e.weight;
	}

	@Override
	public String toString() {
		return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
	}
	
}
