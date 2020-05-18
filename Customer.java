
public class Customer extends Human {
	private char gender;
	private MarketingEmployee registered_by;
	private int tickets_bought;
	
	public Customer(int ID, String name,int age, char gender, MarketingEmployee registered_by) throws ImpossibleGenderException {
		super(ID, name, age);
		this.registered_by = registered_by;
		if ((gender=='m') |(gender=='f')) {
			this.gender=gender;
		}
		else {
			throw new ImpossibleGenderException("Gender is not valid!");
		}
	}
	
	
	public char GetGender() {
		return this.gender;
	}
	
	public int GetRegisteredBy() {
		return this.registered_by.getID();
	}
	
	public int GetTicketsBought() {
		return this.tickets_bought;
	}
	
	
	
}
