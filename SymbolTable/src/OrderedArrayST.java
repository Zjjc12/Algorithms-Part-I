
// Symbol Table using array
// Key extends Comparable to use the compareTo function in the key
public class OrderedArrayST<Key extends Comparable<Key>, Value>
{
	private static final int INIT_SIZE = 8;

	Key[] keys;
	Value[] vals;

	int N = 0;

	public OrderedArrayST()
	{
		keys = (Key[]) new Object[INIT_SIZE];
		vals = (Value[]) new Object[INIT_SIZE];
	}

	public int size()
	{
		return N;
	}

	public boolean isEmpty()
	{
		return size() == 0;
	}

	private void resize(int capacity)
	{
		Key[] tempk = (Key[]) new Object[capacity];
		Value[] tempv = (Value[]) new Object[capacity];
		for (int i = 0; i < N; i++)
			tempk[i] = keys[i];
		for (int i = 0; i < N; i++)
			tempv[i] = vals[i];
		keys = tempk;
		vals = tempv;
	}

	// Get the value based on the key
	public Value get(Key key)
	{
		// If empty, just return null
		if (isEmpty())
			return null;

		// Rank key giving # of keys less than that key
		int i = rank(key);

		// If exist, then return that value
		if (i < N && keys[i].compareTo(key) == 0)
			return vals[i];
		// If not, return null
		else
			return null;
	}

	private int rank(Comparable<Key> key)
	{
		// Binary search
		int lo = 0, hi = N - 1;

		while (lo <= hi)
		{
			// Set mid point
			int mid = lo + (hi - lo) / 2;

			// see if key is bigger or smaller than mid
			int cmp = key.compareTo(keys[mid]);

			// If key is smaller, search bottom half
			if (cmp < 0)
				hi = mid - 1;

			// If key is bigger, search top half
			else if (cmp > 0)
				lo = mid + 1;
			// If key is the same, return found key
			else
				return mid;
		}

		// If couldn't be found, return lo (# of keys less than that key)
		return lo;
	}
	
	
	
	// Put a new value at key
	public void put(Key key, Value val)
	{
		// if adding one meets cap, double size
		if (N + 1 >= keys.length)
			resize (N * 2);
		
		for (int i = 0; i < N; i++)
		{
			int cmp = keys[i].compareTo(key);
			
			// If key is greater
			if (cmp > 0) 
			{
				// Save old keys and values
				Key[] tempk = keys;
				Value[] tempv = vals;
				
				// Set the key and value to new key and value at index i
				keys[i] = key;
				vals[i] = val;
				
				// Replaces the rest of keys and values with old values
				for (int j = i; j < keys.length; j++)
				{
					keys[j + 1] = tempk[j];
				}
				
				for (int j = i; j < vals.length; j++)
				{
					vals[j + 1] = tempv[j];
				}
				
				// Add 1 to N
				N++;
			}
			
			// If key already exists
			else if (cmp == 0) {
				
				// Overwrite Key
				keys[i] = key;
				vals[i] = val;
			}
		}
	}
	
	public void delete(Key key)
	{
		for (int i = 0; i < N; i++) 
		{
			if (key.equals(keys[i])) 
			{
				// Save old keys and values
				Key[] tempk = keys;
				Value[] tempv = vals;
				
				// Set the key and value to null
				keys[i] = null;
				vals[i] = null;
				
				// Replaces the rest of keys and values with old values
				for (int j = i; j < keys.length; j++)
				{
					keys[j - 1] = tempk[j];
				}
				
				for (int j = i; j < vals.length; j++)
				{
					vals[j - 1] = tempv[j];
				}
				
				// Subtract 1 to N
				N--;
				
				// If only a quarter is full, halve the size
				if (N > 0 && N == keys.length/4) 
					resize(keys.length/2);
				
				// Exit
                return;
			}
		}
	}
	

}
