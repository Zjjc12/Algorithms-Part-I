
public class RedLeaningBST<Key extends Comparable<Key>, Value>
{
	private Node root;

	private static final boolean RED = true;
	private static final boolean BLACK = false;
	
	private class Node
	{
		
		// Stores the key and value
		private Key key;
		private Value val;

		// Reference to the left and right sub tree
		private Node left, right;
		
		private int count;

		public Node(Key key, Value val, boolean color)
		{
			this.key = key;
			this.val = val;
			this.color = color;
		}
		
		// Stores color of parent link
		boolean color;
	}

	private boolean isRed(Node x)
	{
		// Null links are black
		if (x == null)
			return false;
		return x.color == RED;
	}
	
	// Orient a right leaning red link to lean left
	private Node rotateLeft(Node h)
	{
		// Check to make sure right link is red
		assert isRed(h.right);
		
		// Rotate left
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = h.color;
		h.color = RED;
		return x;
	}
	
	// Orient a left leaning red link to lean right
	private Node rotateRight(Node h)
	{
		assert isRed(h.left);
		
		// Rotate right
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		return x;
	}
	
	// Flip colors red nodes to black and make upper node red (Temporary 4-node)
	private void flipColors(Node h)
	{
		assert !isRed(h);
		assert isRed(h.left);
		assert isRed(h.right);
		
		h.color = RED;
		h.left.color = BLACK;
		h.right.color = BLACK;
	}
	
	
	// Get the value of a certain key by searching through a binary tree
	public Value get(Key key)
	{
		// Start from root node
		Node x = root;
		while (x != null)
		{
			int cmp = key.compareTo(x.key);

			// If the get key is smaller, go to the left subtree
			if (cmp < 0)
				x = x.left;
			// If the get key is greater, go to the right subtree
			else if (cmp > 0)
				x = x.right;
			// If the get key is equal, return the key found
			else
				return x.val;
		}

		// If not found, return null
		return null;
	}

	
	public int size()
	{
		return size(root);
	}
	
	private int size(Node x)
	{
		if (x == null)
			return 0;
		return x.count;
	}
	
	
	// Recursively put key into the lowest branch
	public void put(Key key, Value val)
	{
		// Set whatever is returned finnaly as the root
		root = put(root, key, val);
	}

	
	// Simulate 2-3 search tree to maintain balance using red and black links
	// Red representing intra-connection in node
	private Node put(Node h, Key key, Value val)
	{
		// If reached a null branch, returned a new Node with key and value
		if (h == null)
			return new Node(key, val, RED);
		int cmp = key.compareTo(h.key);

		// If new key is smaller, go down to the left
		if (cmp < 0)
			// Link is updated if a new node is created
			h.left = put(h.left, key, val);
		// If new key is greater, go down to the right
		else if (cmp > 0)
			// Link is updated if a new node is created
			h.right = put(h.right, key, val);

		// If value equal, then just replace the value
		else
			h.val = val;
		
		// Right child red, left child black: rotate left
		if (isRed(h.right) && !isRed(h.left))
			h = rotateLeft(h);
		// Left child, left-left grandchild red: rotate right
		if (isRed(h.left) && isRed(h.left.left))
			h = rotateRight(h);
		// Both children red: Flip colors
		if (isRed(h.left) && isRed(h.right))
			flipColors(h);
			
		return h;
	}

	public Key max()
	{
		Node current = root;
		
		// If the currently checking right key isn't null, set that as current
		while(current.right != null)
		{
			current = current.right;
		}
		
		// When finally the right of the current key is null, that key is the largest
		return current.key;
	}
	
	public Key min()
	{
		Node current = root;
		
		// If the currently checking right key isn't null, set that as current
		while(current.left != null)
		{
			current = current.left;
		}
		
		// When finally the right of the current key is null, that key is the largest
		return current.key;
	}
	
