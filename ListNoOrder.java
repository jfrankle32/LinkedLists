

package listAndSublist;

import java.lang.IndexOutOfBoundsException;

/* This class is a list of nodes of type T that is in no specific order.
 * It includes many methods to manipulate the list. Its functionalities include 
 * creating an empty list, adding to the list, getting the length of the list, and 
 * many others.
 */
public class ListNoOrder<T extends Comparable<T>>
             implements Comparable<ListNoOrder<T>> {
	
	protected Node head, tail;
	
	//This inner class Node is used to store the data in the unordered list.
	//Each node consists of a piece of data of type T and a reference to the 
	//next Node.
	protected class Node {
		
		protected T data;
		protected Node next;
		
		//creates a new Node object
		public Node(T obj) {
			if (obj == null)
				throw new IllegalArgumentException();
			data = obj;
			next = null;
		}
	}

	//creates an empty list. Sets head and tail references to null
	public ListNoOrder() {
		head = null;
		tail = null;
	}
  
	//adds new element at the end of the linked list 
  	public void add(T newElt) {
  		try{ //trys to add the new element 
  			if (head == null) { //if the list is empty
  				head = new Node(newElt); //head points to new element
  				tail = head; 
  			}
  			else {
  				//tail next reference points to new element
  				tail.next = new Node(newElt); 
  				tail = tail.next; //new element becomes tail
  			}
  		}	
  		catch(IllegalArgumentException e) { //catches exception if newElt is null
  			throw new IllegalArgumentException("Illegal Argument Null");
  		}
  	}
  	
  	//returns the number of elements in the list
  	public int length() {
  		int i = 0;
  		Node current = head;
  		//adds one to i for every element in the list
  		while (current != null) { 
  			current = current.next; //pointer moves to next element
  			i++; 
  		}
  		return i; //returns number of elements
  	}

  	//returns a text representation of the elements of the list
  	public String toString() {
  		String out = "";
  		Node cur = head;
  		findTail();
  		while(cur != tail) { // for every element except the last one
  			out += cur.data.toString() + ", "; //adds string representation 
  			cur = cur.next;  // moves to next element 
  		}
  		if(tail != null) // for last element if list isn't empty
  			out += cur.data.toString();
  		return out;  //returns string
  	}
  	
  	// removes all objects from the list
  	public void reset() {
  		tail = head = null;
  	}
  	
  	// returns the number of elements that match the parameter in the list
  	public int numOccurrencesOfElement(T element) {
  		int count = 0;
  		Node cur = head; //sets current object to the first node
  		//throws exception if argument is null
  		if (element == null)
  			throw new IllegalArgumentException();
  		while (cur != null) {
  			//if the elements are the same
  			if (cur.data.compareTo(element) == 0) 
  				count++; // add one to count
  			cur = cur.next; // move to next object 
  		}
  		return count;
  	}
  	
  	//returns the position of the first occurrence of the given element
  	//returns -1 if the element doesn't exist in the list
  	public int positionOfElement(T element) {
  		int pos = -1;
  		Node cur = head;
  		//throws exception if argument is null
  		if (element == null)
  			throw new IllegalArgumentException();
  		while (cur != null) {
  			pos++; //updates position
  			//if the elements are the same
  			if (cur.data.compareTo(element) == 0)
  				return pos; 
  			cur = cur.next; //moves to next node
  		}
  		return -1; //returns -1 if element isn't found
  	}
  	
  	//this method returns the data at the indicated position on the list
  	public T elementAtPosition(int position)
              throws IndexOutOfBoundsException {
  		Node cur = head;
  		//throws exception if the index does not exist
  		if (position < 0 || position > this.length() - 1) {
  			throw new IndexOutOfBoundsException("Not a valid index");
  		}
  		for (int i = 0; i < position; i++) //finds element at desired position
  			cur = cur.next;
  		return cur.data; //returns desired element
  	}
  	
  	//returns a new list that consists of all elements of the original list
  	//after the specified element
  	public ListNoOrder<T> elementsAfter(T element) {
  		ListNoOrder<T> list = new ListNoOrder<T>();
  		Node cur = head;
  		boolean found = false;
  		//throws exception if argument is null
  		if (element == null)
  			throw new IllegalArgumentException();
  		while (cur != null) { 
  			if (found) //adds element to new list if it is after desired element
  				list.add(cur.data);
  			if (cur.data.compareTo(element) == 0)
  				found = true; //sets found to true if the element is found
  			cur = cur.next;
  		}
  		return list; //returns new list
  	}
  	
  	//returns the distance between the two elements given
  	public int distanceBetween(T element1, T element2) {
  		int num = -1;
  		Node cur = head;
  		boolean found = false;
  		//throws exception if either argument is null
  		if (element1 == null || element2 == null) 
  			throw new IllegalArgumentException();
  		while (cur != null) { //while not at the end of the list
  			//if the first instance of element1 is found in the list
  			if (found == false && cur.data.compareTo(element1) == 0)
  				found = true;
  			if (found) //if the first element has been found
  				num++; //add one to the distance between the elements
  			//if the second element has been found after the first element
  			if (found && cur.data.compareTo(element2) == 0)
  				return num; //return the distance between
  			cur = cur.next;
  		}
  		return -1;
  	}
  	
  	//removes the list element at the given index
  	public void removeElementAtPosition(int position)
           throws IndexOutOfBoundsException {
  		Node cur = head;
  		//throws exception if index doesn't exist
  		if (position < 0 || position > this.length() - 1)
  			throw new IndexOutOfBoundsException("Not a valid index");
  		if (position == 0) {
  			head = head.next;
  			return;
  		}
  		int count = 0;
  		while (count < position - 1) { //finds element before position element
  			cur = cur.next;
  			count++;
  		}
  		cur.next = cur.next.next; //deletes element indicated
  	}
  	
  	//compares two lists to each other.
  	//returns 0 if they are identical. 
  	public int compareTo(ListNoOrder<T> otherList) {
  		Node curObj = head, curPar = otherList.head;
  		while (curObj != null && curPar != null) { //while both lists have elements
  			if (curObj.data.compareTo(curPar.data) < 0)
  				return -1; //returns -1 if object compares lower than parameter
  			if (curObj.data.compareTo(curPar.data) > 0)
  				return 1;  //returns 1 if object compares higher than parameter
  			curObj = curObj.next; 
  			curPar = curPar.next;
  		}
  		if  (this.length() > otherList.length())
  			return 1; //returns 1 if current list has more elements
  		if (this.length() < otherList.length())
  			return -1; //returns -1 if current list has less elements
  		return 0; //returns 0 if they are identical
  	}
  	
  	//find the last element in the list and sets tail to it
  	private void findTail() {
  		Node cur = head, prev = head;
  		while (cur != null) { //finding the last element
  			prev = cur;
  			cur = cur.next;
  		}
  		tail = prev; //sets tail to last element
  	}
  	

}



