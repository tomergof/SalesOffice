
abstract public class Order {
	
	protected Event event;
	protected Customer customer;
	protected int quantity;
	
	public Order(Event event, Customer customer, int quantity) {
		this.event=event;
		this.customer=customer;
		this.quantity=quantity;
	}
	///getters - in the abstract class because the implementation is identical
	public int GetEventID() {
		return this.event.GetID();
	}
	
	public int GetCostumerID() {
		return this.customer.getID();
	}
	
	public Customer GetCostumer() {
		return this.customer;
	}
	public int GetQuantity() {
		return this.quantity;
	}
	
	
}
