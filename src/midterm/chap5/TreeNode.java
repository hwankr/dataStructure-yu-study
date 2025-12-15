package midterm.chap5;

public class TreeNode<E>{
	private E element;
	private TreeNode<E> left, right;
	
	public TreeNode(E element) {
		this.element = element;
	}
	
	public TreeNode(E element, TreeNode<E> left, TreeNode<E> right) {
		this.element = element;
		this.left = left;
		this.right = right;
	}
	
	public E getElement() {
		return this.element;
	}
	public TreeNode<E> getLeft() {
		return this.left;
	}
	public TreeNode<E> getRight() {
		return this.right;
	}
	
	public void setElement(E element) {
		this.element = element;
	}
	public void setLeft(TreeNode<E> left) {
		this.left = left;
	}
	public void setRight(TreeNode<E> right) {
		this.right = right;
	}
}
