package training;

public class HelloWorld {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//int[] array = new int[6];
		int[] array2 = {13,4,22,21,20,8,1,0};
		
		for(int i = 0; i < array2.length; i++)
			System.out.println(array2[i]+" ");
		
		for(int i =0; i < array2.length; i++) {
			for(int j = i+1; j < array2.length; j++) {
				if(array2[i] > array2[j]) {
					int t = array2[i];
					array2[i] = array2[j];
					array2[j] = t;
				}
			}
		}
		
		for(int i = 0; i < array2.length; i++)
			System.out.print(array2[i]+" ");
		//int a = 15;
		/*if(array2.length > a) {
			System.out.println(array2[4]+1);
			if(true) {
				if()
			}
		} else if(array2.length < 2){
			System.out.println(array2[2]);
		} else if() {
			System.out.println("Sorry");
		} else if() {
			
		}*/
		
		/*switch(a) {
		case 10:
			System.out.println(array2[0]);
			break;
		case 11:
			System.out.println(array2[1]);
		default:
			System.out.println("default");
		case 13: 
			System.out.println(array2[2]);
		}*/
		//int i = 0;
		/*while(true) {
			System.out.println(array2[i]);
			i++;
			if(array2.length <= i)
				break;
		}*/
		
		/*for(int i = 0; i < array2.length; i++) {
			System.out.println(array2[i]);
			
		}*/
		
		/*do {
			
		}while(true);*/
		
	}
}
