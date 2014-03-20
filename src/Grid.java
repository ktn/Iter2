import java.lang.reflect.Array;
import java.util.*;

public class Grid<E> implements Iterable<E> {
	private final int childNum = 4; // Change this to increase board size
	private E[] members;

	public static final int RIGHT=0;
	public static final int TOP=1;
	public static final int LEFT=2;
	public static final int BOTTOM=3;
	
	/*
	 * ======================================== 
	 *	The standard is as follows
	 * 1st child = right child 
	 * 2nd child = TOP child 
	 * 3rd child = left child 
	 * 4th child = BOTTOM child
	 * =========================================

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

		E temp = members[0];
		for (int i = 1; i < childNum; i++) {
			members[i - 1] = members[i];
		}
		members[childNum - 1] = temp;
	}

	public Iterator<E> iterator() {
		return new GridIterator();
	}

	private class GridIterator implements Iterator<E> {
		private int index = 0;

		GridIterator() {

		}

		public boolean hasNext() {
			return index < childNum;
		}

		public E next() {
			index++;
			return members[index - 1];
		}

		public void remove() {

		}
	}
}