package com.example.ivleshch.listview_recyclerviev.data;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.SwitchCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.ivleshch.listview_recyclerviev.R;
import com.example.ivleshch.listview_recyclerviev.broadcastreceivers.Receivers;
import com.example.ivleshch.listview_recyclerviev.contacts.ContactsViewActivity;
import com.example.ivleshch.listview_recyclerviev.listview.ListViewActivity;
import com.example.ivleshch.listview_recyclerviev.recyclerview.RecyclerViewActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListViewActivity listViewActivity = new ListViewActivity();
    private RecyclerViewActivity recyclerViewActivity = new RecyclerViewActivity();
    private SwitchCompat switchChange;
    private IntentFilter receiverFilter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
    private Receivers receiver;
    private AppCompatButton buttonPickGet;
    private final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_list_recycler, listViewActivity)
                    .commit();
        }


        switchChange = (SwitchCompat)findViewById(R.id.switchcompat);

//        buttonPickGet = (AppCompatButton) findViewById(R.id.PickGetImage);

//        buttonPickGet.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, PickGetImageActivity.class);
//                startActivity(intent);
//            }
//        });

        switchChange.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if(checked){
                    switchChange.setText(R.string.textSwitchRecyclerView);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_list_recycler, recyclerViewActivity)
                            .addToBackStack(null)
                            .commit();
                }else{
                    switchChange.setText(R.string.textSwitchListView);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_list_recycler, listViewActivity)
                           .addToBackStack(null)
                            .commit();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.pick_get_image) {
            Intent intent = new Intent(MainActivity.this, PickGetImageActivity.class);
            startActivity(intent);

            return true;
        }
        if (id == R.id.contacts) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && this.checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
            } else {
                Intent intent = new Intent(MainActivity.this, ContactsViewActivity.class);
                startActivity(intent);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent(MainActivity.this, ContactsViewActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Need permission", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        receiver = new Receivers();
        registerReceiver(receiver, receiverFilter );
        MyApplication.activityResumed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
        MyApplication.activityPaused();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        switchChange.setChecked(false);
    }

    public static ArrayList informationAboutStudents() {
        ArrayList<StudentInformation> studentsInformation = new ArrayList<>();
        studentsInformation.add(new StudentInformation("Евгений Жданов"        , "https://github.com/zhdanov-ek"        , "https://plus.google.com/u/0/113264746064942658029", "zhdanov-ek"     ,"113264746064942658029"));
        studentsInformation.add(new StudentInformation("Edgar Khimich"         , "https://github.com/lyfm"              , "https://plus.google.com/u/0/102197104589432395674", "lyfm"           ,"102197104589432395674"));
        studentsInformation.add(new StudentInformation("Alexander Storchak"    , "https://github.com/new15"             , "https://plus.google.com/u/0/106553086375805780685", "new15"          ,"106553086375805780685"));
        studentsInformation.add(new StudentInformation("Yevhenii Sytnyk"       , "https://github.com/YevheniiSytnyk"    , "https://plus.google.com/u/0/101427598085441575303", "YevheniiSytnyk" ,"101427598085441575303"));
        studentsInformation.add(new StudentInformation("Alyona Prelestnaya"    , "https://github.com/HelenCool"         , "https://plus.google.com/u/0/107382407687723634701", "HelenCool"      ,"107382407687723634701"));
        studentsInformation.add(new StudentInformation("Богдан Рибак"          , "https://github.com/BogdanRybak1996"   , "https://plus.google.com/u/0/103145064185261665176", "BogdanRybak1996","103145064185261665176"));
        studentsInformation.add(new StudentInformation("Ірина Смалько"         , "https://github.com/IraSmalko"         , "https://plus.google.com/u/0/113994208318508685327", "IraSmalko"      ,"113994208318508685327"));
        studentsInformation.add(new StudentInformation("Владислав Винник"      , "https://github.com/vlads0n"           , "https://plus.google.com/u/0/117765348335292685488", "vlads0n"        ,"117765348335292685488"));
        studentsInformation.add(new StudentInformation("Ігор Пахаренко"        , "https://github.com/IhorPakharenko"    , "https://plus.google.com/u/0/108231952557339738781", "IhorPakharenko" ,"108231952557339738781"));
        studentsInformation.add(new StudentInformation("Андрей Рябко"          , "https://github.com/RyabkoAndrew"      , "https://plus.google.com/u/0/110288437168771810002", "RyabkoAndrew"   ,"110288437168771810002"));
        studentsInformation.add(new StudentInformation("Ivan Leshchenko"       , "https://github.com/ivleshch"          , "https://plus.google.com/u/0/111088051831122657934", "ivleshch"       ,"111088051831122657934"));
        studentsInformation.add(new StudentInformation("Микола Піхманець"      , "https://github.com/NikPikhmanets"     , "https://plus.google.com/u/0/110087894894730430086", "NikPikhmanets"  ,"110087894894730430086"));
        studentsInformation.add(new StudentInformation("Ruslan Migal"          , "https://github.com/rmigal"            , "https://plus.google.com/u/0/106331812587299981536", "rmigal"         ,"106331812587299981536"));
        studentsInformation.add(new StudentInformation("Руслан Воловик"        , "https://github.com/RuslanVolovyk"     , "https://plus.google.com/u/0/109719711261293841416", "RuslanVolovyk"  ,"109719711261293841416"));
        studentsInformation.add(new StudentInformation("Valerii Gubskyi"       , "https://github.com/gvv-ua"            , "https://plus.google.com/u/0/107910188078571144657", "gvv-ua"         ,"107910188078571144657"));
        studentsInformation.add(new StudentInformation("Иван Сергеенко"        , "https://github.com/dogfight81"        , "https://plus.google.com/u/0/111389859649705526831", "dogfight81"     ,"111389859649705526831"));
        studentsInformation.add(new StudentInformation("Вова Лымарь"           , "https://github.com/VovanNec"          , "https://plus.google.com/u/0/109227554979939957830", "VovanNec"       ,"109227554979939957830"));
        studentsInformation.add(new StudentInformation("Даша Кириченко"        , "https://github.com/dashakdsr"         , "https://plus.google.com/u/0/103130382244571139113", "dashakdsr"      ,"103130382244571139113"));
        studentsInformation.add(new StudentInformation("Michael Tyoply"        , "https://github.com/RedGeekPanda"      , "https://plus.google.com/u/0/110313151428733681846", "RedGeekPanda"   ,"110313151428733681846"));
        studentsInformation.add(new StudentInformation("Павел Сакуров"         , "https://github.com/sakurov"           , "https://plus.google.com/u/0/108482088578879737406", "sakurov"        ,"108482088578879737406"));
        return studentsInformation;
    }
}
