import java.util.Scanner;

public class Factorial {

	int fact(int n)
	{
		if(n<=1)
			return 1;
		else
			return n*fact(n-1);
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		int ans = fact(n);
		System.out.println(ans);

	}

}
