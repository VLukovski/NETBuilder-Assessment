package com.qa.assessment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class Family {

	private List<String> males = new ArrayList<>();
	private List<String> females = new ArrayList<>();
	private HashMap<String, List<String>> parentList = new HashMap<String, List<String>>();
	private HashMap<String, List<String>> childrenList = new HashMap<String, List<String>>();

	public boolean male(String name) {
		if (males.contains(name) || females.contains(name)) {
			return false;
		}
		else {
			males.add(name);
			if(this.getChildrenOf(name).size() != 0) {
				for(String child : this.getChildrenOf(name)) {
					List<String> parents = this.getParentsOf(child);
					if (parents.size() != 1) {
						Boolean temp = (parents.get(0) == name) ? females.add(parents.get(1)) : females.add(parents.get(0));
					}
				}
			}
			return true;
		}
	}

	public boolean female(String name) {
		if (females.contains(name) || males.contains(name)) {
			return false;
		}
		else {
			females.add(name);
			if(this.getChildrenOf(name).size() != 0) {
				for(String child : this.getChildrenOf(name)) {
					List<String> parents = this.getParentsOf(child);
					if (parents.size() != 1) {
						Boolean temp = (parents.get(0) == name) ? males.add(parents.get(1)) : males.add(parents.get(0));
					}
				}
			}
			return true;
		}
	}

	public boolean isMale(String name) {
		return males.contains(name) ? true : false;
	}

	public boolean isFemale(String name) {
		return females.contains(name) ? true : false;
	}

	public boolean setParentOf(String childName, String parentName) {

		List<String> parents;
		List<String> children;
		
		if (childName == parentName) {
			return false;
		}
		else if (this.parentOfParent(this.getChildrenOf(childName), parentName)) {
			return false;
		}
		else if (parentList.get(childName) == null) {
			parents = new ArrayList<>();
			parents.add(parentName);
			parentList.put(childName, parents);
		} 
		else if (parentList.get(childName).size() == 1 && parentList.get(childName).get(0) != parentName) {
			parents = parentList.get(childName);
			parents.add(parentName);
			parentList.replace(childName, parents);

			// The block below attempts to infer genders of parents

			if (males.contains(parentName)) {
				females.add(parentList.get(childName).get(0));
			} 
			else if (females.contains(parentName)) {
				males.add(parentList.get(childName).get(0));
			} 
			else if (males.contains(parentList.get(childName).get(0))) {
				females.add(parentName);
			} 
			else if (females.contains(parentList.get(childName).get(0))) {
				males.add(parentName);
			}
		} else {
			return false;
		}
		
		// If the conditions did not stop the process this will 
		// proceed to update the children list
		
		if (childrenList.get(parentName) == null) {
			children = new ArrayList<>();
			children.add(childName);
			childrenList.put(parentName, children);
			return true;
		} 
		else {
			children = childrenList.get(parentName);
			children.add(childName);
			childrenList.replace(parentName, children);
			return true;
		}
	}

	public List<String> getParentsOf(String name) {
		return parentList.containsKey(name) ? parentList.get(name) : new ArrayList<String>();
	}

	public List<String> getChildrenOf(String name) {
		return childrenList.containsKey(name) ? childrenList.get(name) : new ArrayList<String>();
	}
	
	//The method below recursively collects everyone related to a list of children and once it stops, checks if the new list contains the name
	
	private boolean parentOfParent(List<String> children, String name) {
		List<String> offspring = new ArrayList<>();
		offspring.addAll(children);
		for(String child : children) {
			if (this.getChildrenOf(child).size() != 0 && !offspring.contains(name)) {
				offspring.addAll(this.getChildrenOf(child));
			}
		}
		return offspring.equals(children) ? offspring.contains(name) : this.parentOfParent(offspring, name);
	}
}
