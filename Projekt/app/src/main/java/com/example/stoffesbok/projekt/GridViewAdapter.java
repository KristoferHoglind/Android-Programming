package com.example.stoffesbok.projekt;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Adapter class - adapting a GridView that contains items (images) as data.
 */
public class GridViewAdapter extends BaseAdapter
{
    private Context context;

    /**
     * An ArrayList of CaouselItems (images).
     */
    private ArrayList<CarouselItem> items;

    /**
     * Creates a new GridViewAdapter, wraps the content of CarouselItems
     * @param _context Context from the component (Carousel)
     * @param _items Items that hold the data for the Adapter
     */
    public GridViewAdapter(Context _context, ArrayList<CarouselItem> _items)
    {
        context = _context;
        items = _items;
    }

    /**
     * Sets the data view of CourselItems.
     * @param _items The data.
     */
    public void setItems(ArrayList<CarouselItem> _items)
    {
        items = _items;
    }

    @Override
    public int getCount()
    {
        return items.size();
    }

    /**
     * Get the item.
     * @param position Position for where the item is.
     */
    @Override
    public Object getItem(int position)
    {
        return items.get(position);
    }

    /**
     * Get the item id.
     * @param position Position for where the item is.
     */
    @Override
    public long getItemId(int position)
    {
        return items.get(position).getId();
    }

    /**
     * Get the view.
     * @param position Position for where the view is.
     * @param convertView Not used.
     * @param parent Not used.
     *
     * @see BaseAdapter for better description.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        return items.get(position);
    }
}
