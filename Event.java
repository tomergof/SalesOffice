
public class Event implements Comparable<Event>, Enumarable<Event> {
	private String name;
	private int ID;
	private int price;
	private int total_tickets=0;
	
	///constructor
	
	public Event(String name,int ID, int price) throws NegativePriceException {
		if (price<0) {
			throw new NegativePriceException("The Price Can Not Be Nagtive!");
		}
		this.name = name;
		this.ID = ID;
		this.price = price;		
	}

	public String GetName() {
		return this.name;
	}
	
	public int GetID() {
		return this.ID;
	}
	
	public int GetPrice() {
		return this.price;
	}
	
	public int GetTickets() {
		return this.total_tickets;
	}
	
	public void SetTickets(int num) {
		this.total_tickets+=num;
	}
	
	public int compareTo(Event other) {
		if (this.total_tickets>other.total_tickets) {
			return 1;
		}
		return 0;
	}
	
	public double GetNumValue() {
		return this.price;
	}
}
