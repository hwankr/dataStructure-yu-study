package examSol;

import java.io.*;
import java.util.*;

class Edge {
    int target;
    int weight;
    public Edge(int target, int weight) {
        this.target = target;
        this.weight = weight;
    }
}

public class Problem2_Graph {
    static int N;
    static ArrayList<Edge>[] adj;      // 정방향: u -> v
    static ArrayList<Edge>[] invAdj;   // 역방향: v -> u (Latest 계산용)
    static boolean[] visited;
    
    // Earliest Time 계산 결과
    static int[] earliest; 

    public static void main(String[] args) throws IOException {
        // 프로젝트 폴더 최상단에 g.txt가 있어야 합니다.
        Scanner fileSc = new Scanner(new File("g.txt")); 
        
        if (fileSc.hasNext()) N = fileSc.nextInt();
        
        adj = new ArrayList[N];
        invAdj = new ArrayList[N];
        
        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
            invAdj[i] = new ArrayList<>();
        }

        // [정답 1] 그래프 구성 (adj, invAdj)
        while (fileSc.hasNext()) {
            int u = fileSc.nextInt();
            int v = fileSc.nextInt();
            int w = fileSc.nextInt();
            
            adj[u].add(new Edge(v, w));     // u에서 v로 감
            invAdj[v].add(new Edge(u, w));  // v로 들어오는 u를 저장 (역방향)
        }
        fileSc.close();

        Scanner sc = new Scanner(System.in);
        System.out.print("시작점? ");
        int startNode = sc.nextInt();

        // 1. DFS 수행
        System.out.print("문제 2.1: " + startNode + "에서 시작하는 DFS = [ ");
        visited = new boolean[N];
        DFS(startNode);
        System.out.println("]");

        // 2. Earliest Time 계산 (Latest를 위해 필수)
        earliest = new int[N];
        calculateEarliest(); 
        
        // 마지막 노드(N-1)의 Earliest Time이 전체 프로젝트 기간임
        int projectDuration = earliest[N-1]; 

        // 3. Latest Time 계산
        int[] latest = new int[N];
        calculateLatestTime(latest, projectDuration);

        System.out.print("문제 2.2: Latest time = [ ");
        for (int i = 0; i < N; i++) System.out.print(latest[i] + " ");
        System.out.println("]");
        
        // (보너스 확인) Critical Path: earliest[i] == latest[i] 인 노드들
        sc.close();
    }

    // [정답 2] DFS 구현 (재귀)
    static void DFS(int v) {
        visited[v] = true;
        System.out.print(v + " ");
        
        // 인접한 노드 중 방문하지 않은 곳으로 깊이 들어감
        for (Edge e : adj[v]) {
            if (!visited[e.target]) {
                DFS(e.target);
            }
        }
    }

    // [보조] Earliest Time 계산 (Forward)
    static void calculateEarliest() {
        int[] inDegree = new int[N];
        // 진입 차수 계산
        for(int i=0; i<N; i++) {
            for(Edge e : adj[i]) inDegree[e.target]++;
        }
        
        Queue<Integer> q = new LinkedList<>();
        // 시작점(진입차수 0) 큐에 넣기
        for(int i=0; i<N; i++) if(inDegree[i]==0) q.offer(i);
        
        while(!q.isEmpty()) {
            int u = q.poll();
            for(Edge e : adj[u]) {
                int v = e.target;
                int w = e.weight;
                
                // Earliest[v] = max(Earliest[v], Earliest[u] + w)
                if(earliest[v] < earliest[u] + w) {
                    earliest[v] = earliest[u] + w;
                }
                
                if(--inDegree[v] == 0) q.offer(v);
            }
        }
    }

    // [정답 3] Latest Time 계산 (Backward)
    static void calculateLatestTime(int[] latest, int projectDuration) {
        // 1. 모든 노드의 latest를 프로젝트 종료 시간으로 초기화
        Arrays.fill(latest, projectDuration);
        
        // 2. 역방향 위상정렬을 위해 '진출 차수(Out-degree)'를 사용
        int[] outDegree = new int[N];
        for (int i = 0; i < N; i++) {
            outDegree[i] = adj[i].size();
        }
        
        Queue<Integer> q = new LinkedList<>();
        // 진출 차수가 0인 노드(종료점)를 큐에 넣음
        for (int i = 0; i < N; i++) {
            if (outDegree[i] == 0) q.offer(i);
        }
        
        while (!q.isEmpty()) {
            int v = q.poll(); // 뒤쪽 노드부터 꺼냄 (Child)
            
            // v로 들어오는 부모 노드 u를 찾음 (invAdj 사용)
            for (Edge e : invAdj[v]) {
                int u = e.target; // 역방향 간선이므로 target이 부모 u
                int w = e.weight;
                
                // Latest[u] = min(Latest[u], Latest[v] - w)
                // 늦어도 v의 시작시간(Latest[v])보다 w만큼 전에는 끝나야 함
                if (latest[u] > latest[v] - w) {
                    latest[u] = latest[v] - w;
                }
                
                // 부모의 진출 차수를 줄이고 0이 되면 큐에 넣음
                if (--outDegree[u] == 0) q.offer(u);
            }
        }
    }
}