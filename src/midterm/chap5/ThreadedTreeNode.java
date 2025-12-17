package midterm.chap5;

public class ThreadedTreeNode<E> {
	E element;
	boolean lthread;
	boolean rthread;
	ThreadedTreeNode<E> left;
	ThreadedTreeNode<E> right;
	
	public ThreadedTreeNode (E element) {
		this.element = element;
		this.lthread = true;
		this.rthread = true;
		this.left = null;
		this.right = null;
	}
	
	public E getElement() {
		return element;
	}
	public void setElement(E element) {
		this.element = element;
	}
	
	public ThreadedTreeNode<E> getLeft() {
		return left;
	}
	public ThreadedTreeNode<E> getRight() {
		return right;
	}
	
	public void setLeft(ThreadedTreeNode<E> left) {
		this.left = left;
	}
	public void setRight(ThreadedTreeNode<E> right) {
		this.right = right;
	}
	
	public boolean isLthread() {
	    return lthread;
	}

	public boolean isRthread() {
	    return rthread;
	}

	public void setLthread(boolean lthread) {
	    this.lthread = lthread;
	}

	public void setRthread(boolean rthread) {
	    this.rthread = rthread;
	}

}
