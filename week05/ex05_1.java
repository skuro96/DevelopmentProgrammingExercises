import java.util.Scanner;
import java.io.*;

public class ex05_1
{
	private static int[][] board = new int[9][9];

	public static void main(String[] args)
	{
		String fname = "input.txt";

		if (args.length > 0)
			fname = args[0];

		try
		{
			Scanner sc = new Scanner(new File(fname));
			for (int i = 0; i < 9; i++)
			{
				for (int j = 0; j < 9; j++)
				{
					board[i][j] = sc.nextInt();
					if (board[i][j] < 0 || 9 < board[i][j])
						board[i][j] = 0;
				}
			}
			for (int i = 0; i < 9; i++)
			{
				for (int j = 0; j < 9; j++)
				{
					System.out.print(board[i][j] + " ");
				}
				System.out.println();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}