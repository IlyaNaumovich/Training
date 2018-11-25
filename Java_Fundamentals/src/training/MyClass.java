package training;

public class MyClass {

	int age;
	String name;
	
	public MyClass(int age, String name) {
		this.age = age;
		this.name = name;
	}
	
	public void hello() {
		this.helloWorld();
	}
	
	private void helloWorld() {
		System.out.println("Hello world");
	}
	
	int getAge() {
		return this.age;
	}
}
