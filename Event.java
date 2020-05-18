
public class Event {
	private String name;
	private int ID;
	private int price;
	
	///constructor
	
	public Event(String name,int ID, int price) {
		if (price<0) {
			throw new NegativePriceException("The Price Can Not Be Nagtive!");
		}
		this.name = name;
		this.ID = ID;
		this.price = price;		
	}
	
	//getters
	
	public String GetName() {
		return this.name;
	}
	public int GetID() {
		return this.ID;
	}
	public int GetPrice() {
		return this.price;
	}
}
