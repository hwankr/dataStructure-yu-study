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
}
