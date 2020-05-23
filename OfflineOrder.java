import java.util.Vector;
public class OfflineOrder extends Order {
	SalesEmployee sales_employee; //will get pointer to salesman that made the order
	
	public OfflineOrder(Event event, Customer customer, int quantity, SalesEmployee sales_employee) {
		super(event, customer, quantity);
		this.sales_employee = sales_employee;		
	}
	
	public SalesEmployee GetSalesEmployee () {
		return this.sales_employee;
	}

}
