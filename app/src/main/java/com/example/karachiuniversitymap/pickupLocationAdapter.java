package com.example.karachiuniversitymap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class pickupLocationAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> arrayList = new ArrayList<>();

    public pickupLocationAdapter(Context context, ArrayList<String> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView Ttitle,Tdescription;
        view = LayoutInflater.from(context).inflate(R.layout.customlistrecord, null);

        Ttitle = view.findViewById(R.id.titleview);

        Ttitle.setText(arrayList.get(i));
        return view;
    }
}
