package com.example.stoffesbok.projekt;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;

/**
 * Contains a group of components and sets up a structure to hold them.
 * Updates the components (by function calls or listeners).
 */
public class Carousel extends LinearLayout {
    // Private variables

    /**
     * Context.
     */
    private Context context;

    /**
     * Array of all custom view items.
     */
    private ArrayList<CarouselItem> items;

    /**
     * Array of items that will be showed at current page.
     */
    private ArrayList<CarouselItem> currentPageItems;

    /**
     * Array of items that will be showed at current page.
     */
    private GridView gridView;

    /**
     * Adapter to dynamically control the changed data in CarouselItem.
     */
    private GridViewAdapter gridViewAdapter;

    /**
     * Button to turn page backward.
     */
    private ImageButton backward;

    /**
     * Button to turn page forward.
     */
    private ImageButton forward;

    /**
     * Text to show current page/item number.
     */
    private TextView page_number;

    /**
     * Maximum number of items in the list.
     */
    private int MAX_nItems;

    /**
     * Number of items at each shown page/view.
     */
    private int VIEW_nItems;

    /**
     * Maximum number of pages for the given dataset.
     */
    private int MAX_nPages;

    /**
     * Index for which page is currently shown.
     */
    private int current_page;

    /**
     * Index for which image is currently shown.
     */
    private int current_image;

    /**
     * Modes: Letting the buttons switch one item at a time or switch one page at a time.
     */
    public enum mode {
        IMAGE_SWITCH_MODE, PAGE_SWITCH_MODE
    }

    public enum button_position {
        UNDER, OVER
    }

    /**
     * Save the carousel mode that was sent to this class.
     */
    private mode carousel_mode;

    /**
     * Save the chosen button position that was sent to this class.
     */
    private button_position button_position_view;

    /**
     * Creates a wrapper to include several components and align them with layout properties.
     *
     * @param _context     the context that will be connected with the layout.
     * @param _items A list of Carousel items.
     * @param _VIEW_nItems Number of items that will be shown per page/view.
     */
    public Carousel(Context _context, ArrayList<CarouselItem> _items, int _VIEW_nItems, mode _carousel_mode, button_position _button_position_view) {
        super(_context);

        // Init variables
        items = _items;
        context = _context;
        VIEW_nItems = _VIEW_nItems;
        MAX_nItems = items.size();
        carousel_mode = _carousel_mode;
        button_position_view = _button_position_view;

        MAX_nPages = (int) Math.ceil((float) MAX_nItems / (float) VIEW_nItems);
        current_page = 1; // We always start at page 1
        current_image = 1; // We start at image 1
        currentPageItems = new ArrayList<CarouselItem>();

        page_number = new TextView(context);
        page_number.setTextSize(24);
        carouselModeRecognizer();

        // Initialize the component view with components and their adapters
        initComponentView();

        // Initialize the (button) listeners
        initListeners();
    }

    /**
     * Initialize components and add them to the layout/view.
     */
    private void initComponentView() {
        // The whole component layout
        LinearLayout componentLayout = new LinearLayout(context);
        componentLayout.setOrientation(LinearLayout.VERTICAL);

        // The grid view for images
        gridView = new GridView(context);
        gridView.setNumColumns(VIEW_nItems);

        // Adapter functionality (mode)
        carouselModeRecognizer();
        gridViewAdapter = new GridViewAdapter(context, currentPageItems);

        // GridView functionality
        gridView.setAdapter(gridViewAdapter);
        gridView.setGravity(Gravity.CENTER);
        gridView.invalidateViews();

        // Buttons
        backward = new ImageButton(context);
        backward.setImageResource(R.drawable.ic_keyboard_arrow_left_black_24dp);
        forward = new ImageButton(context);
        forward.setImageResource(R.drawable.ic_keyboard_arrow_right_black_24dp);

        // Listener for buttons
        initListeners();

        // Create layout for buttons and textview
        LinearLayout buttonLayout = new LinearLayout(context);
        buttonLayout.setOrientation(LinearLayout.HORIZONTAL);
        buttonLayout.setGravity(Gravity.CENTER);

        // Add buttons and textview to buttonLayout
        buttonLayout.addView(backward);
        buttonLayout.addView(page_number);
        buttonLayout.addView(forward);

        // Add all layouts together
        switch (button_position_view){
            case UNDER:
                componentLayout.addView(gridView);
                componentLayout.addView(buttonLayout);
                break;
            case OVER:
                componentLayout.addView(buttonLayout);
                componentLayout.addView(gridView);
                break;
        }

        this.addView(componentLayout);
    }

