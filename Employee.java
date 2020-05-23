import java.util.Vector;
abstract public class Employee extends Human implements Comparable<Employee>,
	Enumarable<Employee> {

	protected double salary = 0;
	
	public Employee(int ID, String name,int age) {
		super(ID, name, age);
		
	}
	public double GetSalary() {
		return this.salary;
	}
	
	public int compareTo(Employee other) {
		if (this.salary>other.salary) {
			return 1;
		}
		return 0;
	}
	
	public double GetNumValue() {
		return this.salary;
	}
	
	public abstract void UpdateSalary(int price, int quantity);
		
}