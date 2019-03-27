/*
 * Copyright (c) 2019 Christopher Myers
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 * ======
 *
 * This file was developed as part of CS 4233: Object Oriented Analysis &
 * Design, at Worcester Polytechnic Institute.
 */
package ocr;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the OCR translator.
 * @version Mar 13, 2019
 */
class OCRTranslatorTest
{
	static OCRTranslator trns;

	/**
	 * Init function that just creates a reusable OCRTranslator object
	 */
	@BeforeAll
	static void init() {
		trns = new OCRTranslator();
	}

	/**
	 * Helper concatenate digits to form strings of digits (used for testing tokenization).
	 * @param whitespace Whitespace to put between each digit
	 * @param digits Digits to insert into string
	 */
	static String[] concatDigits(int whitespace, String[] ... digits) {
		String[] str = new String[]{"", "", ""};
		for (String[] digit: digits) {
			for (int i = 0; i < 3; i++) {
				str[i] += digit[i];
				for (int j = 0; j < whitespace; j++)
					str[i] += " ";
			}
		}

		return str;
	}

	/**
	 * TEST 1: whether we can correctly match a digit to a string-encoded digit.
	 */
	@Test
	void digitMatching() {
		// Make sure that checking works
		for (int i = 0; i <= 9; i++)
			assertTrue(trns.digitMatches(i, trns.digits[i]));

		// Check every single other possible combination to make sure that other possibilities don't work. Yes, there's
		// a slightly complex loop in test code, but it's necessary to test all possible combinations.
		for (int i = 0; i <= 9; i++) {
			for (int offset = 1; offset <= 9; offset++) {
				assertFalse(trns.digitMatches(i, trns.digits[(i+offset) % 10]));
			}
		}

		// Idiot checks to make sure that invalid stuff is rejected out of hand
		assertFalse(trns.digitMatches(10, trns.digits[0]));
		assertFalse(trns.digitMatches(-1, trns.digits[0]));
		assertFalse(trns.digitMatches(9, new String[]{}));
		assertFalse(trns.digitMatches(9, new String[]{"", "", "", ""}));
	}

	/**
	 * TEST 2: whether, given a digit in string form, it can be parsed correctly
	 */
	@Test
	void digitScanning() {
		for (int i = 0; i <= 9; i++)
			assertEquals(i, trns.scanDigit(trns.digits[i]));

		assertThrows(OCRException.class, () -> trns.scanDigit(new String[]{"", "", ""}));
		assertThrows(OCRException.class, () -> trns.scanDigit(new String[]{"_", "_", "_"}));
	}

	/**
	 * TEST 3: Make sure we can tokenize digit strings correctly
	 */
	@Test
	void tokenization() {
		String[] str = concatDigits(1, trns.digits[1], trns.digits[7], trns.digits[0], trns.digits[1]);
		ArrayList<String[]> res = trns.tokenize(str);

		// Basic check with no extra whitespace
		assertEquals(4, res.size());
		assertEquals(1, trns.scanDigit(res.get(0)));
		assertEquals(7, trns.scanDigit(res.get(1)));
		assertEquals(0, trns.scanDigit(res.get(2)));
		assertEquals(1, trns.scanDigit(res.get(3)));

		// Now use a ridiculous amount of whitespace and make sure we get the same results
		str = concatDigits(15, trns.digits[8], trns.digits[4], trns.digits[7], trns.digits[2]);
		res = trns.tokenize(str);
		assertEquals(4, res.size());
		assertEquals(8, trns.scanDigit(res.get(0)));
		assertEquals(4, trns.scanDigit(res.get(1)));
		assertEquals(7, trns.scanDigit(res.get(2)));
		assertEquals(2, trns.scanDigit(res.get(3)));
	}
}
