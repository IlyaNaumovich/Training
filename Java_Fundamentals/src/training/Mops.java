package training;

public class Mops extends Dog{

private String color;
	
	public Mops(String name, int age) {
		super(name, age);
		// TODO Auto-generated constructor stub
	}

	public Mops(String name, int age, String color) {
		super(name, age);
		this.color = color;
	}
	
	public void bark() {
		System.out.println("gav gav ");
	}
}
