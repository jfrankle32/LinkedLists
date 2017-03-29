

package listAndSublist;

/* This class is a list of nodes of type T that is nondecreasing order.
* Its functionalities are the same as its superclass except certain methods are
* overwritten for improved efficiency.
*/
public class ListInOrder<T extends Comparable<T>> extends ListNoOrder<T> {

	/*
	 * Implement (i.e., override) whatever methods from the superclass that you
	 * find are necessary to have this list be sorted, *as well as* any methods
	 * that would be more efficient if overridden in this subclass.
	 */

	// creates an empty list. Sets head and tail references to null
	public ListInOrder() {
		super();
	}

	// adds new elements to the list in its correct order
	@Override
	public void add(T newElt) {
		if (newElt == null) // throws exception if argument is null;
			throw new IllegalArgumentException();

		Node cur = head, prev = head;
		Node element = new Node(newElt);

		if (head == null) { // if the list is empty
			head = element;
			tail = head;
			return;
		}
		// if the new element must become the new head
		if (head.data.compareTo(newElt) >= 0) {
			element.next = head;
			head = element;
			return;
		}
		// while cur hasn't reached the end of the list
		while (cur != null) {
			if (cur.data.compareTo(newElt) >= 0) { // if element is equal to or
													// larger
				element.next = cur;
				prev.next = element;
				return;
			}
			prev = cur;
			cur = cur.next;
		}
		tail.next = element; // adds new element to the end of the list
		tail = tail.next;
	}

	// counts number of occurrences of the element provided and returns that
	// number
	@Override
	public int numOccurrencesOfElement(T element) {
		// throws exception if argument is null
		if (element == null)
			throw new IllegalArgumentException();
		int count = 0;
		Node cur = head;
		// cycles through elements while they are less than or equal to
		// "element"
		while (cur.data.compareTo(element) <= 0) {
			if (cur.data.compareTo(element) == 0)
				count++; // add one to count if the element is equal to
							// "element"
			cur = cur.next;
		}
		return count;
	}

	// returns the position of the first occurrence of the given element
	// returns 01 if the element doesn't exist in the list
	@Override
	public int positionOfElement(T element) {
		int pos = -1;
		Node cur = head;
		// throws exception if argument is null
		if (element == null)
			throw new IllegalArgumentException();
		// checks that cur is not null and cur.data is not larger than element
		while (cur != null && cur.data.compareTo(element) <= 0) {
			pos++; // updates position
			// if the elements are the same
			if (cur.data.compareTo(element) == 0)
				return pos;
			cur = cur.next; // moves to next node
		}
		return -1; // returns -1 if element isn't found
	}

	// returns a new list that consists of all elements of the original list
	// after the specified element
	@Override
	public ListInOrder<T> elementsAfter(T element) {
		ListInOrder<T> list = new ListInOrder<T>();
		Node cur = head;
		boolean found = false;
		// throws exception if argument is null
		if (element == null)
			throw new IllegalArgumentException();
		while (cur != null && cur.data.compareTo(element) <= 0) {
			if (found) // adds element to new list if it is after desired
						// element
				list.add(cur.data);
			if (cur.data.compareTo(element) == 0)
				found = true; // sets found to true if the element is found
			cur = cur.next;
		}
		return list; // returns new list
	}

	// returns the distance between the two elements given in the list
	@Override
	public int distanceBetween(T element1, T element2) {
		int num = -1;
		Node cur = head;
		// throws exception if either argument is null
		if (element1 == null || element2 == null)
			throw new IllegalArgumentException();
		while (cur != null && cur.data.compareTo(element1) <= 0) { // while not
																	// at the
																	// end of
																	// the list
			// if the first instance of element1 is found in the list
			if (cur.data.compareTo(element1) == 0) {
				while (cur != null && cur.data.compareTo(element2) <= 0) {
					num++;
					// if the second element has been found after the first
					// element
					if (cur.data.compareTo(element2) == 0)
						return num; // returns the distance between
					cur = cur.next;
				}
				if (cur != null)
					cur = cur.next;
			}
			if (cur != null)
				cur = cur.next;
		}
		return -1;
	}

}
