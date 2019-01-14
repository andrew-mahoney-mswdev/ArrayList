import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * 
 * @author Andrew Mahoney
 *
 * @param <E> The element type that will be stored by the ArrayList.
 * 
 * This class manages and array list of a generic type.
 * 
 */
public class ArrayList<E> implements List<E> {
	
	private int maxSize = 5;
	private E[] data = (E[])new Object[maxSize];
	private int size = 0;

	private void extendArray() {
		//Private method increases the size of the Array List.
		maxSize *= 2;
		E[] newArray = (E[])new Object[maxSize];
		for (int index = 0; index < size; index++) {
			newArray[index] = data[index];
		}
		data = newArray;
	}

	/**
	 * Adds a value to the ArrayList.
	 * 
	 * @param The value to be added.
	 * @return Returns true
	 */
	@Override
	public boolean add(E value) {
		if (size >= maxSize) {extendArray();}
		data[size] = value;
		size++;
		return true;
	}

	/**
	 * Checks whether the ArrayList is empty.
	 * 
	 * @return Returns true if the ArrayList is empty, otherwise returns false.
	 */
	@Override
	public boolean isEmpty() {
		if (size == 0) return true;
		else return false;
	}

	/**
	 * @return Returns the size of the ArrayList.
	 */
	@Override
	public int size() {
		return size;
	}

	@Override
	public E get(int index) {
		if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
		return data[index];
	}

	@Override
	public E set(int index, E value) {
		if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
		E returnValue = data[index];
		data[index] = value;
		return returnValue;
	}

	@Override
	public void clear() {
		data = (E[])new Object[maxSize];
		size = 0;
	}
	
	@Override
	public void add(int index, E value) {
		if (index < 0 || index > size) throw new IndexOutOfBoundsException(); //An element can be added at size, creating a new array element.		
		
		size++;
		if (size >= maxSize) {extendArray();}
		if (index == size) add(value);
		else {
			int arrayIndex;
			for (arrayIndex = size-1; arrayIndex >= index; arrayIndex--)
				data[arrayIndex+1] = data[arrayIndex];
			data[index] = value;
		}
	}

	@Override
	public E remove(int index) {
		if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
		
		int arrayIndex;
		E returnValue = data[index];
		
		for (arrayIndex = index; arrayIndex <= size-2; arrayIndex++)
			data[arrayIndex] = data[arrayIndex+1];
		size--;
		
		return returnValue;
	}

	@Override
	public boolean contains(Object value) {
		for (int index = 0; index < size; index++)
			if (data[index].equals(value)) return true;
		return false;
	}

	@Override
	public boolean remove(Object value) {
		int index;
		
		for (index = 0; index < size && !data[index].equals(value); index++);
		if (index == size) return false;
		else {
			remove(index); //This method already updates size.
			return true;
		}
	}
	
	@Override
	public boolean addAll(Collection<? extends E> c) {
		for (E e : c) add(e);
		if (c.isEmpty()) return false; 
		else return true; //The data array has changed if there are elements in collection c.
	}

	@Override
	public int indexOf(Object value) {
		int index;
		for (index = 0; index < size && !data[index].equals(value); index++);
		if (index == size) index = -1; //Return -1 if value is not found.
		return index;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object o : c)
			if (!contains(o)) return false; //Return false if a single element is missing.
		return true;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		boolean returnValue = false;
		for (Object o : c)
			if (remove(o))
				returnValue = true; //Return true if a single value was changed.
		return returnValue;
	}
	
	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		if (index < 0 || index > size) throw new IndexOutOfBoundsException();

		for (E e : c) {
			add(index, e);
			index++;
		}
		
		if (c.isEmpty()) return false; 
		else return true; //The data array has changed if there are elements in collection c.
	}

	@Override
	public int lastIndexOf(Object value) {
		for (int index = size-1; index >= 0; index--)
			if (data[index].equals(value)) return index;
		return -1; //Return -1 if value is not found.
	}

	@Override
	public Object[] toArray() { 
		E[] returnArray = (E[])new Object[size];
		for (int index = 0; index < size; index++) returnArray[index] = data[index];
		return returnArray;
	}

	@Override
	public List<E> subList(int beginIndex, int endIndex) {
		if (beginIndex < 0 || endIndex > size || beginIndex > endIndex) throw new IndexOutOfBoundsException();
		List<E> newList = new ArrayList<E>();
		for (int index = beginIndex; index < endIndex; index++)
			newList.add(data[index]);
		return newList;
	}
	
	@Override
	public Iterator<E> iterator() {
		final class ArrayListIterator<E> implements Iterator<E> {
			private int index = 0;
			public boolean hasNext() {
				if (index < size) return true;
				else return false;
			}
			public E next() {
				E value = (E)data[index];
				index++;
				return value;
			}
		}
		
		return new ArrayListIterator();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		int oldSize = size; //Used to determine if the list has changed.
		for (int index = 0; index < size; index++)
			if (!c.contains(data[index])) {
				remove(index);
				index--;
			}

		if (size != oldSize) return true;
		else return false;
	}

	@Override
	public <T> T[] toArray(T[] array) {
		//This method may fail after the return command if it has to create a new array.
		//Due to time constraints, I was unable to find a way to resolve this.
		if (array.length < size) array = (T[]) new Object[size];
		int index;
		for (index = 0; index < size; index++)
			array[index] = (T)data[index];
		if (index < array.length) array[index] = null;
		return (T[])array;
	}

	@Override
	public ListIterator<E> listIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
