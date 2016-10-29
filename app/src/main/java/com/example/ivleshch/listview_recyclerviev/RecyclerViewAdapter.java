package com.example.ivleshch.listview_recyclerviev;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ivleshch on 27.10.2016.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CustomViewHolder> {

    Context context;
    ArrayList<StudentInformation> studentsInformation;
    LayoutInflater inflater;

    public RecyclerViewAdapter(Context context, ArrayList<StudentInformation> studentsInformation) {
        this.context = context;
        this.studentsInformation = studentsInformation;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return studentsInformation.size();
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_list_view, parent, false);
        return new CustomViewHolder(view);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder  {

        LinearLayout linearLayout;
        TextView Name;
        Button linkToGit;

        CustomViewHolder(View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.item);
            Name = (TextView) itemView.findViewById(R.id.Name);
            linkToGit = (Button) itemView.findViewById(R.id.Git);
        }
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        final StudentInformation studentInformation = studentsInformation.get(position);
        holder.Name.setText(studentInformation.getName());
        holder.linkToGit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(studentInformation.getLinkToGit())));
            }
        });

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(studentInformation.getLinkToGoogle())));
            }
        });
    }
}

