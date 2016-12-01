package com.example.ivleshch.listview_recyclerviev.contacts;

import android.Manifest;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ivleshch.listview_recyclerviev.R;
import com.example.ivleshch.listview_recyclerviev.data.ContactsInformation;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;

/**
 * Created by Ivleshch on 24.11.2016.
 */
public class ContactsViewFragment extends Fragment {

    Button buttonAdd;
    EditText inputName;
    EditText inputPhone;

    private final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    private final int PERMISSIONS_REQUEST_WRITE_CONTACTS = 200;

    private  ArrayList<ContactsInformation> contactsInformation;
    private ContactsViewAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_contacts, container, false);
    }

    public void addContact(){
        boolean check = checkEditText();
        if (check){
            String DisplayName = inputName.getText().toString();
            String MobileNumber = inputPhone.getText().toString();

            ArrayList <ContentProviderOperation> ops = new ArrayList <ContentProviderOperation> ();
            ops.add(ContentProviderOperation.newInsert(
                    ContactsContract.RawContacts.CONTENT_URI)
                    .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                    .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                    .build());

            ops.add(ContentProviderOperation.newInsert(
                    ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                    .withValue(ContactsContract.Data.MIMETYPE,
                            ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                    .withValue(
                            ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,
                            DisplayName).build());

            ops.add(ContentProviderOperation.
                    newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                    .withValue(ContactsContract.Data.MIMETYPE,
                            ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, MobileNumber)
                    .withValue(ContactsContract.CommonDataKinds.Phone.TYPE,
                            ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                    .build());

            try {
                getActivity().getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);

                contactsInformation.add(0,new ContactsInformation(DisplayName,MobileNumber));
                adapter.notifyItemInserted(0);
                recyclerView.smoothScrollToPosition(0);


            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        buttonAdd = (Button) getActivity().findViewById(R.id.buttonAdd);
        inputName = (EditText) getActivity().findViewById(R.id.inputName);
        inputPhone = (EditText) getActivity().findViewById(R.id.inputPhone);


        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && getActivity().checkSelfPermission(Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.WRITE_CONTACTS}, PERMISSIONS_REQUEST_WRITE_CONTACTS);
                } else {
                    addContact();
                }

            }
        });

        contactsInformation = contactInformation();

        recyclerView = (RecyclerView) view.findViewById(R.id.contactsList);
        adapter = new ContactsViewAdapter(getActivity(), contactsInformation);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity()).build());
    }


    public boolean checkEditText(){
        boolean returnTrue = true;

        String ed_text_name = inputName.getText().toString().trim();
        String ed_text_phone = inputPhone.getText().toString().trim();

        if(ed_text_name.isEmpty() || ed_text_name.length() == 0 || ed_text_name.equals("") || ed_text_name == null)
        {
            Toast.makeText(getActivity(), "You did not enter a name", Toast.LENGTH_SHORT).show();
            returnTrue = false;
        }
        if(ed_text_phone.isEmpty() || ed_text_phone.length() == 0 || ed_text_phone.equals("") || ed_text_phone == null)
        {
            Toast.makeText(getActivity(), "You did not enter a phone", Toast.LENGTH_SHORT).show();
            returnTrue = false;
        }
        if (returnTrue){

        }
       return returnTrue;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_WRITE_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                addContact();
            } else {
                Toast.makeText(getActivity(), "Need permission", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public ArrayList contactInformation() {
        ArrayList<ContactsInformation> contactInformation = new ArrayList<>();


        ContentResolver cr = getActivity().getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,null, null, null, "DISPLAY_NAME");

        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));

                if (cur.getInt(cur.getColumnIndex(
                        ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));
//                        Toast.makeText(getActivity(), "Name: " + name
//                                + ", Phone No: " + phoneNo, Toast.LENGTH_SHORT).show();
                        contactInformation.add(new ContactsInformation(name,phoneNo));
                    }
                    pCur.close();
                }
            }
        }
        return contactInformation;
    }
}