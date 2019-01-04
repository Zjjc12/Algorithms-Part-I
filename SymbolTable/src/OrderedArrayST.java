
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
		keys = (Key[]) new Comparable[INIT_SIZE];
		vals = (Value[]) new Comparable[INIT_SIZE];
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
		Key[] tempk = (Key[]) new Comparable[capacity];
		Value[] tempv = (Value[]) new Comparable[capacity];
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

		// If the array is full
		if (N >= keys.length)
		{
			// if adding one meets cap, double size
			resize(N * 2);

		}

		// If array is empty, just add to index 0 of array
		if (N == 0)
		{
			keys[0] = key;
			vals[0] = val;
			N++;
		} 
		else
		{
			
			boolean change = false;
			
			// Loop through all keys
			for (int i = 0; i < N; i++)
			{

				int cmp = key.compareTo(keys[i]);
				
				// If new key is less than key at index i
				if (cmp < 0)
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

					change = true;
					
					// Add 1 to N
					N++;
					break;
				}
				// If key already exists
				else if (cmp == 0)
				{

					// Overwrite Key
					keys[i] = key;
					vals[i] = val;
					
					
					change = true;
					break;
				} 

			}
			
			// If it was greater than all  keys, then just add to the end
			if (!change) {
				keys[N] = key;
				vals[N] = val;
			}

			N++;
		}
		

		printKeys();
		
		
	}

	public void delete(Key key)
	{
		// Loop through all keys
		for (int i = 0; i < N; i++)
		{
			// If found an equal key
			if (key.equals(keys[i]))
			{
				// Save old keys and values
				Key[] tempk = keys;
				Value[] tempv = vals;

				// Set the key and value to null
				keys[i] = null;
				vals[i] = null;

				// Replaces the rest of keys and values with old values
				for (int j = i; j < keys.length - 1; j++)
				{
					keys[j] = tempk[j + 1];
				}

				for (int j = i; j < vals.length - 1; j++)
				{
					vals[j] = tempv[j + 1];
				}

				// Subtract 1 to N
				N--;

				// If only a quarter is full, halve the size
				if (N > 0 && N == keys.length / 4)
					resize(keys.length / 2);

				// Exit
				break;
			}
		}
	}
	
	// Debug print
	public void printKeys()
	{
		for (int i = 0; i < keys.length; i++)
		{
			System.out.print(keys[i] + ", ");
		}
		System.out.println("");
		for (int i = 0; i < vals.length; i++)
		{
			System.out.print(vals[i] + ", ");
		}
		System.out.println("");
	}

}
