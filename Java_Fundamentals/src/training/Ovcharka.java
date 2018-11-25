package training;

public class Ovcharka extends Dog{

	private String color;
	
	public Ovcharka(String name, int age) {
		super(name, age);
		// TODO Auto-generated constructor stub
	}

	public Ovcharka(String name, int age, String color) {
		super(name, age);
		this.color = color;
	}
	
	public void bark() {
		System.out.println("GAV GAV");
	}
}
