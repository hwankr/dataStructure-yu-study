package examSol;

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
    static boolean[] visited;
    static int[] inDegree; 
    
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
            
            for (int i = 0; i < N; i++) {
                adjList[i] = new ArrayList<>();
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
            BFS(0);
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
    }

    static void DFS(int v) {
        visited[v] = true;
        System.out.print(v + " ");
        for (Edge e : adjList[v]) {
            if (!visited[e.dest]) DFS(e.dest);
        }
    }

 // [문제 2-2 추가] BFS 구현 (Queue: LinkedList 사용)
    static void BFS(int startNode) {
        LinkedList<Integer> q = new LinkedList<>();
        visited[startNode] = true;
        System.out.print(startNode + " ");
        q.addLast(startNode); // 줄 서기 (Enqueue)

        // 3. 큐가 빌 때까지 반복
        while (!q.isEmpty()) {
            int u = q.removeFirst(); // 맨 앞 사람 입장 (Dequeue)

            // u와 연결된 모든 노드들을 확인
            for (Edge e : adjList[u]) {
                int v = e.dest;

                // 아직 방문하지 않은 곳이라면?
                if (!visited[v]) {
                    visited[v] = true;       // [중요] 큐에 넣을 때 방문 체크!
                    System.out.print(v + " "); // 출력
                    q.addLast(v);            // 줄 서기
                }
            }
        }
    }
    
    static void findEarliestTime() {
    	int[] earliest = new int[N];
    	
    	LinkedList<Integer> queue = new LinkedList<>();
    	
    	for (int i=0; i<N; i++) {
    		if (inDegree[i] == 0)
    			queue.addLast(i);
    	}
    	
    	while (!queue.isEmpty()) {
    		int u = queue.removeFirst();
    		
    		for (Edge e : adjList[u]) {
    			if (earliest[e.dest] < earliest[u] + e.weight)
    				earliest[e.dest] = earliest[u] + e.weight;
    			
    			inDegree[e.dest]--;
    			
    			if (inDegree[e.dest] == 0)
    				queue.addLast(e.dest);
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
        Arrays.fill(latest, projectDuration); // 초기값은 '끝나는 시간'

        // 스택에서 꺼내면 '역순(뒤->앞)'이 됨
        while (!topoStack.isEmpty()) {
            int u = topoStack.pop();

            // u의 다음 단계(v)들을 보면서 내 시간(latest[u])을 앞당김
            for (Edge e : adjList[u]) {
                int v = e.dest;
                int w = e.weight;
                
                // 점화식: L[u] = Min(L[v] - w)
                if (latest[u] > latest[v] - w) {
                    latest[u] = latest[v] - w;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            System.out.print(latest[i] + " ");
        }
    }
}