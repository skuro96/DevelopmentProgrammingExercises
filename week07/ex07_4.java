import java.io.*;

class ex07_4
{
	public static void main(String[] args) throws IOException
	{
		System.out.println("数式を入力");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();

		String regex = "[+]?[0-9]+[+]{1}[0-9]+";
		if (str.matches(regex))
		{
			String regrex2 = "(?=[+])";
			String[] operands = str.toString().split(regrex2);
			System.out.println("オペランドに分割します");
			System.out.println(operands[0]);
			System.out.println(operands[1]);
			int op1 = Integer.parseInt(operands[0]);
			int op2 = Integer.parseInt(operands[1].substring(1));
			int result = op1 + op2;
			
			System.out.println(op1 + "+" + op2 + "=" + result);
		}
		else
			System.out.println("足し算ではありません");
	}
}