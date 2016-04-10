package com.example.stoffesbok.projekt;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create layout
        ScrollView scrollView = new ScrollView(this);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setPadding(40, 40, 40, 40);

        // Create an EditText
        EditText editText = new EditText(this);
            editText.setHint("Password");
            editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        // Password Strength meter
        ArrayList<Integer> levels = new ArrayList<>();
            levels.add(8);
            levels.add(12);
            levels.add(16);
        Boolean requireLowerUppercase = true;
        Boolean requireSpecialCharacter = true;
        Boolean requireNumber = true;

        // Create a custom Password Strength algorithm
        MyPasswordAlgorithm myPasswordAlgorithm = new MyPasswordAlgorithm();
            myPasswordAlgorithm.setMinLengthLevels(levels);
            myPasswordAlgorithm.setRequireLowerUpperCase(requireLowerUppercase);
            myPasswordAlgorithm.setRequireSpecChar(requireSpecialCharacter);
            myPasswordAlgorithm.setRequireNumber(requireNumber);

        // Create a custom Password Strength view
        MyPasswordStrengthView myPasswordStrengthView = new MyPasswordStrengthView(this);
            myPasswordStrengthView.setMaxScore(myPasswordAlgorithm.getMaxScore());

        // Create a Password strength meter
        final PasswordStrengthMeter passwordStrengthMeter = new PasswordStrengthMeter(this, editText, myPasswordAlgorithm, myPasswordStrengthView);

        // Prepare items for the carousel
        ArrayList<String> image_names = new ArrayList<String>();
        image_names = prepareCarousel();
        ArrayList<CarouselItem> items = new ArrayList<CarouselItem>();
        items = createItems(image_names);

        // Create a Carousel with our items
        Carousel carousel = new Carousel(this, items, 4, Carousel.mode.IMAGE_SWITCH_MODE, Carousel.button_position.OVER);

        // Parameters
        LinearLayout.LayoutParams passwordStrengthMeterParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

        LinearLayout.LayoutParams carouselParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

        // Change image on buttons in the carousel, if wanted/needed...
        //carousel.setBackwardImage(R.drawable.ic_backup_black_24dp);
        //carousel.setForwardImage(R.drawable.ic_backup_black_24dp);

        // Test/Dummy button
        Button testButton = new Button(this);
            testButton.setText("Dummy Button!");

        // Add to view
        linearLayout.addView(passwordStrengthMeter, passwordStrengthMeterParams);
        linearLayout.addView(editText);
        linearLayout.addView(testButton);
        linearLayout.addView(carousel, carouselParams);

        // Set view
        scrollView.addView(linearLayout);
        setContentView(scrollView);
    }

    // Load all the images in any convenient way...
    ArrayList<String> prepareCarousel()
    {
        String origin_path = "/storage/emulated/0/Images_tddd13/";

        ArrayList<String> image_names = new ArrayList<String>();
            image_names.add(origin_path + "beach.jpg");
            image_names.add(origin_path + "bird.jpg");
            image_names.add(origin_path + "blue_bird.jpg");
            image_names.add(origin_path + "dog_bubbles.jpg");
            image_names.add(origin_path + "field.jpg");
            image_names.add(origin_path + "hero_dog.jpg");
            image_names.add(origin_path + "many_birds.jpg");
            image_names.add(origin_path + "math.jpg");
            image_names.add(origin_path + "polarbear.jpg");
            image_names.add(origin_path + "rose.jpg");
            image_names.add(origin_path + "space_tiger.jpg");
            image_names.add(origin_path + "sunflower.jpg");
            image_names.add(origin_path + "sunset.jpg");
            image_names.add(origin_path + "tiger.jpg");
            image_names.add(origin_path + "turtle.jpg");

        return image_names;
    }

    // Create an item list
    private ArrayList<CarouselItem> createItems(ArrayList<String> _image_path_names)
    {
        ArrayList<CarouselItem> items = new ArrayList<CarouselItem>();
        for (int i = 0; i < _image_path_names.size(); i++) {
            // Decode image to bitmap
            Bitmap temp_image = BitmapFactory.decodeFile(_image_path_names.get(i));

            // Check if image is read correctly, ignore it if it didn't
            if (temp_image == null) {
                System.out.println("NULL!");
                System.out.println("NULL!: " + _image_path_names.get(i));
                continue;
            }

            // Add the image to an ArrayList of items
            CarouselItem temp_item = new CarouselItem(this, temp_image);
            items.add(temp_item);
        }

        return items;
    }
}
