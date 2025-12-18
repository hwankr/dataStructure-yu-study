package midterm.chap5;

public class ThreadedBinaryTree<E> {
//	private ThreadedTreeNode<E> insucc(ThreadedTreeNode<E> p) {
//		ThreadedTreeNode<E> temp = p.getRight();
//		if (p.rthread == false) 
//			while (temp.lthread == false)
//				temp = temp.getLeft();
//		return temp;
//	}
	
	private ThreadedTreeNode<E> insucc(ThreadedTreeNode<E> p) {
		ThreadedTreeNode<E> temp = p.getRight();
		
		if (p.rthread == false) {
			while (temp.lthread == false)
				temp = temp.getLeft();
		}
		return temp;
	}
}
