package com.example.stoffesbok.lab2;

import com.example.stoffesbok.lab2.ExpandableListAdapter;

import java.util.HashMap;
import java.util.List;

// TODO: Finish this class and make this concrete
public class TreeContent implements com.example.stoffesbok.lab2.Mediator {

    ExpandableListAdapter adapter;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    public TreeContent(ExpandableListAdapter adapter, List<String> listDataHeader, HashMap<String, List<String>> listDataChild)
    {
        this.adapter = adapter;
        this.listDataHeader = listDataHeader;
        this.listDataChild = listDataChild;
    }
    @Override
    public void search(String query) {
        query = query.toLowerCase();
        for (HashMap.Entry<String, List<String>> entry : listDataChild.entrySet())
        {
            entry.getKey();
        }

    }
}
