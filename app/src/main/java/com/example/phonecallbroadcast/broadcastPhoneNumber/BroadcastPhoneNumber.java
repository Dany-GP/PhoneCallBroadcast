package com.example.phonecallbroadcast.broadcastPhoneNumber;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class BroadcastPhoneNumber extends BroadcastReceiver {

    String phoneNumber;

    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)){
            phoneNumber=intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
        }else{

            TelephonyManager telMang=(TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);
            switch (telMang.getCallState()){
                case TelephonyManager.CALL_STATE_RINGING:{
                    phoneNumber=intent.getStringExtra("incoming_number");
                    Toast.makeText(context.getApplicationContext(), phoneNumber, Toast.LENGTH_SHORT).show();
                    break;
                }

                case TelephonyManager.CALL_STATE_OFFHOOK:{

                    break;
                }

                case TelephonyManager.CALL_STATE_IDLE:{

                    break;
                }

                default:
                    break;
            }
        }

    }
}
