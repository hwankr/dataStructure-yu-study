package exam;

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
    	int node, next, root;
    	
    	for (root = i; parent[root] >= 0; root = parent[root]);
    	for (node = i; node != root; node = next) {
    		next = parent[node];
    		parent[node] = root;
    	}
    	return root;
    }

    // [TODO 2] Weighted Union (무게 고려 합집합)
    static void weightedUnion(int i, int j) {
    	int root1 = collapsingFind(i);
    	int root2 = collapsingFind(j);
    	
    	if (root1 != root2) {
    		int temp = parent[i] + parent[j];
    		
    		if (parent[i] > parent[j]) {
    			parent[i] = j;
    			parent[j] = temp;
    		} else {
    			parent[j] = i;
    			parent[i] = temp;
    		}
    	}
    }
}