import java.util.Vector;	

public class OnlineOrder extends Order {
	private String url;
	
	public OnlineOrder(Event event, Customer customer, int quantity, String url) {
		super(event, customer, quantity);
		this.url = url;
	}
}
