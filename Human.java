import java.util.Vector;
abstract public class Human { 
	 protected int ID;
	 protected String name;
	 protected int age;
	 
	 public Human(int ID, String name,int age) {
		this.ID = ID;
		this.name = name;
		this.age = age;
	 }
	 
	 public int getID (){
		 return this.ID;
	 }
	 public String getName() {
		 return this.name;
	 }
	 public int getAge (){
		 return this.age;
	 }
	 
}