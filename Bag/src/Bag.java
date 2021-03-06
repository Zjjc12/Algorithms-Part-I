import java.util.Iterator;

public class Bag<Item> implements Iterable<Item>
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
	public void add (Item item) {
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
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
