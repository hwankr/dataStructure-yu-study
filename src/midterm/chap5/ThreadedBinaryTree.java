package midterm.chap5;

import java.util.ArrayList;
import java.util.List;

public class ThreadedBinaryTree<E> {
	ThreadedTreeNode<E> root;
	
	private ThreadedTreeNode<E> insucc(ThreadedTreeNode<E> p) {
		ThreadedTreeNode<E> temp = p.getRight();
		if (p.rthread == false) {
			while (temp.lthread == false)
				temp = temp.getLeft();
		}
		return temp;
	}	
	
	private ThreadedTreeNode<E> inpre(ThreadedTreeNode<E> p) {
		ThreadedTreeNode<E> temp = p.getLeft();
		if (p.lthread == false) 
			while (temp.rthread == false)
				temp = temp.getRight();
		return temp;
	}
	
	public List<ThreadedTreeNode<E>> inorder() {
		List<ThreadedTreeNode<E>> result = new ArrayList<>();
		ThreadedTreeNode<E> p = root;
		
		while (true) {
			p = insucc(p);
			if (p == root) break;
			result.add(p);
		}
		return result;
	}
	
	public void insertRight(ThreadedTreeNode<E> pParent, ThreadedTreeNode<E> pChild) {
		ThreadedTreeNode<E> parent = pParent;
		ThreadedTreeNode<E> child = pChild;
		
		child.right = parent.right;
		child.rthread = parent.rthread;
		child.left = parent;
		child.lthread = true;
		parent.right = child;
		parent.rthread = false;
		if (child.rthread == false) {
			ThreadedTreeNode<E> temp = insucc(child);
			temp.left = child;
		}
	}
	
	public void insertLeft(ThreadedTreeNode<E> pParent, ThreadedTreeNode<E> pChild) {
		ThreadedTreeNode<E> parent = pParent;
		ThreadedTreeNode<E> child = pChild;
		
		child.left = parent.left;
		child.lthread = parent.lthread;
		child.right = parent;
		child.rthread = true;
		parent.left = child;
		parent.lthread = false;
		if (child.lthread == false) {
			ThreadedTreeNode<E> temp = inpre(child);
			temp.right = child;
		}
	}
}
