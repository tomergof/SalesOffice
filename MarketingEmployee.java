
import java.util.Vector;
public class MarketingEmployee extends Employee {
	
	private String phone_number;
	
	public MarketingEmployee (int ID, String name, int age, String number_phone) {
		super(ID, name,age);
		this.phone_number = number_phone;
	}
	
	public void UpdateSalary(int price, int quantity) {
		this.salary += price*quantity*0.01;
	}
	
	public void AddCustomerBonus() {
		this.salary+=2;
	}
}
