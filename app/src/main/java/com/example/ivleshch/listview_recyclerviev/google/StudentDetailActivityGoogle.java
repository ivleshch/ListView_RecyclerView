package com.example.ivleshch.listview_recyclerviev.google;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ivleshch.listview_recyclerviev.BuildConfig;
import com.example.ivleshch.listview_recyclerviev.data.MyApplication;
import com.example.ivleshch.listview_recyclerviev.R;
import com.example.ivleshch.listview_recyclerviev.broadcastreceivers.Receivers;
import com.google.gson.Gson;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.IOException;
import java.net.URL;

public class StudentDetailActivityGoogle extends AppCompatActivity {

    String name;
    String id;
    String linkToGoogle;

    String gender;
    String birthday;
    String circle;
    String imageUrl;

    Boolean openActivityFromApp;

    TextView nameGoogle, birthdayGoogle, gendrGoogle, errorGoogle;
    Button openGoogle;
    ImageView imgView;

    private IntentFilter receiverFilter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
    private Receivers receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail_google);

        nameGoogle = (TextView) findViewById(R.id.NameGoogle);
        birthdayGoogle = (TextView) findViewById(R.id.birthdayGogole);
        gendrGoogle = (TextView) findViewById(R.id.genderGoogle);
        errorGoogle = (TextView) findViewById(R.id.ErrorGoogle);
        openGoogle = (Button) findViewById(R.id.detailGoogle);
        imgView = (ImageView) findViewById(R.id.imageViewGoogle);


        id = "";

        Intent intent = this.getIntent();
        if (intent != null && intent.hasExtra("Name") && intent.hasExtra("ID") && intent.hasExtra("LinkToGoogle")) {
            openActivityFromApp = true;
            name = intent.getStringExtra("Name");
            id = intent.getStringExtra("ID");
            linkToGoogle = intent.getStringExtra("LinkToGoogle");
        } else {
            if (intent != null) {
                openActivityFromApp = false;
                String[] arrayMessage = intent.getData().getPath().split("/");
                if (arrayMessage.length > 3) {
                    id = arrayMessage[3];
                }
            }
        }
        if (id.length() > 0) {
            try {
                doGetRequest(id);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            errorGoogle.setVisibility(View.VISIBLE);
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

    void doGetRequest(String... params) throws IOException {

        final String BASE_URL = "https://www.googleapis.com/plus/v1/people/" + params[0] + "?key=" + BuildConfig.GOOGLE_API_KEY;

        URL url = new URL(BASE_URL);


        Request request = new Request.Builder()
                .url(url)
                .build();

        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
                                            @Override
                                            public void onFailure(Request request, IOException e) {

                                            }

                                            @Override
                                            public void onResponse(Response response) throws IOException {

                                                if (response.isSuccessful()) {
                                                    String res = response.body().string();

                                                    Gson gson = new Gson();
                                                    GoogleInfoData googleInfo = gson.fromJson(res, GoogleInfoData.class);

                                                    if (googleInfo != null) {
                                                        int k = 0;
                                                        birthday = googleInfo.getBirthday();
                                                        gender = googleInfo.getGender();
                                                        String urlWithParam = googleInfo.getImage().getUrl();
                                                        imageUrl = urlWithParam.substring(0, urlWithParam.lastIndexOf("?")) + "?sz=200";
                                                        if (!openActivityFromApp) {
                                                            name = googleInfo.getDisplayName();
                                                        }


                                                        circle = googleInfo.getCircledByCount();
                                                        StudentDetailActivityGoogle.this.runOnUiThread(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                nameGoogle.setText(name);
                                                                if (!openActivityFromApp) {
                                                                    openGoogle.setOnClickListener(new View.OnClickListener() {
                                                                        @Override
                                                                        public void onClick(View view) {
                                                                            StudentDetailActivityGoogle.this.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/u/0/" + id)));
                                                                        }
                                                                    });
                                                                } else {
                                                                    openGoogle.setOnClickListener(new View.OnClickListener() {
                                                                        @Override
                                                                        public void onClick(View view) {
                                                                            StudentDetailActivityGoogle.this.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(linkToGoogle)));
                                                                        }
                                                                    });
                                                                }
                                                                openGoogle.setVisibility(View.VISIBLE);
                                                                birthdayGoogle.setText(birthday);
                                                                gendrGoogle.setText(gender);
//                                                                ((TextView) findViewById(R.id.circleGoogle)).setText(circle);
//                                                            ((TextView) findViewById(R.id.email)).setText(email);
//                                                            Picasso.with(StudentDetailActivityGit.this).load(avatar_url).into(imgView);
//                                                                Picasso.with(StudentDetailActivityGoogle.this).load(imageUrl).fit().into(imgView);
                                                                Transformation transformation = new RoundedTransformationBuilder()
                                                                        .borderColor(Color.BLACK)
                                                                        .borderWidthDp(3)
                                                                        .cornerRadiusDp(30)
                                                                        .oval(false)
                                                                        .build();

                                                                Picasso.with(StudentDetailActivityGoogle.this)
                                                                        .load(imageUrl)
                                                                        .fit()
                                                                        .transform(transformation)
                                                                        .into(imgView);
                                                            }
                                                        });
//
//
                                                    }

//
                                                } else {
                                                    StudentDetailActivityGoogle.this.runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            openGoogle.setVisibility(View.INVISIBLE);
                                                            errorGoogle.setVisibility(View.VISIBLE);
                                                        }
                                                    });
                                                }
                                            }
                                        }

        );
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (!openActivityFromApp) {
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
