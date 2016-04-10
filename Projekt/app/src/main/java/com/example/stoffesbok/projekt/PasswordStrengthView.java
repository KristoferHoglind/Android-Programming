package com.example.stoffesbok.projekt;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.view.View;

/**
 * A view class that defines password strength view properties.
 */
public abstract class PasswordStrengthView extends View
{

    /**
     * Call and get the functions from the superclass (View)
     * @param _context The superclass View needs a context.
     */
    public PasswordStrengthView(Context _context)
    {
        super(_context);
    }

    /**
     * Get maximum score.
     * @return The maximum score.
     */
    public abstract int getMaxScore();

    /**
     * Sets the max score of this view. The amount of defined arguments for the password strength.
     * <p>
     * Needed to make the visualization dynamic to the amount of user defined arguments.
     *
     * @param _maxScore If not user defined; the max score can also be calculated from the PassWordStrengthAlgorithm.
     * @see PasswordStrengthAlgorithm
     */
    public abstract void setMaxScore(int _maxScore);

    /**
     * Set the current score of this view. How many user defined arguments are fulfilled at this point in time?
     * <p>
     * Needed to make the visualization expand and extract when the user changes the input in the EditText.
     *
     * @param _score the score at this point of time. Can be calculated in the PasswordStrengthAlgorithm and added to a listener.
     * @see PasswordStrengthMeter
     */
    public abstract void setScore(int _score);
}
