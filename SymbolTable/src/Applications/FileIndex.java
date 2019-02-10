package Applications;

import java.io.File;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FileIndex
{

	public static void main(String[] args)
	{
		// Create symbol table of alll the words and all files
		ST<String, SET<File>> st = new ST<String, SET<File>>();
		
		// For every file
		for (String filename: args) {
			File file = new File(filename);
			
			In in = new In(file);
			
			// If word exist in file, add file to the set
			while(!in.isEmpty()){
				String key = in.readString();
				if(!st.contains(key))
					st.put(key, new SET<File>());
				
				SET<File> set = st.get(key);
				set.add(file);
			}	

		}
		
		// Print out files for index
		while(!StdIn.isEmpty())
		{
			String query = StdIn.readString();
			StdOut.println(st.get(query));
		}
	}
}
