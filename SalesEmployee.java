import java.util.Vector;
public class SalesEmployee extends Employee {
	
	private double bonus_rate;
	
	public SalesEmployee (int ID, String name, int age ,double bonus_rate ) {
		super(ID, name,age);
		this.bonus_rate = bonus_rate;
	}
	
	public void UpdateSalary(int price, int quantity) {
		this.salary += this.bonus_rate * price * quantity;
	}
	

}