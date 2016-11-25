package com.example.ivleshch.listview_recyclerviev.contacts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ivleshch.listview_recyclerviev.R;
import com.example.ivleshch.listview_recyclerviev.data.ContactsInformation;

import java.util.ArrayList;

/**
 * Created by Ivleshch on 24.11.2016.
 */

public class ContactsViewAdapter extends RecyclerView.Adapter<ContactsViewAdapter.CustomViewHolder> {

    Context context;
    ArrayList<ContactsInformation> contactsInformations;
    LayoutInflater inflater;

    public ContactsViewAdapter(Context context, ArrayList<ContactsInformation> contactsInformations) {
        this.context = context;
        this.contactsInformations = contactsInformations;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return contactsInformations.size();
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_list_contact, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        final ContactsInformation contactsInformation= contactsInformations.get(position);
        holder.Name.setText(contactsInformation.getContactName());
        holder.phoneNumber.setText(contactsInformation.getPhoneNumber());
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
        TextView phoneNumber;

        CustomViewHolder(View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.itemContact);
            Name = (TextView) itemView.findViewById(R.id.contactName);
            phoneNumber =(TextView) itemView.findViewById(R.id.contactNumber);

//            linkToGit.setOnClickListener(this);
            linearLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }


}
