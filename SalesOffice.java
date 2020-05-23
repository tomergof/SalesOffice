import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

public class SalesOffice {
	
	//Class attributes - 4 vectors
	
	private Vector <Event> events_list;
	private Vector <Employee> employees_list;
	private Vector <Customer> customers_list;
	private Vector <Order> orders_list;
	
	// Constructor - fills vectors + updates employees' salary + updates order's info
	
	public SalesOffice (String fileEvents, String fileEmployees, String fileCustomers , String fileTicketsSales)
			throws IOException, ImpossibleGenderException {
		this.events_list = Create_Events_List(fileEvents);
		this.employees_list = Create_Employees_List(fileEmployees);
		this.customers_list = Create_Customers_List(fileCustomers);
		this.orders_list = Create_Orders_List(fileTicketsSales);
		SetSalaries();
		UpdateOrders();
	}
	
	//adds to event & customer their tickets counter + customer's expenditure
	
	private void UpdateOrders() {
		for (int i=0; i<orders_list.size(); i++) {
			int SumOfTickets = this.orders_list.elementAt(i).quantity;
			int EventPrice = this.orders_list.elementAt(i).GetEvent().GetPrice();
			this.orders_list.elementAt(i).customer.SetTickets(SumOfTickets);
			this.orders_list.elementAt(i).event.SetTickets(SumOfTickets);
			this.orders_list.elementAt(i).customer.SetMoney(EventPrice, SumOfTickets);
		}
	}
	
	//updates salaries depend on type of order
	
	private void SetSalaries (){
		for (int i=0;i<this.orders_list.size();i++) {
			Order ord = this.orders_list.elementAt(i);
			if (this.orders_list.elementAt(i) instanceof OfflineOrder) {
				SetSalesSalary((OfflineOrder)ord);
			}
			else {
				SetMarketingSalary((OnlineOrder)ord);
			}
		}
		AddForRegistration();
				
	}
		
	private void SetSalesSalary(OfflineOrder ord) {
		int price = ord.GetEvent().GetPrice();
		int quantity = ord.GetQuantity();
		ord.GetSalesEmployee().UpdateSalary(price, quantity);
					
	}
	
	private void SetMarketingSalary(OnlineOrder ord) {
		int price = ord.GetEvent().GetPrice();
		int quantity = ord.GetQuantity();
		ord.GetCostumer().GetRegisteredBy().UpdateSalary(price, quantity);
	}
	
	//updates 2$ to the customer's registrar
	
	private void AddForRegistration() {
		for (int i=0;i<this.customers_list.size();i++) {
			this.customers_list.elementAt(i).GetRegisteredBy().AddCustomerBonus();
		}
	}
	
	//function for each vector - get info from file, parse it and add to vector  
	
	private Vector<Event> Create_Events_List(String fileEvents) throws IOException {
		Vector<Event> events_list = new Vector<Event>();
		Vector <String[]> events;
		events = File_Reader(fileEvents);
		for (int i=1;i<events.size();i++) {
			String name = events.elementAt(i)[0];
			int ID = Integer.parseInt(events.elementAt(i)[1]);
			int price = Integer.parseInt(events.elementAt(i)[2]);
			Event new_event = null;
			try {
				new_event = new Event(name, ID, price);
				events_list.add(new_event);
			}
			catch (NegativePriceException e) {
				System.err.println("Caught exception: " + e.getMessage());
			}

		}
		return events_list;
	}
	
	private Vector<Employee> Create_Employees_List(String fileEmployees) throws IOException{
		Vector<Employee> employee_list = new Vector<Employee>();
		Vector <String[]> employee;
		employee = File_Reader(fileEmployees);
		for (int i=1;i<employee.size();i++) {
			Employee new_employee;
			int ID = Integer.parseInt(employee.elementAt(i)[0]);
			String name = employee.elementAt(i)[1];
			int age = Integer.parseInt(employee.elementAt(i)[2]);	
			if (employee.elementAt(i)[3].length()!=0) {
				double bonus_rate = Double.parseDouble(employee.elementAt(i)[3]);
				new_employee = new SalesEmployee(ID, name, age, bonus_rate);
			}
			else {
				String phone_number = employee.elementAt(i)[4];
				new_employee = new MarketingEmployee(ID, name, age, phone_number);
			}
			employee_list.add(new_employee);
		}
		return employee_list;
	}
		
	private Vector<Customer> Create_Customers_List(String fileCustomers) throws IOException {
		Vector<Customer> customers_list = new Vector<Customer>();
		Vector <String[]> customers;
		customers = File_Reader(fileCustomers);
		for (int i=1;i<customers.size();i++) {
			int ID = Integer.parseInt(customers.elementAt(i)[0]);
			String name = customers.elementAt(i)[1];
			int age = Integer.parseInt(customers.elementAt(i)[2]);
			char gender = customers.elementAt(i)[3].charAt(0);
			int reg_by = Integer.parseInt(customers.elementAt(i)[4]);
			MarketingEmployee registrar = (MarketingEmployee)PointToEmployee(reg_by);
			Customer new_customer = null;
			try {
				new_customer = new Customer(ID, name, age, gender, registrar);
				customers_list.add(new_customer);
			}
			catch (ImpossibleGenderException e) {
				System.err.println(e.getMessage());
			}
		}
		return customers_list;
	}
	
