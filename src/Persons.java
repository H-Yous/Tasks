import java.util.ArrayList;
import java.util.List;

public class Persons {
	
	String name = "", gender = "";
	
	List <Persons> spouse = new ArrayList<Persons>();
	
	List<Persons> parentsList = new ArrayList<Persons>();
	List <Persons> childList = new ArrayList<Persons>();
	
	
	public Persons() {
		
	}
	
	public Persons(String name) {
		this.name = name;
	}

}
