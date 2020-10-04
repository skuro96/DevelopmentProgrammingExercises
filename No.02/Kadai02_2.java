import java.util.Scanner;

class Kadai02_2
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);

		// System.out.print("input n: ");
		int n = sc.nextInt();

		int max_val = Integer.MIN_VALUE;
		int min_val = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++)
		{
			// System.out.print("input value: ");
			int v = sc.nextInt();
			max_val = Math.max(v, max_val);
			min_val = Math.min(v, min_val);
		}

		System.out.println("max value = " + max_val);
		System.out.println("min value = " + min_val);
	}
}

/*
5
10 3 42 -30 0
max value = 42
min value = -30
*/