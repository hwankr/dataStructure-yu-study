package dataStructure.lesson.chap7;

import java.util.Random;
import java.util.Scanner;

public class TestDriver {

	public static void main(String[] args) {
		Integer[] a = {5, 2, 1, 9, 8, 5, 7, 4, 6, 3};
//		SelectionSort.sort(a);
//		SelectionSort.show(a);
		
//		InsertionSort.sort(a);
//		InsertionSort.show(a);
		
//		QuickSort.QuickSort(a);
//		QuickSort.show(a);
		HeapSort.sort(a);
		HeapSort.show(a);
		
//		
//		Scanner sc = new Scanner(System.in);
//		System.out.print("N? ");
//		int N = sc.nextInt();
//		
//		Double[] a1 = new Double[N];
//		Random rand = new Random();
//		
//		for (int i=0; i < N; i++) {
//			a1[i] = rand.nextDouble();
//		}
//		
//		Double[] b = new Double[N];
//		Double[] c = new Double[N];
//		
//		System.arraycopy(a1, 0, b, 0, N);
//		System.arraycopy(a1, 0, c, 0, N);
//		
//	
//		long start = System.currentTimeMillis();
//		SelectionSort.sort(a1);
//		long end = System.currentTimeMillis();
//		System.out.println("Selection Sort: 정렬 여부 " + SelectionSort.isSorted(a1));
//		System.out.println("실행 시간 = " + (end - start) / 1000.0);
//		
//		start = System.currentTimeMillis();
//		InsertionSort.sort(b);
//		end = System.currentTimeMillis();
//		System.out.println("InsertionSort: 정렬 여부 " + InsertionSort.isSorted(b));
//		System.out.println("실행 시간 = " + (end - start) / 1000.0);
//		
//		
//		start = System.currentTimeMillis();
//		QuickSort.sort(c);
//		end = System.currentTimeMillis();
//		System.out.println("QuickSort: 정렬 여부 " + QuickSort.isSorted(c));
//		System.out.println("실행 시간 = " + (end - start) / 1000.0);
	}
}
