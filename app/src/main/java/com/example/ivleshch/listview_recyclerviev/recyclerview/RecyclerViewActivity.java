package com.example.ivleshch.listview_recyclerviev.recyclerview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ivleshch.listview_recyclerviev.R;
import com.example.ivleshch.listview_recyclerviev.data.StudentInformation;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;

import static com.example.ivleshch.listview_recyclerviev.data.MainActivity.informationAboutStudents;

/**
 * Created by Ivleshch on 27.10.2016.
 */
public class RecyclerViewActivity extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_recycler_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final ArrayList<StudentInformation> studentsInformation = informationAboutStudents();

        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.RecyclerView);
        final RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity(), studentsInformation);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity()).build());

        ItemTouchHelper.SimpleCallback mIth = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView,RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                final int fromPos = viewHolder.getAdapterPosition();
                final StudentInformation studentInformation= studentsInformation.get(fromPos);
                studentsInformation.remove(studentInformation);
                adapter.notifyItemRemoved(fromPos);


                Snackbar snackbar = Snackbar.make(recyclerView, studentInformation.getName() + " deleted", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        studentsInformation.add(fromPos, studentInformation);
                        adapter.notifyDataSetChanged();
                    }
                });
                snackbar.show();

            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(mIth);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}
