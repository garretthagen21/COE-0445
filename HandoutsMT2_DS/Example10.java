// CS 0445 Example 10
// Simple demo of Mergesort
import java.util.*;
public class Example10
{
	public static Random R = new Random();
	// Fill array with random data
	public static void fillArray(Integer [] A, int range)
	{
		for (int i = 0; i < A.length; i++)
		{
			A[i] = new Integer(R.nextInt(range));
		}
	}

	public static void showArray(Integer [] A)
	{
		for (int i = 0; i < A.length; i++)
		{
			System.out.print(A[i] + " ");
		}
		System.out.println("\n");
	}

	public static void main(String [] args)
	{
		int size = 15;
		int range = 100;
		Integer [] A = new Integer[size];
		fillArray(A, range);
		showArray(A);
		TextMergeQuick.mergeSort(A, A.length);
		showArray(A);
	}
}

