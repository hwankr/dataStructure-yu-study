package dataStructure.lesson.chap7;

public class HeapSort extends AbstractSort {
	@SuppressWarnings("rawtypes")
	private static void adjust(Comparable[] a, int root, int N) {
		Comparable rootKey = a[root];
		int child = root * 2 + 1;
		while (child <= N) {
			if (child < N && less(a[child], a[child+1]))
				child += 1;
			if (less(a[child], rootKey)) break;
			a[(child-1)/2] = a[child];
			child = child*2 + 1;
		}
		a[(child-1)/2] = rootKey;
	}
	public static void sort(Comparable[] a) {
		int N = a.length;
		for (int i=N/2; i>=0; i--)
			adjust(a, i, N);
		
		for (int i=N; i>0; i--) {
			swap(a, 0, i);
			adjust(a, 0, i-1);
		}
	}
}
