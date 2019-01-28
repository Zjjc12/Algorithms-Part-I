package HashTable;

public class HTClient
{
	public static void main(String[] args)
	{
		SeparateChainingHashST<Integer, String> ht = new SeparateChainingHashST<Integer, String>();
		
		
		ht.put(1, "one");
		ht.put(2, "two");
		ht.put(3, "three");
		ht.put(4, "four");
		
		ht.put(2, "not two");
		
		ht.put(5, "five");
		ht.put(6, "six");
		ht.put(7, "seven");
		ht.put(8, "eight");
		
		System.out.println(ht.get(3));
		System.out.println(ht.get(2));
		System.out.println(ht.get(5));
	}
}
