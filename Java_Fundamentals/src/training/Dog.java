package training;

public class  Dog extends Animal{

	public static final float PI;
	
	public Dog(String name, int age) {
		super(name, age);
		// TODO Auto-generated constructor stub
	}
	
	public void bark() {
		System.out.println("GAV");
	}
	
	public void bark(String gav) {
		System.out.println(gav);
	}

	
	static {
		PI = 3.14f;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
