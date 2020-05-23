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
	 
	 ///the next 3 getters are identical for humans - so they are in the abstract class
	 
	 public int GetID (){
		 return this.ID;
	 }
	 public String GetName() {
		 return this.name;
	 }
	 public int GetAge (){
		 return this.age;
	 }
	 
}