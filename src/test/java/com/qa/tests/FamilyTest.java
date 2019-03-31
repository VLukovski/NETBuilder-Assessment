package com.qa.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.qa.assessment.Family;

public class FamilyTest {
	
	Family fam;
	
	@Before
	public void setup() {
		fam = new Family();
	}
	
	@Test
	public void setMaleTest() {
		fam.male("John");
		assertFalse(fam.female("John"));
	}
	
	@Test
	public void setFemaleTest() {
		fam.female("Mary");
		assertFalse(fam.male("Mary"));
	}

	@Test
	public void isMaleTest() {
		fam.male("John");
		assertTrue(fam.isMale("John"));
		assertFalse(fam.isFemale("John"));
	}
	
	@Test
	public void unsetIsMaleTest() {
		assertFalse(fam.isMale("John"));
	}
	
	@Test
	public void unsetIsFemaleTest() {
		assertFalse(fam.isFemale("Mary"));
	}
	
	@Test
	public void isFemaleTest() {
		fam.female("Mary");
		assertFalse(fam.isMale("Mary"));
		assertTrue(fam.isFemale("Mary"));
	}
	
	@Test
	public void setParentPassTest() {
		fam.setParent("Sam", "John");
		assertTrue(fam.setParent("Sam", "Mary"));
	}
	
	@Test
	public void setSameParentTest() {
		fam.setParent("Sam", "John");
		assertFalse(fam.setParent("Sam", "John"));
	}
	
	@Test
	public void setTooManyParentsTest() {
		fam.setParent("Sam", "John");
		fam.setParent("Sam", "Mary");
		assertFalse(fam.setParent("Sam", "Anna"));
	}
	
	@Test
	public void setParentOfSelfTest() {
		assertFalse(fam.setParent("Sam", "Sam"));
	}
	
	@Test
	public void newNameGetChildrenTest() {
		assertEquals(0, fam.getChildren("Sam").length);
	}
	
	@Test
	public void newNameGetParentsTest() {
		assertEquals(0, fam.getParents("Sam").length);
	}
	
	@Test
	public void getParentsTest() {
		fam.setParent("Sam", "John");
		fam.setParent("Sam", "Mary");
		assertEquals("[John, Mary]", fam.getParents("Sam").toString());
	}
	
	@Test
	public void getChildrenTest() {
		fam.setParent("Sam", "John");
		fam.setParent("Sam", "Mary");
		fam.setParent("Anna", "John");
		fam.setParent("Anna", "Mary");
		assertEquals("[Sam, Anna]", fam.getChildren("John").toString());
		assertEquals("[Sam, Anna]", fam.getChildren("Mary").toString());
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
