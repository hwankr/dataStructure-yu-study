package examSol;

import java.util.Arrays;

public class Problem3_UnionFind {
    static int[] parent;

    public static void main(String[] args) {
        int N = 10; // 0 ~ 9번 노드
        parent = new int[N];
        
        // 초기화: 모든 노드는 루트이며, 크기(무게)는 -1로 설정
        Arrays.fill(parent, -1);

        // 연산 시나리오
        weightedUnion(0, 1);
        weightedUnion(2, 3);
        weightedUnion(4, 5);
        weightedUnion(6, 7);
        weightedUnion(0, 2); // {0,1} U {2,3} -> {0,1,2,3}
        weightedUnion(4, 6); // {4,5} U {6,7} -> {4,5,6,7}
        weightedUnion(0, 4); // 전체 병합

        System.out.println("문제 3.1 배열 상태: " + Arrays.toString(parent));
        
        // 경로 압축 테스트를 위해 강제로 깊은 트리 생성 가정 후 find 호출
        // (위의 union 결과에 따라 0이 루트가 됨)
        System.out.println("문제 3.2 Find(7) 결과: " + collapsingFind(5));
        System.out.println("문제 3.3 Find 후 배열 상태: " + Arrays.toString(parent));
    }

    // [TODO 1] Collapsing Find (경로 압축)
    static int collapsingFind(int i) {
        int root, node, next;
        
        // 1. 루트 찾기 (값이 음수인 곳까지 이동)
        for (root = i; parent[root] >= 0; root = parent[root]);
        
        // 2. 경로 압축 (지나온 노드들을 모두 루트 밑으로 붙임)
        for (node = i; node != root; node = next) {
            next = parent[node];
            parent[node] = root;
        }
        
        return root;
    }

    // [TODO 2] Weighted Union (무게 고려 합집합)
    // parent[i]에는 -노드수(무게)가 저장되어 있음
    static void weightedUnion(int i, int j) {
        int root1 = collapsingFind(i);
        int root2 = collapsingFind(j);

        if (root1 != root2) {
            int temp = parent[root1] + parent[root2]; // 전체 노드 수 합 (음수)

            // root2가 더 노드가 많으면 (값이 더 작으면, 예: -5 < -3)
            // 주의: parent[] 값은 음수이므로, 값이 작을수록 덩치가 큰 것임
            // 여기서는 강의자료 로직대로 parent[root1] > parent[root2] (root2가 더 큼) 일 때
            if (parent[root1] > parent[root2]) {
                parent[root1] = root2; // 작은 놈(1)을 큰 놈(2) 밑으로
                parent[root2] = temp;  // 큰 놈 무게 갱신
            } else {
                parent[root2] = root1; // 작은 놈(2)을 큰 놈(1) 밑으로
                parent[root1] = temp;  // 큰 놈 무게 갱신
            }
        }
    }
}