package HashTable;

public class SeparateChainingHashST<Key, Value>
{
	// # of chains
	private int M = 97;

	// array of chains
	private Node[] st = new Node[M];

	private static class Node
	{
		
		private Object key;
		private Object val;
		private Node next;
		
		
		public Node(Object key, Object val, Node node)
		{
			this.key = key;
			this.val = val;
			this.next = node;
		}
	}
	
	
	private int hash(Key key)
	{
		return (key.hashCode() & 0x7fffffff) % M;
	}

	public Value get(Key key)
	{
		// Compute hash value
		int i = hash(key);

		// Iterate through ith linked list, if finds key that equals, return the key
		for (Node x = st[i]; x != null; x = x.next)
			if (key.equals(x.key))
				return (Value) x.val;

		// If found nothing, return null
		return null;
	}

	public void put(Key key, Value val)
	{
		// Compute hash
		int i = hash(key);
		
		// Loop through ith linked list
		for (Node x = st[i]; x != null; x = x.next)
		{
			// If found a key, replace it with the new one
			if (key.equals(x.key))
			{
				x.val = val;
				return;
			}
		}
		
		// If not found, created a new node and linking it to st[i]
		/* new                     old */
		st[i] = new Node(key, val, st[i]);
	}
}
