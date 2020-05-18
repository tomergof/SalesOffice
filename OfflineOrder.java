import java.util.Vector;
public class OfflineOrder extends Order {
	SalesEmployee sales_employee;
	
	public OfflineOrder(Event event, Customer customer, int quantity, SalesEmployee sales_employee) {
		super(event, customer, quantity);
		this.sales_employee = sales_employee;		
	}
	
	public int GetSalesID() {
		return this.sales_employee.getID();
	}
	

}
