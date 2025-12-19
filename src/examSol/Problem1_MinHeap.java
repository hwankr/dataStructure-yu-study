package examSol;

import java.util.*;

public class Problem1_MinHeap {
    static int[] heap;
    static int size = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("seed와 노드 수? ");
        int seed = sc.nextInt();
        int N = sc.nextInt();
        
        Random rand = new Random(seed);
        heap = new int[N + 1]; // 1-based index 사용

        // 1. Random Insert (0~99 사이의 정수)
        for (int i = 0; i < N; i++) {
            insertMinHeap(rand.nextInt(100));
        }

        System.out.print("문제 1.1 insert 후의 Heap 내용: [ ");
        for (int i = 1; i <= size; i++) System.out.print(heap[i] + " ");
        System.out.println("]");

        // 2. Inorder Traversal
        System.out.print("문제 1.2 heap을 inorder 순회한 내용: [ ");
        inorder(1);
        System.out.println("]");

        // 3. Delete N/3 times
        int loopCount = N / 3;
        for (int i = 0; i < loopCount; i++) {
            deleteMinHeap();
        }

        System.out.print("문제 1.3 delete 후의 Heap 내용: [ ");
        for (int i = 1; i <= size; i++) System.out.print(heap[i] + " ");
        System.out.println("]");
        
        sc.close();
    }

    // [TODO 1] 최소 힙 삽입 연산 (Up-heap bubbling)
    static void insertMinHeap(int data) {
    	heap[++size] = data;
    	int i = size;
    	
    	while (i != 1 && data < heap[i/2]) {
    		heap[i] = heap[i/2];
    		i /= 2;
    	}
    	heap[i] = data;
    }

    // [TODO 2] 최소 힙 삭제 연산 (Down-heap bubbling)
    static int deleteMinHeap() {
        if (size == 0) return -1;
        int answer = heap[1];
        int temp = heap[size--];
        
        int parent = 1;
        int child = 2;
        
        while (child <= size) {
        	if (child < size && heap[child] > heap[child+1]) child += 1;
        	if (temp  <= heap[child]) break;
        		
        	heap[parent] = heap[child];
        	parent = child;
        	child *= 2;
        	
        }
        heap[parent] = temp;
        return answer; // 삭제된 값 리턴
    }

    // [TODO 3] Inorder 순회 (Left -> Root -> Right)
    static void inorder(int i) {
        if (i > size) return;
        inorder(2*i);
        System.out.print(heap[i] + " ");
        inorder(2*i+1);
    }
}