package dataStructure.lesson.chap6;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class WeightedListGraph implements WeightedGraph {
	int size;
	boolean directed;
	LinkedList<Edge> adj[];
	LinkedList<Edge> inv[];
	
	
	@SuppressWarnings("unchecked")
	public WeightedListGraph(int size, boolean directed) {
		this.size = size;
		this.directed = directed;
		
		adj = new LinkedList[size];
		for (int i=0; i<size; i++)
			adj[i] = new LinkedList<Edge>();
		if (directed) {
			inv = new LinkedList[size];
			for (int i=0; i<size; i++)
				inv[i] = new LinkedList<Edge>();
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void addEdge(int from, int to, int weight) {
		adj[from].add(new Edge(from, to, weight));
		if (directed)
			inv[to].add(new Edge(from, to, weight));
		else
			adj[to].add(new Edge(from, to, weight));
	}

	@Override
	public Iterable<Edge> adj(int v) {
		ArrayList<Edge> list = new ArrayList<>();
			for (int v1 = 0; v1 < size; v1++) {
			for (Edge e: adj[v1]) if (e.from == v1)
			list.add(e);
		}
		return list;
	}

	@Override
	public Iterable<Edge> edges() {
		ArrayList<Edge> list = new ArrayList<>();
			for (int v = 0; v < size; v++) {
			for (Edge e: adj[v]) if (e.from == v)
			list.add(e);
		}
		return list;
	}
	
	public List<Edge> criticalActivity(int start, int finish) {
		int[] earliest = getEarliest(start, finish);
		System.out.print(" " + Arrays.toString(earliest));
		return null;
	}
	
	private int[] getEarliest(int start, int finish) {
		int[] earliest = new int[size]; // 0으로 초기화
		int[] count = new int[size]; // vertex의 indegree로 초기화
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		
		for (int i=0; i < size; i++) {
			count[i] = inv[i].size();
			if (count[i] == 0)
				stack.push(i);
		}
		
		while (stack.isEmpty()) {
			int v = stack.pop();
			for (Edge e : adj(v)) {
				int to = e.to;
				count[to]--;
				if (count[to] == 0)
					stack.push(to);
				if (earliest[to] < earliest[v] + e.weight)
					earliest[to] = earliest[v] + e.weight;
			}
		}
		return earliest;
	}

}
