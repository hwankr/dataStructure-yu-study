package midterm.chap5;

import java.util.ArrayList;
import java.util.Comparator;

public class HeapPriorityQueue<K extends Comparable<K>, V> implements PriorityQueue<K, V> {
	private int size;
	protected ArrayList<Entry<K, V>> heap = new ArrayList<>();
	private Comparator<K> comp;
	
	protected static class PQEntry<K, V> implements Entry<K, V> {
		private K key;
		private V value;
		public PQEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}
		public K getKey() {
			return key;
		}
		public V getValue() {
			return value;
		}
	}

	public HeapPriorityQueue() {
		this.comp = Comparator.naturalOrder();
		heap.add(null);
	}
	
	public HeapPriorityQueue(Comparator<K> comp) {
		this.comp = comp;
		heap.add(null);
	}
	
	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}
	
	protected int compare(Entry<K, V> a, Entry<K, V> b) {
		return comp.compare(a.getKey(), b.getKey());
	}
	protected int parent(int k) {
		return k/2;
	}
	protected int left(int k) {
		return 2*k;
	}
	protected int right(int k) {
		return 2*k+1;
	}

	@Override
//	public Entry<K, V> insert(K key, V value) {
//		Entry<K, V> newest = new PQEntry<>(key, value);
//		heap.add(newest);
//		int i = ++size;
//		while (i != 1 && compare(newest, heap.get(parent(i))) > 0) {
//			heap.set(i,  heap.get(parent(i)));
//			i = parent(i);
//		}
//		heap.set(i, newest);
//		return newest;
//	}

	public Entry<K, V> insert(K key, V value) {
		Entry<K, V> newest = new PQEntry<>(key, value);
		heap.add(newest);
		int i = ++size;
		
		while (i != 1 && compare(heap.get(parent(i)), newest) < 0) {
			heap.set(i, heap.get(parent(i)));
			i = parent(i);
		}
		heap.set(i, newest);
		return newest;
	}
	
	@Override
	public Entry<K, V> top() {
		if (isEmpty())
			return null;
		return heap.get(1);
	}

	@Override	
	public Entry<K, V> delete() {
		Entry<K, V> answer = heap.get(1);
		Entry<K, V> temp = heap.get(size);
		int parent = 1;
		int child = 2;
		size--;
		
		while (child <= size) {
			if (child < size && compare(heap.get(child), heap.get(child + 1)) < 0)
				child += 1;
			if (compare(temp, heap.get(child)) > 0)
				break;
			heap.set(parent, heap.get(child));
			parent = child;
			child *= 2;
		}
		heap.set(parent, temp);
		heap.remove(size+1);
		return answer;
	}
}
