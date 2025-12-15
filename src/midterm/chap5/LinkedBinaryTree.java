package midterm.chap5;

public class LinkedBinaryTree<E> {
	protected TreeNode<E> root = null;
	protected int size = 0;
	
	public int size() {
		return this.size;
	}
	public boolean isEmpty() {
		return size==0;
	}
	
	public TreeNode<E> root() {
		return this.root;
	}
	
	public TreeNode<E> addRoot(E e) {
		if (!isEmpty()) {
			System.out.println("Tree is not Empty");
		}
		
		root = new TreeNode<E>(e, null, null);
		size = 1;
		return root;
	}
	
	public TreeNode<E> addLeft(TreeNode<E> p,E e) {
		if (p.getLeft() != null) {
			System.out.println("p already has a left child");
		}
		
		TreeNode<E> child = new TreeNode<E>(e, null, null);
	
		p.setLeft(child);
		size++;
		return child;
	}
	
	public TreeNode<E> addRight(TreeNode<E> p, E e) {
		if (p.getRight() != null) {
			System.out.println("오른쪽 비어있지 않음");
		}
		
		TreeNode<E> child = new TreeNode<E>(e, null, null);
		
		p.setRight(child);
		size++;
		return child;
	}
	
	public E set(TreeNode<E> p, E newValue) {
		E oldValue = p.getElement();
		p.setElement(newValue);
		return oldValue;
	}
}
