import java.util.Vector;
public class SalesEmployee extends Employee{
	
	private double bonus_rate;
	
	public SalesEmployee (int ID, String name, int age ,double bonus_rate ) {
		super(ID, name,age);
		this.bonus_rate = bonus_rate;
	}
	
	public double getbonus_rate() {
		return this.bonus_rate;
	}
	
//	private double CalculateSallery() {
//		
//	}
	

}