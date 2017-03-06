package com.example.sumeet.todolist_try1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Sumeet on 22-02-2017.
 */

public class AdapterList extends BaseAdapter {
    private Context context;
    List<dataContents> dataContentsList;

    public AdapterList() {
    }

    public AdapterList(Context context, List<dataContents> dataContentsList) {
        this.context = context;
        this.dataContentsList = dataContentsList;
    }


    @Override
    public int getCount() {
        return dataContentsList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.home_list_rows, parent, false);
        }

        TextView text1 = (TextView) convertView.findViewById(R.id.tv_duedate_top);
        TextView text2 = (TextView) convertView.findViewById(R.id.tv_tittle);
        TextView text3 = (TextView) convertView.findViewById(R.id.tv_description);
        TextView text4 = (TextView) convertView.findViewById(R.id.et_date);
        ImageView img = (ImageView) convertView.findViewById(R.id.done);
        int status;

        text1.setText(dataContentsList.get(position).getKey_Date());
        text2.setText(dataContentsList.get(position).getKey_Tittle());
        text3.setText(dataContentsList.get(position).getKey_Description());
        text4.setText(dataContentsList.get(position).getKey_Date());

        switch (dataContentsList.get(position).getKey_Status()) {
            case 0:
                img.setImageResource(R.drawable.incomplete);
                break;
            case 1:
                img.setImageResource(R.drawable.complete);
                break;
            default:
                img.setImageResource(R.mipmap.ic_launcher);
                break;
        }
        return convertView;
    }

    public void updatedList(List<dataContents> list) {
        this.dataContentsList = list;
        this.notifyDataSetChanged();
    }
}