	private Vector<Order> Create_Orders_List(String fileTicketsSales) throws IOException{
		Vector<Order> orders_list = new Vector<Order>();
		Vector <String[]> orders;
		orders = File_Reader(fileTicketsSales);
		for (int i=1;i<orders.size();i++) {
			Order new_order;
			int event_ID = Integer.parseInt(orders.elementAt(i)[0]);
			int customer_ID = Integer.parseInt(orders.elementAt(i)[1]);
			int num_of_tickets = Integer.parseInt(orders.elementAt(i)[3]);
			Event event = PointToEvent(event_ID);
			Customer customer = PointToCustomer(customer_ID);
			if (orders.elementAt(i)[2].length()!=0) {
				int sales_ID = Integer.parseInt(orders.elementAt(i)[2]);
				SalesEmployee employee = (SalesEmployee)PointToEmployee(sales_ID);
				new_order = new OfflineOrder(event, customer, num_of_tickets, employee);
			}
			else {
				String url = orders.elementAt(i)[4];
				new_order = new OnlineOrder(event, customer, num_of_tickets, url);
			}
			orders_list.add(new_order);
		}
		return orders_list;
	}
	
	private Vector<String[]> File_Reader(String file_name) throws IOException {
		Vector<String[]> data = new Vector<String []>();
		BufferedReader inFile=null;
		try
		{
		  	FileReader fr = new FileReader (file_name);
		  	inFile = new BufferedReader (fr);	
			String line;

			while ((line = inFile.readLine()) != null) {

				String[] seperate = line.split("\t");
				data.add(seperate);
			}		
		}

		catch (FileNotFoundException exception)
		{
		 System.out.println ("The file " + file_name + " was not found.");
		}
		catch (IOException exception)
		{
		 System.out.println (exception);
		}
		finally{
			inFile.close();
		}
		return data;

	}
	
	//get pointer to employee by ID	
	private Employee PointToEmployee(int id) {
		Employee employee = null;
		for (int i=0;i<this.employees_list.size();i++) {
			if (this.employees_list.elementAt(i).GetID()==id) {
				employee = this.employees_list.elementAt(i);
				break;
			}
		}
		return employee;
	}
	
	//get pointer to event using id
	private Event PointToEvent(int id) {
		Event event = null;
		for (int i=0;i<this.events_list.size();i++) {
			if (this.events_list.elementAt(i).GetID()==id) {
				event = this.events_list.elementAt(i);
				break;
			}
		}
		return event;
	}
	
	//get pointer to customer using id
	private Customer PointToCustomer (int id) {
		Customer customer = null;
		for (int i=0;i<this.customers_list.size();i++) {
			if (this.customers_list.elementAt(i).GetID()==id) {
				customer = this.customers_list.elementAt(i);
				break;
			}
		}
		return customer;
	}
	
	//Q2 - get event name, get ages, print ages report 
	
	public void PrintAgeReport (int eventID) {
		String event_name = GetEventName(eventID);
		int [] ages = new int[6];
		GetAges(ages, eventID);
		int people_in_event = Count_People(ages);
		PrintEventAges(event_name, ages, people_in_event);
	}
	
	//Q3 - sums price*tickets for online & offline orders
	
	public double getOnlineProportion() {
		
		double online=0.0, offline=0.0, sum=0.0;
		for (int i = 0; i < this.orders_list.size();i++)
		{
			if (this.orders_list.elementAt(i) instanceof OnlineOrder)
				online += AddToProportion(i);
			else
				offline+= AddToProportion(i);
			
			sum = online+offline;	
		}
		return (online/sum);
	}
	
	//Q4 - Calculate (Revenues - Expenses) = Balance
	
	public double getBalance() {
		double revenue= CalculateRevenues();
		double expense = CalculateExpenses();
		return (revenue-expense);
	}
	
	//Q5 - sort copies of employees, events & customers and print the sorted lists int report
	
	public void firmReport() {
		///create copies of Vectors
		Vector<Employee> emp_sorted = new Vector<Employee>(this.employees_list); 
		Vector<Event> event_sorted = new Vector<Event>(this.events_list);
		Vector<Customer> cust_sorted = new Vector<Customer>(this.customers_list);
		///sort copies in descending order
		DescOrder(emp_sorted);
		DescOrder(event_sorted);
		DescOrder(cust_sorted);
		PrintFirmReport(emp_sorted,event_sorted,cust_sorted);
	}
	
	//Q6 - sort in descending order, and return first element of any comparable vector
	
	public static <T extends Comparable> T getMax(Vector<T> c) {

		Vector<T> c_copy = new Vector<T>(c);
		DescOrder(c_copy);
		return c_copy.elementAt(0);
	}
	
	//Q7 - get vector of Enumarable objects, return average value of selected attribute
	
