package com.example.ivleshch.listview_recyclerviev.git;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ivleshch.listview_recyclerviev.R;
import com.example.ivleshch.listview_recyclerviev.broadcastreceivers.Receivers;
import com.example.ivleshch.listview_recyclerviev.data.MyApplication;
import com.example.ivleshch.listview_recyclerviev.git.data.GitHubService;
import com.example.ivleshch.listview_recyclerviev.git.data.GitModel;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class StudentDetailActivityGit extends AppCompatActivity {
    String name;
    String login;
    String linkToGit;

    String public_repos;
    String email;
    String avatar_url;
    String repos_url;
    String location;
    public final static String API = "https://api.github.com";

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


    private void doGetRequest(String... params) throws IOException {

        final String BASE_URL = "http://api.github.com/users/" + params[0];


        OkHttpClient.Builder builderHttp = new OkHttpClient.Builder();
        builderHttp.readTimeout(15, TimeUnit.SECONDS);
        builderHttp.writeTimeout(15, TimeUnit.SECONDS);
        OkHttpClient client = builderHttp.build();

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(API)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
//        Retrofit retrofit = new Retrofit.Builder()
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl(API)
//                .build();

        GitHubService service = retrofit.create(GitHubService.class);
//        Observable<GitModel> githubUser = service.getUser("ivleshch");
        Call<GitModel> call = service.getUser(params[0]);
        call.enqueue(new Callback<GitModel>() {
            @Override
            public void onResponse(Call<GitModel> call, Response<GitModel> response) {
                if (response.isSuccessful()) {
                    public_repos = response.body().getName();
                    email = response.body().getEmail();
                    avatar_url = response.body().getAvatarUrl();
                    repos_url = response.body().getReposUrl();
                    location = response.body().getLocation();
                    if (!openActivityFromApp) {
                        name = response.body().getName();
                    }
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
                } else {
                    openGit.setVisibility(View.INVISIBLE);
                    errorGit.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<GitModel> call, Throwable t) {
                Log.d("Logivleshch", t.toString());
            }


        });
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
