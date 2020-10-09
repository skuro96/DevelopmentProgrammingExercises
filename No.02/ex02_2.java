import java.util.Scanner;

class ex02_2{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.print("input number n : ");
		int n = sc.nextInt();

		for (int i = 0;  i < n; i++){
			System.out.print("input number v : ");
			int v = sc.nextInt();
			System.out.println("i = " + i + ", v = " + v);
		}
	}
}