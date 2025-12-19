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

        }
    }

    // [TODO] 강의자료 7장 스타일의 Partition 구현
    static int partition(int[] a, int left, int right) {


    }
    

}