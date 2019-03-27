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

/**
 * This class has a single method that will translate OCR digits to a string of
 * text digits that correspond to the OCR digits.
 * 
 * @version Mar 13, 2019
 */
public class OCRTranslator
{
	final String[][] digits;

	/**
	 * Default constructor. You may not add parameters to this. This is
	 * the only constructor for this class and is what the master tests will use.
	 */
	public OCRTranslator()
	{
		digits = new String[][]{
				{
						" _ ",
						"| |",
						"|_|"
				},
				{
						" ",
						"|",
						"|"
				},
				{
						" _ ",
						" _|",
						"|_ "
				},
				{
						" _ ",
						" _|",
						" _|"
				},
				{
						"   ",
						"|_|",
						"  |"
				},
				{
						" _ ",
						"|_ ",
						" _|"
				},
				{
						" _ ",
						"|_ ",
						"|_|"
				},
				{
						"_ ",
						" |",
						" |"
				},
				{
						" _ ",
						"|_|",
						"|_|"
				},
				{
						" _ ",
						"|_|",
						"  |"
				}
		};
	}
	
	/**
	 * Translate a string of OCR digits to a corresponding string of text
	 * digits. OCR digits are represented as three rows of characters (|, _, and space).
	 * @param s1 the top row of the OCR input
	 * @param s2 the middle row of the OCR input
	 * @param s3 the third row of the OCR input
	 * @return a String containing the digits corresponding tot he
	 */
	public String translate(String s1, String s2, String s3)
	{
		// To be implemented
		return null;
	}

	/**
	 * Checks to see if a digit string matches the given diget
	 * @param digit Number to check against, 0-9
	 * @param digitStr Array-format digit string, three elements long and with strings of constant width.
	 * @return True if the digit matches, false otherwise.
	 */
	protected boolean digitMatches(final int digit, String[] digitStr) {
		if (digit < 0 || digit > 9)
			return false;
		if (digitStr.length < 1 || digitStr.length > 3 || digitStr[0].length() > 3 || digitStr[0].isEmpty())
			return false;

		final String[] checkDigit = digits[digit];
		for (int i = 0; i < 3; i++) {
			if (!digitStr[i].equals(checkDigit[i]))
				return false;
		}

		return true;
	}

	/**
	 * Scans a string-form digit and returns the corresponding int if something matches
	 * @param digitStr String-form digit, basically just a 3-length array of Strings
	 * @return Digit
	 * @throws OCRException if digit does not match
	 */
	protected int scanDigit(String[] digitStr) {
		for (int i = 0; i <= 9; i++) {
			if (digitMatches(i, digitStr)) {
				return i;
			}
		}

		throw new OCRException("Invalid digit");
	}
}
