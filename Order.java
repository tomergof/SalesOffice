
abstract public class Order implements Comparable<Order>, Enumarable<Order> {
	
	protected Event event;  ///pointer to event of order
	protected Customer customer;  ///pointer to the customer
	protected int quantity;
	
	public Order(Event event, Customer customer, int quantity) {
		this.event=event;
		this.customer=customer;
		this.quantity=quantity;
	}
	
	///getters - in the abstract class because the implementation is identical
	
	public Event GetEvent() {
		return this.event;
	}
	
	public int GetCostumerID() {
		return this.customer.GetID();
	}
	
	public Customer GetCostumer() {
		return this.customer;
	}
	public int GetQuantity() {
		return this.quantity;
	}
	
	public int compareTo(Order other) {
		if (this.quantity>other.quantity){
			return 1;
		}
		return 0;
	}
	
	public double GetNumValue() {
		return this.quantity * this.event.GetPrice();
	}
	
}
