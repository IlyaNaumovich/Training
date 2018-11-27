package exceptions;

import java.io.IOError;
import java.io.IOException;

public class exceptionMain {

	public static void main(String[] args) {
		
		int d = 42;
		int g = 8;
		
		try {
			int a = d/g;
			System.out.println("Все хорошо");
			
			try {
				int[] ar = new int[1];
				
				ar[3] = d;
				System.out.println("все ок");
			}catch (IndexOutOfBoundsException e) {
				System.out.println("Выход за пределы массива");
				throw new ArithmeticException();
			}
		}
		catch(ArithmeticException e ) {
			System.out.println("Ве плохо");	
		} finally {
			
		}
		
		System.out.println("после try/catch");
		
		
	}
	
	static void div(int d, int g) throws ArithmeticException, IOException, IndexOutOfBoundsException {
		System.out.println(d/g);
		
	}
}
