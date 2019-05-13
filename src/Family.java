import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Family {

	List <Persons> person = new ArrayList<Persons>();
	
	
	
	public Boolean Male(String name) {
		
		if(ishere(name)) {
			Persons checkPerson = getPerson(name);
			
			if(checkMaleSpouse(name))
			{
				return false;
			} else if(checkPerson.gender =="") {
				
				checkPerson.gender = "Male";
				return true;
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
	
	
	public Boolean Female(String name) {
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
	
	public boolean isMale(String name) { 
			
			if(ishere(name)) { 
				Persons person2 = getPerson(name); 
				
				if(person2.gender.equals("Male")) { 
					return true;
				}
				else { return false; } 
				}
			else { 
				person.add(new Persons(name)); 
				return false;
			}
			
		}
	
	public boolean isFemale(String name) {
		if(ishere(name)) { 
			Persons person2 = getPerson(name); 
			
			if(person2.gender.equals("Female")) { 
				return true;
			}
			else { return false; } 
			}
		else { 
			person.add(new Persons(name)); 
			return false;
		}
	}


	public Boolean setParentsOf(String childName, String parentName) {
		
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

//			for(int i = 0; i<children.length; i++) {
//				System.out.print(children[i]);
//			}

			return children;
		}
		else {
			Persons parent = getPerson(name);
			String[] children = new String[parent.childList.size()];
			
			return children;
		}
	}
	
	
	
	//MADE FOR ME
	public Boolean ishere(String name) {
		boolean ishe = person.stream().anyMatch(x -> x.name.equals(name));
		return ishe;
	}
	
	public Persons getPerson(String name) {
		List<Persons> person2 = person.stream().filter(x -> x.name.equals(name)).collect(Collectors.toList());
		return person2.get(0);
	}
	
	
	public Boolean checkMaleSpouse(String name) {
		Persons checkP = getPerson(name);
		if(checkP.spouse.size() > 0) {
			if(isMale(checkP.spouse.get(0).name))
			{
				return true;
			}
			else return false;
		}
		else return false;
	
		
	}
	
	public Boolean isChild(String childName, String parentName) {
		Persons parent = getPerson(parentName);

		boolean ishe = parent.childList.stream().anyMatch(x-> x.name.equals(childName));
		return ishe;
	}
	
	
}
