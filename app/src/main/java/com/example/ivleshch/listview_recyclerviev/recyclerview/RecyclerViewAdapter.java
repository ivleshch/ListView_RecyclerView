package com.example.ivleshch.listview_recyclerviev.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ivleshch.listview_recyclerviev.R;
import com.example.ivleshch.listview_recyclerviev.data.StudentInformation;
import com.example.ivleshch.listview_recyclerviev.git.StudentDetailActivityGit;
import com.example.ivleshch.listview_recyclerviev.google.StudentDetailActivityGoogle;

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

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        final StudentInformation studentInformation = studentsInformation.get(position);
        holder.Name.setText(studentInformation.getName());
//        holder.linkToGit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(studentInformation.getLinkToGit())));
//            }
//        });
//
//        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(studentInformation.getLinkToGoogle())));
//            }
//        });
    }

    class CustomViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{

        LinearLayout linearLayout;
        TextView Name;
        Button linkToGit;

        CustomViewHolder(View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.item);
            Name = (TextView) itemView.findViewById(R.id.Name);
            linkToGit = (Button) itemView.findViewById(R.id.Git);

            linkToGit.setOnClickListener(this);
            linearLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            StudentInformation studentInformation = studentsInformation.get(getAdapterPosition());

            if (view.getId()==R.id.Git) {
                //context.startActivity(new Intent(context, StudentDetailActivityGit.class));
                Intent intent = new Intent(context, StudentDetailActivityGit.class)
                        .putExtra("Name", studentInformation.getName())
                        .putExtra("Login", studentInformation.getGitLogin())
                        .putExtra("LinkToGit", studentInformation.getLinkToGit())
                        ;
                context.startActivity(intent);
//                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(studentInformation.getLinkToGit())));
            }
            if (view.getId()==R.id.item) {
                Intent intent = new Intent(context, StudentDetailActivityGoogle.class)
                        .putExtra("Name", studentInformation.getName())
                        .putExtra("ID", studentInformation.getIdGoogle())
                        .putExtra("LinkToGoogle", studentInformation.getLinkToGoogle())
                        ;
                context.startActivity(intent);


//                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(studentInformation.getLinkToGoogle())));
            }
        }
    }


}

