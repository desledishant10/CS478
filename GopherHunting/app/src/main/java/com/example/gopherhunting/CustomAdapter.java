package com.example.gopherhunting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private final int[][] gridData;  // The game grid data
    private LayoutInflater inflater;

    public CustomAdapter(Context context, int[][] gridData) {
        this.context = context;
        this.gridData = gridData;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return gridData.length * gridData[0].length;
    }

    @Override
    public Object getItem(int position) {
        int row = position / gridData[0].length;
        int col = position % gridData[0].length;
        return gridData[row][col];
    }

    @Override
    public long getItemId(int position) {
        return position;  // Use the position as a unique ID
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int row = position / gridData[0].length;
        int col = position % gridData[0].length;

        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.grid_item, parent, false);
            holder = new ViewHolder();
            holder.textView = convertView.findViewById(R.id.grid_item_text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        int itemValue = gridData[row][col];
        updateViewAppearance(holder.textView, itemValue);

        return convertView;
    }

    private void updateViewAppearance(TextView textView, int value) {
        switch (value) {
            case 0:  // Assume 0 is unguessed
                textView.setText("");
                textView.setBackgroundResource(R.drawable.grid_item_default);
                break;
            case 1:  // Assume 1 is a miss
                textView.setText("Miss");
                textView.setBackgroundResource(R.drawable.grid_item_miss);
                break;
            case 2:  // Assume 2 is a near miss
                textView.setText("Near Miss");
                textView.setBackgroundResource(R.drawable.grid_item_near_miss);
                break;
            case 3:  // Assume 3 is a hit
                textView.setText("Hit");
                textView.setBackgroundResource(R.drawable.grid_item_hit);
                break;
            default:
                textView.setText("");
                textView.setBackgroundResource(R.drawable.grid_item_default);
        }
    }

    static class ViewHolder {
        TextView textView;
    }
}
