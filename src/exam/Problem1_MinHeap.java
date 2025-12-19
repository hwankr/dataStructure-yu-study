package exam;

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
    	
    }

    // [TODO 2] 최소 힙 삭제 연산 (Down-heap bubbling)
    static int deleteMinHeap() {

    }

    // [TODO 3] Inorder 순회 (Left -> Root -> Right)
    static void inorder(int i) {

    }
}