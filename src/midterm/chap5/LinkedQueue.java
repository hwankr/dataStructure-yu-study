package midterm.chap5;

import java.util.LinkedList;

public class LinkedQueue<E> {
	private LinkedList<E> list;
	
	public LinkedQueue() {
		list = new LinkedList<>();
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	public void enqueue(E e) {
		list.addLast(e);
	}
	
	public E dequeue() {
		if (list.isEmpty()) return null;
		return list.removeFirst();
	}
}