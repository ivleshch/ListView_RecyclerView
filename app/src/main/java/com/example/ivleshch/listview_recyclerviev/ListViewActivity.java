package com.example.ivleshch.listview_recyclerviev;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {

    private boolean isSwipe = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        final ArrayList<StudentInformation> studentsInformation = informationAboutStudents();

        final ListView listView = (ListView) findViewById(R.id.ListView);
        final ListViewAdapter adapter = new ListViewAdapter(this, studentsInformation);
        listView.setAdapter(adapter);

        listView.setOnTouchListener(new OnSwipeTouchListener(ListViewActivity.this) {

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
                    StudentInformation studentinformation = (StudentInformation) parent.getAdapter().getItem(position);
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(studentinformation.getLinkToGoogle()));
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

    public ArrayList informationAboutStudents() {
        ArrayList<StudentInformation> studentsInformation = new ArrayList<>();
        studentsInformation.add(new StudentInformation("Евгений Жданов", "https://github.com/zhdanov-ek", "https://plus.google.com/u/0/113264746064942658029"));
        studentsInformation.add(new StudentInformation("Edgar Khimich", "https://github.com/lyfm", "https://plus.google.com/u/0/102197104589432395674"));
        studentsInformation.add(new StudentInformation("Alexander Storchak", "https://github.com/new15", "https://plus.google.com/u/0/106553086375805780685"));
        studentsInformation.add(new StudentInformation("Yevhenii Sytnyk", "https://github.com/YevheniiSytnyk", "https://plus.google.com/u/0/101427598085441575303"));
        studentsInformation.add(new StudentInformation("Alyona Prelestnaya", "https://github.com/HelenCool", "https://plus.google.com/u/0/107382407687723634701"));
        studentsInformation.add(new StudentInformation("Богдан Рибак", "https://github.com/BogdanRybak1996", "https://plus.google.com/u/0/103145064185261665176"));
        studentsInformation.add(new StudentInformation("Ірина Смалько", "https://github.com/IraSmalko", "https://plus.google.com/u/0/113994208318508685327"));
        studentsInformation.add(new StudentInformation("Владислав Винник", "https://github.com/vlads0n", "https://plus.google.com/u/0/117765348335292685488"));
        studentsInformation.add(new StudentInformation("Ігор Пахаренко", "https://github.com/IhorPakharenko", "https://plus.google.com/u/0/108231952557339738781"));
        studentsInformation.add(new StudentInformation("Андрей Рябко", "https://github.com/RyabkoAndrew", "https://plus.google.com/u/0/110288437168771810002"));
        studentsInformation.add(new StudentInformation("Ivan Leshchenko", "https://github.com/ivleshch", "https://plus.google.com/u/0/111088051831122657934"));
        studentsInformation.add(new StudentInformation("Микола Піхманець", "https://github.com/NikPikhmanets", "https://plus.google.com/u/0/110087894894730430086"));
        studentsInformation.add(new StudentInformation("Ruslan Migal", "https://github.com/rmigal", "https://plus.google.com/u/0/106331812587299981536"));
        studentsInformation.add(new StudentInformation("Руслан Воловик", "https://github.com/RuslanVolovyk", "https://plus.google.com/u/0/109719711261293841416"));
        studentsInformation.add(new StudentInformation("Valerii Gubskyi", "https://github.com/gvv-ua", "https://plus.google.com/u/0/107910188078571144657"));
        studentsInformation.add(new StudentInformation("Иван Сергеенко", "https://github.com/dogfight81", "https://plus.google.com/u/0/111389859649705526831"));
        studentsInformation.add(new StudentInformation("Вова Лымарь", "https://github.com/VovanNec", "https://plus.google.com/u/0/109227554979939957830"));
        studentsInformation.add(new StudentInformation("Даша Кириченко", "https://github.com/dashakdsr", "https://plus.google.com/u/0/103130382244571139113"));
        studentsInformation.add(new StudentInformation("Michael Tyoply", "https://github.com/RedGeekPanda", "https://plus.google.com/u/0/110313151428733681846"));
        studentsInformation.add(new StudentInformation("Павел Сакуров", "https://github.com/sakurov", "https://plus.google.com/u/0/108482088578879737406"));
        return studentsInformation;
    }

    public void OpenRecyclerActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), RecyclerViewActivity.class);
        startActivity(intent);
    }


}


