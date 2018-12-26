package LinkedList;
import java.util.Iterator;

public class IterableStack<Item> implements Iterable<Item>
{
	private Node first = null;

	private class Node
	{
		Item item;
		Node next;
	}

	public boolean isEmpty()
	{
		return first == null;
	}
	
	public boolean onlyOne()
	{
		return first.next == null;
	}
	
	public void push (Item item) {
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
	}
	
	public Item pop() {
		Item item = first.item;
		first = first.next;
		return item;
	}
	
	public Iterator<Item> iterator(){
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item>{
		private Node current = first;
		
		public boolean hasNext(){
			return current != null;
		}
		
		public void remove() {/* NOT IMPLEMENTED */}
		
		public Item next() {
			Item item = current.item;
			current = current.next;
			return item;
		}
		
	}

}
