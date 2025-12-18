package midterm.chap5;

public interface PriorityQueue<K, V> {
	int size();
	boolean isEmpty();
	Entry<K, V> insert(K key, V value);
	Entry<K, V> top();
	Entry<K, V> delete();
}
