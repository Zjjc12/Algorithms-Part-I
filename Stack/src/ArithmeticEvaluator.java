
import LinkedList.IterableStack;
import java.util.Scanner;

public class ArithmeticEvaluator

{
	public static void main(String[] args)
	{
		IterableStack<String> operator = new IterableStack<String>();
		IterableStack<Double> values = new IterableStack<Double>();

		Scanner in = new Scanner(System.in);
		in.useDelimiter("");
		
		System.out.print("Enter Arithemtic Equation: ");
		
		while (in.hasNext())
		{
			
			String s = in.next().trim();

			//System.out.println(s);
			
			if (s.equals("")) {
				break;
			}
			
			if (s.equals("(")  || s.equals(""))
			{
				// nothing
			} 
			else if (s.equals("+"))
			{
				operator.push(s);
			} 
			else if (s.equals("-"))
			{
				operator.push(s);
			} 
			else if (s.equals("*"))
			{
				operator.push(s);
			} 
			else if (s.equals("/"))
			{
				operator.push(s);
			} 
			else if (s.equals(")"))
			{
				String ops = operator.pop();

				Double v2 = values.pop();
				Double v1 = values.pop();

				if (ops.equals("+"))
				{
					values.push(v1 + v2);
				} 
				else if (ops.equals("-"))
				{
					values.push(v1 - v2);
				} 
				else if (ops.equals("*"))
				{
					values.push(v1 * v2);
				} 
				else if (ops.equals("/"))
				{
					values.push(v1 / v2);;
				} 
			} 
			else
			{
				values.push(Double.parseDouble(s));
			}
		}
		/*
		while(!values.onlyOne()) {
			String ops = operator.pop();
		
			Double v2 = values.pop();
			Double v1 = values.pop();

			if (ops.equals("*"))
			{
				values.push(v1 * v2);
			} 
			else if (ops.equals("/"))
			{
				values.push(v1 / v2);
			} 
			else if (ops.equals("+"))
			{
				values.push(v1 + v2);
			} 
			else if (ops.equals("-"))
			{
				values.push(v1 - v2);
			}
		}
		*/
		in.close();
		System.out.println(values.pop());
	}
}
