
public class Customer extends Human implements Comparable<Customer>, 
	Enumarable<Customer> {
	private char gender;
	private MarketingEmployee registered_by; //will get pointer to employee that registered
	private int tickets_bought;
	private int sum_money;
	
	public Customer(int ID, String name,int age, char gender, MarketingEmployee registered_by) throws ImpossibleGenderException {
		super(ID, name, age);
		this.registered_by = registered_by;
		if ((gender=='m') |(gender=='f')) {
			this.gender=gender;
		}
		else {
			throw new ImpossibleGenderException("Gender is not valid! Can't create customer " + this.ID);
		}
	}
	
	
	public char GetGender() {
		return this.gender;
	}
	
	public MarketingEmployee GetRegisteredBy() {
		return this.registered_by;
	}
	
	public int GetTickets() {
		return this.tickets_bought;
	}
	
	public void SetTickets(int num) {
		this.tickets_bought+=num;
	}
	
	public int compareTo(Customer other) {
		if (this.tickets_bought>other.tickets_bought) {
			return 1;
		}
		return 0;
	}
	
	public void SetMoney(int EventPrice, int SumOfTickets) {
		this.sum_money += EventPrice*SumOfTickets;
	}
	
	public double GetNumValue() {
		return this.sum_money;
	}
	
	
}