	public static double getAvgValue(Vector <? extends Enumarable> a){
		double sum = 0;
		for(int i=0; i<a.size(); i++) {
			sum += a.elementAt(i).GetNumValue();
		}
		return (sum/a.size());

	}
	
	private String GetEventName(int id) {
		String name = "";
		for (int i=0;i<this.events_list.size();i++) {
			if (this.events_list.elementAt(i).GetID()==id) {
				name = this.events_list.elementAt(i).GetName();
			}
		}
		return name;
	}
	
	//for specific event - add age of each customer to array
	private void GetAges(int [] ages, int eventID) {
		for (int i=0;i<this.orders_list.size();i++) {
			if (this.orders_list.elementAt(i).GetEvent().GetID()==eventID) {
				int age = this.orders_list.elementAt(i).GetCostumer().GetAge();
				Add_Age (age, ages);
			}
		}
	}
	
	private void Add_Age(int age, int[] ages) {
		if ((age>=0) &(age<=18)){
			ages[0]+=1;
		}
		else if ((age>=19) &(age<=24)){
			ages[1]+=1;
		}
		else if ((age>=25) &(age<=35)){
			ages[2]+=1;
		}
		else if ((age>=36) &(age<=50)){
			ages[3]+=1;
		}
		else if ((age>=51) &(age<=70)){
			ages[4]+=1;
		}
		else {
			ages[5]+=1;
		}
	}
	
	private int Count_People(int [] ages) {
		int people = 0;
		for (int i=0;i<ages.length;i++) {
			people+=ages[i];
		}
		return people;
	}
	
	private void PrintEventAges(String event_name, int[] ages, int people) {
		
		System.out.println("Event name: " + event_name);
		System.out.println("0-18: "+100*ages[0]/people+"%");
		System.out.println("19-24: "+100*ages[1]/people+"%");
		System.out.println("25-35: "+100*ages[2]/people+"%");
		System.out.println("36-50: "+100*ages[3]/people+"%");
		System.out.println("51-70: "+100*ages[4]/people+"%");
		System.out.println("71+: "+100*ages[5]/people+"%");
	}
	
	//adds to online/offline - price*quantity of order
	private double AddToProportion(int idx) {
		return this.orders_list.elementAt(idx).GetQuantity()*this.orders_list.elementAt(idx).GetEvent().GetPrice();
	}
	
	//function that sorts any comparable in descending order	
	private static void DescOrder (Vector<? extends Comparable> c) {
		for (int i=0;i<c.size()-1;i++) {
			for (int j=0;j<c.size()-i-1;j++) {
				if (c.elementAt(j).compareTo(c.elementAt(j+1))<=0) {
					Collections.swap(c, j, j+1);
				}
			}
		}
	}	

	private void PrintFirmReport (Vector<Employee> em, Vector<Event> ev, Vector<Customer> cust) {
		System.out.println("SalesOffice report");
		PrintEmployees(em);
		PrintEvents(ev);
		PrintCustomers(cust);
	}
	
	//printings for the report
	private void PrintEmployees(Vector<Employee> em) {
		System.out.println("Employees list:");
		for (int i=0;i<em.size();i++) {
			System.out.println("Name: " + em.elementAt(i).GetName() + " ; age: " + em.elementAt(i).GetAge());
		}
		System.out.println("- - - - - - - - - - - - - - -");
	}
	private void PrintEvents(Vector<Event> ev) {
		System.out.println("Event list:");
		for (int i=0;i<ev.size();i++) {
			System.out.println(ev.elementAt(i).GetName());
		}
		System.out.println("- - - - - - - - - - - - - - -");
	}
	private void PrintCustomers(Vector<Customer> cust) {
		System.out.println("Customer list:");
		for (int i=0;i<cust.size();i++) {
			System.out.println("Name: " + cust.elementAt(i).GetName() + " ; age: " + cust.elementAt(i).GetAge() + " ; Gender: " + cust.elementAt(i).GetGender());
		}
	}
	
	private double CalculateRevenues() {
		double revenue = 0;
		for (int i = 0; i < this.orders_list.size();i++) {
			revenue += this.orders_list.elementAt(i).GetQuantity()*this.orders_list.elementAt(i).GetEvent().GetPrice();
		}
		return revenue;
	}
	
	private double CalculateExpenses() {
		double expense = 0;
		for (int i = 0; i < this.employees_list.size();i++) {
			expense += this.employees_list.elementAt(i).GetSalary();
		}
		return expense;
	}
	
	public static void main(String[] args) throws IOException {
		String events = "C:\\Users\\user\\eclipse-workspace\\SalesOffice\\Events.txt";
		String employees = "C:\\Users\\user\\eclipse-workspace\\SalesOffice\\Employees.txt";
		String customers = "C:\\Users\\user\\eclipse-workspace\\SalesOffice\\Customers.txt";
		String orders = "C:\\Users\\user\\eclipse-workspace\\SalesOffice\\Orders.txt";
		SalesOffice s = new SalesOffice(events, employees, customers, orders);

	}
}

