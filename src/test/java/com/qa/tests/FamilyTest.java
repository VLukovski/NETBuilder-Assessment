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
		fam.setParentOf("Sam", "John");
		assertTrue(fam.setParentOf("Sam", "Mary"));
	}
	
	@Test
	public void setSameParentTest() {
		fam.setParentOf("Sam", "John");
		assertFalse(fam.setParentOf("Sam", "John"));
	}
	
	@Test
	public void setTooManyParentsTest() {
		fam.setParentOf("Sam", "John");
		fam.setParentOf("Sam", "Mary");
		assertFalse(fam.setParentOf("Sam", "Anna"));
	}
	
	@Test
	public void setParentOfSelfTest() {
		assertFalse(fam.setParentOf("Sam", "Sam"));
	}
	
	@Test
	public void newNameGetChildrenTest() {
		assertEquals(0, fam.getChildrenOf("Sam").size());
	}
	
	@Test
	public void newNameGetParentsTest() {
		assertEquals(0, fam.getParentsOf("Sam").size());
	}
	
	@Test
	public void getParentsTest() {
		fam.setParentOf("Sam", "John");
		fam.setParentOf("Sam", "Mary");
		assertEquals("[John, Mary]", fam.getParentsOf("Sam").toString());
	}
	
	@Test
	public void getChildrenTest() {
		fam.setParentOf("Sam", "John");
		fam.setParentOf("Sam", "Mary");
		fam.setParentOf("Anna", "John");
		fam.setParentOf("Anna", "Mary");
		assertEquals("[Sam, Anna]", fam.getChildrenOf("John").toString());
		assertEquals("[Sam, Anna]", fam.getChildrenOf("Mary").toString());
	}
	
	@Test
	public void genderDeductionTest() {
		fam.male("John");
		fam.setParentOf("Sam", "John");
		fam.setParentOf("Sam", "Mary");
		assertTrue(fam.isFemale("Mary"));
		assertFalse(fam.isMale("Mary"));
	}
	
	@Test
	public void childrenFromDifferentMothersTest() {
		fam.setParentOf("Sam", "John");
		fam.setParentOf("Sam", "Mary");
		fam.setParentOf("Tom", "John");
		fam.setParentOf("Tom", "Anna");
		assertEquals("[John, Mary]", fam.getParentsOf("Sam").toString());
		assertEquals("[John, Anna]", fam.getParentsOf("Tom").toString());
	}
	
	@Test
	public void parentIsAlsoGrandparentTest() {
		fam.setParentOf("Sam", "John");
		fam.setParentOf("Sam", "Mary");
		fam.setParentOf("Mary", "John");
		fam.setParentOf("Mary", "Anna");
		assertEquals("[John, Mary]", fam.getParentsOf("Sam").toString());
		assertEquals("[John, Anna]", fam.getParentsOf("Mary").toString());
	}
	
	@Test
	public void caseSensetiveTest() {
		fam.setParentOf("Sam", "John");
		assertTrue(fam.setParentOf("Sam", "john"));
	}
	
	@Test
	public void setBothParentsGenderTest() {
		fam.setParentOf("Vera", "George");
		fam.setParentOf("Vera", "Vanessa");
		fam.setParentOf("Mary", "John");
		fam.setParentOf("Mary", "Vanessa");
		fam.female("Vanessa");
		assertFalse(fam.female("George"));
		assertTrue(fam.isMale("George"));
		assertFalse(fam.female("John"));
		assertTrue(fam.isMale("John"));
	}
	
	@Test
	public void assessmentPaperTest() {
		assertTrue(fam.setParentOf("Frank", "Morgan"));
		assertTrue(fam.setParentOf("Frank", "Dylan"));
		assertTrue(fam.male("Dylan"));
		assertTrue(fam.setParentOf("Joy", "Frank"));
		assertTrue(fam.male("Frank"));
		assertFalse(fam.male("Morgan"));
		assertTrue(fam.setParentOf("July", "Morgan"));
		assertFalse(fam.isMale("Joy") || fam.isFemale("Joy"));
		assertEquals("[Frank, July]", fam.getChildrenOf("Morgan").toString());
		assertTrue(fam.setParentOf("Jennifer", "Morgan"));
		assertEquals("[Frank, July, Jennifer]", fam.getChildrenOf("Morgan").toString());
		assertEquals("[Frank]", fam.getChildrenOf("Dylan").toString());
		assertEquals("[Morgan, Dylan]", fam.getParentsOf("Frank").toString());
		assertFalse(fam.setParentOf("Morgan", "Frank"));
		
		assertFalse(fam.setParentOf("Morgan", "Joy")); //This was not in the example originally, but I added this to check recursion
	}
	
	
}
