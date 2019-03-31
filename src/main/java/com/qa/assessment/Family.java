package com.qa.assessment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Family {

	private List<String> males = new ArrayList<>();
	private List<String> females = new ArrayList<>();
	private HashMap<String, List<String>> parents = new HashMap<String, List<String>>();
	private HashMap<String, List<String>> children = new HashMap<String, List<String>>();

	public boolean male(String name) {
		if (males.contains(name) || females.contains(name)) {
			return false;
		} else {
			males.add(name);
			return true;
		}
	}

	public boolean female(String name) {
		if (females.contains(name) || males.contains(name)) {
			return false;
		} else {
			females.add(name);
			return true;
		}
	}

	public boolean isMale(String name) {
		if (males.contains(name)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isFemale(String name) {
		if (females.contains(name)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean setParent(String childName, String parentName) {
		if (childName == parentName) {
			return false;
		} else if (parents.get(childName) == null) {
			List<String> parent = new ArrayList<>();
			parent.add(parentName);
			parents.put(childName, parent);
			return true;
		} else if (parents.get(childName).size() == 1 && parents.get(childName).get(0) != parentName) {
			List<String> parent = parents.get(childName);
			parent.add(parentName);
			parents.replace(childName, parent);
			return true;
		} else {
			return false;
		}
	}

	public String[] getParents(String name) {

		return null;
	}

	public String[] getChildren(String name) {

		return null;
	}
}
