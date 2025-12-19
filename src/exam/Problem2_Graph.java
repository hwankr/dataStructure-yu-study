package exam;

import java.io.*;
import java.util.*;

// 간선 정보를 저장할 클래스
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
    static ArrayList<Edge>[] adj;      // 인접 리스트
    static ArrayList<Edge>[] invAdj;   // 역인접 리스트 (Latest Time 계산용)
    static boolean[] visited;
    
    // Earliest Time 계산 결과 (Latest 계산에 필요함, 이미 주어졌다고 가정하거나 간단히 구현)
    static int[] earliest; 

    public static void main(String[] args) throws IOException {
        Scanner fileSc = new Scanner(new File("g.txt")); // 파일이 프로젝트 폴더에 있어야 함
        
        if (fileSc.hasNext()) N = fileSc.nextInt();
        
        adj = new ArrayList[N];
        invAdj = new ArrayList[N];
        
        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
            invAdj[i] = new ArrayList<>();
        }

        // 파일 형식: u v w (u->v 가중치 w)
        while (fileSc.hasNext()) {
            int u = fileSc.nextInt();
            int v = fileSc.nextInt();
            int w = fileSc.nextInt();
            
            // [TODO 1] 그래프 구성하기 (adj와 invAdj 모두 추가)
            // 여기에 코드를 작성하세요.
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

        // 2. Latest Time 수행
        // (편의상 Earliest Time은 이미 계산되어 마지막 노드의 값이 전체 프로젝트 기간이라고 가정)
        // 실제 시험에서는 Earliest를 먼저 구해야 할 수도 있음.
        earliest = new int[N];
        calculateEarliest(); // (구현 생략 가능하지만, Latest 계산하려면 필요함)
        int projectDuration = earliest[N-1]; // 마지막 노드의 Earliest Time이 프로젝트 종료 시간

        int[] latest = new int[N];
        calculateLatestTime(latest, projectDuration);

        System.out.print("문제 2.2: Latest time = [ ");
        for (int i = 0; i < N; i++) System.out.print(latest[i] + " ");
        System.out.println("]");
        
        sc.close();
    }

    // [TODO 2] DFS 구현 (재귀 권장)
    static void DFS(int v) {
        
    }

    // [TODO 3] Latest Time 계산
    static void calculateLatestTime(int[] latest, int projectDuration) {
        // 1. latest 배열을 모두 projectDuration으로 초기화
        // 2. 역방향 위상 정렬을 수행하거나, 큐를 사용하여 뒤에서부터 계산
        // 공식: latest[u] = min(latest[u], latest[v] - weight)  (단, 간선은 u -> v)
        // 힌트: invAdj(역인접 리스트)를 사용하면 편리합니다.
        
        // 여기에 코드를 작성하세요.
    }
    
    // (참고용) Earliest Time 계산 - Latest Time 계산을 위해 필요하면 참조해서 구현
    static void calculateEarliest() {
        int[] inDegree = new int[N];
        for(int i=0; i<N; i++) {
            for(Edge e : adj[i]) inDegree[e.target]++;
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i=0; i<N; i++) if(inDegree[i]==0) q.offer(i);
        
        while(!q.isEmpty()) {
            int u = q.poll();
            for(Edge e : adj[u]) {
                if(earliest[e.target] < earliest[u] + e.weight) {
                    earliest[e.target] = earliest[u] + e.weight;
                }
                if(--inDegree[e.target] == 0) q.offer(e.target);
            }
        }
    }
}