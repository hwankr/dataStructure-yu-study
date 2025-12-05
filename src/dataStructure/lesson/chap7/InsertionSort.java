package dataStructure.lesson.chap7;

public class InsertionSort extends AbstractSort {
	@SuppressWarnings("rawtypes")
	public static void sort(Comparable[] a) {
		int i, j;
		for (i=1; i<a.length; i++) {
			Comparable next = a[i];
			for (j=i-1; j>=0 && less(next, a[j]); j--)
				a[j+1] = a[j];
			a[j+1] = next;
		}
	}
}
