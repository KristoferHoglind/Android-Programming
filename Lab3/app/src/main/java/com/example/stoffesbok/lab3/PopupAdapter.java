package com.example.stoffesbok.lab3;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

public class PopupAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> searched_names;

    public PopupAdapter(Context _context, ArrayList<String> _searched_names)
    {
        this.context = _context;
        this.searched_names = _searched_names;
    }

    @Override
    public int getCount()
    {
        return searched_names.size();
    }

    @Override
    public Object getItem(int position)
    {
        return searched_names.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        NameComponent nameComponent = new NameComponent(context, searched_names.get(position), position);
        return nameComponent;
    }
}
