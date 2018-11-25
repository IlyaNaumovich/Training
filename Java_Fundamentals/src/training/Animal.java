package training;

public abstract class Animal {

	int age;
	String name;
	
	public Animal(String name, int age) {
		this.age = age;
		this.name = name;
	}
	
	public abstract void run();
}
