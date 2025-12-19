package exam;

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
    	System.out.print(v + " ");
    	
    	for (Edge e : adjList[v]) {
    		if (!visited[e.dest]) DFS(e.dest);
    	}
    }
    
    static List<Integer> BFS(int startNode) {
    	List<Integer> result = new ArrayList<>();
    	LinkedList<Integer> queue = new LinkedList<>();
    	
    	visited[startNode] = true;
    	result.add(startNode);
    	queue.addLast(startNode);
    	while (!queue.isEmpty()) {
    		int u = queue.removeFirst();
    		for (Edge e: adjList[u]) {
    			if (!visited[e.dest]) {
    				result.add(e.dest);
    				visited[e.dest] = true;
    				queue.addLast(e.dest);
    			}
    		}
    	}
    	return result;
    }

    static void findEarliestTime() {
    	int[] earliest = new int[N];
    	LinkedList<Integer> queue = new LinkedList<>();
    	
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
    			if (inDegree[v] == 0)
    				queue.addLast(v);
    		}
    	}
    	
    	for (int t : earliest) {
    		projectDuration = Math.max(t, projectDuration);
    		System.out.print(t + " ");
    	}
    }
    
    // [새로 만든 메서드]
    static void findLatestTime(int projectDuration) {
    	int[] latest = new int[N];
    	Arrays.fill(latest, projectDuration);
    	LinkedList<Integer> queue = new LinkedList<>();
    	
    	for (int i=0; i<N; i++) {
    		outDegree[i] = adjList[i].size();
    	}
    	
    	for (int i=0; i<N; i++) {
    		if (outDegree[i] == 0)
    			queue.addLast(i);
    	}
    	
    	while (!queue.isEmpty()) {
    		int u = queue.removeFirst();
    		for (Edge e : invList[u]) {
    			int v = e.dest;
    			int w = e.weight;
    			
    			if (latest[v] > latest[u] - w)
    				latest[v] = latest[u] - w;
    			outDegree[v]--;
    			if (outDegree[v] == 0) 
    				queue.addLast(v);
    		}
    	}
    	
    	for (int i = 0; i < N; i++) {
            System.out.print(latest[i] + " ");
        }
    }
}