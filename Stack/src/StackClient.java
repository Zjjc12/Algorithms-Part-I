import Array.ResizingArrayStackOfStrings;

public class StackClient
{
	public static void main(String[] args)
	{
		ResizingArrayStackOfStrings stack = new ResizingArrayStackOfStrings();
		/*
		 * while (!StdIn.isEmpty()) { String s = StdIn.readString(); if (s.equals("-"))
		 * StdOut.print(stack.pop()); else stack.push(s); }
		 */

		stack.push("hi");
		stack.push("lel");
		System.out.println(stack.pop());
		stack.push("yo");
		stack.push("wassup");
		System.out.println(stack.pop());
	}
}
