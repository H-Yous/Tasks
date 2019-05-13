import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Family {
	
	List <Persons> person = new ArrayList<Persons>();

	
	
//	This function will set the person in question to Male
//	However if they are set already to a gender it will return False!
//	This function is almost exactly the same as the female function.
	public boolean male(String name) {
		
		if(ishere(name)) {
			Persons checkPerson = getPerson(name);
			if(checkPerson.gender =="") {
				
				checkPerson.gender = "Male";
				return true;
			} 
			else if(checkSpouse(name).equals("Male"))
			{
				return false;
			}
			else {
				return false;
			}
			
		} else {
			person.add(new Persons(name));
			Persons newPerson = getPerson(name);
			newPerson.gender = "Male";
			return true;
		}
		
	}
	

	//Refer to male() method for comments
	public boolean female(String name) {
		if(ishere(name)) {
			Persons checkPerson = getPerson(name);
			if(checkPerson.gender == "") {
				checkPerson.gender = "Female";
				return true;
			} else return false;
			
		} else {
			person.add(new Persons(name));
			Persons newPerson = getPerson(name);
			newPerson.gender = "Male";
			return true;
		}
	}
	
	/* Check if the person in question is a male.
	 * Almost exactly same code for isFemale. */
	public boolean isMale(String name) { 
		
		if(ishere(name)) { //Check if person is there
			Persons person2 = getPerson(name); //Get the person and store in person2.
			
			if(person2.gender.equals("Male")) { //If their gender is male, return true;
				return true;
			}
			else { return false; } //If not return false; Will return if no gender assigned.
		}
		else { //If no person is there, create one and return false anyway.
			person.add(new Persons(name)); 
			return false;
		}
		
		
		
		
	}
	
	
	//Refer to isMale() method for comments
	public boolean isFemale(String name) {
		Persons person2 = getPerson(name);
		
		if(person2.gender.equals("Female")) {
			return true;
		}
		else { return false; }
	}
	

	public boolean setParentOf(String childName, String parentName) { 
		
		if(ishere(parentName)) { 
			
			Persons parent = getPerson(parentName);
			String gender = parent.gender;
			
			if(ishere(childName)) { 
				
				Persons child = getPerson(childName);
				
				if(isChild(parentName, childName)) {
					return false;
				}
				else {
					if(child.parentsList.size() == 0 ) { //If parents = 0, add the next parent.
						child.parentsList.add(parent);
						parent.childList.add(child);
						
						return true;
					} 
					else if (child.parentsList.size() == 1) { //If parents = 1, check the gender of the first parent.
						if(child.parentsList.get(0).gender.equals("")){ //If gender == null, add new parent
							child.parentsList.add(parent);
							parent.childList.add(child);
							
							child.parentsList.get(0).spouse.add(parent);
							parent.spouse.add(child.parentsList.get(0));
							return true;
						} else if(!child.parentsList.get(0).gender.equals(parent.gender)){ // ELSE If Parent1 gender != new parent gender , add parent
							child.parentsList.add(parent);
							parent.childList.add(child);
							
							child.parentsList.get(0).spouse.add(parent);
							parent.spouse.add(child.parentsList.get(0));
							return true;
							}
						else { //if genders are the same, return false;
							return false;
						}	
					} 
					
					else return false;
				}
			}
			else { //Else add the child, and set their parent to the parentName + gender.
				person.add(new Persons(childName));
				Persons child = getPerson(childName);
				parent.childList.add(child);
				child.parentsList.add(parent);
				
				return true;
			}
		}
		else { //If there is no person by the parentName, Return false as we cannot assume their gender. 
			person.add(new Persons(parentName));
			Persons parent = getPerson(parentName);
			
			if(ishere(childName)) { //if there is a person by the name of the child, get them and store in 'child'.
				
				Persons child = getPerson(childName);
				
				if(child.parentsList.size() == 0 ) { //If parents = 0, add the next parent.
					child.parentsList.add(parent);
					parent.childList.add(child);
					
					return true;
				} 
				else if (child.parentsList.size() == 1) { //If parents = 1, check the gender of the first parent.
						if(child.parentsList.get(0).gender.equals("")){ //If gender == null, add new parent
							child.parentsList.add(parent);
							parent.childList.add(child);
							
							child.parentsList.get(0).spouse.add(parent);
							parent.spouse.add(child.parentsList.get(0));
							
							return true;
						} else if(!child.parentsList.get(0).gender.equals(parent.gender)){ // ELSE If Parent1 gender != new parent gender , add parent
							child.parentsList.add(parent);
							parent.childList.add(child);
							
							child.parentsList.get(0).spouse.add(parent);
							parent.spouse.add(child.parentsList.get(0));
							return true;
							}
						else { //if genders are the same, return false;
							return false;
						}	
					} 
					
					else return false;
				}
			
			else { //Else add the child, and set their parent to the parentName + gender.
				person.add(new Persons(childName));
				Persons child = getPerson(childName);
				child.parentsList.add(parent);
				parent.childList.add(child);
				return true;
			}
		}
			
	}
	
	public String[] getParentsOf(String name) {
		String[] parents = new String[2];
		if(ishere(name)) {
			Persons child = getPerson(name);
			List <Persons> parentList = child.parentsList;
			
			List<String> names = parentList.stream().map(x -> x.name).sorted().collect(Collectors.toList());
			parents = names.parallelStream().toArray(String[]::new);
			
			return parents;
		} else {
			person.add(new Persons(name));
			return parents;
		}
	}

	public String[] getChildrenOf(String name) {
		if(ishere(name)) {
			Persons parent = getPerson(name);
			String[] children = new String[parent.childList.size()];

			List <String> names = parent.childList.stream().map(x -> x.name).sorted().collect(Collectors.toList());
			children = names.stream().toArray(String[]::new);


			return children;
		}
		else {
			Persons parent = getPerson(name);
			String[] children = new String[parent.childList.size()];
			
			return children;
		}
	}
	
	
//	Uses name to find the person within the personList and returns it to be assigned.
	public Persons getPerson(String name) {
		List<Persons> person2 = person.stream().filter(x -> x.name.equals(name)).collect(Collectors.toList());
		return person2.get(0);
	}
	
//	This function will be used to determine whether the person has already been created!
//	Return false if not
	public Boolean ishere(String name) {
		boolean ishe = person.stream().anyMatch(x -> x.name.equals(name));
		return ishe;
	}
	
	public Boolean isChild(String childName, String parentName) {
		Persons parent = getPerson(parentName);
	
		
		boolean ishe = parent.childList.stream().anyMatch(x-> x.name.equals(childName));
		
		System.out.println(ishe + "HIIIIII");
		return ishe;
	}
	
	
	public String checkSpouse(String name) {
		Persons checkP = getPerson(name);
		String gender = checkP.spouse.get(0).gender;
		return gender;
	}
}


