package examSol;

import java.util.*;

public class InsertionSortExam {
    public static void main(String[] args) {
        int[] data = {5, 2, 4, 6, 1, 3};
        
        System.out.println("정렬 전: " + Arrays.toString(data));
        insertionSort(data);
        System.out.println("정렬 후: " + Arrays.toString(data));
    }

    // [TODO] 강의자료 7장 스타일의 Insertion Sort 구현
    static void insertionSort(int[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            int key = a[i]; // 이번에 삽입할 값
            int j = i - 1;

            // key보다 큰 놈들은 오른쪽으로 한 칸씩 미뤄버림
            while (j >= 0 && a[j] > key) {
                a[j + 1] = a[j]; // 한 칸 뒤로 밀기
                j--;
            }
            
            // 반복문이 끝난 지점(j+1)이 바로 key가 들어갈 명당자리
            a[j + 1] = key;
        }
    }
}