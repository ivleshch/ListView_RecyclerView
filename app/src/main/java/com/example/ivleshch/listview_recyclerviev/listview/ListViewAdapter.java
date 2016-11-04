package com.example.ivleshch.listview_recyclerviev.listview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.ivleshch.listview_recyclerviev.R;
import com.example.ivleshch.listview_recyclerviev.data.StudentInformation;
import com.example.ivleshch.listview_recyclerviev.git.StudentDetailActivityGit;

import java.util.ArrayList;

/**
 * Created by Ivleshch on 27.10.2016.
 */
public class ListViewAdapter extends BaseAdapter {

    Context context;
    ArrayList<StudentInformation> studentsInformation;

    public ListViewAdapter(Context context, ArrayList<StudentInformation> studentsInformation) {
        this.context = context;
        this.studentsInformation = studentsInformation;
    }

    @Override
    public int getCount() {
        return studentsInformation.size();
    }

    @Override
    public Object getItem(int i) {
        return studentsInformation.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View rowView  = view;

        if (rowView  == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView  = inflater.inflate(R.layout.item_list_view, parent, false);
        }

        final StudentInformation studentInformation = (StudentInformation) getItem(position);

        TextView Name = (TextView) rowView.findViewById(R.id.Name);
        Button linkToGit = (Button) rowView.findViewById(R.id.Git);
        Name.setText(studentInformation.getName());

//        rowView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context, "Touch", Toast.LENGTH_SHORT).show();
//            }
//        });

        linkToGit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, StudentDetailActivityGit.class)
                        .putExtra("Name", studentInformation.getName())
                        .putExtra("Login", studentInformation.getGitLogin())
                        .putExtra("LinkToGit", studentInformation.getLinkToGit())
                        ;
                context.startActivity(intent);
            }
        });
        return rowView ;
    }
}

