package dataStructure.lesson.chap7;

@SuppressWarnings("rawtypes")
public abstract class AbstractSort {
	
	public static void sort(Comparable[] a) {};
	
	@SuppressWarnings("unchecked")
	protected static boolean less(Comparable a, Comparable b) {
		return a.compareTo(b) < 0;
	}
	
	protected static void swap(Comparable[] a, int i, int j) {
		Comparable t = a[i]; a[i] = a[j]; a[j] = t;
	}
	
	protected static boolean isSorted(Comparable[] a) {
		for (int i = 1; i < a.length; i++)
			if (less(a[i], a[i-1]))
				return false;
		return true;
	}
	
	protected static void show(Comparable[] a) {
		System.out.print("[ ");
		for (int i = 0; i < a.length; i++)
			System.out.print(a[i] + " ");
		System.out.println("]");
	}
}
