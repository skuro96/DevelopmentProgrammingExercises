import java.util.Scanner;
import java.io.File;
import java.io.IOException;

class ex02_3
{
	public static void main(String[] args)
	{
		String fname = "test.dat";

		if (args.length > 0)
			fname = args[0];

		try
		{
			Scanner sc = new Scanner(new File(fname));
			int n = sc.nextInt();
			System.out.println("n = " + n);

			for (int i = 0; i < n; i++)
			{
				int v = sc.nextInt();
				System.out.println("v = " + v);
			}
		}
		catch (IOException e)
		{
			System.out.println(e);
		}
	}
}