package dataStructure.lesson.chap6;

public interface WeightedGraph {
	int size();
	void addEdge(int from, int to, int weight);
	Iterable<Edge> adj(int v);
	Iterable<Edge> edges();
}
