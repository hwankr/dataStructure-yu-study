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
	
	public Iterable<Integer> adj(int v) {
		ArrayList<Integer> L = new ArrayList<Integer>(size);
		for (int i=0; i<size; i++)
			if (adj[v][i] == 1)
				L.add(i);
		return L;
	}
	
	public int inDegree(int v) {
		int count = 0;
		for (int i=0; i <size; i++) 
			count += adj[i][v];
		return count;
	}
	
	public int outDegree(int v) {
		int count = 0;
		for (int i=0; i < size; i++)
			count += adj[v][i];
		return count;
	}
}
