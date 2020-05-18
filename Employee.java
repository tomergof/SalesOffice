import java.util.Vector;
abstract public class Employee extends Human {

	protected double salary;
	
	public Employee(int ID, String name,int age) {
		super(ID, name, age);
		
	}
	public double getSaary() {
		return this.salary;
	}
		
}