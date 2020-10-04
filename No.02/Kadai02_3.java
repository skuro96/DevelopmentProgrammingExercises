import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.TreeSet;

class Kadai02_3
{
	public static void main(String[] args)
	{
		String fname;

		if (args.length > 0)
			fname = args[0];
		else
			fname = "test.dat";

		try
		{
			Scanner sc = new Scanner(new File(fname));
			int n = sc.nextInt();
			// System.out.println("n = " + n);

			TreeSet<Integer> array = new TreeSet<Integer>();
			for (int i = 0; i < n; i++)
			{
				int v = sc.nextInt();
				array.add(v);
				// System.out.println("v = " + v);
			}
			int size = array.size();

			System.out.println("max value : " + array.last());
			System.out.print("second max value : ");
			if (size < 2)
				System.out.println("undefined");
			else
			{
				array.pollLast();
				System.out.println(array.last());
			}
		}
		catch (IOException e)
		{
			System.out.println(e);
		}
	}
}

/*
$ java Kadai02_3 a.dat
max value : 42
second max value : 25
*/

/*
$ java Kadai02_3
max value : 42
second max value : undefined
*/