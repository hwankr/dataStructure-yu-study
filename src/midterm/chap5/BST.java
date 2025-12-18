package midterm.chap5;

public class BST<K extends Comparable<K>, V> {
	protected Node<K, V> root;
	
	class Node<K, V> {
		K key;
		V value;
		Node<K, V> left, right, parent;
		
		public Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

	protected Node<K, V> treeSearch(K searchKey) {
		Node<K, V> x = root;
		Node<K, V> parent = null;
		
		while (x != null) {
			parent = x;
			int cmp = searchKey.compareTo(x.key);
			if (cmp == 0) return x;
			else if (cmp < 0) x = x.left;
			else x = x.right;
		}
		return parent;
	}
	protected boolean isLeaf(Node<K, V> x) {
		return x.left == null && x.right == null;
	}
	
	public V get(K key) {
		if (root == null) return null;
		Node<K, V> x = treeSearch(key);
		if (x != null && key.equals(x.key)) return x.value;
		else return null;
	}
	
	public void put(K key, V value) {
		if (root == null) {
			root = new Node<K, V>(key, value);
			return;
		}
		
		Node<K, V> x = treeSearch(key);
		int cmp = key.compareTo(x.key);
		if (cmp == 0) x.value = value;
		else {
			Node<K, V> newNode = new Node<K, V>(key, value);
			if (cmp < 0) x.left = newNode;
			else x.right = newNode;
			newNode.parent = x;
		}
	}
}
