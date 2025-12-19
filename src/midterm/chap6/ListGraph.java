package midterm.chap6;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListGraph implements Graph {
	int size;
	boolean directed;
	LinkedList<Integer>[] adj;
	LinkedList<Integer>[] inv;
	
	public ListGraph(int size, boolean directed) {
		this.size = size;
		this.directed = directed;
		adj = new LinkedList[size];
		inv = new LinkedList[size];
		for (int i=0; i<size; i++) {
			adj[i] = new LinkedList<Integer>();
			inv[i] = new LinkedList<Integer>();
		}
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public void addEdge(int v, int w) {
		adj[v].add(w);
		if (!directed)
			adj[w].add(v);
		else
			inv[w].add(v);
	}
	
	public int inDegree(int v) { return inv[v].size(); }
	public int outDegree(int v) { return adj[v].size(); }

	@Override
	public Iterable<Integer> adj(int v) {
		return adj[v];
	}
	
	public List<Integer> dfs(int v) {
		List<Integer> L = new ArrayList<Integer>(size);
		boolean[] visited = new boolean[size];
		dfsSub(v, L, visited);
		return L;
	}
	private void dfsSub(int v, List<Integer> L, boolean[] visited) {
		visited[v] = true;
		L.add(v);
		for (int w : adj[v])
			if (!visited[w])
				dfsSub(w, L, visited);
	}
	
	public List<Integer> bfs(int v) {
		List<Integer> L = new ArrayList<Integer>(size);
		boolean[] visited = new boolean[size];
		ArrayDeque<Integer> queue = new ArrayDeque<>(size);
		visited[v] = true;
		queue.addLast(v);
		while (!queue.isEmpty()) {
			int k = queue.pollFirst();
			for (int w: adj[k]) {
				if (!visited[w]) {
					L.add(w);
					visited[w] = true;
					queue.addLast(w);
				}
			}
		}
		return L;
	}
}
