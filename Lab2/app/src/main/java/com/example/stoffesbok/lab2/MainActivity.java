package com.example.stoffesbok.lab2;

import java.nio.channels.Selector;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<Integer, List<String>> listDataChild;
    EditText textOut;
    boolean isPart = true;
    boolean isComplete = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textOut = (EditText) findViewById(R.id.TextField);
        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.ExpList);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview Group click listener
        /*
        expListView.setOnGroupClickListener(new OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                textOut = (EditText) findViewById(R.id.TextField);
                textOut.setText("");
                return true;
            }
        });
        */
        /*
        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                textOut = (EditText) findViewById(R.id.TextField);
                textOut.setText("");
            }
        });*/

        // Listview Group collasped listener

        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                expListView.setItemChecked(-1, true);
            }
        });


        // Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                textOut = (EditText) findViewById(R.id.TextField);

                textOut.setText("/" +
                                listDataHeader.get(groupPosition)
                                + "/"
                                + listDataChild.get(groupPosition).get(
                                childPosition)
                );

                int index = parent.getFlatListPosition(ExpandableListView.getPackedPositionForChild(groupPosition, childPosition));
                parent.setItemChecked(index, true);

                return true;
            }
        });

        textOut.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                List<String> grp;
                String query = s.toString();
                int childIndex = -1;
                int groupIndex = -1;
                int hashKey;
                String title = "";
                String listItem = "";
                String completeWord = "";
                String completeWordLowercase = "";
                String queryLowercase = "";

                if (!query.isEmpty()) {
                    outerloop:
                    {
                        for (HashMap.Entry<Integer, List<String>> entry : listDataChild.entrySet()) {
                            grp = entry.getValue();
                            hashKey = entry.getKey();
                            groupIndex = hashKey;
                            title = listDataHeader.get(groupIndex);

                            listItem = "";
                            for (int i = 0; i < grp.size(); i++) {

                                listItem = grp.get(i);
                                completeWord = "/" + title + "/" + listItem;
                                completeWordLowercase = completeWord.toLowerCase();
                                queryLowercase = query.toLowerCase();

                                if (completeWordLowercase.equals(queryLowercase)) {
                                    isComplete = true;
                                    isPart = false;
                                    childIndex = i;
                                    break outerloop;
                                } else if (completeWordLowercase.startsWith(queryLowercase)) {
                                    isComplete = false;
                                    isPart = true;
                                    childIndex = i;
                                    break outerloop;
                                } else {
                                    isPart = false;
                                    isComplete = false;
                                    childIndex = -1;
                                    expListView.setItemChecked(-1, true);
                                }
                            }
                        }
                    }

                    if (isComplete) {
                        expListView.expandGroup(groupIndex, true);
                        int index = expListView.getFlatListPosition(ExpandableListView.getPackedPositionForChild(groupIndex, childIndex));
                        expListView.setItemChecked(index, true);
                        textOut.setBackgroundColor(Color.TRANSPARENT);

                        //expListView.expandGroup(index);
                    } else if (isPart) {
                        textOut.setBackgroundColor(Color.TRANSPARENT);
                    } else {
                        textOut.setBackgroundColor(Color.RED);
                    }
                } else {
                    textOut.setBackgroundColor(Color.TRANSPARENT);
                }

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            // TODO: Kolla om den här är lämpligare att använda än afterTextChanged()
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<Integer, List<String>>();

        // Adding child data
        listDataHeader.add("Light");
        listDataHeader.add("Medium");
        listDataHeader.add("Medium");
        listDataHeader.add("Dark");

        // Adding child data
        List<String> light = new ArrayList<String>();
        light.add("White");
        light.add("Pink");
        light.add("Teal");
        light.add("Baby-blue");

        List<String> medium_1 = new ArrayList<String>();
        medium_1.add("Green");
        medium_1.add("Yellow");
        medium_1.add("Red");
        medium_1.add("Blue");

        List<String> medium_2 = new ArrayList<String>();
        medium_2.add("Turquoise");
        medium_2.add("Mint");
        medium_2.add("Purple");
        medium_2.add("Indigo");

        List<String> dark = new ArrayList<String>();
        dark.add("Black");
        dark.add("Gray");
        dark.add("Brown");
        dark.add("Dark-blue");

        listDataChild.put(0, light);
        listDataChild.put(1, medium_1);
        listDataChild.put(2, medium_2);
        listDataChild.put(3, dark);
    }
}