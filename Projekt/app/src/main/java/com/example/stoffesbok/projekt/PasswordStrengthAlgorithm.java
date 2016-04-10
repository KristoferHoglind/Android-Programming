package com.example.stoffesbok.projekt;

import java.util.ArrayList;

/**
 * Abstract class, act as a parent for child algorithm classes. The child to this class defines the maximum score for the password and
 * calculates the complexity. Extend this class when you want to define your own password algorithm.
 */
public abstract class PasswordStrengthAlgorithm {

    /**
     * Calculate the complexity of the given input query (password-string)
     * @param password The password string.
     * @return A complexity score of the password. The score is dependant on user defined arguments.
     */
    public abstract int calculateComplexity(String password);

    /**
     * Set the maximum score for the password strength.
     * @param maxScore Maximum score value.
     */
    public abstract void setMaxScore(int maxScore);

    /**
     * Get the maximum score from the user defined arguments.
     *
     * @return The maximum score of the password strength.
     */
    public abstract int getMaxScore();
}
