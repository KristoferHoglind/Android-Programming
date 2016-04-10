package com.example.stoffesbok.projekt;

import java.util.regex.Pattern;

/**
 * A class to decide whenever the conditions that PasswordStrengthAlgorithm defines
 * fulfills.
 * @see PasswordStrengthAlgorithm
 */
public class PasswordAnalyzer {

    // Private variables

    /**
     * The input password query.
     */
    private String password;

    /**
     * Default constructor for the PasswordStrengthAnalyzer.
     * <p>
     * PasswordStrengthAnalyzer analyzes the input query (password-string) and returns true/false of each passed argument.
     *
     * @return a PasswordStrengthAnalyzer object.
     * @see PasswordStrengthMeter, PasswordStrengthView, PasswordAlgorithm
     */
    public PasswordAnalyzer() { }

    /**
     * Checks if the query contains a number
     *
     * @param query the password string from the EditText.
     * @return a true or false statement.
     */
    public boolean containsNumber(String query)
    {
        if(query.matches(".*\\d+.*"))
        {
            //System.out.println("Contains a NUMBER");
            return true;
        }
        else
        {
            //System.out.println("Does NOT contain NUMBER");
            return false;
        }
    }

    /**
     * Checks if the query contains a lower- & an uppercase character
     *
     * @param query the password string from the EditText.
     * @return a true or false statement.
     */
    public boolean containsLowerUpperCase(String query)
    {
        boolean hasLowercase = !query.equals(query.toUpperCase());
        boolean hasUppercase = !query.equals(query.toLowerCase());

        if(hasLowercase && hasUppercase)
        {
            //System.out.println("Contains at least an LOWER- & UPPERCASE character.");
            return true;
        }
        else
        {
            //System.out.println("Does NOT contain any LOWER- & UPPERCASE characters.");
            return false;
        }
    }

    /**
     * Checks if the query contains a 'special' character
     *
     * @param query the password string from the EditText.
     * @return a true or false statement.
     */
    public boolean containsSpecChar(String query)
    {
        String special = "!@#$%^&*()_/?<>[]=";
        String pattern = ".*[" + Pattern.quote(special) + "].*";

        if(query.matches(pattern))
        {
            //System.out.println("Contains SPEC char");
            return true;
        }
        else
        {
            //System.out.println("Does NOT contain SPEC char");
            return false;
        }
    }

    /**
     * Checks if the query contains at least a set amount of characters
     *
     * @param query the password string from the EditText.
     * @param minRequiredLength the minimum amount of characters of the password string.
     * @return a true or false statement.
     */
    public boolean isMinLength(String query, int minRequiredLength)
    {
        if(query.length() >= minRequiredLength)
        {
            //System.out.println("qLength > reqLength = TRUE: MINIMUM LENGTH = " + query.length() + "minRequiredLength = " + minRequiredLength);
            return true;
        }
        else
        {
            //System.out.println("qLength > reqLength = FALSE: MINIMUM LENGTH = " + query.length() + "minRequiredLength = " + minRequiredLength);
            return false;
        }
    }
}
