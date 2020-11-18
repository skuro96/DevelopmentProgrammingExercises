import java.io.*;

class ex07_3
{
	public static void main(String[] args) throws IOException
	{
		System.out.println("数式を入力");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();

		String regex = "[+]?[0-9]+[+]{1}[0-9]+";
		if (str.matches(regex))
			System.out.println("足し算ですね");
		else
			System.out.println("足し算ではありません");
	}
}