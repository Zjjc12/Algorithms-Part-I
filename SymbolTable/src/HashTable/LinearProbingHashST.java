package HashTable;

public class LinearProbingHashST<Key, Value>
{
	private int M = 30001;
	private Value[] vals = (Value[]) new Object[M];
	private Key[] keys = (Key[]) new Object[M];
	
	private int hash(Key key)
	{
		return (key.hashCode() & 0x7fffffff) % M;
	}
	
	// Put new value at hash, if hash already exist add 1 to hash
	public void put(Key key, Value val)
	{
		int i;
		for (i = hash(key); keys[i] != null; i = (i+1) % M)
		{
			// If key already exists, replace the value
			if (keys[i].equals(key))
				break;
		}
		keys[i] = key;
		vals[i] = val;
	}
	
	public Value get(Key key)
	{
		// Goto hash, if not, add 1 
		for (int i = hash(key); keys[i] != null; i = (i+1) % M)
		{
			if (key.equals(keys[i]))
				return vals[i];

		}
		
		// If not found, return null
		return null;
	}
}
