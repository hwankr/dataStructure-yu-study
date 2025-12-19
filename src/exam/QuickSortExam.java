package exam;

import java.util.*;

public class QuickSortExam {
    public static void main(String[] args) {
        int[] data = {57, 28, 98, 35, 66, 33, 90, 87, 77, 21};
        
        System.out.println("정렬 전: " + Arrays.toString(data));
        quickSort(data, 0, data.length - 1);
        System.out.println("정렬 후: " + Arrays.toString(data));
    }

    static void quickSort(int[] a, int left, int right) {
        if (left < right) {
            int p = partition(a, left, right);
            quickSort(a, left, p-1);
            quickSort(a, p+1, right);
        }
    }

    // [TODO] 강의자료 7장 스타일의 Partition 구현
    static int partition(int[] a, int left, int right) {
    	int pivot = a[left];
    	int i = left + 1;
    	int j = right;
    	
    	while (i <= j) {
    		while (i <= right && a[i] < pivot) i++;
    		while (j >= left + 1 && a[j] > pivot) j--;
    		if (i < j) swap(a, i, j);    	   		
    	}
    	swap(a, left, j);
    	return j;

    }
    
    static void swap(int[] a, int i, int j) {
    	int temp = a[i];
    	a[i] = a[j];
    	a[j] = temp;
    }
}