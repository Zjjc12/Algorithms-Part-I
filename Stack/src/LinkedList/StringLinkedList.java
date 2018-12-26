package LinkedList;
public class StringLinkedList
{
	private Node first = null;

	private class Node
	{
		String item;
		Node next;
	}

	public boolean isEmpty()
	{
		return first == null;
	}

	public void insert(String item)
	{
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
	}

	public String remove()
	{
		String item = first.item;
		first = first.next;
		return item;
	}
}
