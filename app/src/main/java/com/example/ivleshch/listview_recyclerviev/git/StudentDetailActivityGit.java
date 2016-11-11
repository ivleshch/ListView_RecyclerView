package com.example.ivleshch.listview_recyclerviev.git;

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

public class StudentDetailActivityGit extends AppCompatActivity {
    String name;
    String login;
    String linkToGit;

    String public_repos;
    String email;
    String avatar_url;
    String repos_url;
    String location;

    Boolean openActivityFromApp;

    TextView nameGit, loginGit, errorGit, emailGit, locationGit;
    Button openGit;
    ImageView imgView;

    private IntentFilter receiverFilter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
    private Receivers receiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail_git);

        nameGit = (TextView) findViewById(R.id.NameGit);
        loginGit = (TextView) findViewById(R.id.LoginGit);
        openGit = (Button) findViewById(R.id.detailGit);
        errorGit = (TextView) findViewById(R.id.ErrorGit);
        emailGit = (TextView) findViewById(R.id.emailGit);
        locationGit = (TextView) findViewById(R.id.locationGit);
        imgView = (ImageView) findViewById(R.id.imageViewGit);



        login = "";
        Intent intent = this.getIntent();
        if (intent != null && intent.hasExtra("Name") && intent.hasExtra("Login") && intent.hasExtra("LinkToGit")) {
            openActivityFromApp = true;
            name = intent.getStringExtra("Name");
            login = intent.getStringExtra("Login");
            linkToGit = intent.getStringExtra("LinkToGit");
        } else {
            if (intent != null) {
                openActivityFromApp = false;
                String[] arrayMessage = intent.getData().getPath().split("/");
                if (arrayMessage.length > 1) {
                    login = arrayMessage[1];
                }
            }
        }
        if (login.length() > 0) {
            try {
                doGetRequest(login);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            errorGit.setVisibility(View.VISIBLE);
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        receiver = new Receivers();
        registerReceiver(receiver, receiverFilter);
        MyApplication.activityResumed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
        MyApplication.activityPaused();
    }


    void doGetRequest(String... params) throws IOException {

        final String BASE_URL = "http://api.github.com/users/" + params[0];

        //Uri builtUri = Uri.parse(BASE_URL).buildUpon().build();
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
                                                    GitInfo gitInfo = gson.fromJson(res, GitInfo.class);

                                                    if (gitInfo != null) {

                                                        public_repos = gitInfo.getPublicRepos();
                                                        email = gitInfo.getEmail();
                                                        avatar_url = gitInfo.getAvatarUrl();
                                                        repos_url = gitInfo.getReposUrl();
                                                        location = gitInfo.getLocation();
                                                        if (!openActivityFromApp) {
                                                            name = gitInfo.getName();
                                                        }

                                                        StudentDetailActivityGit.this.runOnUiThread(new Runnable() {
                                                            @Override
                                                            public void run() {
//                                                            ((TextView) findViewById(R.id.repositoryGit)).setText(public_repos);
                                                                if (!openActivityFromApp) {
                                                                    openGit.setOnClickListener(new View.OnClickListener() {
                                                                        @Override
                                                                        public void onClick(View view) {
                                                                            StudentDetailActivityGit.this.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/" + login)));
                                                                        }
                                                                    });

                                                                } else {
                                                                    openGit.setOnClickListener(new View.OnClickListener() {
                                                                        @Override
                                                                        public void onClick(View view) {
                                                                            StudentDetailActivityGit.this.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(linkToGit)));
                                                                        }
                                                                    });
                                                                }
                                                                openGit.setVisibility(View.VISIBLE);
                                                                nameGit.setText(name);
                                                                loginGit.setText("(" + login + ")");
                                                                emailGit.setText(email);
                                                                locationGit.setText(location);
//                                                            ((TextView) findViewById(R.id.email)).setText(email);
//                                                            Picasso.with(StudentDetailActivityGit.this).load(avatar_url).into(imgView);
//                                                            Picasso.with(StudentDetailActivityGit.this).load(avatar_url).fit().into(imgView);
                                                                Transformation transformation = new RoundedTransformationBuilder()
                                                                        .borderColor(Color.BLACK)
                                                                        .borderWidthDp(3)
                                                                        .cornerRadiusDp(30)
                                                                        .oval(false)
                                                                        .build();

                                                                Picasso.with(StudentDetailActivityGit.this)
                                                                        .load(avatar_url)
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
                                                    StudentDetailActivityGit.this.runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            openGit.setVisibility(View.INVISIBLE);
                                                            errorGit.setVisibility(View.VISIBLE);
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
