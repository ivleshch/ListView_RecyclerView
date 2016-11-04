package com.example.ivleshch.listview_recyclerviev.listview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ivleshch.listview_recyclerviev.R;
import com.example.ivleshch.listview_recyclerviev.data.StudentInformation;
import com.example.ivleshch.listview_recyclerviev.google.StudentDetailActivityGoogle;

import java.util.ArrayList;

import static com.example.ivleshch.listview_recyclerviev.data.MainActivity.informationAboutStudents;

public class ListViewActivity extends Fragment {

    private boolean isSwipe = false;
    SwitchCompat fragmentsSwitcher;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_list_view, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final ArrayList<StudentInformation> studentsInformation = informationAboutStudents();

        final ListView listView = (ListView) view.findViewById(R.id.ListView);
        final ListViewAdapter adapter = new ListViewAdapter(getActivity(), studentsInformation);
        listView.setAdapter(adapter);

        listView.setOnTouchListener(new OnSwipeTouchListener(getActivity()) {

            @Override
            public void onSwipeLeft() {
                isSwipe = true;
            }

            @Override
            public void onSwipeRight() {
                isSwipe = true;
            }

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(!isSwipe){
                    view.performClick();
                }
                isSwipe = false;
                return super.onTouch(view, motionEvent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                if (!isSwipe) {
                    StudentInformation studentInformation = (StudentInformation) parent.getAdapter().getItem(position);

                    Intent intent = new Intent(getActivity(), StudentDetailActivityGoogle.class)
                            .putExtra("Name", studentInformation.getName())
                            .putExtra("ID", studentInformation.getIdGoogle())
                            .putExtra("LinkToGoogle", studentInformation.getLinkToGoogle())
                            ;
                    startActivity(intent);
                }
                else {
                    final int positionToDelete = position;
                    final StudentInformation studentInformation= studentsInformation.get(positionToDelete);

                    studentsInformation.remove(studentInformation);
                    adapter.notifyDataSetChanged();

                    Snackbar snackbar = Snackbar.make(listView, studentInformation.getName() + " deleted", Snackbar.LENGTH_LONG);
                    snackbar.setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            studentsInformation.add(positionToDelete, studentInformation);
                            adapter.notifyDataSetChanged();
                        }
                    });
                    snackbar.show();


                }

            }
        });
    }
}


