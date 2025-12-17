package midterm.chap5;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import dataStructure.chap5.LinkedStack;

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
	public TreeNode<E> left(TreeNode<E> p) {
		return p.getLeft();
	}
	public TreeNode<E> right(TreeNode<E> p) {
		return p.getRight();
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
	
	public List<TreeNode<E>> inorder() {
		List<TreeNode<E>> result = new ArrayList<TreeNode<E>>();
		inorderSubtree(root(), result);
		return result;
	}
	private void inorderSubtree(TreeNode<E> p, List<TreeNode<E>> result) {
		if (p != null) {
			inorderSubtree(left(p), result);
			result.add(p);
			inorderSubtree(right(p), result);
		}
	}
	
	public List<TreeNode<E>> preorder() {
		List<TreeNode<E>> result = new ArrayList<TreeNode<E>>();
		preorderSubtree(root(), result);
		return result;
	}
	private void preorderSubtree(TreeNode<E> p, List<TreeNode<E>> result) {
		if (p != null) {
			result.add(p);
			preorderSubtree(left(p), result);
			preorderSubtree(right(p), result);
		}
	}
	
	public List<TreeNode<E>> postorder() {
		List<TreeNode<E>> result = new ArrayList<TreeNode<E>>();
		postorderSubtree(root(), result);
		return result;
	}
	private void postorderSubtree(TreeNode<E> p, List<TreeNode<E>> result) {
		if (p != null) {
			postorderSubtree(left(p), result);
			postorderSubtree(right(p), result);
			result.add(p);
		}
	}
	
	
	public List<TreeNode<E>> iterativeInorder() {
		List<TreeNode<E>> result = new ArrayList<TreeNode<E>>();
		Stack<TreeNode<E>> stack = new Stack<>();
		TreeNode<E> p = root;
		
		while (true) {
			while (p != null) {
				stack.push(p);
				p = p.getLeft();
			}
			if (stack.isEmpty()) break;
			
			p = stack.pop();
			result.add(p);
			p = p.getRight();
		}
		return result;
	}
	
	public List<TreeNode<E>> levelOrder() {
		List<TreeNode<E>> result = new ArrayList<TreeNode<E>>();
		LinkedQueue<TreeNode<E>> queue = new LinkedQueue<>();
		
		if (isEmpty()) return result;
		queue.enqueue(root);
		while (true) {
			if (queue.isEmpty()) break;
			TreeNode<E> p = queue.dequeue();
			result.add(p);
			if (p.getLeft() != null) queue.enqueue(p.getLeft());
			if (p.getRight() != null) queue.enqueue(p.getRight());
		}
		return result;
	}
	
	public LinkedBinaryTree<E> copy() {
		LinkedBinaryTree<E> dup = new LinkedBinaryTree<>();
		if (root != null) dup.root = copy(root);
		return dup;
	}
	private TreeNode<E> copy(TreeNode<E> original) {
		if (original != null) {
			TreeNode<E> temp = new TreeNode<E>(original.getElement());
			temp.setLeft(copy(original.getLeft()));
			temp.setRight(copy(original.getRight()));
			return temp;
		}
		return null;
	}
	
	public boolean equalTo(LinkedBinaryTree<E> other) {
		return equal(root, other.root);
	}
	private boolean equal(TreeNode<E> first, TreeNode<E> second) {
		return ((first == null && second == null) || (first != null && second != null && first.getElement().equals(second.getElement())
				&& equal(first.getLeft(), second.getLeft()) && equal(first.getRight(), second.getRight())));
	}
	
	public int leafCount(TreeNode<E> tree) {
		return leafCountSub(tree);
	}
	private int leafCountSub(TreeNode<E> root) {
		if (root != null) {
			if (root.getLeft() == null && root.getRight() == null) return 1;
			else return leafCountSub(root.getLeft()) + leafCountSub(root.getRight());
		}
		return 0;
	}

	public int height(TreeNode<E> tree) {
		return heightSub(tree);
	}
	private int heightSub(TreeNode<E> root) {
		if (root == null) return 0;
		else return Math.max(heightSub(root.getLeft()), heightSub(root.getRight()));
	}
}
