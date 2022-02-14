package com.example.phonecallbroadcast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CallLog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.phonecallbroadcast.broadcastPhoneNumber.BroadcastPhoneNumber;

public class MainActivity extends AppCompatActivity {

    EditText txtPhoneNumber, txtMessage;
    Button btnSave;
    BroadcastPhoneNumber broadcastPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtPhoneNumber=findViewById(R.id.txtPhoneNumber);
        txtMessage=findViewById(R.id.txtMessage);
        btnSave=findViewById(R.id.btnSave);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BOOT_COMPLETED);
        intentFilter.addAction(Intent.ACTION_NEW_OUTGOING_CALL);
        //intentFilter.addAction(Intent.ACTION_CALL);
        broadcastPhoneNumber = new BroadcastPhoneNumber();
        registerReceiver(broadcastPhoneNumber, intentFilter);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String phoneNumber = txtPhoneNumber.getText().toString();
                String message = txtMessage.getText().toString();





            }
        });

    }


}