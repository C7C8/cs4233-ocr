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

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the OCR translator.
 * @version Mar 13, 2019
 */
class OCRTranslatorTest
{
	static OCRTranslator trns;

	@BeforeAll
	static void init() {
		trns = new OCRTranslator();
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
}
