package dataStructure.lesson.chap7;

@SuppressWarnings("rawtypes")
public class QuickSort extends AbstractSort {
	public static void QuickSort(Comparable[] a) {
		sort(a, 0, a.length-1);
	}
	
	private static void sort(Comparable[] a, int left, int right) {
		if (left >= right) return;
		
		int i = left;
		int j = right + 1;
		Comparable pivot = a[left];
		
		while (true) {
			while (less(a[++i], pivot))
				if (i == right) break;
			while (less(pivot, a[--j]))
				if (j == left) break;
			if (i >= j) break;
			swap(a, i, j);
		}
		swap(a, left, j);
		sort(a, left, j-1);
		sort(a, j+1, right);
	}
}