	// Largest key =< the key
	public Key floor(Key key)
	{
		Node x = floor(root, key);
		if (x == null)
			return null;
		return x.key;
	}
	
	
	private Node floor(Node x, Key key)
	{
		// If the key is null, return null
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		
		// If keys are equal, then key is found, return key
		if (cmp == 0)
			return x;
		
		
		// If key is less, then it has to be in the left, so go down to left
		if (cmp < 0)
			return floor (x.left, key);
		
		// if key is greater, then it could be on the right
		Node t = floor(x.right, key);
		
		// If floor is null in the subtree, then the key is x, else the key is t
		if (t != null)
			return t;
		else
			return x;
	}
	
	// # of keys < key
	public int rank(Key key)
	{
		return rank(key, root);
	}
	
	private int rank(Key key, Node x)
	{
		// If reached null, then return 0
		if (x == null)
			return 0;
		int cmp = key.compareTo(x.key);
		// If key is less, then just keep going down to the left;
		if (cmp < 0)
			return rank(key, x.left);
		// If key is greater, then then rank is the size of the left subtree
		// plus the rank of the right subtree and 1 because of the current
		else if (cmp > 0)
			return 1 + size(x.left) + rank(key, x.right);
		// If key is equal to current key, then the rank is the size of the left subtree
		else
			return size(x.left);
	}
	
	public Iterable<Key> keys()
	{
		IterableGenericQueue<Key> q = new IterableGenericQueue<Key>();
		
		inorder(root, q);
		return q;
	}
	
	// Queue all keys in tree in order
	private void inorder(Node x, IterableGenericQueue<Key> q)
	{
		if (x == null)
			return;
		
		// First enqueue all left keys
		inorder(x.left, q);
		q.enqueue(x.key);
		
		// Then right
		inorder(x.right, q);
	}
	
	// Deletion not Implemented
	// Hilbard deletion does not optimize red-black bst
	/*
	public void deleteMin() 
	{
		root = deleteMin(root);
	}
	
	private Node deleteMin(Node x)
	{
		// If left is null, lowest, replace that node by its right link
		if (x.left == null)
			return x.right;
		// Keep going down on left
		x.left = deleteMin(x.left);
		
		// Update count
		x.count = 1 + size(x.left) + size (x.right);
		return x;
	}
	
	public void deleteMax() 
	{
		root = deleteMax(root);
	}
	
	
	private Node deleteMax(Node x)
	{
		// If right is null, lowest, replace that node by its left link
		if (x.right == null)
			return x.left;
		// Keep going down on left
		x.right = deleteMin(x.right);
		
		// Update count
		x.count = 1 + size(x.left) + size (x.right);
		return x;
	}
	
	public void delete(Key key)
	{
		root = delete(root, key);
	}
	
	private Node delete(Node x, Key key)
	{
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		
		// If key is less, go left
		if (cmp < 0)
			x.left = delete(x.left, key);
		// If key is greater, go right
		else if (cmp > 0)
			x.right = delete(x.right, key);
		
		// If found key
		else
		{
			// If there isn't a right key just return the left subtree
			// Removing connection to the key
			if (x.right == null)
				return x.left;
			// If there isn't a left key just return the right subtree
			// Removing connection to the key
			if (x.left == null)
				return x.right;
			
			// If both exists
			// Stores node
			Node t = x;
			
			// Find the next biggest node
			// Replace next biggest as the node
			x = min(t.right);
			
			// Delete the minimum (next biggest) and set it to the right of x
			x.right = deleteMin(t.right);
			// Set left of x to be left of t (attaching the left side of the old tree
			// to the new parent)
			x.left = t.left;
		}
		
		// Update count
		x.count = size(x.left) + size (x.right) + 1;
		
		return x;
	}
	*/
	public boolean isEmpty() {
        return size() == 0;
    }


    private Node min(Node x) { 
        if (x.left == null) 
        	return x; 
        else                
        	return min(x.left); 
    } 
	
}