    /**
     * Change the image that is displayed on the backward button.
     * @param image_drawable_id index of a drawable resource.
     */
    public void setBackwardImage(int image_drawable_id)
    {
        backward.setImageResource(image_drawable_id);
    }

    /**
     * Change the image that is displayed on the forward button.
     * @param image_drawable_id index of a drawable resource.
     */
    public void setForwardImage(int image_drawable_id)
    {
        forward.setImageResource(image_drawable_id);
    }

    /**
     * Initialize listeners for this component.
     */
    private void initListeners() {
        // Backward-button listener
        backward.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                current_page--;
                current_image--;
                // Start over from last page when hitting
                if (current_page == 0) {
                    current_page = MAX_nPages;
                }

                if (current_image < 1) {
                    current_image = MAX_nItems;
                }

                carouselModeRecognizer();

                // Notify the adapter
                gridViewAdapter.notifyDataSetChanged();
                gridViewAdapter.setItems(currentPageItems);
                gridView.invalidateViews();
            }
        });

        // Forward-button listener
        forward.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                current_page++;
                current_image++;

                if (current_page > MAX_nPages)
                    current_page = 1;

                if (current_image > MAX_nItems)
                    current_image = 1;

                carouselModeRecognizer();

                // Notify the adapter
                gridViewAdapter.notifyDataSetChanged();
                gridViewAdapter.setItems(currentPageItems);
                gridView.invalidateViews();
            }
        });
    }

    /**
     * Get VIEW_nItems at page _page_index. Assumes page starting index is 1.
     *
     * @param _page_index Index for current page.
     */
    private void updateImagesAtPage(int _page_index) {
        // Calculate the first and last image index from the page index
        int firstImageIndex = (_page_index - 1) * VIEW_nItems;
        int lastImageIndex = _page_index * VIEW_nItems - 1;

        // Clear the "buffer", the current items in the list
        currentPageItems.clear();

        // Dismiss if _page_index too big
        if (_page_index <= MAX_nPages) {
            // Check if miscalculation
            if (firstImageIndex >= MAX_nItems) {
                System.out.println("Miscalculation! : firstImageIndex >= MAX_nItems :" + firstImageIndex + ">=" + MAX_nItems);
                System.out.println("...keeping the old images in the buffer...");
            } else {
                // If lastImageIndex is bigger than max value, set to max value
                if (lastImageIndex >= MAX_nItems)
                    lastImageIndex = (MAX_nItems - 1);

                // Add the images at the current page to the currentPageItems list
                for (int i = firstImageIndex; i <= lastImageIndex; i++) {
                    currentPageItems.add(items.get(i));
                }
            }
        } else {
            System.out.println("Page index is toooo big!!");
        }
    }

    /**
     * Changes images when the single image carousel mode is activated.
     *
     * @param _image_index the index of the current image (1 is the first image, not 0).
     */
    private void updateToNextImage(int _image_index)
    {
        int img_index;
        if(VIEW_nItems % 2 == 1)
            img_index = (int) (_image_index - 1 - Math.ceil(VIEW_nItems / 2));
        else
            img_index = (int) (_image_index - Math.ceil(VIEW_nItems / 2));

        // Clear the "buffer", the current items in the list
        currentPageItems.clear();

        if(img_index > MAX_nItems - 1)
            img_index = 0;

        if(img_index < 0)
            img_index = MAX_nItems + img_index;

        // Add the images at the current page to the currentPageItems list
        for (int i = 0; i < VIEW_nItems; i++)
        {
            currentPageItems.add(items.get(img_index));
            img_index++;
            if(img_index > MAX_nItems - 1) {
                img_index = 0;
            }
        }
    }

    /**
     * This function applies different techniques depending on the chosen carousel mode when creating this object.
     */
    private void carouselModeRecognizer()
    {
        String text;
        switch (carousel_mode)
        {
            case IMAGE_SWITCH_MODE:
                updateToNextImage(current_image);
                text = current_image + "/" + MAX_nItems;
                page_number.setText(text);
                break;
            case PAGE_SWITCH_MODE:
                updateImagesAtPage(current_page);
                text = current_page + "/" + MAX_nPages;
                page_number.setText(text);
                break;
            default:
                updateImagesAtPage(current_page);
                text = current_page + "/" + MAX_nPages;
                page_number.setText(text);
                break;
        }
    }
}
