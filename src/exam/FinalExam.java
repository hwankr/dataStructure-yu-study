package exam;

import java.awt.Taskbar.State;
import java.io.*;
import java.util.*;

public class FinalExam {
    static class Edge {
        int dest;
        int weight;
        public Edge(int d, int w) {
            this.dest = d;
            this.weight = w;
        }
    }
    
    static int N;
    static ArrayList<Edge>[] adjList;
    static ArrayList<Edge>[] invList;
    static boolean[] visited;
    static int[] inDegree; 
    static int[] outDegree;
    
    // [추가됨] Latest Time 계산을 위한 스택
    static Stack<Integer> topoStack = new Stack<>();
    // [추가됨] 프로젝트 전체 기간 (Earliest의 최댓값)
    static int projectDuration = 0;

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("g.txt"));
            if(sc.hasNext()) N = sc.nextInt();
            
            adjList = new ArrayList[N];
            inDegree = new int[N]; 
            invList = new ArrayList[N];
            outDegree = new int[N];
            
            for (int i = 0; i < N; i++) {
                adjList[i] = new ArrayList<>();
                invList[i] = new ArrayList<>();
            }

            while (sc.hasNextInt()) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                int w = sc.nextInt();
                addEdge(u, v, w);
            }
            sc.close();

            System.out.print("문제 2. DFS 결과 (시작점 0): [ ");
            visited = new boolean[N];
            DFS(0);
            System.out.println("]");

            System.out.print("문제 2(보너스). BFS 결과: [ ");
            visited = new boolean[N]; // [중요!] 방문 배열 다시 깨끗하게 초기화
            System.out.println(BFS(0));
            System.out.println("]");
            
            System.out.print("문제 3. Earliest Time: [ ");
            findEarliestTime();
            System.out.println("]");
            
            // [추가됨] Latest Time 실행
            System.out.print("문제 3(추가). Latest Time: [ ");
            findLatestTime(projectDuration);
            System.out.println("]");

            // 5. [추가됨] Connected Component
            System.out.println("\n문제 4. 연결 요소 (Connected Components):");
            System.out.println("총 연결 요소 개수: " + connectedComponent());
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static void addEdge(int u, int v, int w) {
    	adjList[u].add(new Edge(v, w));
    	inDegree[v]++;
    	invList[v].add(new Edge(u, w));
    	outDegree[u]++;
    }

    static void DFS(int v) {
    	visited[v] = true;
    	
    	for (Edge e: adjList[v]) {
    		if (!visited[e.dest]) DFS(e.dest);
    	}
    }
    
    static List<Integer> BFS(int startNode) {
    	List<Integer> result = new ArrayList<>();
    	LinkedList<Integer> queue = new LinkedList<Integer>();
    	
    	visited[startNode] = true;
    	result.add(startNode);
    	queue.addLast(startNode);
    	
    	while (!queue.isEmpty()) {
    		int u = queue.removeFirst();
    		
    		for (Edge e: adjList[u]) {
    			int v = e.dest;
    			
    			if (!visited[v]) {
    				visited[v] = true;
    				result.add(v);
    				queue.addLast(v);
    			}
    		}
    	}
    	return result;
    }

    static void findEarliestTime() {
    	int[] earliest = new int[N];
    	LinkedList<Integer> queue = new LinkedList<Integer>();
    	
    	for (int i=0; i<N; i++) {
    		if (inDegree[i] == 0) queue.addLast(i);
    	}
    	
    	while (!queue.isEmpty()) {
    		int u = queue.removeFirst();
    		
    		for (Edge e: adjList[u]) {
    			int v = e.dest;
    			int w = e.weight;
    			
    			if (earliest[v] < earliest[u] + w)
    				earliest[v] = earliest[u] + w;
    			inDegree[v]--;
    			if (inDegree[v] == 0) queue.addLast(v);
    		}
    	}
    }
    
    // [새로 만든 메서드]
    static void findLatestTime(int projectDuration) {
    	int[] latest = new int[N];
    	LinkedList<Integer> queue = new LinkedList<Integer>();
    	
    	for (int i=0; i<N; i++) {
    		outDegree[i] = adjList[i].size();
    	}
    	for (int i=0; i<N; i++) {
    		if (outDegree[i] == 0) queue.addLast(i);
    	}
    	
    	while (!queue.isEmpty()) {
    		int u = queue.removeFirst();
    		
    		for (Edge e: invList[u]) {
    			int v = e.dest;
    			int w = e.weight;
    			
    			if (latest[v] > latest[u] - w)
    				latest[v] = latest[u] - w;
    			outDegree[v]--;
    			if (outDegree[v] == 0) queue.addLast(v);
    		}
    	}
    	
    }
    
    static int connectedComponent() {
    	List<Integer> L = new ArrayList<>();
    	visited = new boolean[N];
    	int count = 0;
    	
    	for (int i=0; i<N; i++) {
    		if (!visited[i]) {
    			count++;
    			dfsForCC(i, L);
    			for (int k: L) {
    				System.out.print(k + " ");
    			}
    			System.out.println();
    			L.clear();
    		}
    	}
    	return count;
    }
    
    static void dfsForCC(int v, List<Integer> L) {
    	visited[v] = true;
    	L.add(v);
    	
    	for (Edge e: adjList[v]) {
    		if (!visited[e.dest]) dfsForCC(e.dest, L);
    	}
    }
}