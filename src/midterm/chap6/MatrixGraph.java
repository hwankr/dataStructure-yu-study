package midterm.chap6;

import java.util.ArrayList;

public class MatrixGraph implements Graph {
	int size;
	boolean directed;
	int[][] adj;
	
	public MatrixGraph(int size, boolean directed) {
		this.size = size;
		this.directed = directed;
		adj = new int[size][size];
	}

	public int size() {
		return size;
	}
	
	public void addEdge(int v, int w) {
		adj[v][w] = 1;
		if (!directed) adj[w][v] = 1;
	}
}
