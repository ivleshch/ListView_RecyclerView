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
import com.example.ivleshch.listview_recyclerviev.data.StudentInformationRealm;
import com.example.ivleshch.listview_recyclerviev.git.StudentDetailActivityGit;
import com.example.ivleshch.listview_recyclerviev.google.StudentDetailActivityGoogle;

import java.util.List;

/**
 * Created by Ivleshch on 27.10.2016.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.UserViewHolder> {

    static private List<StudentInformationRealm> users;
    private LayoutInflater inflater;
    static private Context context;

    public RecyclerViewAdapter(Context context,List<StudentInformationRealm> users) {
        this.users = users;
        this.context = context;
        setHasStableIds(true);
    }

    @Override
    public long getItemId(int position) {
        return users.get(position).getId();
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        return UserViewHolder.create(inflater, parent);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.bind(users.get(position));

    }

    @Override
    public int getItemCount() {
        return users == null ? 0 : users.size();
    }


    static class UserViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        public TextView Name;
        private Button linkToGit;
        private LinearLayout linearLayout;


        public static UserViewHolder create(LayoutInflater inflater, ViewGroup parent) {
            return new UserViewHolder(inflater.inflate(R.layout.item_list_view, parent, false));
        }

        private UserViewHolder(View itemView) {
            super(itemView);
            Name = (TextView) itemView.findViewById(R.id.Name);
            linkToGit = (Button) itemView.findViewById(R.id.Git);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.item);
        }

        public void bind(StudentInformationRealm user) {
            Name.setText(user.getName());
            linkToGit.setOnClickListener(this);
            linearLayout.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            StudentInformationRealm studentInformation = users.get(getAdapterPosition());

            if (view.getId()==R.id.Git) {
                Intent intent = new Intent(context, StudentDetailActivityGit.class)
                        .putExtra("Name", studentInformation.getName())
                        .putExtra("Login", studentInformation.getGitLogin())
                        .putExtra("LinkToGit", studentInformation.getLinkToGit())
                        ;
                context.startActivity(intent);
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

