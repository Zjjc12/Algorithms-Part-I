
public class StackOfStrings {
	StringLinkedList list = new StringLinkedList();
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	public void push(String item) {
		list.insert(item);
	}
	
	public String pop() {
		return(list.remove());
	}
}
