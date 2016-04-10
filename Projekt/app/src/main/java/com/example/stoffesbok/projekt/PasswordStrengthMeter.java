package com.example.stoffesbok.projekt;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

// Reference:
// http://ui-patterns.com/patterns/PasswordStrengthMeter

/**
 * A layout containing custom components to build a password strength meter.
 */
public class PasswordStrengthMeter extends LinearLayout
{
    // Private variables
    /**
     * Context of the component.
     */
    private Context context;

    /**
     * Object to handle input password queries.
     */
    private EditText password = null;

    /**
     * The layout for this component.
     */
    private LinearLayout linearLayout;

    /**
     * The password complexity (score).
     */
    private int complexity;

    /**
     * A listener to connect with the component.
     */
    private EditText listener;

    /**
     * Defines the requirements/criteria for the password.
     */
    private PasswordStrengthAlgorithm passwordStrengthAlgorithm;
    private MyPasswordAlgorithm passwordStrengthAlgorithm_demo;

    /**
     * The view component of the object.
     */
    private PasswordStrengthView passwordStrengthView;

    /**
     * A measure for how complex the password is. Starts at 0 as default.
     */
    private int passwordScore = 0;

    /**
     * The input password string.
     */
    private String passwordQuery;

    /**
     * A demo constructor implementation that returns a PasswordStrengthMeter
     * which can be added to a layout.
     * <p>
     * This constructor only spawns with default values of the strength algorithm
     * and with an EditText-object already linked to it.
     * <p>
     * Only use this constructor if you want a quick and functioning password
     * strength meter and an EditText.
     *
     * @param  _context  the context from a parent is needed ('this' in MainActivity).
     * @return      A PasswordStrengthMeter-object with an EditText-object
     * @see EditText
     */
    public PasswordStrengthMeter(Context _context)
    {
        super(_context);
        this.context = _context;

        // Initialize the strength algorithm
        initPasswordStrengthMeterAlgorithmDefault();

        // Initialize the strength view
        passwordStrengthView = new MyPasswordStrengthView(context);
        passwordStrengthView.setMaxScore(passwordStrengthAlgorithm_demo.getMaxScore());

        // Initialize and setup component layout (editText created here too)
        setupComponentLayout();

        linearLayout.setGravity(Gravity.CENTER);

        // Edit Text listener
        initListener();

        // Send them back to the main
        this.addView(linearLayout);
    }


    /**
     * The PasswordStrengthMeter constructor for creating a strength meter
     * with an already existing EditText in the parent-module.
     * <p>
     * This constructor also allows you to tweak the algorithm of the
     * strength meter to your liking.
     *
     * @param  _context the context from a parent is needed ('this' in MainActivity).
     * @param myPasswordAlgorithm the created algorithm for the password strength complexity.
     * @return      A PasswordStrengthMeter-object which can be added to layout view.
     * @see PasswordStrengthView, PasswordStrengthAlgorithm, PasswordAnalyzer
     */
    public PasswordStrengthMeter(Context _context,
                                 PasswordStrengthAlgorithm myPasswordAlgorithm)
    {
        super(_context);
        this.context = _context;

        // Initialize the strength algorithm
        setAlgorithm(myPasswordAlgorithm);

        // Initialize the strength view
        passwordStrengthView = new MyPasswordStrengthView(context);
        passwordStrengthView.setMaxScore(passwordStrengthAlgorithm.getMaxScore());

        // Initialize and setup component layout (editText created here too)
        setupComponentLayout();

        linearLayout.setGravity(Gravity.CENTER);

        // Edit Text listener
        initListener();

        // Send them back to the main
        this.addView(linearLayout);
    }

    /**
     * The PasswordStrengthMeter constructor for creating a strength meter
     * without an already existing EditText in the parent-module.
     * <p>
     * This constructor also allows you to tweak the algorithm of the
     * strength meter to your liking.
     *
     * @param  _context the context from a parent is needed ('this' in MainActivity).
     * @param  passwordStrengthView the created view for the strength visualization.
     * @return      A PasswordStrengthMeter-object which can be added to layout view.
     * @see PasswordStrengthView, PasswordStrengthAlgorithm, PasswordAnalyzer
     */
    public PasswordStrengthMeter(Context _context,
                                 PasswordStrengthView passwordStrengthView)
    {
        super(_context);
        this.context = _context;

        // Initialize the strength algorithm
        initPasswordStrengthMeterAlgorithmDefault();

        // Initialize the strength view
        setView(passwordStrengthView.getContext(), passwordStrengthAlgorithm_demo.getMaxScore());

        // Initialize and setup component layout (editText created here too)
        setupComponentLayout();

        linearLayout.setGravity(Gravity.CENTER);

        // Edit Text listener
        initListener();

        // Send them back to the main
        this.addView(linearLayout);
    }

