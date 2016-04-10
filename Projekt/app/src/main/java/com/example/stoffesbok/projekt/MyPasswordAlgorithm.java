package com.example.stoffesbok.projekt;

import java.util.ArrayList;

public class MyPasswordAlgorithm extends PasswordStrengthAlgorithm{

    // Private variables
    /**
     * Object to analyze the password.
     */
    PasswordAnalyzer analyzer;

    /**
     * Maximum score of complexity.
     */
    private int maxScore;

    /**
     * Array of minimum password length levels.
     */
    private ArrayList<Integer> minLengthLevels;

    /**
     * To decide whenever the password has to contains lower case character.
     */
    private boolean lowerUpperCaseRequired;

    /**
     * Decides if password contains numbers.
     */
    private boolean numberRequired;

    /**
     * Decides if password contains special characters.
     */
    private boolean specialCharReq;


    /**
     * A measure value for the complexity of the password.
     */
    private int score = 0;

    /**
     * Default constructor for the PasswordStrengthAlgorithm.
     * <p>
     * All the user defined arguments are calculated here.
     *
     * @return a PasswordStrengthAlgorithm object.
     * @see PasswordStrengthMeter, PasswordStrengthView, PasswordAnalyzer
     */
    public MyPasswordAlgorithm()
    {
        analyzer = new PasswordAnalyzer();
        maxScore = 0;
    }

    @Override
    public int calculateComplexity(String password)
    {
        score = 0;

        // Check lower-/uppercase
        if(analyzer.containsLowerUpperCase(password))
            score++;

        // Check levels
        for(int i = 0; i < minLengthLevels.size(); i++)
        {
            if(analyzer.isMinLength(password, minLengthLevels.get(i)))
                score++;
        }

        // Check special characters
        if(analyzer.containsSpecChar(password))
            score++;

        // Check numbers
        if(analyzer.containsNumber(password))
            score++;

        // Return the score
        return score;
    }

    @Override
    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    /**
     * Get the score of the input query (password strength/complexity).
     *
     * @return The score of the password strength.
     */
    public int getComplexity()
    {
        return score;
    }

    @Override
    public int getMaxScore()
    {
        return maxScore;
    }

    /**
     * Sets a minimum length of the input query (password-string) for better complexity
     *
     * @param levels User defined levels for each strength level.
     */
    public void setMinLengthLevels(ArrayList<Integer> levels)
    {
        minLengthLevels = levels;
        maxScore += minLengthLevels.size();
    }

    /**
     * Sets that the password will require lowercase characters for better complexity.
     *
     * @param isLower Decides if the password will contain lower case characters.
     */
    public void setRequireLowerUpperCase(boolean isLower)
    {
        this.lowerUpperCaseRequired = isLower;
        maxScore += (lowerUpperCaseRequired) ? 1 : 0;
    }

    /**
     * Sets that the password will require digit (numbers) characters for better complexity.
     *
     * @param isNumber Decides if the password will contain numbers.
     */
    public void setRequireNumber(boolean isNumber)
    {
        numberRequired = isNumber;
        maxScore += (numberRequired) ? 1 : 0;
    }

    /**
     * Sets that the password will require 'special' characters for better complexity.
     * @param isSpecialChar Decides if the password will contain special character.
     */
    public void setRequireSpecChar(boolean isSpecialChar)
    {
        specialCharReq = isSpecialChar;
        maxScore += (specialCharReq) ? 1 : 0;
    }

}
