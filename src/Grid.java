import java.lang.reflect.Array;
import java.util.*;

public class Grid<E> implements Iterable<E> {
	private final int childNum = 4; // Change this to increase board size
	private E[] members;

	/*
	 * ======================================== The standard is as follows
	 * 1st child = right child 
	 * 2nd child = TOP child 
	 * 3rd child = left child 
	 * 4th child = BOTTOM child =========================================
	 */

	Grid(Class<E> type) {
		@SuppressWarnings("unchecked")
		final E[] temp = (E[]) Array.newInstance(type, childNum);
		this.members = temp;
	}

	public E get(int index) {
		return members[index];
	}

	public E set(int index, E element) {
		// check for out of bounds, return null if true
		if (index >= childNum)
			return null;

		E oldValue = members[index];
		members[index] = element;
		return oldValue;
	}

	public int size() {
		return members.length;
	}

	public int indexOf(E element) {
		// behaves like indexOf in ArrayList
		int ret = -1;

		for (int i = 0; i < childNum; i++) {
			if (members[i] == element)
				ret = i;
		}

		return ret;
	}

	public void add(E element) {
		// behaves like add in ArrayList

		for (int i = 0; i < childNum; i++) {
			if (members[i] != null) {
				members[i] = element;
				break;
			}
		}
	}

	public void remove(E element) {
		// behaves like remove in ArrayList

		for (int i = 0; i < childNum; i++) {
			if (members[i] == element)
				members[i] = null;
		}
	}

	public void rotate() {

		E[] temp = members;

		for (int i = 0; i < childNum; i++) {
			if (i != childNum - 1) {
				members[i] = temp[i + 1];
			} else {
				members[i] = temp[0];
			}

		}
	}

	public Iterator<E> iterator() {
		return new GridIterator();
	}

	private class GridIterator implements Iterator<E> {
		private int index = 0;

		GridIterator() {

		}

		public boolean hasNext() {
			return index <= childNum;
		}

		public E next() {
			return members[index];
		}

		public void remove() {

		}
	}
}