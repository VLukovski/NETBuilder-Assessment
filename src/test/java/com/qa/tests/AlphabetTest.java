package com.qa.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.qa.assessment.Alphabet;

public class AlphabetTest {

	private Alphabet alph;

	@Before
	public void setup() {
		alph = new Alphabet();
	}

	@Test
	public void emptyStringTest() {
		assertEquals("", alph.replaceLetterWithPosition(""));
	}

	@Test
	public void numberStringTest() {
		assertEquals("", alph.replaceLetterWithPosition("1234567890"));
	}

	@Test
	public void spaceStringTest() {
		assertEquals("", alph.replaceLetterWithPosition(" "));
	}

	@Test
	public void symbolStringTest() {
		assertEquals("", alph.replaceLetterWithPosition("/*-+.!?=\\|,><\"@#$%^&*(){}[]"));
	}
	
	@Test
	public void alphabetTest() {
		assertEquals("1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26", 
				alph.replaceLetterWithPosition("abcdefghijklmnopqrstuvwxyz"));
	}
	
	@Test
	public void alphabetUppercaseTest() {
		assertEquals("1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26", 
				alph.replaceLetterWithPosition("ABCDEFGHIJKLMNOPQRSTUVWXYZ"));
	}

	@Test
	public void sentenceTest() {
		assertEquals("20 8 9 19 14 5 20 2 21 9 12 4 5 18 1 19 19 5 19 19 13 5 14 20 9 19 23 1 25 20 15 15 5 1 19 25", 
				alph.replaceLetterWithPosition("This NETbuilder assessment is way too easy."));
	}
}
