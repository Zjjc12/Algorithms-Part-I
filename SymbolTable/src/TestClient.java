
public class TestClient
{

	public static void main(String[] args)
	{
		
		OrderedArrayST<Integer, String> st = new OrderedArrayST<Integer, String>();
		
		st.put(1, "to");
		
		st.put(2, "be");
		st.put(3, "or");
		st.put(4, "not");
		st.put(5, "to");
		st.put(1, "be");
		
		st.delete(2);
		

		st.printKeys();
	
		
	}
}
