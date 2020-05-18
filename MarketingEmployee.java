
import java.util.Vector;
public class MarketingEmployee extends Employee {
	
	private String number_phone;
	
	public MarketingEmployee (int ID, String name, int age, String number_phone) {
		super(ID, name,age);
		this.number_phone = number_phone;
	}
	
	public String getnumber_phone() {
		return this.number_phone;
	}
	
//	private double CalculateSallery() {
//		
//		salary = 9.9;
//		return salary;
//	}
	

}