    /**
     * The PasswordStrengthMeter constructor for creating a strength meter
     * without an already existing EditText in the parent-module.
     * <p>
     * This constructor also allows you to tweak the algorithm of the
     * strength meter to your liking.
     *
     * @param  _context the context from a parent is needed ('this' in MainActivity).
     * @param passwordStrengthAlgorithm the created algorithm for the password strength complexity.
     * @param  passwordStrengthView the created view for the strength visualization.
     * @return      A PasswordStrengthMeter-object which can be added to layout view.
     * @see PasswordStrengthView, PasswordStrengthAlgorithm, PasswordAnalyzer
     */
    public PasswordStrengthMeter(Context _context,
                                 PasswordStrengthAlgorithm passwordStrengthAlgorithm,
                                 PasswordStrengthView passwordStrengthView)
    {
        super(_context);
        this.context = _context;

        // Initialize the strength algorithm
        setAlgorithm(passwordStrengthAlgorithm);

        // Initialize the strength view
        setView(passwordStrengthView.getContext(), passwordStrengthView.getMaxScore());

        // Initialize and setup component layout (editText created here too)
        setupComponentLayout();

        linearLayout.setGravity(Gravity.CENTER);

        // Edit Text listener
        initListener();

        // Send them back to the main
        this.addView(linearLayout);
    }

    /**
     * The PasswordStrengthMeter constructor for creating a strength meter
     * with an already existing EditText in the parent-module.
     * <p>
     * This constructor also allows you to tweak the algorithm of the
     * strength meter to your liking.
     *
     * @param _editText the editText that is created in the parent module.
     * @param  _context the context from a parent is needed ('this' in MainActivity).
     * @param passwordStrengthAlgorithm the created algorithm for the password strength complexity.
     * @param  passwordStrengthView the created view for the strength visualization.
     * @return      A PasswordStrengthMeter-object which can be added to layout view.
     * @see PasswordStrengthView, PasswordStrengthAlgorithm, PasswordAnalyzer
     */
    public PasswordStrengthMeter(Context _context,
                                 EditText _editText,
                                 PasswordStrengthAlgorithm passwordStrengthAlgorithm,
                                 PasswordStrengthView passwordStrengthView)
    {
        super(_context);
        this.context = _context;
        password = _editText;

        // Initialize the strength algorithm
        setAlgorithm(passwordStrengthAlgorithm);

        // Initialize the strength view
        setView(passwordStrengthView.getContext(), passwordStrengthView.getMaxScore());

        linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setGravity(Gravity.CENTER);

        // Edit Text listener
        initListener();

        // Set parameters for the editText
        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        LinearLayout.LayoutParams passwordParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);

        // Send them back to the main
        linearLayout.addView(passwordStrengthView, passwordParams);
        this.addView(linearLayout);
    }

    /**
     * Default instructions for the constructors without an editText created in the main.
     */
    private void setupComponentLayout()
    {
        // Linear layout for this component
        linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        // Edit text (the password query)
        password = new EditText(context);
        password.setHint("Password...");

        // Set parameters for the editText
        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        LinearLayout.LayoutParams passwordParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);

        //linearLayout.setGravity(Gravity.CENTER);
        // Add password and view to this component
        linearLayout.addView(password, passwordParams);
        linearLayout.addView(passwordStrengthView, passwordParams);
    }

    /**
     * Add a custom view context to the PasswordStrengthMeter.
     * @param _context A custom view context.
     */
    public void setView(Context _context, int _maxScore)
    {
        passwordStrengthView = new MyPasswordStrengthView(_context);
        passwordStrengthView.setMaxScore(_maxScore);
    }

    /**
     * Add a custom algorithm to the PasswordStrengthMeter.
     * @param _my_algorithm A custom algorithm.
     */
    public void setAlgorithm(PasswordStrengthAlgorithm _my_algorithm)
    {
        passwordStrengthAlgorithm = _my_algorithm;
    }

    /**
     * Calculates the complexity of the input query (password-string).
     *
     * @param query the query from the EditText linked to this class by pointer.
     * @return an integer that represents the score of the password calculated to default or user defined arguments.
     */
    private int calculateComplexity(String query)
    {
        complexity = 0;
        if(query.isEmpty())
            System.out.println("Query is empty");
        else
            complexity = passwordStrengthAlgorithm.calculateComplexity(query);

        return complexity;
    }

    /**
     * Initializes the listener to the EditText.
     */
    private void initListener()
    {
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                passwordQuery = s.toString();
                passwordScore = calculateComplexity(passwordQuery);
                System.out.println("Score: " + passwordScore);
                System.out.println("Max score algorithm: " + passwordStrengthAlgorithm.getMaxScore());
                System.out.println("Max score view: " + passwordStrengthView.getMaxScore());
                passwordStrengthView.setScore(passwordScore);
            }
        });
    }

    /**
     * The default arguments put to the strength meter of a password (Only used in the demo constructors)
     */
    private void initPasswordStrengthMeterAlgorithmDefault()
    {
        passwordStrengthAlgorithm_demo = new MyPasswordAlgorithm();
        ArrayList<Integer> levels = new ArrayList<Integer>();
            levels.add(8);
            levels.add(12);

        passwordStrengthAlgorithm_demo.setMinLengthLevels(levels);
        passwordStrengthAlgorithm_demo.setRequireLowerUpperCase(true);
        passwordStrengthAlgorithm_demo.setRequireSpecChar(true);
        passwordStrengthAlgorithm_demo.setRequireNumber(true);
    }
}