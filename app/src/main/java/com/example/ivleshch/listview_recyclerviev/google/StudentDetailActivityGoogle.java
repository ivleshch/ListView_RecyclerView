package com.example.ivleshch.listview_recyclerviev.google;

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

import com.example.ivleshch.listview_recyclerviev.BuildConfig;
import com.example.ivleshch.listview_recyclerviev.R;
import com.example.ivleshch.listview_recyclerviev.broadcastreceivers.Receivers;
import com.example.ivleshch.listview_recyclerviev.data.MyApplication;
import com.example.ivleshch.listview_recyclerviev.google.data.GoogleModel;
import com.example.ivleshch.listview_recyclerviev.google.data.GoogleService;
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

public class StudentDetailActivityGoogle extends AppCompatActivity {

    String name;
    String id;
    String linkToGoogle;

    String gender;
    String birthday;
    int circle;
    String imageUrl;

    Boolean openActivityFromApp;

    TextView nameGoogle, birthdayGoogle, gendrGoogle, errorGoogle;
    Button openGoogle;
    ImageView imgView;

    public final static String API = "https://www.googleapis.com";

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
        registerReceiver(receiver, receiverFilter);
        MyApplication.activityResumed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
        MyApplication.activityPaused();
    }

    void doGetRequest(String... params) throws IOException{

        final String BASE_URL = "https://www.googleapis.com/plus/v1/people/" + params[0] + "?key=" + BuildConfig.GOOGLE_API_KEY;

        OkHttpClient.Builder builderHttp = new OkHttpClient.Builder();
        builderHttp.readTimeout(15, TimeUnit.SECONDS);
        builderHttp.writeTimeout(15, TimeUnit.SECONDS);
        OkHttpClient client = builderHttp.build();

//        final Gson gson = new GsonBuilder()
//                .setExclusionStrategies(new ExclusionStrategy() {
//                    @Override
//                    public boolean shouldSkipField(FieldAttributes f) {
//                        return f.getDeclaringClass().equals(GoogleModel.class);
//                    }
//                    @Override
//                    public boolean shouldSkipClass(Class<?> clazz) {
//                        return false;
//                    }
//                })
//                .create();

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

        GoogleService service = retrofit.create(GoogleService.class);
        Call<GoogleModel> call = service.getUser(params[0], BuildConfig.GOOGLE_API_KEY);

        call.enqueue(new Callback<GoogleModel>() {
            @Override
            public void onResponse(Call<GoogleModel> call, Response<GoogleModel> response) {

                Log.v("Retrofit", "OnResponse!");

                if (response.isSuccessful()) {

                    Log.v("Retrofit", "Success!");

                    birthday = response.body().getBirthday();
                    gender = response.body().getGender();
                    String urlWithParam = response.body().getImage().getUrl();
                    imageUrl = urlWithParam.substring(0, urlWithParam.lastIndexOf("?")) + "?sz=200";
                    if (!openActivityFromApp) {
                        name = response.body().getDisplayName();
                    }

                    circle = response.body().getCircledByCount();
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
                } else {

                    Log.v("Retrofit", "Not Success!");

                    openGoogle.setVisibility(View.INVISIBLE);
                    errorGoogle.setVisibility(View.VISIBLE);
                }

                Log.v("Retrofit", name + ":" + imageUrl);
            }

            @Override
            public void onFailure(Call<GoogleModel> call, Throwable t) {
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
