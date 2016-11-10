package com.example.ivleshch.listview_recyclerviev.data;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.ivleshch.listview_recyclerviev.R;
import com.example.ivleshch.listview_recyclerviev.broadcastreceivers.Receivers;

public class PickGetImageActivity extends AppCompatActivity {

    Button buttonPickGet;
    ImageView imgView;

    private IntentFilter receiverFilter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
    private Receivers receiver = new Receivers();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_get_image);

        buttonPickGet = (Button) findViewById(R.id.buttonViewSelect);
        imgView = (ImageView) findViewById(R.id.imageViewSelect);

        buttonPickGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });

    }

    private void selectImage() {
        final CharSequence[] options = {"Camera", "Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(PickGetImageActivity.this, R.style.MyAlertDialogStyle);
        builder.setTitle("Add image");
        //builder.s

        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Camera")) {
                    Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);
                } else if (options[item].equals("Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, 1);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case 0:
                if (resultCode == RESULT_OK) {
                    Bitmap thumbnail = (Bitmap) imageReturnedIntent.getExtras().get("data");
                    imgView.setImageBitmap(thumbnail);
                }
                break;
            case 1:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = imageReturnedIntent.getData();
                    imgView.setImageURI(selectedImage);
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, receiverFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }
}
